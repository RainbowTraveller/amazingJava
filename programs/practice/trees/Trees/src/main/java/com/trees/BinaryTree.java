package com.trees;

import com.trees.Node;
import java.util.Deque;
import java.util.LinkedList;


public class BinaryTree {
    public Node root;

    public BinaryTree() {
        root = null;
    }

    public void insert( int num ) {
        return;
    }

    public void inOrder() {
        System.out.println(" :: Inorder Traversal Recursive :: ");
        inOrderHelper(root);
        System.out.println();
    }

    private void inOrderHelper(Node root) {
        if(root == null) {
            return;
        } else {
            inOrderHelper(root.left);
            System.out.print(root);
            inOrderHelper(root.right);
        }
    }

    public void inOrderIterative() {
        Deque<Node> stack = new LinkedList<Node>();
        if(root != null) {
            Node tracker = root;
            System.out.println(" :: Inorder Traversal Iterative :: ");
            stack.push(tracker);
            while(!stack.isEmpty()) {
                Node current = stack.getLast();
                while(current.left != null) {
                    current = current.left;
                    stack.push(current);
                }
                stack.push(current);
                while(!stack.isEmpty() && stack.getLast().right == null) {
                    current = stack.pop();
                    System.out.print(current);
                }
                stack.push(stack.getLast().right);
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
        if(root == null) {
            return;
        } else {
            System.out.print(root);
            preOrderHelper(root.left);
            preOrderHelper(root.right);
        }
    }

    public void preOrderIterative() {
        if(root != null) {
            System.out.println(" :: Preorder Traversal Iterative :: ");
            Deque<Node> stack = new LinkedList<Node>();
            Node tracker = root;
            stack.push(tracker);
            while(!stack.isEmpty()) {
                Node current = stack.pop();
                while(current != null ) {
                    System.out.print(current);
                    if(current.right != null) {
                        stack.push(current.right);
                    }
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
        if(root == null) {
            return;
        } else {
            postOrderHelper(root.left);
            postOrderHelper(root.right);
            System.out.print(root);
        }
    }
}
