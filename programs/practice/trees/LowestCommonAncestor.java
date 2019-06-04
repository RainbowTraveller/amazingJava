/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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
                return left == null ? right : left;
            }
        }
    }
}
