class LRUCache {

    LinkedList<Integer> cache;
    Map<Integer, Integer> tracker;
    int capacity;

    public LRUCache(int capacity) {
        cache = new LinkedList<Integer>();
        tracker = new LinkedHashMap<Integer, Integer>();
        this.capacity = capacity;
    }

    public int get(int key) {

        if( tracker.containsKey(key) ) {
            int indexIntoList = tracker.get(key);
            //System.out.println(key + " " + indexIntoList);
            adjust(key);
            System.out.println("Cache : " + cache);
            int val = cache.remove(indexIntoList);
            put(key, val);
            return val;
        }
        return -1;

    }

    public void put(int key, int value) {
		if(tracker.containsKey(key)) {
			get(key);
		}

        if(cache.size() == capacity) {
            adjust( -1 );
            cache.removeFirst();
        }
        cache.addLast(value);
        tracker.put(key, cache.size() - 1);
        System.out.println(cache);
        //System.out.println(tracker);
    }

    private void adjust( int key ) {
        int indexIntoList = 0;
        if( key != -1) {
            indexIntoList = tracker.get( key );
            tracker.remove( key );
        }

        int specialKey = -1;
        for(int currKey : tracker.keySet()) {
            int currIndex = tracker.get(currKey);
            if(key == -1 && currIndex == 0) {
                specialKey = currKey;
            } else if( currIndex > indexIntoList) {
                currIndex--;
                tracker.put(currKey, currIndex);
            }
        }
        if(specialKey > 0) {
            tracker.remove(specialKey);
        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
