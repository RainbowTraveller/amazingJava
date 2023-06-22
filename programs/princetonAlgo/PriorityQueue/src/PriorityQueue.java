public interface PriorityQueue<Key extends Comparable<Key>> {

    /**
     * Insert the given key to the priority queue
     *
     * @param key new key to be inserted to the priority queue
     */
    void insert(Key key);

    /**
     * Deletes the key with the maximum priority from the underlying data structure in this priority queue
     * and returns that key element
     *
     * @return the key with maximum priority value
     */
    Key delete();

    /**
     * Checking if the queue is empty or not
     *
     * @return true if the queue is empty false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the key from the priority queue with maximum priority
     *
     * @return the key with maximum priority
     */
    Key max();

    /**
     * No. of elements maintained by the queue at a given instance
     *
     * @return no. of elements in the queue
     */
    int size();
}
