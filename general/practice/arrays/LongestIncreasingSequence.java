import java.lang.Math;
/*
 * Longest increasing subsequnmce ( this is not continuous )
 *
 *
 *
 */
public class LongestIncreasingSequence {

    public static void longestIncreasingSequnceNoDyn(int [] arr) {
		int max = 1;
		if( arr != null ) {
			for( int i = 0; i < arr.length; ++i) {
				int curr = 1;
				int currMax = arr[i];
				for(int j = i + 1; j < arr.length; ++j ) {
					if( arr[j] > currMax ) {
						curr++;
						currMax = arr[j];
					}
				}
				max = Math.max( max, curr );
			}
		}
			System.out.println( "Longest increasing subsequence length : " + max );
	}
    public static void longestIncreasingSequnce(int [] arr) {
		if( arr != null ) {
			int [] tracker = new int[ arr.length ];
			for( int i = 0; i < arr.length; ++i ) {
				tracker[i] = 1;
			}

			/*
			 * starting with length 2, Calculate the length of the increasing
			 * sequence till index i and store at T[i]
			 */
			for( int i = 1; i < arr.length; ++i) {
				for(int j = 0; j < i; ++j ) {
					if( arr[ j ] < arr[ i ] && tracker[ i ] < tracker[ j ] + 1 ) {
						tracker[ i ] = tracker[ j ] + 1;
					}
				}
			}

			int max = 0;
			for( int i = 0; i < arr.length; ++i ) {
				//System.out.print(" " + tracker[ i ]);
				if( max <  tracker[ i ] ) {
					max = tracker[ i ];
				}
			}

			System.out.println( "Longest increasing subsequence length : " + max );
		}
    }

	/*
	 * This is continuous sequence length finder
	 */
    public static void longestContinuousIncreasingSequnce( int [] arr ) {
		if( arr != null ) {
			int curr = 1;
			int max = 1;
			for(int i = 1; i < arr.length; ++i) {
				if( arr[ i - 1 ] < arr[ i ] ) {
					curr++;
					max = Math.max( curr, max );
				} else {
					curr = 1;
				}
			}
			System.out.println( "Longest increasing subsequence length : " + max );
		}
    }

    public static void main(String [] args) {
        //int [] input = {10, 11, 0 , 0, 1 ,2, 3, -1, 2 ,3, 14, 5, 6, 7};
        int [] input = { 10, 22, 9, 33, 21, 50, 41, 60 };

        longestIncreasingSequnce(input);
        longestIncreasingSequnceNoDyn(input);
        longestContinuousIncreasingSequnce(input);
    }
}
