import java.util.PriorityQueue;
import java.util.Comparator;

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
}
