/*
Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.



Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.



Algorithm

Sort intervals according to the end times.

Initialize an answer variable ans = 0 and an integer k to represent the most recent end time. k should be initialized to a small value like INT_MIN.

Iterate over the intervals. For each interval:
    . If the start time is greater than or equal to k, update k to the end time of the current interval.
    . Otherwise, increment ans.
Return ans.
*/
import java.util.Arrays;

public class NonOverlappingIntervals {
  public static void main(String[] args) {
    int count =
        NonOverlappingIntervals.eraseOverlapIntervals(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 3}});
    System.out.println("Count : " + count);
  }

  public static int eraseOverlapIntervals(int[][] intervals) {
    // Do not sort this way
    // Arrays.sort(intervals, (i1, i2) -> (i1[0] == i2[0] ? i1[1] - i2[1] : i1[0] - i2[0]));
    Arrays.sort(intervals, (i1, i2) -> (i1[1] - i2[1]));
    // OR
    // Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
    int count = 0;
    // We always start with min value
    // Consider this as the end of imaginary previous interval
    int end = Integer.MIN_VALUE;
    for (int i = 0; i < intervals.length; ++i) {
      int x = intervals[i][0];
      int y = intervals[i][1];
      // We always compare the start of the next interval and the
      // end of the previous interval
      if (x >= end) {
        // if the start of the current interval is
        // greater than the end of the previous interval
        // Clearly there is no overlapping,
        // so no interval to drop
        // Mark end to be the end of the current interval
        end = y;
      } else {
        // If overlapping then drop this interval
        // hence increament the count
        count++;
      }
    }
    return count;
  }
}
