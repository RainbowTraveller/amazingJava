/*  We are given a list schedule of employees, which represents the working time for each employee.
 *
 *  Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 *  Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 *  Example 1:
 *
 *  Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 *  Output: [[3,4]]
 *  Explanation: There are a total of three employees, and all common
 *  free time intervals would be [-inf, 1], [3, 4], [10, inf].
 *  We discard any intervals that contain inf as they aren't finite.
 *  Example 2:
 *
 *  Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 *  Output: [[5,6],[7,9]]
 */

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class EmployeeFreeTime {
  public static void main(String[] args) {
    List<List<Interval>> employeeSchedule = new LinkedList<>();

    Interval i1 = new Interval(1, 2);
    Interval i2 = new Interval(5, 6);
    List<Interval> list1 = new LinkedList<>();
    list1.add(i1);
    list1.add(i2);
    employeeSchedule.add(list1);

    List<Interval> list2 = new LinkedList<>();
    Interval i3 = new Interval(1, 3);
    list2.add(i3);
    employeeSchedule.add(list2);

    List<Interval> list3 = new LinkedList<>();
    Interval i4 = new Interval(4, 10);
    list3.add(i4);
    employeeSchedule.add(list3);
    //        Interval i5 = new Interval();
    //        Interval i6 = new Interval();
    List<Interval> freeTime = employeeFreeTime(employeeSchedule);
    freeTime.forEach(System.out::println);
  }

  /** Definition for an interval. */
  private static class Interval {
    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    public Interval(int start, int end) {
      if (start < end) {
        this.start = start;
        this.end = end;
      }
    }

    public String toString() {
      return new String(" { start : " + start + " end : " + end + " }\n");
    }
  }

  /**
   * The approach is to use a min-heap to sort all the busy intervals based on start time and then
   * end time if start times are same. Then we iterate through the sorted busy intervals and look
   * for gaps between the end of the previous interval and the start of the current interval. If a
   * gap is found, it is recorded as a free time interval.
   *
   * <p>Time complexity : O(N log N) where N is the total number of intervals in the input. We add
   * all the intervals to a min-heap and then process each interval exactly once.
   *
   * <p>Space complexity : O(N) the space used by the min-heap.
   */
  public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    List<Interval> free = new LinkedList<>();

    // First sort all the schedule events by starting time and then ending time if
    // start time is same
    PriorityQueue<Interval> sorted =
        new PriorityQueue<Interval>(
            (i1, i2) -> i1.start == i2.start ? i1.end - i2.end : i1.start - i2.start);

    // Populate the heap
    // This will contains all busy intervals in sorted order
    for (List<Interval> busy : schedule) {
      for (Interval currInterval : busy) {
        sorted.add(currInterval);
      }
    }

    // Get the end time of the first interval
    Interval first = sorted.poll();
    int end = first.end;
    while (!sorted.isEmpty()) {
      // Get subsequent busy interval
      Interval curr = sorted.poll();
      // if there is a gap between previous end and current start => free time, record it
      // and then now current end becomes new end,which indicates the end of the current interval
      if (end < curr.start) {
        free.add(new Interval(end, curr.start));
        // end = curr.end;
      } else {
        // The intervals are sorted based on start and then time.
        // so either 2 starts are equal or curr.start > previous start
        // here an interval may start later than previous end but also end after previous end...so
        // we need to
        // update end value with max of end times noticed so far
        // end = Math.max(end, curr.end);
      }
      // Actually this can be common step as we have sorted the busy intervals based on start or if
      // they are equal based on end times
      // 1. start times are equal : end <= curr.end [4,5] [4,5], [4,7]
      // 2. start times are not equal : end can be >, < or = but we need to consider max. [1,3],
      // [4,5] or [1,6], [4,5] or [1, 6], [4,6]
      end = Math.max(end, curr.end);
    }
    return free;
  }
}
