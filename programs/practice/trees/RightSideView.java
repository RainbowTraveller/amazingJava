/*  Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
*
*  Example:
*
*  Input: [1,2,3,null,5,null,4]
*  Output: [1, 3, 4]
*/

import java.util.List;
import java.util.LinkedList;
import java.util.Deque;

public class RightSideView {
    public static void main(String[] args) {

    }

    /*
     * Simple BFS. Only keep track of the queue length.
     * when queue size is 1, it indicates it is the rightmost node at that depth
     * so add it to the list
     * Another alternative will be creating a hashmap of depth and node.
     * Keep putting nodes into hashmap against the depth level. The last node at that level will be the
     * rightmost node which will overwrite all previous ones. So after BFS gather all nodes into a list
     * from the map
     *
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rhs = new ArrayList<>();
        if (root != null) {
            Deque<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size > 0) {
                    TreeNode curr = queue.poll();
                    if (size == 1) {
                        rhs.add(curr.val);
                    }

                    if (curr.left != null) {
                        queue.offer(curr.left);
                    }

                    if (curr.right != null) {
                        queue.offer(curr.right);
                    }
                    size--;
                }
            }
        }
        return rhs;
    }
}
