class LRUCacheWithKeyValue {
    LinkedList<Integer> cache;
    Map<Integer, Integer> tracker;
    int capacity;

    public LRUCache(int capacity) {
        cache = new LinkedList<Integer>();
        tracker = new LinkedHashMap<Integer, Integer>();
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
        }
        put(key, val);
        return val;
    }

    public void put(int key, int value) {
        if(tracker.containsKey(key)) {
            int index = tracker.get(key);
            adjust(index);
            cache.remove(index);
            for(int currKey : tracker.keySet()) {
                if(tracker.get(currKey) == index) {
                    tracker.remove(currKey);
                    break;
                }
            }
        } else if(cache.size() == capacity) {
            for(int currKey : tracker.keySet()) {
                if(tracker.get(currKey) == 0) {
                    tracker.remove(currKey);
                    break;
                }
            }
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
        for(int currKey : tracker.keySet()) {
            int currIndex = tracker.get(currKey);
            if( currIndex > index) {
                currIndex--;
                tracker.put(currKey, currIndex);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

