   /*
    *  The thief has found himself a new place for his thievery again.
    *  There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house.
    *  After a tour, the smart thief realized that "all houses in this place forms a binary tree".
    *  It will automatically contact the police if two directly-linked houses were broken into on the same night.
    *
    *  Determine the maximum amount of money the thief can rob tonight without alerting the police.
    *
    *  Example 1:
    *
    *  Input: [3,2,3,null,3,null,1]
    *
    *       3
    *      / \
    *     2   3
    *      \   \
    *       3   1
    *
    *  Output: 7
    *  Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
    *  Example 2:
    *
    *  Input: [3,4,5,1,3,null,1]
    *
    *       3
    *      / \
    *     4   5
    *    / \   \
    *   1   3   1
    *
    *  Output: 9
    *  Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
    */

package com.trees;

import com.trees.Node;

public class TreeHouseRobber {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(8);
        bst.insert(15);
        bst.insert(17);
        bst.insert(9);
        bst.insert(2);
        bst.insert(7);
        bst.insert(16);
        bst.insert(19);
        bst.insert(18);
        int[] result =  helper(bst.root);
        System.out.println("Max amount that can be robbed : " + Math.max(result[0],result[1]));
    }

    //Modification of Post Order Traversal
    public static int[] helper(Node root) {
        //int[0] largest num in current layer
        //int[1] largest num in children layer
        int [] result = new int[2];

        if(root == null) {
            //No values return empty array
            return result;
        }
        //current layer element is root value itself
        //next layer is empty, so 0
        if(root.left == null && root.right == null) {
            result[0] = root.data;
            result[1] = 0;
        }
        else {

            //Get both left and right arrays
            int [] left = helper(root.left);
            int [] right = helper(root.right);

            //elements at index 1 are next to next layer elements, so
            //they are not connected so add them to current one.
            //This forms greatest at current level
            result[0] = left[1] + right[1] + root.data;
            //To form greatest at next level, determine greater between
            //corresponding left and right elements from next level, as they are not
            //connected to one level above, we don't mind if they are connected to
            //this level which might be  the case if they 0th index elements, but for one
            //level above it does not matter so choose greatest
            result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        }
        return result;
    }
}
