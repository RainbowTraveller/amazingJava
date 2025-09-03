/**
 * Calculate the max sum for a leaf to leaf path The path should start from a leaf and end to a leaf
 * in a given tree find out such a path with max value when the values of the nodes is added
 */
public class MaxPathSum {
  int max = Integer.MIN_VALUE;

  public static void main(String[] args) {
    MaxPathSum obj = new MaxPathSum();
    TreeNode root = new TreeNode(-10);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    System.out.println(obj.maxPathSum(root)); // Output: 42
  }

  /**
   * Function to find the maximum path sum in a binary tree. A path is defined as any sequence of
   * nodes from some starting node to any node in the tree along the parent-child connections. The
   * path must contain at least one node and does not need to go through the root.
   *
   * @param root The root of the binary tree.
   * @return The maximum path sum.
   */
  public int maxPathSum(TreeNode root) {
    // With global variable
    // helper(root);
    // return max;

    // Keeping track of the max value at each level
    LevelResult result = pathSumHelper(root, new LevelResult(0, Integer.MIN_VALUE));
    return result.max;
  }

  public int helper(TreeNode root) {
    if (root == null) return 0;

    // Get max from left and right side, set to 0 if negative
    int left = Math.max(helper(root.left), 0);
    int right = Math.max(helper(root.right), 0);

    // Here we calculate the entire left to right path from the given node and
    // record it against the max value obtained so far
    max = Math.max(max, root.val + left + right);

    // Pass on the max branch value to upper level so that it can be used by the
    // parent node to decide it's max value path and then it will be compared with
    // max value which is nothing but the through path for one of it's children
    // nodes

    return (root.val + Math.max(left, right));
  }

  /**
   * Helper function to calculate the maximum path sum in a binary tree. It returns a LevelResult
   * object containing the current path sum and the maximum path sum found so far. The function
   * recursively traverses the tree and updates the LevelResult object at each node. The local
   * maximum path sum is calculated by considering the current node's value and the maximum path
   * sums from its left and right children.
   *
   * @param root The current node in the binary tree.
   * @param level The LevelResult object to keep track of the current path sum and maximum path sum.
   * @return The updated LevelResult object with the current path sum and maximum path sum.
   */
  public LevelResult pathSumHelper(TreeNode root, LevelResult level) {
    if (root == null) return level;

    LevelResult left = pathSumHelper(root.left, level);
    LevelResult right = pathSumHelper(root.right, level);
    // VERY IMP STEP
    // Make sure you consider only positive values as negative values decrease the
    // sum
    // so if left and / or right are negative and root value is positive then it is
    // not considered when you add left or right to it which should not happen
    int leftVal = Math.max(left.val, 0);
    int rightVal = Math.max(right.val, 0);
    // Get max path sum obtained for far from left and right
    int max = Math.max(left.max, right.max);
    // Calculate max at this level
    max = Math.max(max, root.val + leftVal + rightVal);
    // either left path from root or right will contribute to max path sum at next
    // level
    // so get greater between them and add to root value
    return new LevelResult(root.val + Math.max(leftVal, rightVal), max);
  }
}

/** Class representing a node containing an inteter value and a max value Obtained so far */
class LevelResult {
  // val represents the max path sum from the current node to any of its leaf nodes
  int val;
  // max represents the overall maximum path sum found in the tree so far
  int max;

  public LevelResult() {
    val = 0;
    max = 0;
  }

  public LevelResult(int val, int max) {
    this.val = val;
    this.max = max;
  }
}
