/**
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *
 * <p>Collect all the leaf nodes. Remove all the leaf nodes. Repeat until the tree is empty.
 *
 * <p>Input: root = [1,2,3,4,5]
 *
 * <p>Output: [[4,5,3],[2],[1]]
 *
 * <p>Explanation: [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since
 * per each level it does not matter the order on which elements are returned.
 *
 * <p>Example 2:
 *
 * <p>Input: root = [1] Output: [[1]]
 */
public class FindLeaves {
  public List<List<Integer>> findLeaves(TreeNode root) {

    List<List<Integer>> leaves = new LinkedList<>();
    helper(root, leaves);
    return leaves;
  }

  /**
   * The following function leverages the height of the binary tree to separate leaves at each level
   */
  public int helper(TreeNode root, List<List<Integer>> leaves) {

    if (root == null) {
      // height at a null node is -1 so parent will be at height 0
      return -1;
    }
    // Left Height
    int left = helper(root.left, leaves);
    // Right Height
    int right = helper(root.right, leaves);
    // Get the correct height at this level
    int level = Math.max(left, right) + 1;

    // The index is 0 based so if size is 1 that means there is a list
    // at level 0 already there but when new list will be added at level 1
    // then the size will be 2
    if (leaves.size() == level) {
      System.out.println("Creating New : " + level);
      leaves.add(new LinkedList<>());
    }

    List<Integer> currLevel = leaves.get(level);
    currLevel.add(root.val);
    return level;
  }
}
