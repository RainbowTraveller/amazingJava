import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

public class MemoryDB {
    /*   **********************************
     *   *Class implementing the memory DB*
     *   **********************************
     */
    private Map<String, Integer> datamap;//Data storage
    private ValueCount frequency;//Value frequency storage
    private Transaction transaction;//Transaction management entity

    /*
     * Constructor
     */
    public MemoryDB() {
        //Instantiates the data and frequency storage maps
        datamap = new HashMap<String, Integer>();
        frequency = new ValueCount();
    }

    /*
     * Sets the value for the key into the data map and also updates the value
     * count in the frequency map
     */
    public void set(String key, int value) {
        if(transaction != null) {//Check if the operation is part of transaction
            transaction.set(key, value);
        } else { // else deal with the main storage
            Integer oldValue  = datamap.get(key);
            //manage the count of the old value
            if(oldValue != null) {
                frequency.decrement(oldValue);
            }
            datamap.put(key, value);//Add key-value pair
            frequency.increment(value);//Account for value count
        }
    }

    /*
     * Return the value for given key if present else null
     *
     */
    public Integer get(String key) {
        if(transaction != null) {//Check if the operation is part of transaction
            return transaction.get(key);
       } else {
            return datamap.get(key);//else fetch from the main storage
        }
    }
    /*
     * Remove key value association from the data store
     */
    public void unset(String key) {
        if(transaction != null) {//Check if the operation is part of transaction
            transaction.set(key, null);
        } else {
            Integer value  = datamap.get(key);
            if(value != null) {// Account for data and count
                frequency.decrement(value);
                datamap.remove(key);
            }
        }
    }
    /*
     * Begin transaction
     */
    public void begin() {
        if (transaction == null) {
            transaction = new Transaction();
        }
        transaction.begin();
    }

    /*
     * Commit all transactions
     */
    public void commit() {
        if(transaction != null) {
            datamap.putAll(transaction.getUpdatedValues());
            updateFrequencies();
        }
        transaction = null;//No active transactions
    }
    /*
     * Rollback the current transaction
     */
    public boolean rollback() {
        if(transaction != null && !transaction.isEmpty()) {
            transaction.rollback();
            return true;
        }
        return false;
    }
    /*
     * Get the count for input value present in the DB
     */
    public int numEqualTo(int value) {
        return frequency.get(value);
    }

    /*
     * Update the count for all the values bases on data store
     *
     */
    private void updateFrequencies() {
        frequency.reset();
        for(Entry<String, Integer> entry : datamap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value == null) {
                datamap.remove(key);
            } else {
                frequency.increment(value);
            }
        }
    }

    /*
     * Overriding to facilitate printing of data store at any point
     *
     */
    public String toString() {
        String printStr = "";
        for(Entry<String, Integer> entry : datamap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            printStr = printStr + key + " : " +  value + "\t";
        }
        return printStr;
    }

    /*   ************************************************
     *   *Class implementing map for value count sotrage*
     *   ************************************************
     */
    class ValueCount {

        private Map<Integer, Integer> countmap;//value-count pair map

        /*
         * Constructor
         */
        public ValueCount() {
            //Instantiates empty map
            countmap = new HashMap<Integer, Integer>();
        }

        /*
         * Increments count for particular value
         */
        public void increment(int key) {
            Integer count = countmap.get(key);
            if(count == null) {
                countmap.put(key, 1);
            } else {
                countmap.put(key, ++count);
            }
        }
        /*
         * Decrements the count for given value
         */
        public void decrement(int key) {
            Integer count = countmap.get(key);
            if(count == null) {
                return;
            } else {
                if (count == 1) {
                    //count will be 0 after decrement hence remove
                    remove(key);
                } else {
                    countmap.put(key, --count);
                }
            }
        }
        /*
         * Get count for given key
         */
        public int get(int key) {
            Integer count = countmap.get(key);
            if(count == null) {
                return 0;
            } else {
                return count;
            }
        }

        /*
         * Remove key-value pair from map
         */
        private void remove(int key) {
            countmap.remove(key);
        }

        /*
         * Remove all values from the map and clear the map
         */
        public void reset() {
            countmap.clear();
        }
    }


    /*   ************************************************
     *   *Class implementing transaction handling       *
     *   ************************************************
     */
    class Transaction {
        //Key : Values for the key from all active transactions
        private Map<String, LinkedList<Integer>> data;
        //Tracks nested transactions and keys for values changed
        private LinkedList<String> tracking;

        /*
         * Constructor
         */
        public Transaction() {
            data = null;
            tracking = null;
        }

        /*
         * Set the value for a key within a transaction
         */
        public void set(String key, Integer value) {
            //We need a collection to store multiple values for a given key
            //within nested transactions
            LinkedList<Integer> vallist = data.get(key);
            // add one if new key
            if(vallist == null) {
                vallist = new LinkedList<Integer>();
            }
            // put latest value at the top
            vallist.addFirst(value);
            data.put(key, vallist);
            // Add key to the tracking list
            tracking.addFirst(key);
        }

        /*
         * Get the value for the key set by innermost valid transaction
         */
        public Integer get(String key) {
            LinkedList<Integer> vallist = data.get(key);
            Integer value = null;
            if(vallist != null) {
                value =  vallist.getFirst();
            }
            return value;
        }
        /*
         * Rollback the values for keys in innermost transaction
         */
        public void rollback() {
            ListIterator<String> trackIter = tracking.listIterator();
            //Fetch the list of values modified in 'this' transaction
            while(trackIter.hasNext()) {
               String key = trackIter.next();
               trackIter.remove();
               if(!key.equals("{")) {
                   //remove the related value as well
                   removeLatest(key);
               } else {
                   break;
               }
            }
            //Was it the last transaction ?
            if(tracking.size() == 0) {
                tracking = null;
            }
        }

        /*
         * Collect the values for all the keys set by transactions, to be used
         * by commit operation
         */
        public Map<String, Integer> getUpdatedValues() {
           Map<String, Integer> latestDataMap = new HashMap<String, Integer>();
           if(!isEmpty()) {
               ListIterator<String> trackIter = tracking.listIterator();
               //Get keys from tracking collection
               while(trackIter.hasNext()) {
                   String key = trackIter.next();
                   trackIter.remove();
                   if(!key.equals("{")) {//ignore transaction delimiter
                       if(!latestDataMap.containsKey(key)) {
                           //Only update the map if value is not already present.
                           //if present, that is value set by innermost transaction
                           LinkedList<Integer> vallist = data.get(key);
                           int value = vallist.removeFirst();
                           latestDataMap.put(key, value);
                       }
                   }
               }
               tracking = null;
           }
           return latestDataMap;
        }

        /*
         * Begin transaction
         */
        public void begin() {
            if(isEmpty()) {
                //if first transaction create tracking
                tracking = new LinkedList<String>();
            }
            tracking.addFirst("{");//add delimiter
            if(data == null) {
                data = new HashMap<String, LinkedList<Integer>>();
            }
        }

        /*
         * Remove the value for a key set by latest transaction
         */
        private void removeLatest(String key) {
            LinkedList<Integer> vallist = data.get(key);
            vallist.removeFirst();
            if(vallist.size() == 0) {
                data.remove(key);
            } else {
                data.put(key, vallist);
            }
        }

        /*
         * Check if there is any transaction active
         */
        public boolean isEmpty() {
            return tracking == null;
        }
    }
}
