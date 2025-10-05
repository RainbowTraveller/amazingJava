/**
 * Leetcode Link :
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/?envType=problem-list-v2&envId=vaakbpfe
 *
 * <p>Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * <p>The "linked list" should use the same TreeNode class where the right child pointer points to
 * the next node in the list and the left child pointer is always null.
 *
 * <p>The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
package com.trees;

public class FlatteningBinaryTree {
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
    FlatteningBinaryTree.flatten(bst.root);
    System.out.println("Original Preorder Traversal");
    bst.preOrder();
    Node curr = bst.root;
    System.out.println("Flattened Tree");
    while (curr != null) {
      System.out.print(curr.data + " : ");
      curr = curr.right;
    }
  }

  public static void flatten(Node root) {
    root = helper(root);
  }

  public static Node helper(Node root) {

    // If left and right both are null then return the node to previous level
    if (root == null || (root.left == null && root.right == null)) {
      return root;
    } else {
      Node left = helper(root.left);
      Node right = helper(root.right);

      // Now we have backeup the orginal left and right subtree pointers
      // We can manipulate the current tree pointers
      // Assign the left to the right of the root
      if (left != null) root.right = left;

      // Now check if left has any right subtree
      // if yes then traverse to the right most node of left subtree
      // This is building on the base case at the leaf node where we have taken care of
      // basic structur of root, left and right and this builds on the top of that
      // At basic leverl we have root -> left -> right taken care of
      // Here we reach to the right most node and attach the right subtree
      while (left != null && left.right != null) {
        left = left.right;
      }

      // Attach the original right subtee to the right most node of the left subtree
      if (left != null) {
        left.right = right;
      }
      // Finally make the left subtree as null
      root.left = null;
      return root;
    }
  }
}
