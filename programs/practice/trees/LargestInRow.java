/* Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
*
* iInput: root = [1,3,2,5,3,null,9]
* Output: root = [1,2,3]
* Output: [1,3]
* Example  3:
*
* Input: root = [1]
* Output: [1]
* Example 4:
*
* Input: root = [1,null,2]
* Output: [1,2]
* Example 5:
*
* Input: root = []
* Output: []
*/

public class LargestInRow {
    public static void main(String[] args) {
        List<Integer> largest = new ArrayList<Integer>();
        if (root != null) {
            helper(root, largest, 0);
        }
        return largest;
    }

    // BFS
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            Deque<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {

                int max = Integer.MIN_VALUE;
                int i = 0;
                int len = queue.size();

                while (i < len) {
                    TreeNode curr = queue.poll();
                    if (curr.val > max) {
                        max = curr.val;
                        // OR max = Math.max(max, curr.val);
                    }
                    if (curr.left != null) {
                        queue.offer(curr.left);
                    }

                    if (curr.right != null) {
                        queue.offer(curr.right);
                    }
                    i++;
                }
                result.add(max);
            }
        }
        return result;
    }

    // Recursive
    public void helper(TreeNode root, List<Integer> largest, int level) {
        if (root == null) {
            return;
        } else {

            if (largest.size() <= level) {
                largest.add(level, root.val);
            } else if (largest.get(level) < root.val) {
                largest.set(level, root.val);
            }
            helper(root.left, largest, level + 1);
            helper(root.right, largest, level + 1);
        }
    }
}
