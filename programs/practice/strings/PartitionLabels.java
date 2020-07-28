/*  A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
*
*
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

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.LinkedHashMap;

public class PartitionLabels {
    public static void main(String[] args) {
        System.out.println(PartitionLabels.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(PartitionLabels.partitionLabels("caedbdedda"));
        System.out.println(PartitionLabels.partitionLabels("aabbkdlaklasjdlsf"));
        System.out.println(PartitionLabels.partitionLabels("ajdlaksakfjlsjfhglaksdfsdlsdfgha"));
    }

    public static List<Integer> partitionLabels(String S) {
        List<Integer> ans = new LinkedList<>();
        if (S != null) {
            Map<Character, Integer> lastIndex = new LinkedHashMap<>();
            for (int i = 0; i < S.length(); ++i) {
                lastIndex.put(S.charAt(i), S.lastIndexOf(S.charAt(i)));
            }

            int boundary = 0;
            int start = 0;

            for (int i = 0; i < S.length(); ++i) {
                boundary = Math.max(lastIndex.get(S.charAt(i)), boundary);
                if (boundary == i) {
                    ans.add(boundary - start + 1);
                    start = i + 1;
                }
            }

        }
        return ans;
    }
}
