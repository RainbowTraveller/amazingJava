/**
 * Valid Palindrome with at most one removal Leetcode Problem:
 * https://leetcode.com/problems/valid-palindrome-ii/
 *
 * <p>Given a string return true if it can be made a palindrome by removing at most one char ( 0 or
 * 1 ) which means return true even if it is already palindrome
 *
 * <p>Optimal Solution for k=1 (No Memoization Needed) For the "remove at most one character"
 * problem, the optimal strategy is greedy, using two pointers. Memoization is unnecessary because
 * the state space is small.
 *
 * <p>Algorithm Start two pointers, left at 0 and right at length - 1.
 *
 * <p>Iterate inward while left < right:
 *
 * <p>If s[left] equals s[right], move both pointers inward (left++, right--).
 *
 * <p>If s[left] does not equal s[right], you have found the only required removal. You must check
 * two possibilities:
 *
 * <p>Possibility A: Is the rest of the string a palindrome if you remove s[left]? (Check s[left +
 * 1] to s[right])
 *
 * <p>Possibility B: Is the rest of the string a palindrome if you remove s[right]? (Check s[left]
 * to s[right - 1])
 *
 * <p>Return true if either A or B is true.
 *
 * <p>If the loop completes without a mismatch, the string is already a palindrome, so return true.
 */
public class ValidPalindromeOneChar {
  public static void main(String[] args) {
    ValidPalindromeOneChar vpoc = new ValidPalindromeOneChar();
    System.out.println(vpoc.validPalindrome("aba"));
    System.out.println(vpoc.validPalindrome("abca"));
    System.out.println(vpoc.validPalindrome("abcad"));
    System.out.println(vpoc.validPalindrome("aeeee"));
  }

  public boolean validPalindrome(String s) {
    if (s != null && s.length() > 0) {
      int start = 0, end = s.length() - 1;

      while (start < end) {
        // Identify first pair which is not matching
        if (s.charAt(start) != s.charAt(end)) {
          // check if by removing each unmatching char, remaining string is palindrome or
          // not
          return isPalindrome(s, start + 1, end) || isPalindrome(s, start, end - 1);
        }
        start++;
        end--;
      }
    }
    return true;
  }

  private boolean isPalindrome(String s, int l, int h) {
    while (l < h) {
      if (s.charAt(l++) != s.charAt(h--)) {
        return false;
      }
    }
    return true;
  }
}
