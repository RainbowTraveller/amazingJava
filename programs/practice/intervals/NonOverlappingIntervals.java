/*
Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the
intervals non-overlapping.

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
import java.util.Comparator;

public class NonOverlappingIntervals {
  public static void main(String[] args) {
    int count =
        NonOverlappingIntervals.eraseOverlapIntervals(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 3}});
    System.out.println("Count : " + count);
    count =
        NonOverlappingIntervals.eraseOverlapIntervalsGreedy(
            new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 3}});
    System.out.println("Greedy Count : " + count);
  }

  /**
   * The approach is to sort the intervals based on their end time. Then we iterate through the
   * sorted intervals and keep track of the end time of the last added interval. If the start time
   * of the current interval is greater than or equal to the end time of the last added interval, we
   * can add this interval to our non-overlapping set. Otherwise, we need to remove this interval.
   * We count the number of intervals we need to remove and return that count.
   *
   * <p>Time Complexity : O(n log n) + O(n) ~ O(n log n) Space Complexity : O(1)
   *
   * @param intervals 2D array of intervals
   * @return minimum number of intervals to remove to make the rest of the intervals non-overlapping
   */
  public static int eraseOverlapIntervals(int[][] intervals) {
    if (intervals.length == 0) {
      return 0;
    }
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
        // hence increment the count
        count++;
      }
    }
    return count;
  }

  /**
   * The optimal approach is a greedy algorithm.
   *
   * <p>Algorithm Sort the intervals: Sort the intervals based on their end times in ascending
   * order. If two intervals have the same end time, the order doesn't matter. Sorting by end time
   * is the key insight for this greedy strategy. It ensures that when we select a non-overlapping
   * interval, we choose the one that finishes earliest, leaving the maximum room for subsequent
   * intervals.
   *
   * <p>Iterate and Count:
   *
   * <p>Initialize a count of non-overlapping intervals to 1 (since the first interval in the sorted
   * list is always part of the solution).
   *
   * <p>Initialize end to the end time of the first interval.
   *
   * <p>Iterate through the rest of the intervals (from the second one to the end).
   *
   * <p>For each current interval, check if its start time is greater than or equal to the end time
   * of the last selected non-overlapping interval.
   *
   * <p>If intervals[i][0] >= end, it means the current interval does not overlap with the previous
   * selected one. Increment the count and update end to the end time of the current interval (end =
   * intervals[i][1]).
   *
   * <p>If intervals[i][0] < end, it means the intervals overlap. To make the remaining intervals
   * non-overlapping, we must remove either the current interval or the last selected one. Our
   * greedy choice of sorting by end time and selecting the earliest-finishing interval ensures we
   * can simply skip the current overlapping interval.
   *
   * <p>Calculate Removals: The minimum number of intervals to remove is intervals.length - count.
   */
  public static int eraseOverlapIntervalsGreedy(int[][] intervals) {
    if (intervals.length == 0) {
      return 0;
    }

    // Sort intervals by their end times. This is the greedy choice.
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

    int end = intervals[0][1];
    int count = 1;

    // Iterate through the rest of the intervals.
    for (int i = 1; i < intervals.length; i++) {
      // If the current interval does not overlap with the last selected one,
      // we can include it in our non-overlapping set.
      if (intervals[i][0] >= end) {
        end = intervals[i][1];
        count++;
      }
    }

    // The minimum number of removals is the total number of intervals
    // minus the maximum number of non-overlapping intervals found.
    return intervals.length - count;
  }
}
