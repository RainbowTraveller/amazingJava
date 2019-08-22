import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class LRUCache {

    class DoubleLLNode {
        int key;
        int val;
        DoubleLLNode prev;
        DoubleLLNode next;

        public DoubleLLNode() {
            key = 0;
            val = 0;
            prev = null;
            next = null;
        }

        public DoubleLLNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }

        public String toString() {
            return new String("Key : " + key + " Value : " + val );
        }
    }

    private Map<Integer,DoubleLLNode> cache;
    private int capacity;
    private DoubleLLNode head;
    private DoubleLLNode tail;

    public LRUCache( int capacity ) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        head = new DoubleLLNode();
        tail = new DoubleLLNode();
        head.next = tail;
        tail.prev = head;
    }

    //Adding latest element here
    private void addNodeInFront(DoubleLLNode node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    //When capacity overflows, remove node from end
    private DoubleLLNode  removeNodeFromEnd() {
        DoubleLLNode stale = tail.prev;
        removeNode(stale);
        cache.remove(stale.key);
        return stale;
    }

    //remove specific node
    private void removeNode(DoubleLLNode node) {
        DoubleLLNode currPrev = node.prev;
        DoubleLLNode currNext = node.next;
        currPrev.next = currNext;
        currNext.prev = currPrev;
        node.next = null;
        node.prev = null;
    }

    public void put(int key, int value) {
        //add and if capacity is overflown, remove last node which is stale
        if(cache.containsKey(key)) {
            DoubleLLNode desiredNode = cache.get(key);
            //Set new value
            desiredNode.val = value;
            //Put at the start, indicating recently used node
            removeNode(desiredNode);
            addNodeInFront(desiredNode);
        } else {
            DoubleLLNode notFoudNode = new DoubleLLNode(key, value);
            addNodeInFront(notFoudNode);
            cache.put(key, notFoudNode);
            if(cache.size() > capacity) {
                removeNodeFromEnd();
            }
        }
    }

    public int get(int key, int value) {
        //Check if the key exists or return -1;
        if(cache.containsKey(key)) {
            DoubleLLNode foundNode = cache.get(key);
            int val = foundNode.val;
            //Put at the start, indicating recently used node
            removeNode(foundNode);
            addNodeInFront(foundNode);
            return val;
        }
        return -1;
    }

    public String toString() {
        return cache.toString();
    }

	public static void main ( String[] args ) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Cache Capacity : ");
		int c = sc.nextInt();
		LRUCache lru = new LRUCache( c );

		while( true ) {
			System.out.println("Please enter Cache element : ");
			int key = sc.nextInt();
			int value = sc.nextInt();
			lru.put( key, value );
			System.out.println( lru );
		}
	}
}

