package com.trees;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * BinaryTree class that provides various tree traversal methods (in-order, pre-order, post-order),
 * both recursively and iteratively, as well as methods to print all root-to-leaf paths and compare
 * two trees for equality.
 */
public class BinaryTree {
  public Node root;

  public BinaryTree() {
    root = null;
  }

  public void insert(int num) {
    return;
  }

  /** Inorder Traversal : Left, Root, Right */
  public void inOrder() {
    System.out.println(" :: Inorder Traversal Recursive :: ");
    inOrderHelper(root);
    System.out.println();
  }

  /** Inorder Hellper */
  private void inOrderHelper(Node root) {
    if (root == null) {
      return;
    } else {
      inOrderHelper(root.left);
      System.out.print(root);
      inOrderHelper(root.right);
    }
  }

  /** Inorder Traversal Iterative */
  public void inOrderIterative() {
    // Base case
    if (root == null) {
      return;
    }

    // Stack to keep track of nodes
    Deque<Node> stack = new LinkedList<>();
    // Start from the root node
    Node current = root;
    System.out.println("Inorder Traversal (Iterative): ");

    // Loop until all nodes are processed
    while (current != null || !stack.isEmpty()) {
      // Traverse down the left side, pushing nodes onto the stack
      while (current != null) {
        stack.push(current);
        current = current.left;
      }

      // Pop the top node, process it, and move to its right subtree
      current = stack.pop();
      System.out.print(current.data + " ");
      current = current.right;
    }
  }

  /** Preorder Traversal : Root, Left, Right */
  public void preOrder() {
    System.out.println(" :: Preorder Traversal Recursive :: ");
    preOrderHelper(root);
    System.out.println();
  }

  /** Preorder Helper */
  private void preOrderHelper(Node root) {
    if (root == null) {
      return;
    } else {
      System.out.print(root);
      preOrderHelper(root.left);
      preOrderHelper(root.right);
    }
  }

  public void preOrderIterative() {

    // Base case
    if (root == null) {
      return;
    }

    // Stack to keep track of nodes
    Deque<Node> stack = new LinkedList<>();
    // Start from the root node
    stack.push(root);

    System.out.println(" :: Preorder Traversal Iterative :: ");

    // Loop until all nodes are processed
    while (!stack.isEmpty()) {
      // Pop the top node
      Node current = stack.pop();

      // Process the node (e.g., print it)
      System.out.print(current.data + " ");

      // Push right child first, so left child is processed next
      if (current.right != null) {
        stack.push(current.right);
      }

      // Push left child last
      if (current.left != null) {
        stack.push(current.left);
      }
    }
    System.out.println();
  }

  /** Postorder Traversal : Left, Right, Root */
  public void postOrder() {
    System.out.println(" :: Postorder Traversal Recursive :: ");
    postOrderHelper(root);
    System.out.println();
  }

  /** Postorder Helper */
  private void postOrderHelper(Node root) {
    if (root == null) {
    } else {
      postOrderHelper(root.left);
      postOrderHelper(root.right);
      System.out.print(root);
    }
  }

  /** Postorder Traversal Iterative */
  public void postOrderItertative() {
    // Base case
    if (root == null) {
      return;
    }

    // Stack to keep track of nodes
    Deque<Node> stack = new LinkedList<>();
    // Start from the root node
    Node current = root;
    // To keep track of the last visited node
    Node lastVisitedNode = null;

    System.out.println("Postorder Traversal (Iterative - Single Stack): ");

    while (current != null || !stack.isEmpty()) {
      if (current != null) {
        // If there's a current node, push it and go left
        stack.push(current);
        current = current.left;
      } else {
        // Peek at the node on top of the stack
        Node peekNode = stack.peek();

        // If it has a right child and that right child hasn't been visited yet
        if (peekNode.right != null && peekNode.right != lastVisitedNode) {
          // Go right to process the right subtree
          current = peekNode.right;
        } else {
          // The node's left and right subtrees are fully processed
          Node processedNode = stack.pop();
          System.out.print(processedNode.data + " ");
          // Mark this node as last visited
          // This is crucial to avoid re-processing
          // the right subtrees
          lastVisitedNode = processedNode;
        }
      }
    }
    System.out.println();
  }

  /** Print all paths from root to leaves */
  public void printPaths() {
    Deque<Node> path = new LinkedList<Node>();
    pathHelper(root, path);
  }

  /**
   * Print Paths Helper The logic is as follows:
   *
   * <p>If the current node is null, return.
   *
   * <p>Add the current node to the path.
   *
   * <p>If the current node is a leaf (no left or right child), print the path
   *
   * <p>Recursively call the helper for the left and right children.
   *
   * <p>Backtrack by removing the current node from the path.
   *
   * @param root this is the current node
   * @param path this is the path from root to current node
   */
  private void pathHelper(Node root, Deque<Node> path) {
    if (root == null) {
      return;
    } else {
      path.add(root);
      if (root.left != null) {
        pathHelper(root.left, path);
      }

      if (root.right != null) {
        pathHelper(root.right, path);
      }

      if (root.left == null && root.right == null) {
        System.out.println("Path :" + path);
      }
      // Backtrack - remove the current node from the path
      path.removeLast();
    }
  }

