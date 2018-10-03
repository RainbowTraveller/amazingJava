/**
 Find the length of longest common  subsequence


*/
public class LongestCommonSubsequence {

	public static void main( String[] args ) {
		System.out.println( LCS(  "abcdaf", "acbcf" ));
	}
	public static int LCS( String a, String b) {
		if( a != null && b != null) {
			int max = 0;
			int[][] tracker = new int[ a.length() + 1][b.length() + 1];
			for( int i = 1; i <= a.length(); ++i) {
			  for( int j = 1; j <= b.length(); ++j) {
				if( a.charAt( i - 1 ) == b.charAt( j - 1 )) {
				  tracker[ i ][ j ] = tracker[ i - 1][ j - 1] + 1;
				} else {
				  tracker[ i ][ j ] = Math.max( tracker[ i ][ j - 1], tracker[ i - 1][ j ]);
				}
				if( tracker[ i ][ j ] > max ) {
				  max  = tracker[ i ][ j ];
				}
			  }
			}
		  return max;
		}
		return -1;
	  }
}
