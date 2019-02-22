/*       a a b
 *       0 1 2
 *    0  1 3 4
 *    1  0 1 2
 *    2  0 0 1
 *
 *    a a b : starting with each letter ( position ) and count strings with 1 len, 2 len
 *    a, a, b : 3 palindromes with 1 char ( diagonal of matrix )
 *    aa : starting from 0, 2 char long sequence starting at 0 ending at 1 so matrix[0][1] = 3 ( a, a, aa )
 *    ab : starting at position 1, 2 char sequence, not palindrome, starting at 1 ending at 2, so matrix[1][2] = 2 (a, b)
 *    aab : in aa 3, and in ab : 2 palindromes = 5, but middle a is counted twice so 5 -1 = 4 sequences
 */
public class PalindromicSubSeqCounter {
    public static void main(String [] args) {
        String str = "aaab";
        //String str = "aaaa";
        //String str = "abcb";
        System.out.println("Total palindromic "+
                            "subsequence are : "
                              + palindromicSubSequenceCounter(str));
    }

    public static int palindromicSubSequenceCounter( String input ) {
        if(input != null) {
            int length = input.length();
            //Let's create a matrix, which will contains on x axis starting index in the string
            //on y axis ending index. The element x,y = true if and only if
            //string starting at x of ending at y is a palindrome
            int [][] tracker = new int[length][length];
            //Starting at each index and 1 character long
            for(int i = 0; i < length; ++i) {
                tracker[i][i] = 1;
            }

            //Let's consider now strings of length from 2 character long
            //to length of entire string
            for(int i = 2; i <= length; ++i) {
                for(int j = 0; j < length; ++j) {
                    // start + length - 1 = 0 + 2 - 1 = (0,1)
                    int index = i + j - 1;
                    if(index < length) {
                        if(input.charAt(j) == input.charAt(index) ) {
                            System.out.println("Matched : " + input.charAt(j) + " and " + input.charAt(index));
                            tracker[j][index] =  1 + tracker[j][ index - 1 ] + tracker[ j + 1 ][ index ];
                        } else {
                            System.out.println("Not Matched : " + input.charAt(j) + " and " + input.charAt(index));
                            tracker[j][index] =  tracker[j ][ index - 1 ] + tracker[ j + 1 ][ index ] - tracker[ j + 1 ][ index - 1 ];
                        }
                    }
                }
            }
            for(int i = 0; i < length; ++i) {
                for(int j = 0; j < length; ++j) {
                    System.out.print(tracker[i][j] + " ");
                }
                System.out.println();
            }
            return tracker[0][length-1];
        }
        return 0;
    }
}
