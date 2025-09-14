/*
 *  LeeCode Link : https://leetcode.com/problems/minimum-window-substring/
 *  Minimum Window Substring problem
 *  Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 *   Example:
 *
 *   Input: S = "ADOBECODEBANC", T = "ABC"
 *   Output: "BANC"
 *   Note:
 *
 *   If there is no such window in S that covers all characters in T, return the empty string "".
 *   If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MinimumWindowSubstring {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    String pattern = sc.nextLine();
    /*
     * String input = "asdbsdcsdleavbfc"; String input = "asdbsdcsdleavbfca"; String
     * pattern = "abc";
     */

    String smallestSubSet = findSmallestContainerSubStringOptimal(input, pattern);
    System.out.println("Smallest Container Found : " + smallestSubSet);
    smallestSubSet = minWindow(input, pattern);
    System.out.println("Smallest Container Found : " + smallestSubSet);
  }

  /** Helper function to check if the character and frequency matches with the pattern */
  public static boolean isWindowContainingAllChars(String window, Map<Character, Integer> tracker) {
    Map<Character, Integer> currWindowStatus = new HashMap<>();

    for (int i = 0; i < window.length(); i++) {
      int frequency = currWindowStatus.getOrDefault(window.charAt(i), 0);
      currWindowStatus.put(window.charAt(i), frequency + 1);
    }

    for (char k : tracker.keySet()) {
      int frequency = currWindowStatus.getOrDefault(k, 0);
      if (frequency == 1) {
        currWindowStatus.remove(k);
      } else {
        currWindowStatus.put(k, frequency - 1);
      }
    }

    return currWindowStatus.size() == 0;
  }

  /**
   * Smarter approach using sliding window technique Time complexity : O(N) <br>
   * Space complexity : O(N) for the map
   *
   * @param candidate String in which we are searching for the pattern
   * @param pattern Pattern string whose characters need to be found in the candidate string
   * @return Smallest substring containing all characters from the pattern
   */
  public static String findSmallestContainerSubStringOptimal(String s, String t) {
    // Pair class to hold the index and character
    class Pair<U, V> {
      public U first;
      public V second;

      public Pair(U first, V second) {
        this.first = first;
        this.second = second;
      }

      public U getKey() {
        return first;
      }

      public V getValue() {
        return second;
      }
    }

    // Edge cases
    if (s.length() == 0 || t.length() == 0) {
      return "";
    }

    // Dictionary which keeps a count of all the unique characters in t.
    Map<Character, Integer> referece = new HashMap<Character, Integer>();

    // Build the frequency map for the pattern string
    for (int i = 0; i < t.length(); i++) {
      int count = referece.getOrDefault(t.charAt(i), 0);
      referece.put(t.charAt(i), count + 1);
    }

    // Number of unique characters in t, which need to be present in the desired window.
    int required = referece.size();

    // Filter all the characters from s into a new list along with their index.
    // The filtering criteria is that the character should be present in t.
    List<Pair<Integer, Character>> filteredS = new ArrayList<Pair<Integer, Character>>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (referece.containsKey(c)) {
        filteredS.add(new Pair<Integer, Character>(i, c));
      }
    }

    int l = 0, r = 0, actual = 0;
    // Dictionary which keeps a count of all the unique characters in the current window.
    Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();
    // ans list of the form (window length, left, right)
    int[] ans = {-1, 0, 0};

    // Look for the first character from t in s to initialize the left pointer
    while (l < s.length() && !referece.containsKey(s.charAt(l))) {
      l++;
      r++;
    }
    // Look for the characters only in the filtered list instead of entire s.
    // This helps to reduce our search.
    // Hence, we follow the sliding window approach on as small list.

    while (r < filteredS.size()) {
      char c = filteredS.get(r).getValue();
      int count = windowCounts.getOrDefault(c, 0);
      windowCounts.put(c, count + 1);

      // Check if the frequency of the current character added equals to the
      // desirable frequency in t
      if (referece.containsKey(c) && windowCounts.get(c).intValue() == referece.get(c).intValue()) {
        actual++;
      }

      // Try and contract the window till the point where it ceases to be 'desirable'.
      while (l <= r && actual == required) {
        c = filteredS.get(l).getValue();

        // Save the smallest window until now.
        int end = filteredS.get(r).getKey();
        int start = filteredS.get(l).getKey();
        if (ans[0] == -1 || end - start + 1 < ans[0]) {
          ans[0] = end - start + 1;
          ans[1] = start;
          ans[2] = end;
        }

        windowCounts.put(c, windowCounts.get(c) - 1);
        if (referece.containsKey(c)
            && windowCounts.get(c).intValue() < referece.get(c).intValue()) {
          actual--;
        }
        l++;
      }
      r++;
    }
    return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
  }

  /**
   * LeetCode solution for the same problem
   * https://leetcode.com/problems/minimum-window-substring/solution/
   *
   * <p>The logic is similar to the above optimal solution Time complexity : O(N) <br>
   * Space complexity : O(N) for the map
   *
   * @param s Source string
   * @param t Target string
   * @return Minimum window in S which will contain all the characters in T
   */
  public static String minWindow(String s, String t) {
    String result = "";

    if (s != null && t != null && !s.isEmpty() && !t.isEmpty()) {
      // To track the minimum length
      int minLength = Integer.MAX_VALUE;
      // To track the start and end index of the minimum length substring
      int shortLeft = 0, shortRight = 0;
      // Controlling window
      int left = 0, right = 0;
      // Create Map of characters to count from the target string
      Map<Character, Integer> ref = new HashMap<>();
      // Build the frequency map for the pattern string
      for (int i = 0; i < t.length(); i++) {
        char curr = t.charAt(i);
        ref.put(curr, ref.getOrDefault(curr, 0) + 1);
      }
      // Number of unique characters in t, which need to be present in the desired window.
      int required = ref.size();
      // To keep track of how many unique characters in t are present in the current window
      int actual = 0;
      // Maintain a similar map for the source string
      Map<Character, Integer> tracker = new HashMap<>();
      // Scan the source
      while (right < s.length()) {
        // Get current char
        char candidate = s.charAt(right);
        // Update it's count in the map if present in the target string
        if (ref.containsKey(candidate)) {
          tracker.put(candidate, tracker.getOrDefault(candidate, 0) + 1);
          // If the frequency matches with the target string, increment actual
          if (tracker.get(candidate) == ref.get(candidate)) {
            actual++;
          }
        }

        while (left <= right && required == actual) {
          // Update the minimum length and the start and end index
          if (minLength > right - left + 1) {
            minLength = right - left + 1;
            shortLeft = left;
            shortRight = right;
          }

          // Try and contract the window till the point it ceases to be 'desirable'
          char leftChar = s.charAt(left);
          // Update the map
          if (tracker.containsKey(s.charAt(left))) {

            tracker.put(leftChar, tracker.get(leftChar) - 1);
            // If the frequency falls below the required count, decrement actual
            // as the window is no longer desirable
            // This will break the while loop
            if (tracker.get(leftChar) < ref.get(leftChar)) {
              // This is important check, as the left char may not be
              // if the target string and hence not present in the map
              // So actual should not be decremented in that case
              // This will come into picture when the count actually is less and does not matter if
              // it is greater than the required count
              actual--;
            }
          }
          left++;
        }
        right++;
      }
      result = minLength == Integer.MAX_VALUE ? "" : s.substring(shortLeft, shortRight + 1);
    }
    return result;
  }
}
