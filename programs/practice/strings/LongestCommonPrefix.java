/*
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */

public class LongestCommonPrefix {
  public static void main(String[] args) {
    String[] input = {"flower", "flow", "flight"};
    System.out.println("L C prefix : " + longestCommonPrefix(input));
    System.out.println("L C prefix : " + longestCommonPrefixNew(input));
  }

  /**
   * 1. Take the first string as the base string.
   *
   * <p>2. For each character in the base string, compare it with the corresponding character in all
   * other strings.
   *
   * <p>3. If a mismatch is found or if any string is shorter than the current index, return the
   * common prefix found so far.
   *
   * <p>4. If all characters match for the current index, append the character to the result buffer
   * and continue to the next index.
   *
   * <p>5. If the end of the base string is reached without mismatches, return the entire base
   * string as the common prefix.
   *
   * <p>Time Complexity: O(N * M) where N is the number of strings and M is the length of the
   * shortest string.
   *
   * <p>Space Complexity: O(M) for the result buffer in the worst case.
   *
   * @param strs Array of strings to find the longest common prefix
   * @return The longest common prefix string
   */
  public static String longestCommonPrefixNew(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }
    // Get the first string
    String base = strs[0];
    // Buffer for tracking the common string
    StringBuffer buffer = new StringBuffer();
    // For each char in the first string
    for (int i = 0; i < base.length(); i++) {
      // Get the char
      char c = base.charAt(i);
      // Compare with all other strings
      // For each string in the array of strings
      for (int j = 1; j < strs.length; j++) {
        String curr = strs[j]; // Get a string
        // if index of curr string is less than tracking index
        // Or ith char is not matching with tracking char
        if (curr.length() - 1 < i || curr.charAt(i) != c) {
          return buffer.toString();
        }
      }
      // If all strings have the same char at index i, append it to the buffer
      buffer.append(c);
    }
    return buffer.toString();
  }
}
