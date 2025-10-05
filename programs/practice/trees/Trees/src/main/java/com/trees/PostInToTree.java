/**
 * LeetCode 106. Construct Binary Tree from Postorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/?envType=problem-list-v2&envId=vaakbpfe
 *
 * <p>Given two integer arrays postorder and inorder where postorder is the postorder traversal of a
 * binary tree and inorder is the inorder traversal of the same tree, construct and return the
 * binary tree.
 */
package com.trees;

import java.util.HashMap;
import java.util.Map;

public class PostInToTree {
  int index = 0;

  public Node buildTree(int[] inorder, int[] postorder) {
    Map<Integer, Integer> tracker = new HashMap<>();
    index = postorder.length - 1;
    for (int i = 0; i < inorder.length; i++) {
      tracker.put(inorder[i], i);
    }
    return buildTreeFromPostAndInorder(postorder, 0, inorder.length - 1, tracker);
  }

  public Node buildTreeFromPostAndInorder(
      int[] postOrder, int start, int end, Map<Integer, Integer> tracker) {
    if (start > end || index < 0) return null;
    int currVal = postOrder[index--];
    Node root = new Node(currVal);
    System.out.println(currVal + " start : " + start + " end " + tracker.get(currVal));

    // Important: build right subtree first because we are decrementing the index from the end of
    // postorder array
    root.right = buildTreeFromPostAndInorder(postOrder, tracker.get(currVal) + 1, end, tracker);

    root.left = buildTreeFromPostAndInorder(postOrder, start, tracker.get(currVal) - 1, tracker);
    return root;
  }
}
