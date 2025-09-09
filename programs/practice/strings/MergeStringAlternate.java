/**
 * 1768. Merge Strings Alternately
 *
 * <p>https://leetcode.com/problems/merge-strings-alternately/
 *
 * <p>You are given two strings word1 and word2. Merge the strings by adding letters in alternating
 * order, starting with word1. If a string is longer than the other, append the additional letters
 * onto the end of the merged string.
 *
 * <p>Return the merged string.
 *
 * <p>Example 1:
 *
 * <p>Input: word1 = "abc", word2 = "pqr"
 *
 * <p>Output: "apbqcr"
 *
 * <p>Explanation: The merged string will be merged as so:
 *
 * <p>word1: a b c
 *
 * <p>word2: p q r
 *
 * <p>merged: a p b q c r
 *
 * <p>Example 2:
 *
 * <p>Input: word1 = "ab", word2 = "pqrs"
 *
 * <p>Output: "apbqrs"
 *
 * <p>Explanation: Notice that as word2 is longer, "rs" is appended to the end.
 *
 * <p>word1: a b
 *
 * <p>word2: p q r s
 *
 * <p>merged: a p b q r s
 *
 * <p>Example 3:
 *
 * <p>Input: word1 = "abcd", word2 = "pq"
 *
 * <p>Output: "apbqcd"
 *
 * <p>Explanation: Notice that as word1 is longer, "cd" is appended to the end.
 *
 * <p>word1: a b c d
 *
 * <p>word2: p q
 *
 * <p>merged: a p b q c d
 *
 * <p>Constraints:
 *
 * <p>1 <= word1.length, word2.length <= 100
 *
 * <p>word1 and word2 consist of lowercase English letters.
 */
public class MergeStringAlternate {
  public static void main(String[] args) {
    MergeStringAlternate msa = new MergeStringAlternate();
    System.out.println(msa.mergeAlternately("abc", "pqr"));
    System.out.println(msa.mergeAlternately("ab", "pqrs"));
    System.out.println(msa.mergeAlternately("zhriq", "af"));
  }

  /**
   * Merge two strings alternately.
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: O(n)
   *
   * @param word1 First string
   * @param word2 Second string
   * @return Merged string
   */
  public String mergeAlternately(String word1, String word2) {
    StringBuffer buffer = new StringBuffer();
    int l1 = word1.length();
    int l2 = word2.length();
    int i = 0, j = 0;
    while (i < l1 && j < l2) {
      buffer.append(word1.charAt(i));
      buffer.append(word2.charAt(j));
      i++;
      j++;
    }
    word1 = l1 > l2 ? word1 : word2;
    int index = 0;
    if (i == l1) {
      index = j;
    } else {
      index = i;
    }
    buffer.append(word1.substring(index));
    return buffer.toString();
  }
}
