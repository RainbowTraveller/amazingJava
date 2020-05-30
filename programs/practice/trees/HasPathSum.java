/*  Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
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
*   /  \      \
*  7    2      1
*  return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/


public class HasPathSum {
    public static void main(String[] args) {

    }

    /*
     * Simple problem but gets tricky if the values in the tree are negative.
     * So just be careful while testing
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        //Reject plainly if root is null
        //initially or later in the traversal as this condition should be be reached
        //if sum is zero and root is null it should be handled already
        if(root == null) {
            return false;
        }

        sum -= root.val;
        //This is the crux of the logic
        if(root.left == null && root.right == null && sum == 0) {
            return true;
        }

        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}

