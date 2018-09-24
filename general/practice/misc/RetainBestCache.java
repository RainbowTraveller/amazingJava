import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.HashMap;

public class RetainBestCache<K, T extends Rankable> {
	//Cache: Stores recently used items
     Map<K, T> cache;
	 //Actual Data store : this will be accessed if cache miss occurs
	 DataSource<K, T> dataSource;
	 //Maps rank to set of corr keys, if this is the lowest rank
	 //then one of the keys is removed from this set and also
	 //from the cache to make space for new entry
	 SortedMap<Long, Set<K>> ranking;//LinkedHashSet maintains the insertion order so removing first element is slightly better
     int capacity = 0;
    /* Constructor with a data source (assumed to be slow) and a cache size */
    public RetainBestCache(DataSource<K,T> ds, int entriesToRetain) {
		dataSource = ds;
        cache = new HashMap<K, T>();
		ranking = new TreeMap<>();
        capacity = entriesToRetain;
        // Add object here
    }

    /* Gets some data. If possible, retrieves it from cache to be fast. If the data is not cached,
     * retrieves it from the data source. If the cache is full, attempt to cache the returned data,
     * evicting the T with lowest rank among the ones that it has available
     * If there is a tie, the cache may choose any T with lowest rank to evict.
     */
    public T get(K key) {
		if( cache.containsKey( key ) ) {
			return cache.get( key );
		} else {
			T desired = dataSource.get( key );
			if( cache.size() == capacity ) {
				evictElement( desired );
			}
			cache.put(key, desired);
			long currentRank = desired.getRank();
			Set<K> keySet = ranking.get( currentRank );
			if( keySet == null ) {
				keySet = new LinkedHashSet<K>();
			} else {
				keySet.add( key );
			}
			ranking.put( currentRank, keySet );
			return desired;
		}
    }

	private void evictElement( T desired ) {
		long currentRank = desired.getRank();
		long lowestRank = ranking.firstKey();//Tree Set So first will be having lowest rank
		Set<K> lowestRankedKeys = ranking.get( lowestRank );// get corr Set of Keys
		Iterator<K> itr = lowestRankedKeys.iterator();
		K keyToRemove = itr.next();// Delete oldest among them as maintained by LinkedHashMap insertion order
		lowestRankedKeys.remove( keyToRemove );
		cache.remove(keyToRemove);
		if(lowestRankedKeys.size() == 0) {
			ranking.remove( lowestRank );
		}
	}
}

/*
 * For reference, here are the Rankable and DataSource interfaces.
 * You do not need to implement them, and should not make assumptions
 * about their implementations.
 */

interface Rankable {
    /**
     * Returns the Rank of this object, using some algorithm and potentially
     * the internal state of the Rankable.
     */
    long getRank();
}

interface DataSource<K, T extends Rankable> {
    T get (K key);
}





/*It is the end of the school year, and the graduating class of Moore Science High (n students in all) has decided to end the year by making quite a lot of noise. They line up in the locker room, where all n lockers are closed. The first student runs down the row of lockers, opening each one. Then, the second runs down, slamming every other locker (this is where the noise comes in). The third student then runs down, opening (or slamming) every third locker, and so on until all n students have gone through, each opening (or closing) each locker evenly divisible by i, where i is the ith student. Write a function that prints each locker that is open after the students are done.*/
