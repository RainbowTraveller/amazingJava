/*
 *   Given a string of lowercase alphabets, count all possible substrings (not necessarily distinct) that has exactly k distinct characters.
 *   Examples:
 *   Input: abc, k = 2
 *   Output: 2
 *   Possible substrings are {"ab", "bc"} // ab has 2 distinct characters a and b, bc has 2 distinct character b and c
 *   Input: aba, k = 2
 *   Output: 3
 *   Possible substrings are {"ab", "ba", "aba"}
 *   Input: aa, k = 1
 *   Output: 3
 *   Possible substrings are {"a", "a", "aa"}
 *
 **/

public class KUniqueCharsSubString {

  public static void main(String[] args) {
    String input = args[0];
    int num = Integer.valueOf(args[1]);
    // Print all substrings with k unique characters
    getKUniqueCharSubString(input, num);
    // Count of substrings with exactly k distinct characters
    System.out.println(
        "Count of substrings with exactly "
            + num
            + " distinct characters: "
            + countSubstrings(input, num));
    // Count of substrings with exactly k distinct characters using optimized approach
    System.out.println(
        "Count of substrings with exactly "
            + num
            + " distinct characters (optimized): "
            + countSubstringsWithExactlyKDistinct(input, num));
  }

  /**
   * Function to get all substrings with k unique characters Logic: Two nested loops to generate all
   * possible substrings Inner loop maintains a count of unique characters in the substring When the
   * count matches k, print the substring
   *
   * <p>Time Complexity: O(n^3) where n is the length of the input string
   *
   * <p>Space Complexity: O(n) where n is the length of the input string
   *
   * @param input input string
   * @param k number of unique characters
   * @return void
   */
  public static void getKUniqueCharSubString(String input, int k) {
    if (input != null) {
      int len = input.length();
      StringBuffer buffer = new StringBuffer();
      for (int i = 0; i < len; ++i) {
        // Resetting
        int count = 0;
        buffer.delete(0, buffer.length());
        for (int j = i; j < len; ++j) {
          char current = input.charAt(j);
          if (buffer.toString().indexOf(current) < 0) {
            // Check if a character is already present in the string
            // if not we found unique char in a string so increment counter
            count++;
          }
          buffer.append(current);
          if (count == k) { // if unique char count is equal to desired value print string
            System.out.println(buffer);
          }
        }
      }
    }
  }

  /**
   * Function to count all possible substrings (not necessarily distinct) that has exactly k
   * distinct characters.
   *
   * <p>Time Complexity: O(n^2) where n is the length of the input string
   *
   * <p>Space Complexity: O(k) where k is the number of distinct characters
   *
   * @param s input string
   * @param k number of distinct characters
   * @return count of substrings with exactly k distinct characters
   */
  public static int countSubstrings(String s, int k) {
    if (s == null || k <= 0) {
      return 0;
    }

    int n = s.length();
    int count = 0;

    // Outer loop to pick a starting point
    for (int i = 0; i < n; i++) {
      Set<Character> distinctChars = new HashSet<>();

      // Inner loop to form substrings starting from i
      for (int j = i; j < n; j++) {
        distinctChars.add(s.charAt(j));

        // Check the size of the distinct character set
        if (distinctChars.size() == k) {
          count++;
        }
        // If the number of distinct characters exceeds k, we can break the inner loop
        // because any further substrings will also have > k distinct characters.
        if (distinctChars.size() > k) {
          break;
        }
      }
    }
    return count;
  }

  /**
   * Counts the number of substrings with exactly k distinct characters. This is achieved by finding
   * the count of substrings with at most k distinct characters and subtracting the count of
   * substrings with at most k-1 distinct characters.
   *
   * <p>Time Complexity: O(n) where n is the length of the input string
   *
   * <p>Space Complexity: O(k) where k is the number of distinct characters
   */
  public static int countSubstringsWithExactlyKDistinct(String s, int k) {
    if (k <= 0) {
      return 0;
    }
    /*
     * <p>Mathematical Explanation:
     * This principle is based on the Principle of Inclusion-Exclusion.
     * Let's define two sets of substrings for a given string:
     * <p> Set A: The set of all substrings with at most k distinct characters.
     * <p> Set B: The set of all substrings with at most k-1 distinct characters.
     * <p> The problem asks for the number of substrings with exactly k distinct characters. Let's call this Set C.
     * A substring with exactly k distinct characters is included in Set A (since k is at most k) but is not included in Set B (since k is not at most k-1).
     * Therefore, the number of substrings in Set C is the number of substrings in Set A minus the number of substrings that are in both Set A and Set B.
     */
    return countSubstringsWithAtMostKDistinct(s, k) - countSubstringsWithAtMostKDistinct(s, k - 1);
  }

  /**
   * Counts the number of substrings with at most k distinct characters. This uses a sliding window
   * technique.
   */
  private static int countSubstringsWithAtMostKDistinct(String s, int k) {
    if (s == null || s.isEmpty() || k < 0) {
      return 0;
    }

    int count = 0;
    int left = 0;
    Map<Character, Integer> charCount = new HashMap<>();

    for (int right = 0; right < s.length(); right++) {
      char c = s.charAt(right);
      charCount.put(c, charCount.getOrDefault(c, 0) + 1);

      // Shrink the window from the left if the number of distinct characters exceeds k
      while (charCount.size() > k) {
        char leftChar = s.charAt(left);
        charCount.put(leftChar, charCount.get(leftChar) - 1);
        if (charCount.get(leftChar) == 0) {
          charCount.remove(leftChar);
        }
        left++;
      }
      // All substrings ending at 'right' with at most k distinct characters are counted
      // Cossider current window as [ a, b, c, d, e] with k = 5
      // substrings ending at e are : e, de, cde, bcde, abcde
      // The key insight is that the calculation count += (right - left + 1) correctly and
      // efficiently counts all new valid substrings that are formed with the addition of the new
      // character at the right pointer.
      // The previously counted substrings (like a, ab, abc) were already accounted for in earlier
      // iterations when the right pointer was at a previous position.

      // Here's a breakdown of why this works:
      //
      // When the right pointer is at 'e', the algorithm is only concerned with the substrings that
      // end at 'e'. All substrings that ended before 'e' (like a, ab, abc, abcd, etc.) have already
      // been counted in previous loops.
      //
      // The current window is [a, b, c, d, e]. The algorithm guarantees that the number of distinct
      // characters in this window is <= k because of the while loop that adjusts the left pointer.
      // Since the entire window is valid, any substring that ends at the current right ('e') and
      // starts anywhere within the valid window [left, right] will also be valid.
      //
      // Let's revisit your example [a, b, c, d, e], k=5, with right at 'e'. The left pointer is at
      // 'a'.
      //
      // The length of the window is (4 - 0 + 1) = 5.
      //
      // This 5 represents the number of new valid substrings ending at 'e':
      //
      // [e] (starts at right, ends at right)
      //
      // [d, e] (starts at right-1, ends at right)
      //
      // [c, d, e] (starts at right-2, ends at right)
      //
      // [b, c, d, e] (starts at right-3, ends at right)
      //
      // [a, b, c, d, e] (starts at left, ends at right)
      //
      // This is the power of the sliding window technique: it avoids re-evaluating every substring
      // and instead leverages the fact that a valid window contains many valid smaller substrings,
      // which can be counted with a simple arithmetic operation.
      count += (right - left + 1);
      // In short this is counted for each window as it expands
    }
    return count;
  }
}
