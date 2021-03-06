
/**
 * Definition for an interval.
 * Merge intervals indicated by a start and an end
 *
 * e.g. (1, 4), (2, 6), (2, 3), (40, 45), (10, 12)
 *
 * First sort : (1, 4), (2, 6), (2, 3),  (10, 12), (40, 45)
 *
 * Final Result : (1, 6), (10, 12), (40, 45)
 *
 */
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Arrays;

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }

    public String toString() {
        return new String(" { start : " + start + " end : " + end + " }\n");
    }
}

public class MergeIntervals {

    public static void main(String[] args) {
        List<Interval> intervals = new LinkedList<Interval>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(2, 3));
        intervals.add(new Interval(40, 45));
        intervals.add(new Interval(10, 12));
        List<Interval> merged = merge(intervals);
        System.out.println(merged);
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new LinkedList<Interval>();

        /*
         * This makes sure that the order can be anything in input but for ease of
         * processing these needs to be sorted based on the start of each interval
         */

        if (intervals != null && intervals.size() > 0) {
            // Converting comparator to lambda
            Collections.sort(intervals, (i1, i2) -> (i1.start == i2.start ? i1.end - i2.end : i1.start - i2.start));
            Interval first = intervals.get(0);
            // Using first interval as a base referral
            int start = first.start;
            int end = first.end;
            int i = 1;
            // Remember : intervals are sorted by start value
            while (i < intervals.size()) {
                Interval curr = intervals.get(i);
                // check if some part of current interval lies within previous
                if (curr.start <= end) {
                    // Check if `this` interval entirely lies inside last one
                    // previous end is invalid as this one extends beyond it
                    end = Math.max(end, curr.end);
                } else {
                    // No merging possible so add new interval
                    // based on info collected so far
                    result.add(new Interval(start, end));
                    start = curr.start;
                    end = curr.end;
                }
                i++;
            }
            // Tricky : Last one remains out
            result.add(new Interval(start, end));
        }
        return result;
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        if (intervals != null && intervals.length > 0) {
            int[][] temp = new int[intervals.length][2];
            int i = 0;
            int len = 0;
            int start = intervals[0][0];
            int end = intervals[0][1];
            i++;
            while (i < intervals.length) {
                if (end >= intervals[i][0]) {
                    start = Math.min(start, intervals[i][0]);
                    end = Math.max(end, intervals[i][1]);
                } else {
                    temp[len][0] = start;
                    temp[len][1] = end;
                    len++;
                    start = intervals[i][0];
                    end = intervals[i][1];
                }
                i++;
            }
            temp[len][0] = start;
            temp[len][1] = end;
            int[][] finalResult = new int[len][2];
            for (int j = 0; j < len; ++j) {
                finalResult[j] = temp[j];
            }
            return finalResult;
        }
        return intervals;
    }
}
