/*  Given a binary tree, return the vertical order traversal of its nodes values.
 *
 *  For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 *
 *  Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 *
 *  If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 *
 *  Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 *
 *
 *  Example 1:
 *
 *
 *  Input: [3,9,20,null,null,15,7]
 *  Output: [[9],[3,15],[20],[7]]
 *  Explanation:
 *  Without loss of generality, we can assume the root node is at position (0, 0):
 *  Then, the node with value 9 occurs at position (-1, -1);
 *  The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 *  The node with value 20 occurs at position (1, -1);
 *  The node with value 7 occurs at position (2, -2).
 *
 *   Input: [1,2,3,4,5,6,7]
 *   Output: [[4],[2],[1,5,6],[3],[7]]
 *   Explanation:
 *   The node with value 5 and the node with value 6 have the same position according to the given scheme.
 *   However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 *
 */

public class VerticalOrderTraversal {
  public static void main(String[] args) {}

  List<Triplet<Integer, Integer, Integer>> nodeList = new ArrayList<>();

  private void BFS(TreeNode root) {
    Queue<Triplet<TreeNode, Integer, Integer>> queue = new ArrayDeque();
    int row = 0, column = 0;
    queue.offer(new Triplet(root, row, column));

    while (!queue.isEmpty()) {
      Triplet<TreeNode, Integer, Integer> triplet = queue.poll();
      root = triplet.first;
      row = triplet.second;
      column = triplet.third;

      if (root != null) {
        this.nodeList.add(new Triplet(column, row, root.val));
        queue.offer(new Triplet(root.left, row + 1, column - 1));
        queue.offer(new Triplet(root.right, row + 1, column + 1));
      }
    }
  }

  /**
   * Method to perform vertical order traversal of a binary tree. This method uses BFS to traverse
   * the tree and collects nodes' values along with their column and row indices. It then sorts the
   * collected nodes by column, row, and value before grouping them by column for the final output.
   *
   * @param root The root node of the binary tree.
   * @return A list of lists, where each inner list contains the values of nodes
   */
  public List<List<Integer>> verticalTraversal(TreeNode root) {

    List<List<Integer>> output = new ArrayList();
    if (root == null) {
      return output;
    }

    // step 1). BFS traversal
    BFS(root);

    // step 2). sort the global list by <column, row, value>
    Collections.sort(
        this.nodeList,
        new Comparator<Triplet<Integer, Integer, Integer>>() {
          @Override
          public int compare(
              Triplet<Integer, Integer, Integer> t1, Triplet<Integer, Integer, Integer> t2) {
            if (t1.first.equals(t2.first))
              if (t1.second.equals(t2.second)) return t1.third - t2.third;
              else return t1.second - t2.second;
            else return t1.first - t2.first;
          }
        });

    // step 3). extract the values, partitioned by the column index.
    List<Integer> currColumn = new ArrayList();
    Integer currColumnIndex = this.nodeList.get(0).first;

    for (Triplet<Integer, Integer, Integer> triplet : this.nodeList) {
      Integer column = triplet.first, value = triplet.third;
      if (column != currColumnIndex) {
        output.add(currColumn);
        currColumnIndex = column;
        currColumn = new ArrayList();
      }
      currColumn.add(value);
    }
    output.add(currColumn);

    return output;
  }
}

class Triplet<F, S, T> {
  public final F first;
  public final S second;
  public final T third;

  public Triplet(F first, S second, T third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }
}

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  public TreeNode() {
    this.TreeNode(Integer.MIN_VALUE);
  }

  public TreeNode(int val) {
    this.TreeNode(val, null, null);
  }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
