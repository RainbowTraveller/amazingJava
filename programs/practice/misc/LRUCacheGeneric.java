public class LRUCacheGeneric<K,V> {

    private List<V> cache;
    private Map<K, Integer> tracker;
    int capacity;


    public LRUCacheGeneric<K,V>( int capacity) {

    }
}
