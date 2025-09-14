/*  A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part,
 *  and return a list of integers representing the size of these parts.
 *  LeetCode: https://leetcode.com/problems/partition-labels/
 *
 *  Example 1:
 *
 *  Input: S = "ababcbacadefegdehijhklij"
 *  Output: [9,7,8]
 *  Explanation:
 *  The partition is "ababcbaca", "defegde", "hijhklij".
 *  This is a partition so that each letter appears in at most one part.
 *  A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 */

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PartitionLabels {
  public static void main(String[] args) {
    System.out.println(PartitionLabels.partitionLabels("ababcbacadefegdehijhklij"));
    System.out.println(PartitionLabels.partitionLabels("caedbdedda"));
    System.out.println(PartitionLabels.partitionLabels("aabbkdlaklasjdlsf"));
    System.out.println(PartitionLabels.partitionLabels("ajdlaksakfjlsjfhglaksdfsdlsdfgha"));
  }

  /**
   * The idea is to use a sliding window approach. We maintain a boundary which is the farthest last
   * index of any character we have seen so far.
   *
   * <p>We also maintain a start index which marks the beginning of the current partition.
   *
   * <p>As we iterate through the string, we update the boundary to be the maximum of the current
   * boundary and the last index of the current character.
   *
   * <p>When the current index reaches the boundary, we have found a partition. We add the size of
   * the partition to the result list and update the start index to be the next index.
   *
   * <p>We continue this process until we have processed the entire string.
   *
   * @param S input string
   * @return list of integers representing the size of each partition
   */
  public static List<Integer> partitionLabels(String S) {
    List<Integer> ans = new LinkedList<>();
    if (S != null) {
      // Create Map of all unique characters in the string to their last index
      // in the string
      Map<Character, Integer> lastIndex = new LinkedHashMap<>();
      for (int i = 0; i < S.length(); ++i) {
        lastIndex.put(S.charAt(i), S.lastIndexOf(S.charAt(i)));
      }

      int boundary = 0;
      int start = 0;

      // Sliding window bounded by start and boundary
      for (int i = 0; i < S.length(); ++i) {
        // if current char's last index is greater than boundary then that is our
        // current boundary. i.e. We need to go a least to that index so that this
        // char does not appear again so it will be part of only one sub string
        boundary = Math.max(lastIndex.get(S.charAt(i)), boundary);
        // if boundary is equal to current index : We have reached a point
        // where not other character encountered so far has index greater than boundary or i
        // which means they won't appear in later part of the string. So include this string
        // and continue same logic:w
        if (boundary == i) {
          ans.add(boundary - start + 1);
          start = i + 1;
        }
      }
    }
    return ans;
  }
}
