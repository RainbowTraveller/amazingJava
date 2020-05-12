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


import java.util.PriorityQueue;
import java.util.List;
import java.util.LinkedList;

public class IntervalsFreeTime {
    public static void main(String[] args) {

    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> free = new LinkedList<>();

        // First sort all the schedule events by starting time and then ending time if
        // start time is same
        PriorityQueue<Interval> sorted = new PriorityQueue<Interval>(
                (i1, i2) -> i1.start == i2.start ? i1.end - i2.end : i1.start - i2.start);

        // Populate the heap
        // This will contains all busy intervals in sorted order
        for (List<Interval> busy : schedule) {
            for (Interval currInterval : busy) {
                sorted.add(currInterval);
            }
        }

        //Get the end time of the first interval
        Interval first = sorted.poll();
        int end = first.end;
        while (!sorted.isEmpty()) {
            //Get subsequent busy interval
            Interval curr = sorted.poll();
            //if there is a gap between previous end and current start => free time, record it
            //and then now current end becomes new end,which indicates the end of the current interval
            if (end < curr.start) {
                free.add(new Interval(end, curr.start));
                end = curr.end;
            } else {
                // The intervals are sorted based on start and then time.
                // so either 2 starts are equal or curr.start > previous start
                // here an interval may start later than previous end but also end after previous end...so we need to
                // update end value with max of end times noticed so far
                end = Math.max(end, curr.end);
            }
        }
        return free;
    }
}
