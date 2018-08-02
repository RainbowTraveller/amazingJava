import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;


public class LRUCache {
	private LinkedList<Integer> cache;
	private Map<Integer, Integer> tracker;
	private int capacity;


	public LRUCache( int capacity ) {
		//List containing actual data
		this.cache		= new LinkedList<Integer>();
		//Map stored the index where the node for key is located
		this.tracker	= new HashMap<Integer, Integer>();
		this.capacity	= capacity;
	}

	public String get( int key ) {
		String msg = null;
		if( tracker.containsKey( key ) ) {
			int indexIntoList = tracker.get( key );
			//First adjust the indexes of the subsequent elements in the cache
			adjustIndex( indexIntoList );
			//Remove the element to be added at the end
			cache.remove( indexIntoList );
			msg = "Cache Hit";
		} else {
			msg = "Cache Miss";
		}
		set( key );
		return msg;
	}

	public void set( int key ) {
		if( cache.size() == this.capacity ) {
			adjustIndex( 0 );
			int leastRecentlyUsed = cache.removeFirst();
			System.out.println( "Capacity reached...removing first element :  " + leastRecentlyUsed);
			tracker.remove( leastRecentlyUsed );
		}
		cache.addLast( key );
		tracker.put( key, cache.size() - 1);
		System.out.println( "Size : " + cache.size() );
		System.out.println( "Trcker :  " + tracker);
	}

	/* Decrement the index of all subsequent elements from the cache that is tracked
	 */
	private void adjustIndex( int indexIntoList ) {
		for( int i = indexIntoList + 1; i < cache.size(); ++i ) {
			int element  = cache.get( i );
			int index = tracker.get( element );
			index--;
			tracker.put( element, index );
		}
	}
	public String toString() {
		return "Cache Contents : " + cache;
	}

	public static void main ( String[] args ) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Cache Capacity : ");
		int c = sc.nextInt();
		LRUCache lru = new LRUCache( c );

		while( true ) {
			System.out.println("Please enter Cache element : ");
			int i = sc.nextInt();
			System.out.println( lru.get( i ) );
			System.out.println( lru );
		}
	}
}

