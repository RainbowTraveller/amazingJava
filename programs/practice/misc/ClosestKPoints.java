/*
 *
 We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:
Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]

*/
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;

class ClosestKPoints {

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double distance() {
            return Math.sqrt((x*x) + (y*y));
        }

        public String toString() {
            return "x : " + x + "Y : " + y;
        }
    }

    public  static void main(String [] args) {
        int[][] points = {{-2,-2}, {-5,2}, {0,8}, {-7,-9}, {2,10}, {-9,-9}, {8,9}};
        int[][] desired = kClosest(points, 7);
        for(int i = 0; i < desired.length; ++i) {
          System.out.println("Point " + (i + 1) + " X : " + desired[i][0] + " Y : " + desired[i][1] );
        }
        desired = kClosestSimple(points, 7);
        for(int i = 0; i < desired.length; ++i) {
          System.out.println("Point " + (i + 1) + " X : " + desired[i][0] + " Y : " + desired[i][1] );
        }
    }

    public static int[][] kClosest(int[][] points, int K) {
        if(points == null || points.length == 0 || K <= 0 ) {
            return points;
        }

        Comparator<Point> compareDistance = new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                 if(p1.distance() == p2.distance()) {
                    return 0;
                } else if ( p1.distance() > p2.distance()) {
                    return 1;
                } else {
                    return -1;
                }
                //This will not give perfect answer as the distance can be long
                //return p1.distance() - p2.distance();
            }
        };
        PriorityQueue<Point> distancePriority = new PriorityQueue<Point>(points.length, compareDistance);

        for(int i = 0; i< points.length; ++i) {
            Point p = new Point( points[i][0], points[i][1]);
            //System.out.println(p + "Distance : " + p.distance());
            distancePriority.add(p);
        }

        //System.out.println(distancePriority);

        int[][] closest = new int[K][2];
        for(int i = 0; i < K; ++i) {
            Point p =  distancePriority.poll();
            closest[i][0] = p.x;
            closest[i][1] = p.y;
        }
        return closest;
    }

    public static int[][] kClosestSimple(int[][] points, int K) {
        int N = points.length;
        int[] dists = new int[N];

        //Calculate distances
        for (int i = 0; i < N; ++i)
            dists[i] = dist(points[i]);

        //Sort
        Arrays.sort(dists);
        //Get desired largest distance
        int distK = dists[K-1];

        int[][] ans = new int[K][2];
        int t = 0;
        //Now gather only points that are less than or equal to
        //distK
        for (int i = 0; i < N; ++i)
            if (dist(points[i]) <= distK)
                ans[t++] = points[i];
        return ans;
    }

    public static int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}

