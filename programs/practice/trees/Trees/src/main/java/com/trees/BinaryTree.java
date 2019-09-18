package com.trees;

import com.trees.Node;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class BinaryTree {
    public Node root;

    public BinaryTree() {
        root = null;
    }

    public void insert(int num) {
        return;
    }

    public void inOrder() {
        System.out.println(" :: Inorder Traversal Recursive :: ");
        inOrderHelper(root);
        System.out.println();
    }

    private void inOrderHelper(Node root) {
        if (root == null) {
            return;
        } else {
            inOrderHelper(root.left);
            System.out.print(root);
            inOrderHelper(root.right);
        }
    }

    public void inOrderIterative() {
        Deque<Node> stack = new LinkedList<Node>();
        if (root != null) {
            Node tracker = root;
            System.out.println(" :: Inorder Traversal Iterative :: ");
            // Push root node to stack
            stack.push(tracker);
            int i = 0;
            while (!stack.isEmpty()) {
                Node current = stack.getFirst();
                // push all left nodes of current node to stack
                while (current.left != null) {
                    current = current.left;
                    stack.push(current);
                }
                // Remember for Deque : pop() == removeFirst();
                // Now remove all the nodes we just pushed for which there is no
                // right subtree
                while (!stack.isEmpty() && stack.getFirst().right == null) {
                    current = stack.pop();
                    System.out.print(current);
                }

                // Now we found a node in left subtree which has a right node ( and subtree )
                // it is still sitting on stack top so remove and print it
                if (!stack.isEmpty()) {
                    current = stack.pop();
                    System.out.print(current);
                }
                // Now push right node of this current node just poped
                // to stack and follow same procedure
                if (current != null && current.right != null) {
                    stack.push(current.right);
                    // System.out.println("Stack : " + stack);
                }
            }
        }
        System.out.println();
    }

    public void preOrder() {
        System.out.println(" :: Preorder Traversal Recursive :: ");
        preOrderHelper(root);
        System.out.println();
    }

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
        if (root != null) {
            System.out.println(" :: Preorder Traversal Iterative :: ");
            Deque<Node> stack = new LinkedList<Node>();
            Node tracker = root;
            // Push root to the stack
            stack.push(tracker);
            while (!stack.isEmpty()) {
                Node current = stack.pop();
                while (current != null) {
                    // Immediately print the node
                    System.out.print(current);
                    // Keep track of right subtree on the stack
                    // this will be visited when left subtree is processed
                    if (current.right != null) {
                        stack.push(current.right);
                    }
                    // Make left child as current node and it will be processed
                    // in the next iteration
                    current = current.left;
                }
            }
        }
        System.out.println();
    }

    public void postOrder() {
        System.out.println(" :: Postorder Traversal Recursive :: ");
        postOrderHelper(root);
        System.out.println();
    }

    private void postOrderHelper(Node root) {
        if (root == null) {
            return;
        } else {
            postOrderHelper(root.left);
            postOrderHelper(root.right);
            System.out.print(root);
        }
    }

    public void postOrderItertative() {
        Node current = root;
        Deque<Node> stack = new LinkedList<Node>();
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                // Keep pushing left nodes
                stack.push(current);
                current = current.left;
            } else {
                // No more left nodes, so check nodes
                // to the right of current stack top which is left most node
                // of current sub tree or only node in the sub tree
                Node rightNode = stack.peek().right;
                if (rightNode == null) {
                    Node parent = stack.poll();
                    System.out.print(parent);
                    // Check if current node is stack top's right node
                    // in that case left and right is processes so
                    // time to pop that node as well
                    while (!stack.isEmpty() && parent == stack.peek().right) {
                        parent = stack.poll();
                        System.out.print(parent);
                    } // Remember current is still null
                } else {
                    current = rightNode;
                }
            }
        }
        System.out.println();
    }

    /**
     * Print all paths from root to leaves
     */
    public void printPaths() {
        Deque<Node> path = new LinkedList<Node>();
        pathHelper(root, path);
    }

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
            path.removeLast();
        }
    }

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

    public boolean isSameTree(Node root) {
        return compareHelper(this.root, root);
    }

    private boolean compareHelper(Node tree1, Node tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        } else if (tree1 != null && tree2 != null) {
            return (tree1.data == tree2.data) && compareHelper(tree1.left, tree2.right)
                    && compareHelper(tree1.right, tree2.right);
        } else {
            return false;
        }
    }
}
