/* Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 *
 * Input: root = [1,3,2,5,3,null,9]
 * Output: root = [1,2,3]
 * Output: [1,3]
 *
 * Input: root = [1]
 * Output: [1]
 *
 * Input: root = [1,null,2]
 * Output: [1,2]
 *
 * Input: root = []
 * Output: []
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// Definition for a binary tree node.
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

public class LargestInRow {
  public static void main(String[] args) {
    List<Integer> largest = new ArrayList<Integer>();
    TreeNode root = new TreeNode(1);
    if (root != null) {
      helper(root, largest, 0);
    }
  }

  /**
   * Function to find largest value in each row of binary tree The alogorithm uses level-order
   * traversal (BFS) to visit each node in the tree level by level. At each level, it keeps track of
   * the maximum value encountered and adds it to the result list.
   *
   * <p>Time Complexity: O(n), where n is the number of nodes in the tree. Each node is visited
   * once.
   *
   * <p>Space Complexity: O(m), where m is the maximum number of nodes at any level in the tree.
   * This space is used by the queue to store nodes at the current level.
   *
   * @param root The root of the binary tree
   * @return A list of integers representing the largest value in each row
   */
  public List<Integer> largestValues(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root != null) {
      // Queue for level order traversal
      Deque<TreeNode> queue = new LinkedList<>();
      // Start with the root node
      queue.offer(root);
      // Continue until there are no more nodes to process
      while (!queue.isEmpty()) {

        // Initialize max for the current level
        int max = Integer.MIN_VALUE;
        // Track the number of nodes processed at the current level
        int i = 0;
        // Get the number of nodes at the current level
        int len = queue.size();

        while (i < len) {
          // Process each node at the current level
          TreeNode curr = queue.poll();
          // Update max if the current node's value is greater
          if (curr.val > max) {
            max = curr.val;
            // OR max = Math.max(max, curr.val);
          }
          // Add the left child to the queue if it exists
          if (curr.left != null) {
            queue.offer(curr.left);
          }

          // Add the right child to the queue if it exists
          if (curr.right != null) {
            queue.offer(curr.right);
          }
          // Increment the count of processed nodes at this level
          i++;
        }
        // After processing all nodes at this level, add the max to the result list
        result.add(max);
      }
    }
    // Return the list of largest values for each row
    return result;
  }

  /**
   * Helper function for DFS approach Function to find largest value in each row of binary tree
   * using DFS The algorithm uses depth-first traversal (DFS) to visit each node in the tree. It
   * keeps track of the current level and updates the largest value for that level as it traverses
   * the tree.
   *
   * <p>Time Complexity: O(n), where n is the number of nodes in the tree. Each node is visited
   * once.
   *
   * <p>Space Complexity: O(h), where h is the height of the tree. This space is used by the
   * recursion stack.
   *
   * @param root The root of the binary tree
   * @param largest List to store the largest values
   * @param level Current level in the tree
   */
  public static void helper(TreeNode root, List<Integer> largest, int level) {
    if (root == null) {
      return;
    } else {

      if (largest.size() <= level) {
        // If this is the first time we're visiting this level, add the value
        largest.add(level, root.val);
      } else if (largest.get(level) < root.val) {
        // Update the largest value for this level if the current node's value is greater
        largest.set(level, root.val);
      }
      // Recur for left and right subtrees, increasing the level by 1
      helper(root.left, largest, level + 1);
      helper(root.right, largest, level + 1);
    }
  }
}
