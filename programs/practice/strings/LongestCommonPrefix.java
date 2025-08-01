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

  public static String longestCommonPrefix(String[] strs) {
    StringBuffer buff = new StringBuffer();
    if (strs != null && strs.length >= 1) {
      int index = 0;
      boolean process = true;
      while (process) {
        if (index >= strs[0].length()) {
          process = false;
        } else {
          char c = strs[0].charAt(index);
          for (int i = 1; i < strs.length; ++i) {
            if (index >= strs[i].length() || strs[i].charAt(index) != c) {
              return buff.toString();
            }
          }
          buff.append(c);
          index++;
        }
      }
    }
    return buff.toString();
  }

  public static String longestCommonPrefixNew(String[] strs) {
    String base = strs[0]; // Get the first string
    StringBuffer buffer = new StringBuffer(); // Buffer for tracking the common string
    for (int i = 0; i < base.length(); i++) { // For each char in the first string
      char c = base.charAt(i); // get the char
      for (int j = 1; j < strs.length; j++) { // For each string in the array of strings
        String curr = strs[j]; // Get a string
        // if index of curr string is less than tracking index
        // Or ith char is not matching with tracking char
        if (curr.length() - 1 < i || curr.charAt(i) != c) {
          return buffer.toString();
        }
      }
      buffer.append(c);
    }
    return buffer.toString();
  }
}
