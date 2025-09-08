/*  Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 *  Example 1:
 *
 *  Input: "eceba"
 *  Output: 3
 *  Explanation: t is "ece" which its length is 3.
 *  Example 2:
 *
 *  Input: "ccaabbb"
 *  Output: 5
 *  Explanation: t is "aabbb" which its length is 5.
 */

import java.util.*;

public class LongestSubStringWithAtMostNDistictChars {
  public static void main(String[] args) {

    System.out.println(
        "Input : " + "eceba" + " :: " + lengthOfLongestSubstringTwoDistinct("eceba"));
  }

  /**
   * This method uses sliding window approach with 2 pointers left and right The logic is as
   * follows:
   *
   * <p>1. Move the right pointer to the right and add characters to the map until there are 3
   * distinct characters in the map.
   *
   * <p>2. When there are 3 distinct characters, move the left pointer to the right until there are
   * only 2 distinct characters in the map.
   *
   * <p>3. Update the length of the longest substring found so far.
   *
   * <p>4. Repeat steps 1-3 until the right pointer reaches the end of the string.
   *
   * <p>5. Return the length of the longest substring found.
   *
   * <p>Time Complexity: O(n) where n is the length of the string.
   *
   * <p>Space Complexity: O(1) since the map will contain at most 3 distinct characters.
   *
   * @param s input string
   * @return length of the longest substring with at most 2 distinct characters
   */
  public static int lengthOfLongestSubstringTwoDistinct(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    // Initialize variables
    // maxLength to keep track of the maximum length found
    int maxLength = 0;
    // left pointer to keep track of the start of the window
    int left = 0;
    // Map to keep track of the characters in the current window and their rightmost positions
    Map<Character, Integer> charMap = new HashMap<>();
    // Iterate through the string with the right pointer
    for (int right = 0; right < s.length(); right++) {
      // Add the current character to the map
      char currentChar = s.charAt(right);
      // Update the rightmost position of the current character
      charMap.put(currentChar, right);
      // If the number of distinct characters is more than 2, shrink the window
      if (charMap.size() > 2) {
        // Find the character with the smallest index
        // minIndex to keep track of the smallest index
        // Initialize to the length of the string
        int minIndex = s.length();
        char charToRemove = ' ';
        // Find the character with the smallest index
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
          if (entry.getValue() < minIndex) {
            minIndex = entry.getValue();
            charToRemove = entry.getKey();
          }
        }
        // Remove that character and move the left pointer
        charMap.remove(charToRemove);
        left = minIndex + 1;
      }
      // Update the maximum length
      maxLength = Math.max(maxLength, right - left + 1);
    }
    return maxLength;
  }
}
