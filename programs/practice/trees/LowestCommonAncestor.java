/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }


 The aim it so find the common ancestor for 2 given node in a binary tree.
 This has to be the first common ancestor hence called lowest as in farthest from the root node
 */
class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
           Important logic
           There are two conditions
           1. A path will have the desired node
                    In this case the node which is found is returned and it will propagate to the upper levels
           2. A path will not contain the desired node
                    Here only null node which is one of the left or right of a leaf node will propagate up
           */
        if( root == null ||  root.val == p.val || root.val == q.val) {
            return root;
        } else {
            TreeNode left = lowestCommonAncestor( root.left, p, q);
            TreeNode right = lowestCommonAncestor( root.right, p, q);
            //if both left and right are not null then we found
            //current node as common ancestor
            if( left != null && right !=null) {
                return root;
            } else {
                // This condition will return only null from a path where the desired node is not encounterd
                // In case the node is encounterd it will keep returning that node
                return left == null ? right : left;
            }
        }
    }
}
