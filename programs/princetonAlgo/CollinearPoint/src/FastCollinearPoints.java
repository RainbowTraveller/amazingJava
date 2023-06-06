import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] points;
    private LineSegment[] segments;
    private Point[][] ends;
    private int noOfSegments;

    public FastCollinearPoints(Point[] points) {
        validate(points);
        this.points = Arrays.copyOf(points, points.length);
        noOfSegments = 0;
        segments = new LineSegment[10];
        ends = new Point[10][2];
        findSegments();

    }     // finds all line segments containing 4 or more points

    private void validate(Point[] p) {
        if (p == null) {
            throw new IllegalArgumentException("The input array is null");
        }
        Arrays.sort(p);
        for (int i = 0; i < p.length; ++i) {
            if (p[i] == null) {
                throw new IllegalArgumentException("Point can not be null");
            }
            if (i < p.length - 1) {
                if (p[i].compareTo(p[i + 1]) == 0) {
                    throw new IllegalArgumentException("Duplicate points in the input");
                }
            }
        }
    }

    public int numberOfSegments() {
        noOfSegments = segments().length;
        return noOfSegments;
    }        // the number of line segments

    public LineSegment[] segments() {
        int length = 0;
        for (LineSegment segment : segments) {
            if (segment != null) {
                length++;
            }
        }
        LineSegment[] temp = Arrays.copyOf(segments, length);
        segments = temp;
        return Arrays.copyOf(segments, segments.length);
    }

    private void findSegments() {
        Point[] sorted = Arrays.copyOf(points, points.length);
        for (Point candidate : points) {
            Point[] collinear = new Point[4];
            Arrays.sort(sorted, candidate.slopeOrder());
//            System.out.println("Candidate :" + candidate);
            double slope = 0D;
            int count = 0;
            for (int i = 0; i < sorted.length; i++) {
//                System.out.println("Sorted 3 :   :: " + sorted[i]);
                Point buddy = sorted[i];
                if (candidate.compareTo(buddy) == 0) {
                    continue;
                }
                double currentSlope = candidate.slopeTo(buddy);
//                System.out.println("Buddy : " + buddy + "  Slope : " + currentSlope);
                if (slope != currentSlope) {
                    slope = currentSlope;
                    collinear = new Point[4];
                    count = 0;
                    collinear[count++] = buddy;
                } else {
                    collinear[count++] = buddy;
                    if (count == 3) {
                        collinear[count] = candidate;
                        Arrays.sort(collinear);
                        addSegment(collinear[0], collinear[3]);
                        break;
                    }
                }
            }
//            System.out.println("------");
        }
    }

    private void addSegment(Point start, Point end) {
        if (noOfSegments == segments.length) {
            LineSegment[] temp = new LineSegment[noOfSegments * 2];
            Point[][] tempEnd = new Point[noOfSegments * 2][2];
            for (int i = 0; i < noOfSegments; ++i) {
                temp[i] = segments[i];
                tempEnd[i] = ends[i];
            }
            segments = temp;
            ends = tempEnd;
        }
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

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }
    }
}
