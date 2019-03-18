class LRUCacheWithKeyValue {
    LinkedList<Integer> cache;
    Map<Integer, Integer> tracker;
    int capacity;

    public LRUCache(int capacity) {
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
            cache.remove(index);
        } else if(cache.size() == capacity) {
            adjust(0);
            cache.removeFirst();
        }
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
                currIndex--;
                tracker.put(currKey, currIndex);
            } else if(currIndex == index) {
                keyToRemove = currKey;
            }
        }

        if(keyToRemove != -1 )
            tracker.remove(keyToRemove);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

