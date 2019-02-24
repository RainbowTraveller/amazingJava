package com.trees;

import com.trees.BinaryTree;
import com.trees.Node;

public class BinarySearchTree extends BinaryTree {

    public void insert(int num) {
        root = insertHelper(root, num);
    }

    private Node insertHelper(Node tracker, int num) {
        if(tracker == null) {
            return new Node(num);
        } else {
            if( num <= tracker.data ) {
                //System.out.println("Going left");
                tracker.left = insertHelper(tracker.left, num);
            } else {
                //System.out.println("Going right");
                tracker.right = insertHelper(tracker.right, num);
            }
            return tracker;
        }
    }
}
