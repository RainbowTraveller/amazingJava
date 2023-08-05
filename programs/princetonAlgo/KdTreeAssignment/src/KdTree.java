import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;

public class KdTree {
    private static class dTreeNode {
        private Point2D point;
        private dTreeNode left;
        private dTreeNode right;

        public dTreeNode(Point2D point) {
            this.left = null;
            this.right = null;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj instanceof dTreeNode) {
                dTreeNode curr = (dTreeNode) obj;
                return curr.point.equals(this.point);
            }
            return false;
        }
    }

    private dTreeNode root;

    // construct an empty set of points
    public KdTree() {
        root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return inorderSize(root, 0);
    }

    private int inorderSize(dTreeNode node, int size) {
        if (node != null) {
            size = inorderSize(node.left, size);
            size++;
            size = inorderSize(node.right, size);
        }
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Can not insert null point");
        if (isEmpty()) {
            root = new dTreeNode(p);
        } else {
            dTreeNode curr = root;
            boolean isHorizontal = false;
            while (curr != null) {
                if (!curr.point.equals(p)) {
                    // parent is add odd level and hence check for y coordinate a
                    // and split the space horizontally ( up-down )
                    if (isHorizontal) {
                        if (p.y() < curr.point.y()) {
                            if (curr.left == null) {
                                curr.left = new dTreeNode(p);
                                curr = null;
                            } else {
                                curr = curr.left;
                            }
                        } else {
                            if (curr.right == null) {
                                curr.right = new dTreeNode(p);
                                curr = null;
                            } else {
                                curr = curr.right;
                            }
                        }
                    } else {
                        // parent is add even level and hence check for x coordinate a
                        // and split the space vertically ( left - right )
                        if (p.x() < curr.point.x()) {
                            if (curr.left == null) {
                                curr.left = new dTreeNode(p);
                                curr = null;
                            } else {
                                curr = curr.left;
                            }
                        } else {
                            if (curr.right == null) {
                                curr.right = new dTreeNode(p);
                                curr = null;
                            } else {
                                curr = curr.right;
                            }
                        }
                    }
                    isHorizontal = !(isHorizontal);
                }
            }
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {

        if (p == null) throw new IllegalArgumentException("Can not find null point");
        dTreeNode curr = root;
        boolean isHorizontal = false;
        while (curr != null) {
            if (curr.point.equals(p)) {
                return true;
            } else {
                // parent is add odd level and hence check for y coordinate a
                // and split the space horizontally ( up-down )
                if (isHorizontal) {
                    if (p.y() < curr.point.y()) {
                        curr = curr.left;
                    } else {
                        curr = curr.right;
                    }
                } else {
                    // parent is add even level and hence check for x coordinate a
                    // and split the space vertically ( left - right )
                    if (p.x() < curr.point.x()) {
                        curr = curr.left;
                    } else {
                        curr = curr.right;
                    }
                }
                isHorizontal = !(isHorizontal);
            }
        }
        return false;
    }

    // draw all points to standard draw
    public void draw() {
        dTreeNode curr = root;
        printTreeInorder(curr);
    }

    private void printTreeInorder(dTreeNode curr) {
        if (curr == null) return;
        curr.point.draw();
        printTreeInorder(curr.left);
        printTreeInorder(curr.right);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("Can not find range for null rectangle");
        }
        double top = rect.ymax();
        double bottom = rect.ymin();
        double left = rect.xmin();
        double right = rect.xmax();

        dTreeNode curr = root;
        boolean isHorizontal = false;
        List<Point2D> innerPoints = new LinkedList<>();
        range(curr, left, right, top, bottom, innerPoints, isHorizontal);
        return innerPoints;
    }

    private void range(dTreeNode curr, double left, double right, double top, double bottom, List<Point2D> points, boolean isHorizontal) {
        if (curr != null) {
            double currY = curr.point.y();
            double currX = curr.point.x();
            if (isHorizontal) {
                if (currY >= bottom) {
                    if (currY <= top) {
                        if (currX >= left && currX <= right) {
                            points.add(curr.point);
                        }
                        //else {

                        range(curr.left, left, right, top, bottom, points, !isHorizontal);
                        range(curr.right, left, right, top, bottom, points, !isHorizontal);
                        //}
                    } else {
                        range(curr.left, left, right, top, bottom, points, !isHorizontal);
                    }
                } else {
                    range(curr.right, left, right, top, bottom, points, !isHorizontal);
                }
            } else {
                if (currX >= left) {
                    if (currX <= right) {
                        if (currY >= bottom && currY <= top) {
                            points.add(curr.point);
                        }
                        //else {
                        range(curr.left, left, right, top, bottom, points, !isHorizontal);
                        range(curr.right, left, right, top, bottom, points, !isHorizontal);
                        //}
                    } else {
                        range(curr.left, left, right, top, bottom, points, !isHorizontal);
                    }
                } else {
                    range(curr.right, left, right, top, bottom, points, !isHorizontal);
                }
            }
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {

        if (p == null) throw new IllegalArgumentException("Can not find nearest from a null point");
        dTreeNode curr = root;
        double distance = Double.MAX_VALUE;
        Point2D nearest = findNearest(p, curr, distance, null, false);
        return nearest;
    }

    private Point2D findNearest(Point2D candidate, dTreeNode currNode, double nearestDistance, Point2D nearest, boolean isHorizontal) {
        if (currNode != null) {// only if the current node is not null
            Point2D currPoint = currNode.point;//Extrac
            double currDistance = currPoint.distanceTo(candidate);
            if (currDistance < nearestDistance) {
                nearest = currPoint;
                nearestDistance = currDistance;
            }
            if (isHorizontal) {
                if (candidate.y() < currPoint.y()) {
                    //if candidate is in the lower half first go to left
                    nearest = findNearest(candidate, currNode.right, nearestDistance, nearest, !isHorizontal);
                    // and then to right to explore points above the current node
                    nearest = findNearest(candidate, currNode.left, nearestDistance, nearest, !isHorizontal);
                } else {
                    // explore points above first and
                    nearest = findNearest(candidate, currNode.left, nearestDistance, nearest, !isHorizontal);
                    // points below wrt current Node
                    nearest = findNearest(candidate, currNode.right, nearestDistance, nearest, !isHorizontal);
                }
            } else {
                //in case of vertical partitioning, check which side the point lies on
                if (candidate.x() < currPoint.x()) {
                    nearest = findNearest(candidate, currNode.left, nearestDistance, nearest, !isHorizontal);
                } else {
                    nearest = findNearest(candidate, currNode.right, nearestDistance, nearest, !isHorizontal);
                }
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        // initialize the data structures from file
        String filename = args[0];
        In in = new In(filename);
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }

        double x0 = 0.0, y0 = 0.0;      // initial endpoint of rectangle
        double x1 = 0.0, y1 = 0.0;      // current location of mouse
        boolean isDragging = false;     // is the user dragging a rectangle

//        RectHV rectHV = new RectHV(0.1, 0.1, 0.9, 0.9);
//        for (Point2D point2D : kdtree.range(rectHV)) {
//            System.out.println(point2D);
//        }

        // draw the points
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        kdtree.draw();
        StdDraw.show();

        // process range search queries
        StdDraw.enableDoubleBuffering();
        while (true) {

            // user starts to drag a rectangle
            if (StdDraw.isMousePressed() && !isDragging) {
                x0 = x1 = StdDraw.mouseX();
                y0 = y1 = StdDraw.mouseY();
                isDragging = true;
            }

            // user is dragging a rectangle
            else if (StdDraw.isMousePressed() && isDragging) {
                x1 = StdDraw.mouseX();
                y1 = StdDraw.mouseY();
            }

            // user stops dragging rectangle
            else if (!StdDraw.isMousePressed() && isDragging) {
                isDragging = false;
            }

            // draw the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            kdtree.draw();

            // draw the rectangle
            RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
                    Math.max(x0, x1), Math.max(y0, y1));
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius();
            rect.draw();

            // draw the range search results for kd-tree in blue
            StdDraw.setPenRadius(0.02);
            StdDraw.setPenColor(StdDraw.BLUE);
            for (Point2D p : kdtree.range(rect)) {
                System.out.println(p);
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(20);
        }


//        //Nearest neighbor
//
//        // initialize the two data structures with point from file
//        String filename = args[0];
//        In in = new In(filename);
//        KdTree kdtree = new KdTree();
//        while (!in.isEmpty()) {
//            double x = in.readDouble();
//            double y = in.readDouble();
//            Point2D p = new Point2D(x, y);
//            kdtree.insert(p);
//        }
//
//        // process nearest neighbor queries
//        StdDraw.enableDoubleBuffering();
//        while (true) {
//
//            // the location (x, y) of the mouse
//            double x = StdDraw.mouseX();
//            double y = StdDraw.mouseY();
//            Point2D query = new Point2D(x, y);
//
//            // draw all of the points
//            StdDraw.clear();
//            StdDraw.setPenColor(StdDraw.BLACK);
//            StdDraw.setPenRadius(0.02);
//            kdtree.draw();
//
//            // draw in blue the nearest neighbor (using kd-tree algorithm)
//            StdDraw.setPenColor(StdDraw.BLUE);
//            kdtree.nearest(query).draw();
//            StdDraw.show();
//            StdDraw.pause(40);
//        }
    }
}
