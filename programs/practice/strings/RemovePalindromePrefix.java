/*
 *   Leetcode Problem No 1750 : Remove Palindrome Subsequence
 *   https://leetcode.com/problems/remove-palindrome-subsequences/
 *   Difficulty : Easy
 *
 *   You are given a string s. Consider the following algorithm applied to this string:
 *
 *   Take all the prefixes of the string, and choose the longest palindrome between them.
 *   If this chosen prefix contains at least two characters, cut this prefix from s and go back to the first step with
 *   the updated string. Otherwise, end the algorithm with the current string s as a result.
 *   Your task is to implement the above algorithm and return its result when applied to string s.
 *
 *   Note: you can click on the prefixes and palindrome words to see the definition of the terms if you're not familiar
 *   with them.
 *
 *   Example
 *
 *  For s = "aaacodedoc", the output should be solution(s) = "".
 *
 *   The initial string s = "aaacodedoc" contains only three prefixes which are also palindromes - "a", "aa", "aaa".
 *   The longest one between them is "aaa", so we cut it from s.
 *   Now we have string "codedoc". It contains two prefixes which are also palindromes - "c" and "codedoc". The longest
 *   one between them is "codedoc", so we cut it from the current string and obtain the empty string.
 *   Finally the algorithm ends on the empty string, so the answer is "".
 *
 *   For s = "codesignal", the output should be solution(s) = "codesignal".
 *   The initial string s = "codesignal" contains the only prefix, which is also palindrome - "c". This prefix is the
 *   longest, but doesn't contain two characters, so the algorithm ends with string "codesignal" as a result.
 *
 *   For s = "", the output should be solution(s) = "".
 */
public class RemovePalindromePrefix {

  public static void main(String[] args) {
    System.out.println(
        "Original String :aaacodedoc "
            + " Remaining String : "
            + RemovePalindromePrefix.removePalindromeSubsequences("aaacodedoc"));
    System.out.println(
        "Original String :codeSignal "
            + " Remaining String : "
            + RemovePalindromePrefix.removePalindromeSubsequences("codeSignal"));
    System.out.println(
        "Original String :aaaabbaaaabaabbbbb "
            + " Remaining String : "
            + RemovePalindromePrefix.removePalindromeSubsequences("aaaabbaaaabaabbbbb"));
  }

  /**
   * Checks if the substring of s from index i to j is a palindrome.
   *
   * @param s The StringBuffer to check.
   * @param i The starting index of the substring.
   * @param j The ending index of the substring.
   * @return true if the substring is a palindrome, false otherwise.
   */
  public static boolean isPalindrome(StringBuffer s, int i, int j) {
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  /**
   * Implements the algorithm: repeatedly finds and removes the Longest Palindrome Prefix (LPP) of
   * length >= 2 until no such prefix can be found. Returns the remaining string.
   *
   * @param s The initial input string.
   * @return The final remaining string after the removal algorithm completes.
   */
  public static String removePalindromeSubsequences(String s) {
    StringBuffer sb = new StringBuffer(s);
    while (true) {
      int lppEnd = findLongestPalindromePrefix(sb);
      if (lppEnd < 2) {
        break; // No valid LPP found, exit the loop
      }
      sb.delete(0, lppEnd); // Remove the LPP from the string
    }
    return sb.toString();
  }

  /**
   * Finds the length of the longest palindrome prefix in the given StringBuilder.
   *
   * @param sb The StringBUffer to search for the longest palindrome prefix.
   * @return The length of the longest palindrome prefix.
   */
  public static int findLongestPalindromePrefix(StringBuffer sb) {
    int n = sb.length();
    // Check prefixes from longest to shortest
    for (int end = n - 1; end >= 0; end--) {
      if (isPalindrome(sb, 0, end)) {
        return end + 1; // Return length of the palindrome prefix
      }
    }
    return 0; // No palindrome prefix found
  }
}
