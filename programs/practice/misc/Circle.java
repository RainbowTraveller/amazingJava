import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// PROBLEM: Given two circles, how do we identify if they intersect.
// DEFINITION: We define intersection when the circumferences of the two circles share at least one point.
// Center cooordinates
// redius
// circuference : 2 * pi * r
// c1, r1 and c2, r2
// c1  = x1, y1
// c2 = x2,y2

// https://www.desmos.com/calculator/mdweme5d3p

public class Circle  {
    public int centerx;
    public int centery;
    public int radius;

    public Circle(int x, int y, int r) {
        this.centery = y;
        this.centerx = x;
        this.radius = r;
    }

    public static void main(String args[]) throws Exception {

        // C1 = (1, 0, 3) (x, y, radius)
        // C2 = (6, 0, 2) (TRUE)
        Circle c1 = new Circle(1, 0, 3); //-2, 4
        Circle c2 = new Circle(6, 0, 2);// 4, 8

        // C1 = (1, 1, 3)
        // C2 = (-5, -1, 3) (FALSE)
        //c1 = new Circle(1, 1, 3);
        //c2 = new Circle(-5, -1, 3);

        // C1 = (0, 0, 7)
        // C2 = (0, 0, 4) (FALSE)
        // https://www.desmos.com/calculator/pffckklag3
        //c1 = new Circle(0, 0, 7);
        //c2 = new Circle(1, 0, 2); //(FALSE)
        System.out.println(areIntersectingCircles(c1, c2));

    }

    public static boolean areIntersectingCircles(Circle c1, Circle c2) {
        if(c1 != null && c2 != null) {

            //Concentric Circles
            if(c1.centerx == c2.centerx && c1.centery ==  c2.centery && c1.radius != c2.radius) {
                return false;
            }

            double distance = getDistance(c1.centerx, c1.centery, c2.centerx, c2.centery);
            System.out.println("Center Distance : " + distance );
            System.out.println("Radius Sum : " + (double)(c1.radius + c2.radius));

            //Handling internally non intersecting
            if (distance < Math.abs( c1.radius - c2.radius )) {
                return false;
            //Internally intersecting or external circles
            } else if( distance == Math.abs( c1.radius - c2.radius ) || (distance <= (double)(c1.radius + c2.radius))) {
                return true;
            }
        }
        return false;
    }

    public static double getDistance(int x1, int y1, int x2, int y2) {
        double xdistance = Math.pow(( x2 - x1), 2);
        double ydistance = Math.pow(( y2 - y1), 2);
        return Math.sqrt( xdistance + ydistance);
    }
}
