import java.util.Scanner;

public class SubStringIndex {

	public static void main( String args[] ) {
		Scanner scan = new Scanner( System.in );
		String destination = scan.nextLine();
		String candidate = scan.nextLine();
		SubStringIndex ssi = new SubStringIndex();
		System.out.println( ssi.getSubStringIndex( destination, candidate ) );
		System.out.println( ssi.findPattern( destination, candidate ) );
		System.out.println( ssi.kmp( destination, candidate ) );
	}

	/*
	 * Naive approach
	 * Start comparing from start of the 2 lists, if mismatch occurs then candidate
	 * is scanned from beginning and destination index is reset to one more than the previous
	 * iteration
	 *
	 */
	public int getSubStringIndex( String destination, String candidate ) {
		int lendestination = destination.length();
		int lenCandidate = candidate.length();
		int tracker = 0;
		while( lendestination - tracker >= lenCandidate ){
			int current = tracker;
			int ctracker = 0;
			while(current < lendestination  && ctracker < lenCandidate ) {
				if(destination.charAt( current ) == candidate.charAt( ctracker ) ) {
					current++;
					ctracker++;
					if( ctracker == lenCandidate ) {
						return tracker;
					}
				} else {
					break;
				}
			}
			tracker++;
		}
		return -1;
	}

	/**
	*	Simpler version avoinding ugly break
	*/
	public static int findPattern( String source, String pattern ) {
		if( source != null && pattern != null ) {
			for( int i = 0; i < source.length(); i++) {
				if( source.charAt( i ) == pattern.charAt( 0 )) {
					int ptrack = 1;
					int strack = i + 1;
					while( ptrack < pattern.length() && strack < source.length() && source.charAt( strack ) == pattern.charAt( ptrack ) ) {
						ptrack++;
						strack++;
					}
					if( ptrack == pattern.length()) {
						return i;
					}
				}
			}
		}
		return -1;
	}
	/**
	 * Compare strings character by character starting at 0 index
	 * at the mismatch following conditions need to be checked
	 *			if j > 0 : meaning we have found some part of the string so far so look for similar occurrence
	 *
	 *			otherwise no match found so far keep on looking with next character in the input pattern string
	 *
	 */
	public int kmp( String destination, String candidate ) {
		int lendestination = destination.length();
		int lenCandidate = candidate.length();

		int [] prefix = prefixTable( candidate );
		int i = 0, j = 0;
		while( i < lendestination) {
			if( destination.charAt(i) == candidate.charAt( j )) {
				if( j == lenCandidate - 1 ) {
					return i - lenCandidate + 1;
				} else {
					i++;
					j++;
				}
			} else if( j > 0 ) {
				j = prefix[ j - 1 ];
			} else {
				i++;
			}
		}
		return -1;
	}

	/**
	 * Creating prefix table
	 *
	 * a a a a b a a c d
	 * 0 1 2 3 0 1 2 0 0
	 *
	 * a b c d a b e a b f
	 * 0 0 0 0 1 2 0 1 2 0
	 *
	 * Track first repeated occurance of the first character
	 * continue increasing the prefix values if the subsequent characters match
	 * at the mismatch following conditions need to be checked
	 *			if j > 0 : meaning we have found at least one repeated character then start from its previous occurance
	 *
	 *			otherwise no match found so far keep on looking with next character in the input pattern string
	 */
	private int [] prefixTable( String candidate ) {
		 int[] prefix = new int[candidate.length()];
		 int i = 1, j = 0;
		 prefix[0] = 0;
		 while( i < candidate.length() ) {
			 if( candidate.charAt( i ) == candidate.charAt( j ) ) {
				prefix[ i ] = j + 1;
				i++;
				j++;
			 } else if ( j > 0 ) {
				 j = prefix[ j - 1 ];
			 } else {
				  prefix[ i ] = 0;
				  i++;
			 }
		 }
		 for( int c : prefix ) {
			  System.out.print( c  + " ");
		 }
		 System.out.println();
		 return prefix;
	}
}
