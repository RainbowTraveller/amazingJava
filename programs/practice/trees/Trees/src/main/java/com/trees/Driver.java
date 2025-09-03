package com.trees;

/**
 * Driver class to test the BinarySearchTree implementation by inserting elements and printing
 * various traversals and paths.
 */
public class Driver {
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
    // bst.inOrder();
    // bst.preOrder();
    // bst.postOrder();
    bst.preOrderIterative();
    // bst.inOrderIterative();
    // bst.postOrderItertative();
    // bst.printPaths();
  }
}
