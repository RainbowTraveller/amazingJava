import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LRUCacheCollection<K, V> extends LinkedHashMap<K, V> {
	  private int cacheSize;

	  public LRUCacheCollection(int cacheSize) {
		      super(cacheSize, 0.75F, true);
			  this.cacheSize = cacheSize;

	  }

	  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		      return size() >= cacheSize;

	  }

	  public static void main( String[] args ) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter Cache Capacity : ");
			int c = sc.nextInt();
			LRUCacheCollection<Integer, Integer> lru = new LRUCacheCollection<Integer, Integer>( c + 1 );

			while( true ) {
				System.out.println("Please enter Cache element : ");
				int i = sc.nextInt();
				lru.put( i, i );
				if( i % 2 == 0) {
					System.out.println( "Getting an object with key : " + ( i / 2 ) );
					System.out.println( lru.get( i / 2 ) );
				}
				System.out.println( lru );
			}
	  }

}
