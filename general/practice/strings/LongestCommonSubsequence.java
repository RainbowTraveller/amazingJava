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

	private static int LCSRecursiveMemHelper( int i, int j, int len1, int len2, String s1, String s2) {
		if( i >= len1 || j >= len2 ) {
			return 0;
		}

		if( mem[i][j] < 0 ) {
			if( s1.charAt( i  ) == s2.charAt( j  ) ) {
				mem[i][j] = 1 + LCSRecursiveMemHelper( i + 1, j + 1, len1, len2, s1, s2);
			} else {
				mem[i][j] =  Math.max( LCSRecursiveMemHelper( i, j + 1, len1, len2, s1, s2),
					LCSRecursiveMemHelper( i + 1, j, len1, len2, s1, s2));
			}
		}
		return mem[i][j];
	}

	public static int LCSRecursive( String s1, String s2 ) {
		if( s1 != null && s2 != null ) {
			int len1 = s1.length();
			int len2 = s2.length();
			return LCSRecursiveHelper(0, 0, len1, len2, s1, s2);
		}
		return -1;
	}

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
