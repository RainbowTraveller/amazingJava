/**
 * LeetCode: 1216. Valid Palindrome III https://leetcode.com/problems/valid-palindrome-iii/ Hard
 *
 * <ul>
 *   <li>Given a string s and an integer k, return true if s is a k-palindrome.
 *   <li>A k-palindrome is a string that can be transformed into a palindrome by removing at most k
 *       characters from it.
 *   <li>Example 1:
 *   <li>Input: s = "abcdeca", k = 2
 *   <li>Output: true
 *   <li>Explanation: Remove 'b' and 'e' characters.
 *   <li>Example 2:
 *   <li>Input: s = "abbababa", k = 1
 *   <li>Output: true
 *   <li>Constraints:
 *   <li>1 <= s.length <= 1000
 *   <li>s consists of lowercase English letters.
 *   <li>0 <= k <= s.length
 * </ul>
 */
class ValidKPalindrome {
  public static void main(String[] args) {
    ValidKPalindrome validKPalindrome = new ValidKPalindrome();

    System.out.println(
        "Recursive Outputs : Calculates Largest Palindromic Subsequence(LPS) length");
    System.out.println(validKPalindrome.isValidPalindrome("abcdfdecba", 2));
    System.out.println(validKPalindrome.isValidPalindrome("abcdfdecba", 3));
    System.out.println(validKPalindrome.isValidPalindrome("abcdfdecba", 1));
    System.out.println(validKPalindrome.isValidPalindrome("abcdfghjdecba", 1));
    System.out.println(validKPalindrome.isValidPalindrome("abcdfghjdecba", 4));
    System.out.println(validKPalindrome.isValidPalindrome("abcdfghjdecba", 3));
    System.out.println(validKPalindrome.isValidPalindrome("dfghjde", 4));
    System.out.println(validKPalindrome.isValidPalindrome("dfghjde", 3));
    // This time outs
    // System.out.println(
    //     validKPalindrome.isValidPalindrome(
    //         "baaccaacbdcadbcdacbbdabbdddabdddadcabbdbbcaadbbdcbddcbdcdbaadaab", 9));
    System.out.println("DP Outputs : memoization approach");
    System.out.println(validKPalindrome.isValidKPalindromeDP("abcdfdecba", 2));
    System.out.println(validKPalindrome.isValidKPalindromeDP("abcdfdecba", 3));
    System.out.println(validKPalindrome.isValidKPalindromeDP("abcdfdecba", 1));
    System.out.println(validKPalindrome.isValidKPalindromeDP("abcdfghjdecba", 1));
    System.out.println(validKPalindrome.isValidKPalindromeDP("abcdfghjdecba", 4));
    System.out.println(validKPalindrome.isValidKPalindromeDP("abcdfghjdecba", 3));
    System.out.println(validKPalindrome.isValidKPalindromeDP("dfghjde", 4));
    System.out.println(validKPalindrome.isValidKPalindromeDP("dfghjde", 3));

    System.out.println(
        validKPalindrome.isValidKPalindromeDP(
            "baaccaacbdcadbcdacbbdabbdddabdddadcabbdbbcaadbbdcbddcbdcdbaadaab", 9));
  }

  /**
   * Recursive approach
   *
   * <p>The idea is to find the length of the longest palindromic subsequence (LPS) in the string.
   * The minimum number of deletions required to make the string a palindrome is equal to the
   * difference between the length of the string and the length of the LPS. If this difference is
   * less than or equal to k, then the string is a k-palindrome.
   *
   * <p>Time complexity: O(2^n) in worst case, where n is the length of the string. This is because
   * each character can either be included in or excluded from the subsequence.
   *
   * <p>Space complexity: O(n) for the recursion stack.
   *
   * @param s input string
   * @param k maximum number of deletions allowed
   * @return true if s is a k-palindrome, false otherwise
   */
  public boolean isValidPalindrome(String s, int k) {
    int lpsLength = findLPSLength(s, 0, s.length() - 1);
    return s.length() - lpsLength <= k;
  }

  /**
   * Helper method to find the length of the longest palindromic subsequence (LPS) in a string
   *
   * @param s input string
   * @param i starting index
   * @param j ending index
   * @return length of the longest palindromic subsequence in s[i..j]
   */
  private int findLPSLength(String s, int i, int j) {
    // Base case: If the indices cross, the subsequence has length 0.
    if (i > j) {
      return 0;
    }
    // Base case: A single character is an LPS of length 1.
    if (i == j) {
      return 1;
    }

    // Recursive case: If the characters at the ends match, they are part of the LPS.
    if (s.charAt(i) == s.charAt(j)) {
      return 2 + findLPSLength(s, i + 1, j - 1);
    } else {
      // If they don't match, we try removing one character from either end
      // and take the maximum length.
      return Math.max(findLPSLength(s, i + 1, j), findLPSLength(s, i, j - 1));
    }
  }

  /**
   * Dynamic Programming approach
   *
   * <p>The logic is simple. The comparison takes place only between 2 extreme chars 1.
   *
   * <p>If they match start + 1 and end - 1 2. If the do not match we go both ways (start + 1, end )
   * and ( start, end - 1) 3.
   *
   * <p>We also keep track of already found values using an array ( memoization )
   */
  public boolean isValidKPalindromeDP(String s, int k) {

    if (s != null && !s.isEmpty()) {
      int len = s.length();
      int start = 0;
      int end = len - 1;
      // We set value of i, j  to minimum no. of chars that need to be deleted to make
      // string from i to j palindrome
      Integer[][] values = new Integer[len][len];
      return k > dpProcessing(s, start, end, values);
    }
    return false;
  }

  /**
   * DP processing to find minimum no. of chars that need to be deleted to make string from start to
   * end palindrome
   *
   * <p>time complexity O(n^2) : Each state (i, j) is computed only once and there are O(n^2) such
   * states.
   *
   * <p>space complexity O(n^2) for memoization array : The memoization array values uses O(n^2)
   * space to store the results of subproblems.
   *
   * @param input input string
   * @param start starting index
   * @param end ending index
   * @param values memoization array to store already computed values
   * @return minimum no. of chars that need to be deleted to make string from start to end
   *     palindrome
   */
  public int dpProcessing(String input, int start, int end, Integer[][] values) {
    // Base case : single letter it is palindrome
    if (start == end) {
      return 0;
    }
    // Base case : 2 adjacent letters : check if they are equal
    // If the characters do not match, one of them must be removed. This adds 1 to the total removal
    // count.
    // You then recursively solve the two possible smaller subproblems: one where
    // input.charAt(start) is removed,
    // and one where input.charAt(end) is removed. Taking the minimum of these two results ensures
    // you find the most optimal path.
    if (Math.abs(start - end) == 2) {
      // It is 2 char string and check if both chars are equal
      // if the are equal no need to remove any char
      // if they are not equal we need to remove one char
      return input.charAt(start) == input.charAt(end) ? 0 : 1;
    }

    if (values[start][end] != null) {
      return values[start][end];
    }

    if (input.charAt(start) == input.charAt(end)) {
      // if both chars are equal move to next char
      // no need to remove any char
      return values[start][end] = dpProcessing(input, start + 1, end - 1, values);
    }

    // if both chars are not equal we need to remove one char
    // hence add 1 to the result
    // also we need to check both the possibilities
    // 1. remove char at start
    // 2. remove char at end
    // and take the minimum of both
    return values[start][end] =
        1
            + Math.min(
                start + 1 <= end ? dpProcessing(input, start + 1, end, values) : 0,
                start <= end - 1 ? dpProcessing(input, start, end - 1, values) : 0);
  }
}
