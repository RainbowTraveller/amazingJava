/*       a a b
 *       0 1 2
 *    0a 1 2 2
 *    1a 0 1 1
 *    2b 0 0 1
 *
 *    a a b : starting with each letter ( position ) and count strings with 1 len, 2 len
 *    a, a, b : 3 palindromes with 1 char ( diagonal of matrix )
 *    aa : starting from 0, 2 char long sequence starting at 0 ending at 1 so max palindrome length is 2
 *    ab : starting at position 1, 2 char sequence, not palindrome, starting at 1 ending at 2,
 * 			so max palindrome length here is 1
 *    aab : end chars a and b are not matching so max palindrome length is max of found so far max(2, 1) = 2
 *
 *
 *    input : abba
 *		a	1 1 2 4
 *		b	0 1 2 2
 *		b	0 0 1 1
 *		a	0 0 0 1
 *			Total palindromic subsequence are : 9
 *
 *    input : abcd
 *		a	1 1 1 1
 *		b	0 1 1 1
 *		c	0 0 1 1
 *		d	0 0 0 1
 *			Total palindromic subsequence are : 4
 */
public class LongestPalindromeSubsequence {
    public static void main(String [] args) {
        //String str = "abcd";
        //String str = "aaaa";
        String str = "abcb";
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
                            tracker[j][index] =  2 + tracker[ j + 1 ][ index - 1 ];
                        } else {
                            System.out.println("Not Matched : " + input.charAt(j) + " and " + input.charAt(index));
                            tracker[j][index] =  Math.max( tracker[j ][ index - 1 ], tracker[ j + 1 ][ index ]) ;
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
