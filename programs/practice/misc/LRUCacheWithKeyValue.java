import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

class LRUCacheWithKeyValue {
    LinkedList<Integer> cache;
    Map<Integer, Integer> tracker;
    int capacity;

    public LRUCacheWithKeyValue(int capacity) {
        //Actual cache storing values
        cache = new LinkedList<Integer>();
        //Mapping between key - value index in cache
        tracker = new LinkedHashMap<Integer, Integer>();
        //Cache size
        this.capacity = capacity;
    }

    public int get(int key) {
        int val = -1;
        if( tracker.containsKey(key) ) {
            int indexIntoList = tracker.get(key);
            System.out.println("Get");
            System.out.println("Cache : " + cache);
            System.out.println("Tracker" + tracker);
            val = cache.get(indexIntoList);
            put(key, val);
        }
        return val;
    }

    public void put(int key, int value) {
        if(tracker.containsKey(key)) {
            int index = tracker.get(key);
            adjust(index);
        } else if(cache.size() == capacity) {
            adjust(0);
        }
        cache.remove(index);
        cache.addLast(value);
        tracker.put(key, cache.size() - 1);
        System.out.println("Put");
        System.out.println("Cache : " + cache);
        System.out.println("Tracker" + tracker);
    }

    private void adjust( int index ) {
        int keyToRemove = -1;
        for(int currKey : tracker.keySet()) {
            int currIndex = tracker.get(currKey);
            if( currIndex > index) {
                tracker.put(currKey, currIndex - 1);
            } else if(currIndex == index) {
                keyToRemove = currKey;
            }
        }

        if(keyToRemove != -1 )
            tracker.remove(keyToRemove);
    }

	public static void main ( String[] args ) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Cache Capacity : ");
		int c = sc.nextInt();
		LRUCacheWithKeyValue lru = new LRUCacheWithKeyValue( c );

		while( true ) {
			System.out.println("Please enter Cache element : ");
			int i = sc.nextInt();
			System.out.println( lru.get( i ) );
			System.out.println( lru );
		}
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

