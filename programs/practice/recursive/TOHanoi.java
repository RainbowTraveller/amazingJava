/**
 * Implementation of Tower of Hanoi
 *
 * funct( disks to move, source, helper, destination )
 * Example with 3
 *
 *      s       h       d
 *    1,2,3
 *
 *    2,3				1         //Step 1 : getSteps ( capacity - 1, source, destination, helper ) : moving n - 1 disks to helper with destination as temp helper
 *    3			2
 *    3		   1,2
 *
 *    x        1,2		3		 //Step 2 : getSteps ( 1, source, helper, destination ) : moving 1 disks from source to destination
 *
 *    1			2		3		//Step 3 : getSteps ( 1, helper, source, destination ) : now moving capacity - 1 disks moved in step 1 to helper to destination
 *    1					2,3
 *						1,2,3
 *
 **/

import java.util.Deque;
import java.util.ArrayDeque;
public class TOHanoi {

	private Deque<Integer> source;
	private Deque<Integer> helper;
	private Deque<Integer> destination;
	int capacity = 0;


	public TOHanoi( int capacity ) {
		source = new ArrayDeque<Integer>();
		helper = new ArrayDeque<Integer>();
		destination = new ArrayDeque<Integer>();
		this.capacity = capacity;
		for(int i = capacity; i > 0; i--) {
			source.addFirst( i );
		}
		System.out.println( this );
	}

	public static void main(String [] args) {
		TOHanoi toh = new TOHanoi( 5 );
		toh.getSteps();

	}

	public void getSteps() {
		this.getSteps( this.capacity, source, helper, destination );
	}
	private void getSteps( int capacity,  Deque<Integer> source, Deque<Integer> helper, Deque<Integer> destination ) {

		if( capacity == 1 ) {
			if( source.peekFirst() != null ) {
				destination.addFirst( source.removeFirst() );
				System.out.println( this );
			}
		} else {
			getSteps(capacity - 1, source, destination, helper);
			getSteps(1, source, helper, destination);
			getSteps(capacity - 1, helper, source, destination);
		}
	}

	public String toString() {
		return new String( "Current State : source : " + source + " \nHelper : " + helper + " \nDestination : " + destination + "\n\n");
	}


}
