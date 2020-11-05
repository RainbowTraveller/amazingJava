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

public class Solution {

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
            return new Level(true, 0);
        } else {
            if (root.left == null && root.right == null) {
                return new Level(true, root.val);
            }
            Level leftNode = isValidSumTree(root.left);
            Level rightNode = isValidSumTree(root.right);

            if (leftNode.isValid == false || rightNode.isValid == false) {
                return new Level(false, 0);
            }

            if (leftNode.sum + rightNode.sum == root.val) {
                return new Level(true, leftNode.sum + rightNode.sum + root.val);
            }

            return new Level(false, 0);
        }
    }

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

    static class Level {
        public boolean isValid;
        public int sum;

        public Level(boolean isValid, int sum) {
            this.isValid = isValid;
            this.sum = sum;

        }
    }

}
