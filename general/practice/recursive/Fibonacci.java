public class Fibonacci {
	public static void main( String [] args ) {
		int num = Integer.parseInt( args[0] );
		System.out.println( fibbo(num) );
	}

	public static int fibbo(int n) {
		if( n > 1 ) {
			n = fibbo( n - 1 ) + fibbo( n - 2 );
		}
		return n;
	}
}
