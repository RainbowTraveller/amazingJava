/*
 * In a string S of lowercase letters, these letters form consecutive groups of the same character.
 *
 * For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".
 *
 * Call a group large if it has 3 or more characters.
 * We would like the starting and ending positions of every large group.
 *
 * The final answer should be in lexicographic order.
 *
 *
 * Example 1:
 *
 * Input: "abbxxxxzzy"
 * Output: [[3,6]]
 * Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
 * Example 2:
 *
 * Input: "abc"
 * Output: []
 * Explanation: We have "a","b" and "c" but no large group.
 * Example 3:
 *
 * Input: "abcdddeeeeaabbbcd"
 * Output: [[3,5],[6,9],[12,14]]
 */
import java.util.LinkedList;
import java.util.List;

public class LargeGroup {

  public static void main(String[] args) {
    String input1 = "abbxxxxzzy";
    System.out.println("Large groups in : " + input1 + " " + largeGroupPositions(input1));
    String input2 = "abc";
    System.out.println("Large groups in : " + input2 + " " + largeGroupPositions(input2));
    String input3 = "abcdddeeeeaabbbcd";
    System.out.println("Large groups in : " + input3 + " " + largeGroupPositions(input3));
  }

  public static List<List<Integer>> largeGroupPositions(String S) {
    List<List<Integer>> track = new LinkedList<List<Integer>>();
    if (S != null && S.length() >= 1) {

      int start = 0;
      int len = 1;
      char c = S.charAt(0);
      for (int i = 1; i < S.length(); ++i) {
        if (c == S.charAt(i)) {
          len++;
        } else {
          if (len >= 3) {
            List<Integer> curr = new LinkedList<Integer>();
            curr.add(start);
            curr.add(i - 1);
            track.add(curr);
          }
          start = i;
          c = S.charAt(i);
          len = 1;
        }
      }
      if (len >= 3) {
        List<Integer> curr = new LinkedList<Integer>();
        curr.add(start);
        curr.add(S.length() - 1);
        track.add(curr);
      }
    }
    return track;
  }
}
