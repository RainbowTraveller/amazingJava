Question: Provide an implementation of the following class:

We need to implement constructor and get method ?

Rank is some weight

how do I get key if I want to ?

K1(T1)
evicting the T with lowest rank among the ones that it has available

search is based on key : Key to DS mapping.
While returning We need to consider rank, so we need ordering based on rank

public class RetainBestCache<K, T extends Rankable> {
     SortedSet<Long, List<DataSource<K,T>> cache;
     int capacity = 0;
    /* Constructor with a data source (assumed to be slow) and a cache size */
    public RetainBestCache(DataSource<K,T> ds, int entriesToRetain) {
        cache = new TreeSet<Long, List<DataSource<K,T>>();
        capacity = entriesToRetain;
        // Add object here
    }

    /* Gets some data. If possible, retrieves it from cache to be fast. If the data is not cached,
     * retrieves it from the data source. If the cache is full, attempt to cache the returned data,
     * evicting the T with lowest rank among the ones that it has available
     * If there is a tie, the cache may choose any T with lowest rank to evict.
     */
    public T get(K key) {
        // Implementation here
        if(capacity > 0) {
            Iterator itr = cache.iterator();
            while(itr.hasNext()) {

            }

        } else {
        }
    }
}


/*
 * For reference, here are the Rankable and DataSource interfaces.
 * You do not need to implement them, and should not make assumptions
 * about their implementations.
 */

public interface Rankable {
    /**
     * Returns the Rank of this object, using some algorithm and potentially
     * the internal state of the Rankable.
     */
    long getRank();
}

public interface DataSource<K, T extends Rankable> {
    T get (K key);
}





It is the end of the school year, and the graduating class of Moore Science High (n students in all) has decided to end the year by making quite a lot of noise. They line up in the locker room, where all n lockers are closed. The first student runs down the row of lockers, opening each one. Then, the second runs down, slamming every other locker (this is where the noise comes in). The third student then runs down, opening (or slamming) every third locker, and so on until all n students have gone through, each opening (or closing) each locker evenly divisible by i, where i is the ith student. Write a function that prints each locker that is open after the students are done.