  /**
   * Zigzag Level Order Traversal The logic is as follows:
   *
   * <p>Use a queue to perform a level-order traversal of the tree.
   *
   * <p>Keep track of the current level number to determine the order of insertion.
   *
   * <p>For even levels, add node values to the current level list in normal order.
   *
   * <p>For odd levels, add node values to the current level list in reverse order.
   *
   * <p>After processing each level, add the current level list to the result and increment the
   * level counter.
   *
   * <p>Continue until all levels are processed.
   *
   * @param root the root of the binary tree
   * @return a list of lists containing the zigzag level order traversal of the tree
   */
  public List<List<Integer>> zigzagLevelOrder(Node root) {
    List<List<Integer>> result = new LinkedList<List<Integer>>();
    Deque<Node> queue = new LinkedList<Node>();
    int level = 0;
    if (root != null) {
      queue.addLast(root);
      while (!queue.isEmpty()) {
        int len = queue.size();
        List<Integer> currLevel = new LinkedList<Integer>();
        for (int i = 0; i < len; ++i) {
          Node curr = queue.removeFirst();
          currLevel.add(curr.data);

          if (curr.left != null) {
            queue.addLast(curr.left);
          }

          if (curr.right != null) {
            queue.addLast(curr.right);
          }
        }
        if (level % 2 != 0) {
          Collections.reverse(currLevel);
        }
        result.add(currLevel);
        level++;
      }
    }
    return result;
  }

  /** Compare two trees for equality */
  public boolean isSameTree(Node root) {
    return compareHelper(this.root, root);
  }

  /**
   * Compare Helper The logic is as follows:
   *
   * <p>- If both nodes are null, they are equal.
   *
   * <p>- If both nodes are non-null, check if their data is equal and recursively check their left
   * and right children.
   *
   * <p>- If one node is null and the other is not, they are not equal.
   *
   * @param tree1 the root of the first tree
   * @param tree2 the root of the second tree
   * @return true if the trees are identical, false otherwise
   */
  private boolean compareHelper(Node tree1, Node tree2) {
    if (tree1 == null && tree2 == null) {
      return true;
    } else if (tree1 != null && tree2 != null) {
      return (tree1.data == tree2.data)
          && compareHelper(tree1.left, tree2.right)
          && compareHelper(tree1.right, tree2.right);
    } else {
      return false;
    }
  }

  /**
   * Validate if the tree is a Binary Search Tree (BST) The logic is as follows:
   *
   * <p>- Use a helper function that takes the current node and the valid range (low, high) for its
   * data.
   *
   * <p>- If the current node is null, return true (base case).
   *
   * <p>- Check if the current node's data is within the valid range. If not, return false.
   *
   * <p>- Recursively validate the left subtree with an updated high value (current node's data) and
   * the right subtree with an updated low value (current node's data).
   *
   * <p>- Return true if both subtrees are valid.
   *
   * @param root the root of the binary tree
   * @return true if the tree is a valid BST, false otherwise
   */
  public boolean isValidBST() {
    return validate(root, null, null);
  }

  /**
   * Validate Helper
   *
   * @param root the current node
   * @param low the lower bound for the current node's data
   * @param hight the upper bound for the current node's data
   * @return true if the subtree rooted at the current node is a valid BST, false otherwise
   */
  private boolean validate(Node root, Integer low, Integer high) {
    if (root == null) return true;
    if ((low != null && root.data <= low) || (high != null && root.data >= high)) {
      return false;
    }
    return validate(root.left, low, root.data) && validate(root.right, root.data, high);
  }

  /**
   * Recover a BST where two nodes are swappped by mistake The logic is as follows:
   *
   * <p>Perform an in-order traversal of left subtree, add root and then right subtree and get
   * values in a list
   *
   * <p>Find the two swapped values in the list where the order is violated. There will be maximum
   * two such violations.
   *
   * <p>Correct the tree by swapping the two node back in the positions
   *
   * @param root of the binary serach tree
   */
  public void recoverTree(Node root) {
    List<Integer> order = new LinkedList<>();
    if (root != null) {
      inorder(root.left, order);
      order.add(root.data);
      inorder(root.right, order);
      int[] swapped = findSwapped(order);
      correctTree(root, 2, swapped[0], swapped[1]);
    }
  }

  /**
   * Correct the tree by swapping the two nodes back
   *
   * @param root the current node
   * @param count number of nodes to be swapped
   * @param x first value to be swapped
   * @param y second value to be swapped
   */
  public void correctTree(Node root, int count, int x, int y) {
    if (root != null) {
      if (root.data == x || root.data == y) {
        int change = root.data == x ? y : x;
        root.data = change;
        if (--count == 0) return;
      }
      correctTree(root.left, count, x, y);
      correctTree(root.right, count, x, y);
    }
  }

  /**
   * Find the two swapped values in the list where the order is violated. There will be maximum two
   * such violations.
   *
   * @param nums the list of node values in in-order traversal
   * @return an array containing the two swapped values
   */
  public int[] findSwapped(List<Integer> nums) {
    int x = 0, y = 0;
    boolean isFirstOccurrence = true;
    for (int i = 0; i < nums.size() - 1; i++) {
      if (nums.get(i + 1) < nums.get(i)) {
        y = nums.get(i + 1);
        if (isFirstOccurrence) {
          x = nums.get(i);
          isFirstOccurrence = false;
        } else {
          break;
        }
      }
    }
    return new int[] {x, y};
  }

  /**
   * Inorder Traversal Helper to get the node values in a list
   *
   * @param root the current node
   * @param nums the list to store the node values
   */
  public void inorder(Node root, List<Integer> nums) {
    if (root == null) {
      return;
    } else {
      inorder(root.left, nums);
      nums.add(root.data);
      inorder(root.right, nums);
    }
  }
}
