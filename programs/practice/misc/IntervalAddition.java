import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

/**
 *  Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *  You may assume that the intervals were initially sorted according to their start times.
 *  Example 1:
 *  Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 *  Output: [[1,5],[6,9]]
 *  Example 2:
 *  Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 *  Output: [[1,2],[3,10],[12,16]]
 *  Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */

class IntervalAddition {
    public static void main( String[] args ) {

        List<Interval> intervals = new LinkedList<Interval>();
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(6, 8));
        intervals.add(new Interval(9, 10));
        intervals.add(new Interval(11, 15));
        intervals.add(new Interval(16, 18));
        intervals.add(new Interval(19, 22));
        intervals.add(new Interval(23, 25));
        intervals.add(new Interval(26, 28));
        System.out.println("Before Insertion of new interval");
        printIntervals(intervals);
        System.out.println();
        System.out.println("Please enter start and end of new interval : ");

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter start : ");
        int start = sc.nextInt();
        System.out.println("Please enter end: ");
        int end  = sc.nextInt();
        sc.close();

        intervals  = insert(intervals, new Interval(start, end));
        System.out.println("After Insertion of new interval : ");
        printIntervals(intervals);
        System.out.println();
    }

    public static void printIntervals(List<Interval> intervals) {
        for(Interval i : intervals) {
            System.out.print(i + " ");
        }
    }

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(intervals != null && newInterval != null) {
            List<Interval> desired = new LinkedList<Interval>();

            int i = 0;
            //So this new interval will be inserted at a place where the end < newInterval.start
            //Till that point insert the intervals into new desired list
            while(i < intervals.size() && intervals.get(i).end < newInterval.start) {
                desired.add(new Interval(intervals.get(i).start, intervals.get(i).end));
                i++;
            }
            //Now here i points to an interval which has start >= newInterval.start

            int start = newInterval.start;
            int end = newInterval.end;

            //we need to find interval where end > newInterval.end, so that we can merge inbetween intervals if any
            while(i < intervals.size() && intervals.get(i).start < end) {
                //Look for start which is minimum as that will be closer to previous interval end
                start = Math.min (intervals.get(i).start, start);
                //maximize the end so as to encompass smaller intervals and merge them
                end = Math.max(intervals.get(i).end, end);
                i++;
            }

            //Add new interval to the list
            desired.add(new Interval(start, end));
            //add remaining intervals now that we have inserted the new one at proper location
            while(i < intervals.size()) {
                desired.add(intervals.get(i));
                i++;
            }

            return desired;
        }
        return null;
    }

  //Definition for an interval.
   static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }

      public String toString() {
          return new String("[" + start + ", " + end + "]");
      }
  }
}
