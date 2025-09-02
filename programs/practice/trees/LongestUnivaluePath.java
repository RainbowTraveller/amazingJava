/*  Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
 *
 *  The length of path between two nodes is represented by the number of edges between them.
 *
 *  Example 1:
 *
 *  Input:
 *
 *                5
 *               / \
 *              4   5
 *             / \   \
 *            1   1   5
 *  Output: 2
 *
 *  Example 2:
 *
 *  Input:
 *
 *                1
 *               / \
 *              4   5
 *             / \   \
 *            4   4   5
 *  Output: 2
 */

public class LongestUnivaluePath {

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  int ans = 0;

  public static void main(String[] args) {
    TreeNode root = new LongestUnivaluePath().new TreeNode(5);
    root.left = new LongestUnivaluePath().new TreeNode(4);

    root.right = new LongestUnivaluePath().new TreeNode(5);
    root.left.left = new LongestUnivaluePath().new TreeNode(1);
    root.left.right = new LongestUnivaluePath().new TreeNode(1);
    root.right.right = new LongestUnivaluePath().new TreeNode(5);
    System.out.println(new LongestUnivaluePath().longestUnivaluePath(root));
  }

  /**
   * Depth First Search - Post Order Traversal This function does a post order traversal of the tree
   * and checks if the left and right node values are same as the current node value if they are
   * same then it increments the left and right branch values and updates the ans variable which is
   * declared globally with the max of ans and leftBranch + rightBranch and returns the max of
   * leftBranch and rightBranch to the parent node which will be used to calculate the path length
   * for the parent node
   *
   * <p>Time Complexity: O(N) where N is number of nodes in the tree
   *
   * <p>Space Complexity: O(H) where H is height of the tree
   *
   * @param root - root of the tree
   * @return - length of the longest path where each node in the path has the same values
   */
  public int longestUnivaluePath(TreeNode root) {
    if (root == null) {
      return 0;
    } else {
      // Go down to leaf nodes to check
      int left = longestUnivaluePath(root.left);
      int right = longestUnivaluePath(root.right);
      // This is important to keep track of left and right branch separately
      // this is because we want link counts which is 1 less than the node count
      int leftBranch = 0, rightBranch = 0;
      if (root.left != null && root.left.val == root.val) {
        // if left node data matches the increment by 1 left node value
        leftBranch += left + 1;
      }

      if (root.right != null && root.right.val == root.val) {
        // if right  node data matches the increment by 1 right node value
        rightBranch += right + 1;
      }

      // Add left and right branch values which include the current value as well if it is
      // same as left and / or right
      // This is compared with the ans declared globally
      // which will take care of breadth wise spanned tree at this level
      ans = Math.max(ans, leftBranch + rightBranch);

      // returns the max of left and right value to be considered depth wise at next level
      return Math.max(leftBranch, rightBranch);
    }
  }
}
