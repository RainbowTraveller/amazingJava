/**
 *
 *   Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 *   Return the intersection of these two interval lists.
 *   (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *   The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 */

import java.util.List;
import java.util.ArrayList;

public class IntervalsIntersection {
    public static void main(String[] args) {
        int [][] A = {{0,2},{5,10},{13,23},{24,25}};
        int [][] B = {{1,5},{8,12},{15,24},{25,26}};
        System.out.println("Common Interval " + intervalIntersection(A, B));

    }

    public static List<List<Integer>> intervalIntersection(int[][] A, int[][] B) {
        //In case return type is int[][]
        //List<int[]> common = new ArrayList<>();
        List<List<Integer>> common = new ArrayList<>();
        if (A != null && B != null && A.length > 0 && B.length > 0) {
            int indexA = 0, indexB = 0;
            while (indexA < A.length && indexB < B.length) {
                //Get max of start
                int start = Math.max(A[indexA][0], B[indexB][0]);
                //Get min of ends
                int end = Math.min(A[indexA][1], B[indexB][1]);
                //interval like 5,5 is valid
                if (start <= end) {
                    //common.add(new int[] { start, end });
                    List<Integer> pair = new ArrayList<>();
                    pair.add(start);
                    pair.add(end);
                    common.add(pair);
                }
                // Now if A's end is less than B's end then it is already accounted for,
                // check next interval, but keep B as is, because next A may also be within current B and vice versa
                if (A[indexA][1] <= B[indexB][1]) {
                    indexA++;
                } else {
                    indexB++;
                }
            }

        }
        //return common.toArray(new int[common.size()][]);
        return common;
    }
}
