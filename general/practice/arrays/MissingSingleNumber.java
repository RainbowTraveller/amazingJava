/*
 * from a set of n numbers but only n - 1 numbers are given.
 * The n numbers are positive numbers and rant from 1 to n with no duplicates
 */
public class MissingSingleNumber {
	public static void main( String[] args ) {
		int [] arr = { 1, 2, 4, 5, 3, 7};
		MissingSingleNumber msn = new MissingSingleNumber();
		msn.getMissingNumberBySum( arr );
		msn.getMissingNumberXOR( arr );
	}

	public void getMissingNumberBySum( int [] arr ) {
		if( arr != null ) {
			int range = arr.length + 1;

			int sum = range * ( range + 1 ) / 2;


			for(int i = 0;  i < arr.length; ++i) {
				sum -= arr[ i ];
			}

			System.out.println(" Missing no. by Sum : " + sum );
		}
	}

	public void getMissingNumberXOR( int [] arr ) {
		if( arr != null ) {
			int x1 = arr[ 0 ];
			int x2 = 1;

			for (int i = 1; i < arr.length; ++i ) {
				x1 = x1 ^ arr[ i ];
			}

			for(int i = 2;  i <= arr.length + 1; ++i) {
				x2 = x2 ^ i;
			}

			System.out.println(" Missing no. by XOR : " + (x1 ^ x2));
		}
	}
}
