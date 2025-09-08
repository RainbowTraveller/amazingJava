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
  public static void main(String[] args) {
    // String str = "abcd";
    String str = "aaaa";
    // String str = "abcb";
    System.out.println(
        "Dyn Palindromic Subsequence Counter : " + palindromicSubsequenceCounterDyn(str));
    System.out.println(
        "Dyn Longest Palindromic Subsequence : " + getMaxLengthOfPalindromicSubsequenceDyn(str));
    System.out.println(
        "Longest palindromic "
            + "subsequence length : "
            + getMaxLengthOfPalindromicSubsequnce(str, 0, str.length() - 1));
  }

  /**
   * Recursive approach with 2 base cases.
   *
   * <p>1 char long string and 2 char long string
   *
   * <p>end characters are matching then length of inner palindrome + these 2 characters
   *
   * <p>if they are not matching, then check for longest palindrome length from first character to
   * the character before last one i.e. exclude last one.
   *
   * <p>Also get length of largest palin drome subsequence if any from last to char next to first
   * one i.e. excluding first 1. and then return max of these 2 lengths obtained.
   *
   * <p>Exponential and drawback is subproblems are called repeatedly
   *
   * <p>Time complexity O(2^n) and space complexity O(n) for recursion stack
   *
   * @param s input string
   * @param start starting index
   * @param end ending index
   * @return length of longest palindromic subsequence
   */
  public static int getMaxLengthOfPalindromicSubsequnce(String s, int start, int end) {
    if (start == end) {
      return 1;
    }
    char sChar = s.charAt(start);
    char eChar = s.charAt(end);
    if (sChar == eChar && end - start == 1) {
      return 2;
    } else if (sChar == eChar) {
      return getMaxLengthOfPalindromicSubsequnce(s, start + 1, end - 1) + 2;
    } else {
      return Math.max(
          getMaxLengthOfPalindromicSubsequnce(s, start + 1, end),
          getMaxLengthOfPalindromicSubsequnce(s, start, end - 1));
    }
  }

  /**
   * Dynamic programming approach for counting palindromic subsequence.
   *
   * <p>Create a matrix of size length * length.
   *
   * <p>Fill diagonal with 1 as each character is a palindrome of length 1.
   *
   * <p>Now consider strings of length 2 to length of entire string. For each length, start from
   * each index and check if starting and ending characters are matching.
   *
   * <p>If they are matching then 2 + length of palindrome between these 2 characters.
   *
   * <p>If they are not matching then max of palindrome length excluding starting character or
   * excluding ending character.
   *
   * <p>Time complexity O(n^2) and space complexity O(n^2)
   */
  public static int getMaxLengthOfPalindromicSubsequenceDyn(String input) {
    if (input != null) {
      int length = input.length();
      // Let's create a matrix, which will contains on x axis starting index in the string
      // on y axis ending index. The element x,y = true if and only if
      // string starting at x of ending at y is a palindrome
      int[][] tracker = new int[length][length];
      // Starting at each index and 1 character long
      for (int i = 0; i < length; ++i) {
        tracker[i][i] = 1;
      }

      // Let's consider now strings of length from 2 character long
      // to length of entire string
      for (int i = 2; i <= length; ++i) { // length of the string to consider
        for (int j = 0; j < length; ++j) { // starting point in the string
          // start + length - 1 = 0 + 2 - 1 = (0,1)
          int index = j + i - 1;
          if (index < length) { // Check if index is beyond length
            if (input.charAt(j) == input.charAt(index)) {
              System.out.println("Matched : " + input.charAt(j) + " and " + input.charAt(index));
              // If the First and Last Characters Match:
              // The program checks if input.charAt(j) is equal to input.charAt(index).
              // If they match, it means they can extend the palindromic subsequence.
              // The length is 2 (for the two matching characters) plus the
              // length of the longest palindromic subsequence of the inner substring, which is
              // stored in tracker[j + 1][index - 1].
              tracker[j][index] = 2 + tracker[j + 1][index - 1];
            } else {
              System.out.println(
                  "Not Matched : " + input.charAt(j) + " and " + input.charAt(index));
              // If the First and Last Characters Don't Match:
              // In this case, the first and last characters cannot be part of the same longest
              // palindromic subsequence.
              // The solution must be found in one of the two smaller subproblems: the substring
              // without the
              // first character or the substring without the last character. The program correctly
              // takes the maximum of these two subproblems
              tracker[j][index] = Math.max(tracker[j][index - 1], tracker[j + 1][index]);
            }
          }
        }
      }

      for (int i = 0; i < length; ++i) {
        for (int j = 0; j < length; ++j) {
          System.out.print(tracker[i][j] + " ");
        }
        System.out.println();
      }
      return tracker[0][length - 1];
    }
    return 0;
  }

  /**
   * Counts all palindromic subsequences in a string using dynamic programming. Time Complexity:
   * O(N^2), Space Complexity: O(N^2)
   */
  public static int palindromicSubsequenceCounterDyn(String input) {
    if (input == null || input.isEmpty()) {
      return 0;
    }

    int n = input.length();
    int[][] dp = new int[n][n];

    // Base case: every single character is a palindromic subsequence
    for (int i = 0; i < n; i++) {
      dp[i][i] = 1;
    }

    // Fill the DP table diagonally
    for (int len = 2; len <= n; len++) {
      for (int i = 0; i <= n - len; i++) {
        int j = i + len - 1;

        if (input.charAt(i) == input.charAt(j)) {
          // Correct recurrence for matching characters.
          // This is the union of palindromes from s[i+1...j] and s[i...j-1], plus one new
          // palindrome "s[i]s[j]".
          // The common palindromes from s[i+1...j-1] are double-counted in dp[i+1][j] and
          // dp[i][j-1],
          // so we subtract them, then add the new palindromes formed by the matching ends.
          // A simpler way: dp[i][j] = dp[i+1][j] + dp[i][j-1] + 1; This is the correct relation for
          // non-distinct subsequences.
          dp[i][j] = dp[i + 1][j] + dp[i][j - 1] + 1;
        } else {
          // Correct recurrence for non-matching characters.
          // This is the union of palindromes from s[i+1...j] and s[i...j-1].
          // The common part (s[i+1...j-1]) is counted twice, so we subtract once.
          dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
        }
      }
    }
    return dp[0][n - 1];
  }
}
