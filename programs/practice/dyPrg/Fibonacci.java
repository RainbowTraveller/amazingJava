public class Fibonacci {
	public static void main( String [] args ) {
		int num = Integer.parseInt( args[0] );
		long [] mem = new long[ num + 2 ];
		System.out.println( fibboDynamic(num, mem) );
		System.out.println( fibboDynamicLinear(num, mem) );
		System.out.println( fibboDynamicSpaceOptimized(num) );
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

	public static long fibboDynamicLinear( int n, long [] fVals) {
		fVals[ 0 ] = 0;
		fVals[ 1 ] = 1;
		for(int i = 2; i <= n; ++i ){
			fVals[ i ] = fVals[ i - 1 ] + fVals[ i - 2 ];
		}
		return fVals[ n ];
	}

	public static long fibboDynamicSpaceOptimized( int n ) {
		long sum = 0, a = 0, b = 1;
		for(int i = 2; i <= n; ++i ){
			sum = a + b;
			a = b;
			b = sum;
		}
		return sum;
	}
}
