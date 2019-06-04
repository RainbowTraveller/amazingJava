/*
 *
 * Add the values of two nodes at the same level
 * The two trees may not be identical to each other meaning either of the
 * tree may not have corresponding node for the other tree
 *
 */
public class MergeBT {
    //Definition for a binary tree node.
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        //If both are null nothing to do
        if(t1 == null && t2 == null) {
            return null;
        } else {
            //Take whichever is not null
            if(t1 != null && t2 == null) {
                return t1;
            } else if ( t2 != null && t1 == null) {
                return t2;

            } else {
                //both are not null so process
                t1.val += t2.val;
                t1.left = mergeTrees(t1.left, t2.left);
                t1.right = mergeTrees(t1.right, t2.right);
                return t1;
            }
        }

    }
}
