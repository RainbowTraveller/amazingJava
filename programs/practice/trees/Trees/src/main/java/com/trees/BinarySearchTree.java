package com.trees;

/**
 * BinarySearchTree class that extends BinaryTree and provides an insert method to add elements
 * while maintaining the binary search tree property.
 */
public class BinarySearchTree extends BinaryTree {

  /**
   * Inserts a number into the binary search tree.
   *
   * @param num the number to insert
   */
  public void insert(int num) {
    root = insertHelper(root, num);
  }

  /**
   * Helper method to recursively insert a number into the tree. The logic is as follows:
   *
   * <p>- If the current node is null, create a new node with the number.
   *
   * <p>- If the number is less than or equal to the current node's data, recurse left.
   *
   * <p>- If the number is greater than the current node's data, recurse right.
   *
   * <p>- Return the current node after insertion.
   *
   * @param tracker the current node being examined
   * @param num the number to insert
   * @return the updated node after insertion
   */
  private Node insertHelper(Node tracker, int num) {
    if (tracker == null) {
      return new Node(num);
    } else {
      if (num <= tracker.data) {
        // System.out.println("Going left");
        tracker.left = insertHelper(tracker.left, num);
      } else {
        // System.out.println("Going right");
        tracker.right = insertHelper(tracker.right, num);
      }
      return tracker;
    }
  }
}
