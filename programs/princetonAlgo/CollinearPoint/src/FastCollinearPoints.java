import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {
    Point[] points;
    LineSegment[] segments;
    Point[][] ends;
    int noOfSegments;

    public FastCollinearPoints(Point[] points) {
        this.points = points;
        noOfSegments = 0;
        segments = new LineSegment[10];
        ends = new Point[10][2];
        findSegments();

    }     // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return noOfSegments;
    }        // the number of line segments

    public LineSegment[] segments() {
        return segments;
    }
    private void findSegments() {
        double [] slopes = new double[points.length];
        int index = 0;
        for(Point candidate : points) {
            for(Point buddy : points) {
                if(candidate.compareTo(buddy) != 0) {
                    slopes[index++] = candidate.slopeTo(buddy);
                }
            }
            Arrays.sort(slopes);
        }
    }
    private void addSegment(Point start, Point end) {
        for (Point[] endPoints : ends) {
            if (endPoints != null && (endPoints[0] != null && endPoints[0].equals(start)
                    && endPoints[1] != null && endPoints[1].equals(end))) {
                return;
            }
        }
        ends[noOfSegments][0] = start;
        ends[noOfSegments][1] = end;
        segments[noOfSegments++] = new LineSegment(start, end);
    }
}
