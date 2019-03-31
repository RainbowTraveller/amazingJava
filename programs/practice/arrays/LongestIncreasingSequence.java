import java.lang.Math;
/*
 * Longest increasing subsequence ( this is not continuous, hence not substring)
 *
 *
 *
 */
public class LongestIncreasingSequence {

    public static void longestIncreasingSequnceNoDyn(int [] arr) {
        int max = 1;
        if( arr != null ) {
            for( int i = 0; i < arr.length; ++i) {
                int currMaxLength = 1;
                int currMaxNumer = arr[i];
                for(int j = i + 1; j < arr.length; ++j ) {
                    System.out.println("J : "  + j);
                    if( arr[j] > currMaxNumer) {
                        currMaxLength++;
                        currMaxNumer = arr[j];
                    }
                }
                max = Math.max( max, currMaxLength );
                System.out.println("currMaxNumber : " + currMaxNumer + "currMaxLength : " + currMaxLength + " Max : " + max);
            }
        }
            System.out.println( "Longest increasing subsequence length : " + max );
    }

   /*
    * e.g.  2 1 4 0 9 5 11
    *       1 1 2 1 3 3 4
    *       Final result of the function is 4
    *
    *       2 >> 4 >> 9 >> 11 is longest increasing subsequence
    *
    */
    public static void longestIncreasingSequnce(int [] arr) {
        if( arr != null ) {
            int [] tracker = new int[ arr.length ];
            for( int i = 0; i < arr.length; ++i ) {
                tracker[i] = 1;
            }

            /*
             * starting with length 2, Calculate the length of the increasing
             * sequence till index i and store at T[i]
             * Longest increasing subsequence at index 0 is the char itself hence has length 1
             *
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
     * This is continuous sequence length finder (it is substring here)
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
