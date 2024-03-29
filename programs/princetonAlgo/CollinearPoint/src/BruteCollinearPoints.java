import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
Given a set of n distinct points in the plane, find every (maximal) line segment that connects a subset of 4 or more of the points.
 */
public class BruteCollinearPoints {
    // Tracks points given
    private Point[] points;
    //
    private LineSegment[] segments;
    private Point[][] ends;
    private int noOfSegments;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validate(points);
        this.points = Arrays.copyOf(points, points.length);
        noOfSegments = 0;
        segments = new LineSegment[10];
        ends = new Point[10][2];
        findSegments();
    }

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

    // the number of line segments
    public int numberOfSegments() {
        noOfSegments = segments().length;
        return noOfSegments;
    }

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
    }                // the line segments

    private void findSegments() {
        for (Point first : points) {// Start scanning the point list and grab the first point
            Point start, end;
            for (Point second : points) { // Use second point calculate the slope
                if (first.compareTo(second) != 0) {
                    //Track the start and end by looking at the point coordinates
                    if (first.compareTo(second) < 0) {
                        start = first;
                        end = second;
                    } else {
                        start = second;
                        end = first;
                    }
                    for (Point third : points) {// Dealing with the third point
                        if (first.compareTo(third) != 0 && second.compareTo(third) != 0 &&
                                first.slopeOrder().compare(second, third) == 0) {
                            //Update the start and end by looking at the point coordinates
                            if (start.compareTo(third) > 0) {
                                start = third;
                            } else if (end.compareTo(third) < 0) {
                                end = third;
                            }
                            boolean foundForth = false;
                            for (Point forth : points) { // we go through all the remaining points, in order to get the
                                // appropriate start and end and avoid the intermediate points
                                if (first.compareTo(forth) != 0 && second.compareTo(forth) != 0 &&
                                        third.compareTo(forth) != 0 &&
                                        first.slopeOrder().compare(third, forth) == 0 &&
                                        second.slopeOrder().compare(third, forth) == 0) {
                                    foundForth = true;
                                    //Update the start and end by looking at the point coordinates now that the forth point is found
                                    if (start.compareTo(forth) > 0) {
                                        start = forth;
                                    } else if (end.compareTo(forth) < 0) {
                                        end = forth;
                                    }
                                }
                            }
                            if (foundForth) {
                                // now that we have four points,
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
                                addSegment(start, end);
                            }
                        }
                    }
                }
            }
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

        // draw the points
       /* StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();*/

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            //if (segment != null) segment.draw();
        }
//        StdDraw.show();
    }
}