import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/**
 * Tree is valid if root is sum of all its children values
 *
 * Valid sum tree
 *          64
 *     20        -12
 *  2     18   -8    -4
 *
 * Invalid sum tree
 *         17
 *    8         9
 *  2   6    5     4
 *
 **/

public class SumTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(16);
        root.left = new TreeNode(20);
        root.right = new TreeNode(-12);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(18);
        root.right.left = new TreeNode(-8);
        root.right.right = new TreeNode(-4);

        Level finalLevel = isValidSumTree(root);
        System.out.println(finalLevel.isValid);

    }

    public static Level isValidSumTree(TreeNode root) {
        if (root == null) {
            //If the tree is empty or one of the left or right subtree is empty
            return new Level(true, 0);
        } else {
            //Check if leaf node, valid node in this case
            if (root.left == null && root.right == null) {
                return new Level(true, root.val);
            }
            //Go left and right
            Level leftNode = isValidSumTree(root.left);
            Level rightNode = isValidSumTree(root.right);

            //Check if any of the following condition is met, then invalid
            if (leftNode.sum + rightNode.sum != root.val || leftNode.isValid == false || rightNode.isValid == false) {
                return new Level(false, 0);
            }
            //Looks like valid upto current level, so propagate the result to the parent level
            return new Level(true, leftNode.sum + rightNode.sum + root.val);
        }
    }

    /**
      Representing the tree node
      */
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

    }
    /**
      Helper class enabling propagate the status across levels
      */
    static class Level {
        public boolean isValid;
        public int sum;

        public Level(boolean isValid, int sum) {
            this.isValid = isValid;
            this.sum = sum;

        }
    }

}
