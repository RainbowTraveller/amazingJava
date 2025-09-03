/*  Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 *  An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 *  Find the total sum of all root-to-leaf numbers.
 *
 *  Note: A leaf is a node with no children.
 *
 *  Example:
 *
 *  Input: [1,2,3]
 *      1
 *     / \
 *    2   3
 *  Output: 25
 *  Explanation:
 *  The root-to-leaf path 1->2 represents the number 12.
 *  The root-to-leaf path 1->3 represents the number 13.
 *  Therefore, sum = 12 + 13 = 25.
 *  Example 2:
 *
 *  Input: [4,9,0,5,1]
 *      4
 *     / \
 *    9   0
 *   / \
 *  5   1
 *  Output: 1026
 *  Explanation:
 *  The root-to-leaf path 4->9->5 represents the number 495.
 *  The root-to-leaf path 4->9->1 represents the number 491.
 *  The root-to-leaf path 4->0 represents the number 40.
 *  Therefore, sum = 495 + 491 + 40 = 1026.
 */

public class SumRootToLeafNumbers {
  public static void main(String[] args) {}

  public int sumNumbers(TreeNode root) {
    // helper to track the sum
    return helper(root, 0);
  }

  public int helper(TreeNode root, int currSum) {
    if (root == null) {
      // To check if initial tree is null
      // OR
      // non leaf node having left / right subtree empty
      return 0;
    } else {
      // Create a number
      currSum *= 10;
      currSum += root.val;
      if (root.left == null && root.right == null) {
        // When a leaf node we have formed a number
        // so return it
        return currSum;
      }
      // System.out.println(currSum);
      // possible left or right branch will give desired number
      // or 0
      // if both branches are valid then we get the sum of 2 numbers
      // Here the currSum is passed by value being a primitive type, so no need to adjust by
      // dividing by 10
      return helper(root.left, currSum) + helper(root.right, currSum);
    }
  }
}
