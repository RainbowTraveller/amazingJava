/*  Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 *  Note: A leaf is a node with no children.
 *
 *  Example:
 *
 *  Given the below binary tree and sum = 22,
 *
 *        5
 *       / \
 *      4   8
 *     /   / \
 *    11  13  4
 *   /  \    / \
 *  7    2  5   1
 *  Return:
 *
 *  [
 *     [5,4,11,2],
 *     [5,8,4,5]
 *  ]
 */

import java.util.LinkedList;
import java.util.List;

public class AllSumPaths {
  public static void main(String[] args) {}

  /** Definition for a binary tree node. */
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> paths = new LinkedList<>();
    // To track current path
    List<Integer> currentPath = new LinkedList<>();
    // Helper function
    helper(paths, currentPath, root, sum);
    return paths;
  }

  public void helper(List<List<Integer>> paths, List<Integer> currentPath, TreeNode root, int sum) {
    // if root is null, conditions are not met, return
    if (root == null) {
      // System.out.println("Failed : " + currentPath);
      return;
    } else {
      // Reduce the sum by the current value of node
      sum -= root.val;
      currentPath.add(root.val);
      // Wow...leaf and sum is 0 too
      if (root.left == null && root.right == null && sum == 0) {
        // System.out.println("Success : " + currentPath);
        // Add current path to list of paths
        // remember to allocate new list or original list will get modified
        paths.add(new LinkedList<Integer>(currentPath));
      } else {
        // Trace left and right path with root already added to list
        helper(paths, currentPath, root.left, sum);
        helper(paths, currentPath, root.right, sum);
      }
      // Trace left and right path with root already added to list
      // don't forget to remove node before returning to previous node
      if (currentPath.size() > 0) {
        currentPath.remove(currentPath.size() - 1);
      }
    }
  }
}
