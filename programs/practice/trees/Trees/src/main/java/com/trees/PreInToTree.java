/**
 * LeetCode 105. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/?envType=problem-list-v2&envId=vaakbpfe
 *
 * <p>Given two integer arrays preorder and inorder where preorder is the preorder traversal of a
 * binary tree and inorder is the inorder traversal of the same tree, construct and return the
 * binary tree.
 */
package com.trees;

import java.util.HashMap;
import java.util.Map;

public class PreInToTree {
  int index = 0;

  public Node buildTree(int[] preorder, int[] inorder) {
    // Track the position of each node in the inorder array
    Map<Integer, Integer> indexTracker = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      indexTracker.put(inorder[i], i);
    }
    return helper(preorder, 0, inorder.length - 1, indexTracker);
  }

  /**
   * This is a recursive function which will build the tree in a top down manner As each node is
   * being created we will find its position in the inorder array and then split the inorder array
   * into left and right subtree Preorder array contains the root node at each level so we will pick
   * the node from preorder array and then find its position in the inoder array
   *
   * <p>Not when we find the position of a node in inorder array we can split the inorder array into
   * left and right subtree because the inoder array maintains the left root right structure. So all
   * the nodes to the left of the root node in inoder array will be part of left subtree and
   * similarly for the right subtree
   *
   * @param preorder preorder array
   * @param start start index of inorder array
   * @param end end inded of inorder array
   * @param indexTracker map to track the index of each node in inorder array
   * @return
   */
  public Node helper(int[] preorder, int start, int end, Map<Integer, Integer> indexTracker) {
    if (start > end) return null;

    // Pick the current node from preorder array and increment the index
    int preorderNodeVal = preorder[index++];
    Node root = new Node(preorderNodeVal);

    // Now from the index of this node in inorder array we can construct left and right subtree
    root.left = helper(preorder, start, indexTracker.get(preorderNodeVal) - 1, indexTracker);
    root.right = helper(preorder, indexTracker.get(preorderNodeVal) + 1, end, indexTracker);
    return root;
  }
}
