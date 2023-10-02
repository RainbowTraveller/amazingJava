/*
    Given n points on a 2D plane, find if there is such a line parallel to the y-axis that reflects the given points symmetrically.

    In other words, answer whether or not if there exists a line that after reflecting all points over the given line, the original points' set is the same as the reflected ones.

    Note that there can be repeated points.


    Notes : Wording is pretty messed up. The gist is as follows

    1. Given a list and not set of points as x and y
    2. Find a line parallel to y-axis x = constant and y variable
    3. This line should act as mirror and each point in the given list should have corresponding mirroring point in the same list.
    4. In other words, there should be pairs of points in the given list of points such that they are equidistance from the this mirroring line.
    5. i.e. there should be pairs of points where if x coordinates are equidistance from x coordinate which is constant for vertical line parallel to y-axis then the Y coordinate shoud be same


Logic :

1. Scan all the points
2. Track X coordinates
    a. Maintain a Map where we record all Y coordinates in for of a set to corresponding X coordinate
3. Find min and max of x coordinates, to get the range
4. Again scan all the points and check each x coordinate.
    a. Find corresponding mirroring X coordinate.
    b. Go get this find it's distance from the maxX and add the same offset from minX assuming their Y coordinate is same
5. Check if for this X the corresponding Y coordinate is present in the set of y coordinates already recorded

  */
import java.util.*;

public class LineReflection {
    public static void main(String[] args) {
        //int[][] points = {{0,0},{1,0}};
        //int[][] points = {{0,0}, {-2,2}, {2,2}, {5,3}, {6, 7}, {-5,3},{-6,7}};
        //int[][] points = {{1,1},{-1,1}};
        int[][] points = {{1,1},{-1,-1}};
        //int[][] points = {{1,2},{2,2},{1,4},{2,4}};
        System.out.println(LineReflection.lineReflection(points));
    }

    public static boolean lineReflection(int[][] points) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        Map<Integer,Set<Integer>> tracker = new HashMap<>();

        for(int i = 0; i < points.length; ++i) {
            Set<Integer> possibleYs = tracker.getOrDefault(points[i][0], new HashSet<Integer>());
            possibleYs.add(points[i][1]);
            tracker.put(points[i][0], possibleYs);
            //Get Min and Max of X coordinates to get the range
            min = Math.min(min, points[i][0]);
            max = Math.max(max, points[i][0]);
        }
        //System.out.println("Min : " + min + " Max : " + max);
        for(int i = 0; i < points.length; ++i) {
            int mirrorX = min + max - points[i][0];
            Set<Integer> mirrorYs = tracker.get(mirrorX);
            //System.out.println("X : " + mirrorX + " Y : " + mirrorY);
            if( mirrorYs == null || (!mirrorYs.contains(points[i][1]))) {
                return false;
            }
        }
        return true;
    }
}
