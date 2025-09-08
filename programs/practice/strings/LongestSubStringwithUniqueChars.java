/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * <p>Example 1:
 *
 * <p>Input: "abcabcbb" Output: 3 Explanation: The answer is "abc", with the length of 3. Example 2:
 *
 * <p>Input: "bbbbb" Output: 1 Explanation: The answer is "b", with the length of 1. Example 3:
 *
 * <p>Input: "pwwkew" Output: 3 Explanation: The answer is "wke", with the length of 3. Note that
 * the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
import java.util.HashSet;
import java.util.Set;

public class LongestSubStringwithUniqueChars {
  public static void main(String[] args) {
    String s = args[0];
    System.out.println(lengthOfLongestSubstring(s));
    System.out.println(lengthOfLongestSubstringLinear(s));
    System.out.println(lengthOfLongestSubstringLinearIndex(s));
  }

  /**
   * O(n^3) solution and also O(n) space Brute force Checks for all possible substrings and then
   * uniqueness.
   *
   * <p>The logic is as follows:
   *
   * <p>1. Start with first character and check all substrings starting with this character
   *
   * <p>2. For each substring check if all characters are unique
   *
   * <p>3. If unique, record the length if it is more than max length so far
   *
   * <p>4. Move to next character and repeat the process
   *
   * <p>5. Finally return the max length
   *
   * <p>Time complexity is O(n^3) as we have 2 loops to generate all substrings and one loop to
   * check uniqueness
   *
   * <p>Space complexity is O(n) as we use a set to check uniqueness
   *
   * @param s input string
   * @return length of longest substring with unique characters
   */
  public static int lengthOfLongestSubstring(String s) {
    int maxLen = 0;
    int len = 0;
    if (s != null) {
      maxLen = 1; // min length is 1 char
      len = s.length();
      for (int i = 0; i < len; ++i) {
        for (int j = i + 1; j < len; ++j) {
          if (hasUniqueChars(s, i, j)) {
            maxLen = Math.max(maxLen, j - i + 1);
            System.out.println(" i : " + i + " j " + j);
          }
        }
      }
    }
    return maxLen;
  }

  /**
   * Check if all characters in the substring s[i..j] are unique
   *
   * <p>The logic is as follows:
   *
   * <p>1. Create a set to store characters
   *
   * <p>2. Loop through the substring and add characters to the set
   *
   * <p>3. If a character is already in the set, return false
   *
   * <p>4. If we reach the end of the substring, return true
   *
   * <p>Time complexity is O(n) where n is the length of the substring
   *
   * <p>Space complexity is O(n) where n is the length of the substring
   *
   * @param s input string
   * @param i start index of substring
   * @param j end index of substring
   * @return true if all characters are unique, false otherwise
   */
  public static boolean hasUniqueChars(String s, int i, int j) {
    Set<Character> charSet = new HashSet<Character>();
    while (i <= j) { // Need to compare all characters so <=
      char curr = s.charAt(i);
      if (charSet.contains(curr)) {
        return false;
      }
      charSet.add(curr);
      i++;
    }
    return true;
  }

  /**
   * Implementation with sliding window. As long as we encounter unique character increment j and
   * add the char to the set. When we have duplicate char, start deleting char at i from set.
   * Eventually it will delete all the chars from set till is matches j and the procedure repeats we
   * record the length when we add a char to set
   *
   * <p>The logic is as follows:
   *
   * <p>1. Create a set to store characters
   *
   * <p>2. Create two pointers i and j to represent the current window
   *
   * <p>3. Loop through the string with j pointer
   *
   * <p>4. If the character at j is not in the set, add it to the set and increment j
   *
   * <p>5. If the character at j is in the set, remove the character at i from the set and increment
   * i
   *
   * <p>6. Update the max length if the current window is larger than the max length
   *
   * <p>7. Return the max length
   *
   * <p>Time complexity is O(n) where n is the length of the string
   *
   * <p>Space complexity is O(min(m, n)) where m is the size of the charset and n is the length of
   * the string
   *
   * @param s input string
   * @return length of longest substring with unique characters
   */
  public static int lengthOfLongestSubstringLinear(String s) {
    // Sliding window approach
    int len = s.length();
    // Set to store unique characters
    Set<Character> unique = new HashSet<>();
    // i is the start of the window, j is the end of the window
    int i = 0, j = 0, maxlen = 0;
    // Loop until we reach the end of the string
    while (i < len && j < len) {
      // If the character at j is already in the set, remove the character at i and increment i
      if (unique.contains(s.charAt(j))) {
        // Here j is not incremented. We will keep removing chars from start
        unique.remove(s.charAt(i++));
      } else {
        // If the character at j is not in the set, add it to the set and increment j
        unique.add(s.charAt(j++));
        // Update the max length if the current window is larger than the max length
        maxlen = Math.max(maxlen, j - i);
      }
    }
    return maxlen;
  }

  /**
   * This involves no set. We just play with the indexes. We again have a starting index and current
   * index. Initially both start with 0. If char at curr index is found between starting and curr
   * index, then it is repeating one. So we increment starting index to 1 more than this found
   * occurrence of the char. And continue. When not found we record the length of the string
   * obtained so far The logic is as follows:
   *
   * <p>1. Create two pointers start and end to represent the current window
   *
   * <p>2. Loop through the string with end pointer
   *
   * <p>3. If the character at end is found between start and end, update start to the index of the
   * found character + 1
   *
   * <p>4. Update the max length if the current window is larger than the max length
   *
   * <p>5. Return the max length
   *
   * <p>Time complexity is O(n^2) where n is the length of the string
   *
   * <p>Space complexity is O(1)
   *
   * @param s input string
   * @return length of longest substring with unique characters
   */
  public static int lengthOfLongestSubstringLinearIndex(String s) {
    int start = 0;
    int end = 0;
    int maxLen = 0;
    if (s != null) {
      while (start <= end && end < s.length()) {
        char endChar = s.charAt(end);
        int index = s.indexOf(endChar, start);
        if (index >= start && index < end) {
          maxLen = Math.max(maxLen, end - start);
          // Adjust the start to shrink the sliding window
          // start with the next character where the previous occurrence
          // of current end char
          start = index + 1; // We have non unique character at this index
        }
        end++;
      }
    }
    maxLen = Math.max(maxLen, end - start);
    return maxLen;
  }
}
