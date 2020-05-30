public class MaxPathSum {
    int max = Integer.MIN_VALUE;

    public static void main(String[] args) {

    }

    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    public int helper(TreeNode root) {
        if(root == null) return 0;

        //Get max from left and right side, set to 0 if negative
        int left = Math.max( helper(root.left), 0);
        int right = Math.max( helper(root.right), 0);

        //Here we calculate the entire left to right path from the given node and
        //record it against the max value obtained so far
        max = Math.max(max, root.val + left + right);

        //Pass on the max branch value to upper level so that it can be used by the
        //parent node to decide it's max value path and then it will be compared with
        //max value which is nothing but the through path for one of it's children nodes

        return (root.val + Math.max(left, right));
    }
}

