/**
 * You are given a 0-indexed string s consisting of only lowercase English letters. In one
 * operation, you can change any character of s to any other character. LeetCode link:
 * https://leetcode.com/problems/valid-palindrome-iv/ LeetCode problem number: 2664 Level: Easy
 *
 * <p>Return true if you can make s a palindrome after performing exactly one or two operations, or
 * return false otherwise.
 *
 * <p>Example 1:
 *
 * <p>Input: s = "abcdba" Output: true Explanation: One way to make s a palindrome using 1 operation
 * is: - Change s[2] to 'd'. Now, s = "abddba". One operation could be performed to make s a
 * palindrome so return true. Example 2:
 *
 * <p>Input: s = "aa" Output: true Explanation: One way to make s a palindrome using 2 operations
 * is: - Change s[0] to 'b'. Now, s = "ba". - Change s[1] to 'b'. Now, s = "bb". Two operations
 * could be performed to make s a palindrome so return true. Example 3:
 *
 * <p>Input: s = "abcdef" Output: false Explanation: It is not possible to make s a palindrome using
 * one or two operations so return false.
 */
public class ValidPalindromeWith2Operations {
  public static void main(String[] args) {
    ValidPalindrome4 vp = new ValidPalindrome4();

    System.out.println(vp.makePalindrome("abcdba"));
    System.out.println(vp.makePalindrome("aa"));
    System.out.println(vp.makePalindrome("abcdef"));
  }

  /**
   * Two pointer approach to check if a string can be converted to a palindrome with at most two
   * operations.
   *
   * @param s input string
   * @return true if the string can be converted to a palindrome with at most two operations, false
   *     otherwise
   */
  public boolean makePalindrome(String s) {
    int i = 0, e = s.length() - 1, cnt = 0;
    while (i < e) {
      if (cnt > 2) return false;
      if (s.charAt(i) != s.charAt(e)) {
        cnt++;
      }
      i++;
      e--;
    }
    return cnt <= 2;
  }
}
