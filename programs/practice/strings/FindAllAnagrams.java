/*  Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 *  Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 *  The order of output does not matter.
 *
 *  Example 1:
 *
 *  Input:
 *  s: "cbaebabacd" p: "abc"
 *
 *  Output:
 *  [0, 6]
 *
 *  Explanation:
 *  The substring with start index = 0 is "cba", which is an anagram of "abc".
 *  The substring with start index = 6 is "bac", which is an anagram of "abc".
 *  Example 2:
 *
 *  Input:
 *  s: "abab" p: "ab"
 *
 *  Output:
 *  [0, 1, 2]
 *
 *  Explanation:
 *  The substring with start index = 0 is "ab", which is an anagram of "ab".
 *  The substring with start index = 1 is "ba", which is an anagram of "ab".
 *  The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FindAllAnagrams {
  public static void main(String[] args) {
    FindAllAnagrams faa = new FindAllAnagrams();
    System.out.println(
        "Anagram Indexes for abc in cbaebabacd " + faa.findAnagrams("cbaebabacd", "abc"));
    System.out.println("Anagram Indexes for ab in abab " + faa.findAnagrams("abab", "ab"));
  }

  /**
   * Find all anagram indexes of pattern in string Queue:
   * https://leetcode.com/problems/find-all-anagrams-in-a-string/
   *
   * <p>The logic is as follows:
   *
   * <p>Build a map for pattern chars and their counts
   *
   * <p>Scan the string and build a map for string chars and their counts
   *
   * <p>When string length so far is greater than pattern length
   *
   * <p>Remove one char from left in the string map
   *
   * <p>Compare both maps if they are equal we have an anagram at start index
   *
   * <p>Time complexity: O(n) where n is length of string
   *
   * <p>Space complexity: O(1) as we are using fixed space for maps
   *
   * @param s input string
   * @param p input pattern
   * @return list of starting indexes of anagrams of pattern in string
   */
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> indexes = new LinkedList<>();
    // Check for both string and pattern not to be null
    if (s != null && p != null) {
      // Get lengths
      int len = s.length();
      int pLen = p.length();
      // pattern length should be less than string
      if (pLen < len) {
        // Build map for pattern chars
        Map<Character, Integer> patternMap = new HashMap<>();
        for (char c : p.toCharArray()) {
          patternMap.put(c, patternMap.getOrDefault(c, 0) + 1);
        }

        // Map for string chars
        Map<Character, Integer> stringMap = new HashMap<>();
        // Scan the string
        for (int i = 0; i < len; ++i) {
          char c = s.charAt(i);
          // Start building map
          stringMap.put(c, stringMap.getOrDefault(c, 0) + 1);

          // if string length so far > pattern length then remove char from left in the string
          // creating a sliding window
          // This is reached when i = pattern length but it is 0 based index so we check for
          // so we are considering a char out of the window to be removed
          if (i >= pLen) {
            char ch = s.charAt(i - pLen);
            if (stringMap.get(ch) == 1) {
              stringMap.remove(ch);
            } else {
              stringMap.put(ch, stringMap.get(ch) - 1);
            }
          }

          // i = 2 and pattern length 3 and if both maps are equal we have anagram at start
          // else for any string greater than pattern length check both maps as we remove
          // one char from left in previous block of code when string length has crossed pattern
          // length
          if (patternMap.equals(stringMap)) {
            indexes.add(i - pLen + 1);
          }
        }
      }
    }
    return indexes;
  }
}
