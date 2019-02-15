/*It is the end of the school year, and the graduating class of Moore Science High (n students in all) has decided to end the year by making quite a lot of noise. They line up in the locker room, where all n lockers are closed. The first student runs down the row of lockers, opening each one. Then, the second runs down, slamming every other locker (this is where the noise comes in). The third student then runs down, opening (or slamming) every third locker, and so on until all n students have gone through, each opening (or closing) each locker evenly divisible by i, where i is the ith student. Write a function that prints each locker that is open after the students are done.*/

public class Locker {
	public static void main( String[] args ) {
		int noOfStudents = Integer.valueOf( args[0] );
		lockerStatus( noOfStudents );
	}

	public static void lockerStatus( int nos ) {
		boolean [] lockers = new boolean[ nos ];

		for(int i = 0; i < nos; ++i) {
			lockers[ i ] = true;
		}

		for(int i = 0; i < nos; ++i) {
			for( int j = 0; j < nos; ++j ) {
				if( ( j + 1 ) % ( i + 1 ) == 0 ) {
					lockers[ j ] = !lockers[ j ];
				}
			}
			printLocker( lockers );
		}
	}

	public static void printLocker( boolean[] lockers ) {
		for(int i = 0; i < lockers.length; ++i) {
			System.out.print( lockers[i] + " ");
		}
		System.out.println();
	}
}
