/*  Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
*
*  The length of path between two nodes is represented by the number of edges between them.
*
*
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
*
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
    int ans;

    public static void main(String[] args) {

    }

    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        helper(root);
        return ans;
    }

    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            //Go down to leaf nodes to check
            int left = helper(root.left);
            int right = helper(root.right);
            //This is important to keep track of left and right branch separately
            //this is because we want link counts which is 1 less than the node count
            int leftBranch = 0, rightBranch = 0;
            if (root.left != null && root.left.val == root.val) {
                //if left node data matches the increment by 1 left node value
                leftBranch += left + 1;
            }

            if (root.right != null && root.right.val == root.val) {
                //if right  node data matches the increment by 1 right node value
                rightBranch += right + 1;
            }

            //Add left and right branch values which include the current value as well if it is
            //same as left and / or right
            //This is compared with the ans declared globally
            // which will take care of breadth wise spanned tree at this level
            ans = Math.max(ans, leftBranch + rightBranch);

            //returns the max of left and right value to be considered depth wise at next level
            return Math.max(leftBranch, rightBranch);
        }
    }
}
