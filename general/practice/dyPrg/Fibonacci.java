public class Fibonacci {
	public static void main( String [] args ) {
		int num = Integer.parseInt( args[0] );
		long [] mem = new long[ num + 1 ];
		System.out.println( fibboDynamic(num, mem) );
	}

	//Calculates Fibbo upto 95 without overflowing
	public static long fibboDynamic( int n, long[] mem) {
		if( n <= 1 ) {
			return n;
		}
		if( mem[n] != 0 ) {
			return mem[ n ];
		}
		mem[n] = fibboDynamic( n - 1, mem) + fibboDynamic( n - 2, mem );
		return mem[ n ];
	}
}
