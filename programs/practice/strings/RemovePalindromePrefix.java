/*
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
            + RemovePalindromePrefix.removePalindrombePrefix("aaacodedoc"));
    System.out.println(
        "Original String :codeSignal "
            + " Remaining String : "
            + RemovePalindromePrefix.removePalindrombePrefix("codeSignal"));
    System.out.println(
        "Original String :aaaabbaaaabaabbbbb "
            + " Remaining String : "
            + RemovePalindromePrefix.removePalindrombePrefix("aaaabbaaaabaabbbbb"));
  }

  public static String removePalindrombePrefix(String s) {

    if (s != null && s.length() > 1) {
      int len = s.length();
      int i = 0;
      int j = 1;
      int lastIndex = 0;
      StringBuffer buffer = new StringBuffer(s);

      // i will always be 0 which is start index
      while (j < len) {
        // Actions to be taken when a prefix is not palindrome
        if (!isPalindrome(buffer, i, j)) {
          // here first 2 chars do not for a palidrome string
          // Check if first char is extra and remaining string is palindrome
          if (j - i == 1 && i == 0 && isPalindrome(buffer, j, len - 1)) {
            // System.out.println(buffer.substring(i, j));
            buffer.delete(0, buffer.length());
            len = 0;
          } else if (lastIndex > 0) { // indicates there we some palidrome prefix found

            // System.out.println(buffer.substring(i, j));
            // So current prefix is not palindrome and we have already found a palindrome prefix
            // Delete till the last palidrome prefix found
            buffer.delete(i, j);
            i = 0;
            j = 0;
            lastIndex = 0;
            len = buffer.length();
            // System.out.println(buffer.toString() +  " " + len);
          }

        } else {
          // Just keep gathering the last index of the palidrome prefix
          lastIndex = j;
        }
        j++;
      }

      // indicates that there was some palindrome found
      if (lastIndex > 0) {
        // Here here is a remaining palidrome string so remove it
        // System.out.println(buffer.toString() + " " + lastIndex);
        buffer.delete(i, j);
      }
      return buffer.toString();
    }
    return s;
  }

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
}
