import java.util.List;
import java.util.LinkedList;

/**
 *  Exactly same logic in terms of arrays
 */
public class IntervalAdditionArray {
    public static void main(String[] args) {
        //int[][] result = insert(new int[][] {{1,3},{6,9}}, new int[]{2,5});
        int[][] result = insert(new int[][] {{1,2},{3,5},{6,7},{8,10},{12,16}},new int[]{4,8});
        for(int i = 0; i < result.length; ++i) {
            System.out.println("[" + result[i][0] + ", " + result[i][1] + " ]");
        }
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();

        if(intervals != null && intervals.length > 0) {

            int i = 0;

            while(i < intervals.length && intervals[i][1] < newInterval[0]) {
                int curr[] = new int[2];
                curr[0] = intervals[i][0];
                curr[1] = intervals[i][1];
                result.add(curr);
                i++;
            }

            int start = newInterval[0];
            int end = newInterval[1];

            while(i < intervals.length) {
                if(end < intervals[i][0] ) {
                    int curr[] = new int[2];
                    curr[0] = start;
                    curr[1] = end;
                    result.add(curr);
                    start = intervals[i][0];
                    end = intervals[i][1];

                } else {
                    start = Math.min(intervals[i][0], start);
                    end =  Math.max(intervals[i][1], end);
                }
                i++;
            }

            int curr[] = new int[2];
            curr[0] = start;
            curr[1] = end;
            result.add(curr);
        }
        return result.toArray(new int[result.size()][2]);
    }
}

