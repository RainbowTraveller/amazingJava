/**
 * Definition for an interval.
*/
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }

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
		intervals.add(new Interval(40,45));
		intervals.add(new Interval(10, 12));
		List<Interval> merged = merge( intervals );
		System.out.println(merged);
	}

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new LinkedList<Interval>();

		/*
		 * This makes sure that the order can be anything in input
		 * but for ease of processing these needs to be sorted based on the
		 * start of each interval
		 */
        Comparator<Interval> intervalComparator = new Comparator<Interval>() {
            public int compare( Interval i1, Interval i2) {
                if( i1.start < i2.start) {
                    return -1;
                } else if ( i1.start > i2.start ) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };


        if(intervals != null && intervals.size() > 0) {
            Collections.sort(intervals, intervalComparator);
            Interval first = intervals.get(0);
			//Using first interval as a base referral
            int start = first.start;
            int end = first.end;
            int i = 1;
			//Remember : intervals are sorted by start value
            while(i < intervals.size()) {
                Interval curr = intervals.get(i);
				// check if some part of current interval lies within previous
                if( curr.start <= end) {
					//Check if `this` interval entirely lies inside last one
                    if( end < curr.end) {
						//previous end is invalid as this one extends beyond it
                        end = curr.end;
                    }
                } else {
					//No merging possible so add new interval
					//based on info collected so far
                    result.add(new Interval(start, end));
                    start = curr.start;
                    end = curr.end;
                }
                i++;
            }
			//Tricky : Last one remains out
            result.add(new Interval(start, end));
        }
        return result;
    }
}
