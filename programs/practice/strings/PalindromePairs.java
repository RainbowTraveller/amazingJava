/*
 *   Palindrome pairs - Hard
 *   https://leetcode.com/problems/palindrome-pairs/
 *
 *   Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 *   Example 1:
 *
 *   Input: ["abcd","dcba","lls","s","sssll"]
 *   Output: [[0,1],[1,0],[3,2],[2,4]]
 *   Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 *   Example 2:
 *
 *   Input: ["bat","tab","cat"]
 *   Output: [[0,1],[1,0]]
 *   Explanation: The palindromes are ["battab","tabbat"]
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PalindromePairs {

  public static void main(String[] args) {
    System.out.println(palindromePairs(new String[] {"abcd", "dcba", "lls", "s", "sssll"}));
    System.out.println(palindromePairs(new String[] {"bat", "tab", "cat"}));
  }

  /**
   * The idea is to split each word into all possible prefix and suffix pairs and check if either of
   * them is a palindrome.
   *
   * <p>If prefix is a palindrome, then we need to find if there is a word in the list which is the
   * reverse of the suffix.
   *
   * <p>If suffix is a palindrome, then we need to find if there is a word in the list which is the
   * reverse of the prefix.
   *
   * <p>We can use a hashmap to store the words and their indices for O(1) lookup.
   *
   * <p>We need to take care of the case when the suffix is empty, as the entire word is a
   * palindrome in that case.
   *
   * <p>We also need to ensure that we do not use the same word twice by checking the indices.
   *
   * <p>The time complexity is O(n * k^2) where n is the number of words and k is the maximum length
   * of a word.
   *
   * <p>The space complexity is O(n) for the hashmap.
   */
  public static List<List<Integer>> palindromePairs(String[] words) {
    // Resultant list of palindrome pairs
    List<List<Integer>> result = new LinkedList<>();
    // Map to store word and its index
    Map<String, Integer> tracker = new HashMap<>();
    if (words != null && words.length > 0) {
      // First map string to corr. index
      for (int i = 0; i < words.length; i++) {
        tracker.put(words[i], i);
      }

      // System.out.println("TRACKER :" + tracker);
      for (int i = 0; i < words.length; ++i) {

        for (int j = 0; j <= words[i].length(); ++j) {

          // "" --> a --> ab ---> abc ---> abcd
          String prefix = words[i].substring(0, j);
          // abcd --> bcd --> cd --> d --> ""
          String suffix = words[i].substring(j);

          /*
           * 1. Get a prefix and suffix pair e.g. "a" and "bcd"
           * 2. now check if prefix is palindrome and reverse of suffix is present in the map i.e. "dcb" then "abcd"
           *    can be used as a suffix to complete a palindrome "dcbabcd"
           * 3. Similarly if suffix is palindrome say "d" and reverse of prefix i.e. abc => cba is present in the map
           *    then we can use current string as a prefix to a new palindrome viz. "abcdcba"
           */

          if (isPalindrome(prefix)) {
            // System.out.println("Prefix :" + prefix);
            String reversedSuffix = new StringBuffer(suffix).reverse().toString();
            if (tracker.containsKey(reversedSuffix) && tracker.get(reversedSuffix) != i) {
              // System.out.println("Suffix :" + suffix);
              // System.out.println("Prefix :" + prefix);
              recordPalindromePair(tracker.get(reversedSuffix), i, result);
            }
          }

          if (isPalindrome(suffix)) {
            // System.out.println("Suffix :" + suffix);
            // System.out.println();
            String reversedPrefix = new StringBuffer(prefix).reverse().toString();
            // Suffix empty indicates the entire string, but we already checked it when
            // prefix was empty to avoid checking it again which may result in duplicate pair
            if (tracker.containsKey(reversedPrefix)
                && tracker.get(reversedPrefix) != i
                && !suffix.isEmpty()) {
              // System.out.println("Suffix :" + suffix);
              // System.out.println("Prefix :" + prefix);
              recordPalindromePair(i, tracker.get(reversedPrefix), result);
            }
          }
        }
      }
    }
    return result;
  }

  /*
   *	Record index pair in the list
   */
  public static void recordPalindromePair(int prefix, int suffix, List<List<Integer>> result) {
    List<Integer> pair = new LinkedList<Integer>();
    pair.add(prefix);
    pair.add(suffix);
    result.add(pair);
  }

  /*
   * Check if a given string is palindrome
   */

  public static boolean isPalindrome(String b) {
    if (b != null) {
      int start = 0;
      int end = b.length() - 1;
      while (start < end) {
        if (b.charAt(start) != b.charAt(end)) {
          return false;
        }
        start++;
        end--;
      }
      return true;
    }
    return false;
  }
}
