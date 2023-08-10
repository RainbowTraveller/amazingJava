import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;

public class KdTree {
    private static class TwoDTreeNode {
        private Point2D point;
        private TwoDTreeNode left;
        private TwoDTreeNode right;

        private RectHV axisAlignedRect;

        public TwoDTreeNode(Point2D point, RectHV axisAlignedRect) {
            this.left = null;
            this.right = null;
            this.point = point;
            this.axisAlignedRect = axisAlignedRect;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj instanceof TwoDTreeNode) {
                TwoDTreeNode curr = (TwoDTreeNode) obj;
                return curr.point.equals(this.point);
            }
            return false;
        }
    }

    private TwoDTreeNode root;
    private int size;

    // construct an empty set of points
    public KdTree() {
        root = null;
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
//        return inorderSize(root, 0);
        return this.size;
    }

    private int inorderSize(TwoDTreeNode node, int treeSize) {
        if (node != null) {
            size = inorderSize(node.left, treeSize);
            size++;
            size = inorderSize(node.right, size);
        }
        return size;
    }

    /**
     * As nodes are added to the tree, each node's rectangle is either:
     * <p>
     * The left portion of its parent's rectangle,
     * <p>
     * The right portion of its parent's rectangle,
     * <p>
     * The top portion of its parent's rectangle, or
     * <p>
     * The bottom portion of its parent's rectangle.
     * <p>
     * A new node's rectangle depends on the orientation of the parent (left/right or up/down) and the value of the
     * child node's key.
     *
     * @param parent       TreeNode which is parent of the current point for which the axis rectangle is to be decided
     * @param isHorizontal indicator whether the parent divides the plane vertically or horizontally. If true then
     *                     the plane is divided into up and down parts and if false then left and right
     * @param isLeft       indicator if the point lies on the left of the parent point
     * @param isAbove      indicator if the point lies above or below the parent point
     * @return variable of the type RectHV containing the point which either lies on the left/ right or up/down of the
     * parent point
     */
    private RectHV getAxisAngledRectangle(TwoDTreeNode parent, boolean isHorizontal, boolean isLeft, boolean isAbove) {
        RectHV currRect = parent.axisAlignedRect;
        double parentRectXmin = currRect.xmin();
        double parentRectYmin = currRect.ymin();
        double parentRectXmax = currRect.xmax();
        double parentRectYmax = currRect.ymax();
        Point2D parentPoint = parent.point;
        if (isHorizontal) {
            if (isAbove) {
                // Up : Xmin, y - Xmax, Ymax
                return new RectHV(parentRectXmin, parentPoint.y(), parentRectXmax, parentRectYmax);
            } else {
                // Down : Xmin, Ymin - Xmax, y
                return new RectHV(parentRectXmin, parentRectYmin, parentRectXmax, parentPoint.y());
            }
        } else {
            if (isLeft) {
                // Left :Xmin, Ymin - x, Ymax
                return new RectHV(parentRectXmin, parentRectYmin, parentPoint.x(), parentRectYmax);
            } else {
                // Right x,Ymin - Xmax, Ymax
                return new RectHV(parentPoint.x(), parentRectYmin, parentRectXmax, parentRectYmax);
            }
        }
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Can not insert null point");
        if (isEmpty()) {
            root = new TwoDTreeNode(p, new RectHV(0, 0, 1, 1));
            size++;
        } else {
            TwoDTreeNode curr = root;
            boolean isHorizontal = false;
            while (curr != null) {
                if (!curr.point.equals(p)) { // maintaining set invariant where the duplicates are not allowed
                    // parent is at odd level and hence check for y coordinate
                    // and split the space horizontally ( up-down )
                    if (isHorizontal) {
                        if (p.y() < curr.point.y()) {
                            // point belongs to lower part of the horizontal line containing current point
                            if (curr.right == null) {
                                // in case the right subtree is empty, insert the candidate point
                                curr.right = new TwoDTreeNode(p, getAxisAngledRectangle(curr, isHorizontal, false, false));
                                size++;
                                curr = null;
                            } else {
                                // traverse the right subtree in case it is not empty
                                curr = curr.right;
                            }
                        } else {
                            // point belongs to upper part of the horizontal line containing current point
                            if (curr.left == null) {
                                // in case the left subtree is empty, insert the candidate point
                                curr.left = new TwoDTreeNode(p, getAxisAngledRectangle(curr, isHorizontal, false, true));
                                size++;
                                curr = null;
                            } else {
                                // traverse the left subtree in case it is not empty
                                curr = curr.left;
                            }
                        }
                    } else {
                        // parent is at even level and hence check for x coordinate
                        // and split the space vertically ( left - right )
                        if (p.x() < curr.point.x()) {
                            // point belongs to the left side of the vertical line containing the current point
                            if (curr.left == null) {
                                // in case of empty left subtree, insert the candidate node
                                curr.left = new TwoDTreeNode(p, getAxisAngledRectangle(curr, isHorizontal, true, false));
                                size++;
                                curr = null;
                            } else {
                                // traverse the left subtree
                                curr = curr.left;
                            }
                        } else {
                            // point belongs to the right side of the vertical line containing the current point
                            if (curr.right == null) {
                                // in case of empty right subtree, insert the candidate node
                                curr.right = new TwoDTreeNode(p, getAxisAngledRectangle(curr, isHorizontal, false, false));
                                size++;
                                curr = null;
                            } else {
                                // traverse the right subtree
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
        TwoDTreeNode curr = root;
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
        TwoDTreeNode curr = root;
        printTreeInorder(curr, false);
    }

    private void printTreeInorder(TwoDTreeNode curr, boolean isHorizontal) {
        if (curr == null) return;
        StdDraw.setPenRadius(0.001);
        if(isHorizontal) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(curr.axisAlignedRect.xmin(),curr.point.y(), curr.axisAlignedRect.xmax(), curr.point.y());
        } else {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(curr.point.x(),curr.axisAlignedRect.ymin(), curr.point.x(), curr.axisAlignedRect.ymax());
        }
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        curr.point.draw();
        printTreeInorder(curr.left, !isHorizontal);
        printTreeInorder(curr.right, !isHorizontal);
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

        TwoDTreeNode curr = root;
        boolean isHorizontal = false;
        List<Point2D> innerPoints = new LinkedList<>();
//        range(curr, left, right, top, bottom, innerPoints, isHorizontal);
        range(curr, rect, innerPoints);
        return innerPoints;
    }

    private void range(TwoDTreeNode curr, RectHV enclosingRect, List<Point2D> points) {
        if (curr != null) {
            if (curr.axisAlignedRect.intersects(enclosingRect)) {
                if (enclosingRect.contains(curr.point)) {
                    points.add(curr.point);
                }
                if (curr.left != null && curr.left.axisAlignedRect.intersects(enclosingRect)) {
                    range(curr.left, enclosingRect, points);
                }
                if (curr.right != null && curr.right.axisAlignedRect.intersects(enclosingRect)) {
                    range(curr.right, enclosingRect, points);
                }

            }
        }

    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {

        if (p == null) throw new IllegalArgumentException("Can not find nearest from a null point");
        TwoDTreeNode curr = root;
        double distance = Double.MAX_VALUE;
        Point2D nearest = findNearest(p, curr, distance, null);
        return nearest;
    }

    private Point2D findNearest(Point2D candidate, TwoDTreeNode currNode, double nearestDistance, Point2D nearest) {
        if (currNode != null) {// only if the current node is not null
            Point2D currPoint = currNode.point;//Extract point
            double currDistance = currPoint.distanceTo(candidate);
            if (currDistance < nearestDistance) {
                nearest = currPoint;
                nearestDistance = currDistance;
            }
            if (currNode.left != null && currNode.left.axisAlignedRect.distanceTo(candidate) < nearestDistance) {
                nearest = findNearest(candidate, currNode.left, nearestDistance, nearest);
                nearestDistance = candidate.distanceTo(nearest);
            }

            if (currNode.right != null && currNode.right.axisAlignedRect.distanceTo(candidate) < nearestDistance) {
                nearest = findNearest(candidate, currNode.right, nearestDistance, nearest);
                nearestDistance = candidate.distanceTo(nearest);
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


        //Nearest neighbor

        // initialize the two data structures with point from file
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
