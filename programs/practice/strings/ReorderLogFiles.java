import java.util.Arrays;

/*
 *  leetcode Link: https://leetcode.com/problems/reorder-data-in-log-files/
 *  Difficulty: Easy
 *
 *  You have an array of logs.  Each log is a space delimited string of words.
 *
 *  For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 *  Each word after the identifier will consist only of lowercase letters, or;
 *  Each word after the identifier will consist only of digits.
 *  We will call these two varieties of logs letter-logs and digit-logs.
 *  It is guaranteed that each log has at least one word after its identifier.
 *
 *  Reorder the logs so that all of the letter-logs come before any digit-log.
 *  The letter-logs are ordered lexicographically ignoring identifier,
 *  with the identifier used in case of ties. The digit-logs should be put in their original order.
 *
 *  Return the final order of the logs.
 *
 *
 *  Example 1:
 *
 *  Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 *  Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 *
 * Asked in Am : identifiers were alphanumeric and numeric instead only letters and digits
 */
public class ReorderLogFiles {
  public static void main(String[] args) {
    String[] input = {"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};
    reorderLogFiles(input);
    System.out.println(Arrays.asList(input));
  }

  /**
   * The comparator logic:
   *
   * <p>1. Split each log into two parts: the identifier and the rest of the log.
   *
   * <p>2. Determine if each log is a digit-log or a letter-log by checking the first character of
   * the rest of the log.
   *
   * <p>3. If both logs are letter-logs, compare them lexicographically by their content (the rest
   * of the log). If they are identical, use the identifier to break the tie.
   *
   * <p>4. If one log is a letter-log and the other is a digit-log, the letter-log should come
   * first.
   *
   * <p>5. If both logs are digit-logs, maintain their original order by returning 0.
   *
   * <p>This ensures that all letter-logs are sorted before digit-logs, with letter-logs sorted
   * lexicographically and digit-logs retaining their original order.
   *
   * <p>Time Complexity: O(NlogN∗M)
   *
   * <p>The primary operation is sorting the array of logs. Java's Arrays.sort() has a time
   * complexity of O(NlogN) comparisons, where N is the number of logs.
   *
   * <p>Each comparison inside the Comparator involves string splitting and comparison, which takes
   * up to O(M) time, where M is the maximum length of a log string.
   *
   * <p>Therefore, the total time complexity is the number of comparisons multiplied by the time for
   * each comparison, resulting in O(NlogN∗M).
   *
   * <p>Space Complexity: O(1) if we ignore the input and output storage, as the sorting is done in
   * place.
   *
   * @param logs array of log strings to be reordered
   */
  public static void reorderLogFiles(String[] logs) {
    Arrays.sort(
        logs,
        (log1, log2) -> {
          String[] log1Details = log1.split("\s+", 2);
          String[] log2Details = log2.split("\s+", 2);
          String key1 = log1Details[0];
          String key2 = log2Details[0];
          String value1 = log1Details[1];
          String value2 = log2Details[1];

          boolean isDigitl1 = Character.isDigit(value1.charAt(0));
          boolean isDigitl2 = Character.isDigit(value2.charAt(0));
          if (!isDigitl1 && !isDigitl2) {
            return value1.compareTo(value2) == 0 ? key1.compareTo(key2) : value1.compareTo(value2);
          }
          // 0	: if (x==y)
          // 1	: if (x > y)
          // -1: if (x < y)
          // True : True ==> both digits so return 0, order as is
          // True : False ==> log2 should appear before hence log1 > log2
          // False: True  ==> log1 should appear before hence log1 < log2
          return isDigitl1 ? (isDigitl2 ? 0 : 1) : -1;
        });
  }
}
