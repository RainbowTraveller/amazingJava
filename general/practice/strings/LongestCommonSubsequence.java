/**
 Find the length of longest common  subsequence


*/
public class LongestCommonSubsequence {

	public static int mem[][];
	public static void main( String[] args ) {
		System.out.println( LCS(  "abcdaf", "acbcf" ));
		System.out.println( LCSRecursive(  "abcdaf", "acbcf" ));
		System.out.println( LCSRecursiveMem(  "abcdaf", "acbcf" ));
	}

	/**
	 * Recursive and expensive
	 * At each level 2 decisions are made so 2 power n running time
	 * Also involves repeated calculations of values
	 */
	public static int LCSRecursive( String s1, String s2 ) {
		if( s1 != null && s2 != null ) {
			int len1 = s1.length();
			int len2 = s2.length();
			return LCSRecursiveHelper(0, 0, len1, len2, s1, s2);
		}
		return -1;
	}

	/**
	 * Actual recursive function, pretty straight forward
	 */
	private static int LCSRecursiveHelper( int i, int j, int len1, int len2, String s1, String s2 ) {
		if( i >= len1 || j >= len2 ) {
			return 0;
		}

		if( s1.charAt( i  ) == s2.charAt( j  ) ) {
			return 1 + LCSRecursiveHelper( i + 1, j + 1, len1, len2, s1, s2 );
		}

		return Math.max( LCSRecursiveHelper( i, j + 1, len1, len2, s1, s2 ),
			LCSRecursiveHelper( i + 1, j, len1, len2, s1, s2 )
		);
	}

	/**
	 * uses Memoization to store intermediate results
	 * this avoid recalculation of already calculated ones
	 * Running time : m * n
	 * where m and n are lengths of the input strings. We need to
	 * populate only m * n values
	 */
	public static int LCSRecursiveMem( String s1, String s2 ) {
		if( s1 != null && s2 != null ) {
			int len1 = s1.length();
			int len2 = s2.length();
			mem = new int[len1][ len2];
			for(int i = 0; i < len1; ++i) {
				for(int j = 0; j < len2; ++j) {
					mem[i][j] = -1;
				}
			}
			return LCSRecursiveMemHelper(0, 0, len1, len2, s1, s2);
		}
		return -1;
	}
	/**
	 * helper function which is actually called recursively
	 */
	private static int LCSRecursiveMemHelper( int i, int j, int len1, int len2, String s1, String s2) {
		//either of the string is exhausted so return
		if( i >= len1 || j >= len2 ) {
			return 0;
		}

		//initialised to -1 indicating not yet calculated
		if( mem[i][j] < 0 ) {
			if( s1.charAt( i  ) == s2.charAt( j  ) ) {// matched increment both
				mem[i][j] = 1 + LCSRecursiveMemHelper( i + 1, j + 1, len1, len2, s1, s2);
			} else {
				//Not matched so recursive call with either of the index incremented
				mem[i][j] =  Math.max( LCSRecursiveMemHelper( i, j + 1, len1, len2, s1, s2),
					LCSRecursiveMemHelper( i + 1, j, len1, len2, s1, s2));
			}
		}
		return mem[i][j];
	}

	/**
	 * Dynamic programming :
	 * Maintain m + 1 * n + 1 array of integers
	 * 0th row and 0th column are filled with zero
	 * Start comparing row and column contents which are essentially characters of the strings
	 *
	 * if i, j match ---> mem[i][j] = mem[i - 1][ j - 1 ] + 1
	 * if not matched ----> mem[i][j] = Max(  mem[i - 1][ j ], mem[ i ][ j - 1 ] )
	 *
	 */
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
