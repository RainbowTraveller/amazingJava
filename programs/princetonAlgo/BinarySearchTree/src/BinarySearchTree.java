import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Search Tree implementation of a symbol table
 * <p>
 * This also contains ordered operations such a min and max.
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    /**
     * The inner class representing the individual nodes in the BST
     */
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int count;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.count = 0;
        }
    }

    public void put(Key key, Value value) {
        Node current = root;
        while (current != null) {
            int flag = current.key.compareTo(key);
            if (flag == 0) {
                current.value = value;
            } else if (flag < 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        current = new Node(key, value);
        current.count = 1 + size(current.left) + size(current.right);
    }

    public Value get(Key key) {
        Node current = root;
        while (current != null) {
            int flag = current.key.compareTo(key);
            if (flag == 0) {
                return current.value;
            } else if (flag < 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return null;
    }

    /**
     * Implements Habbard's Deletion algorithm
     *
     * @param key
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node current, Key key) {
        if (current == null) {
            return null;
        }
        int flag = current.key.compareTo(key);
        if (flag < 0) current.right = delete(current.right, key);
        else if (flag > 0) current.left = delete(current.left, key);
        else {
            if (current.right == null) return current.left;
            if (current.left == null) return current.right;

            Node old = current;
            //Get successor which will be the smallest node in the right tree, this will keep BST form consistent
            current = getMin(old.right);
            //Now delete this min node from the right tree and attach resultant tree to min node found in previous step
            current.right = deleteMin(old.right);
            // Remember original tree node which matched, it may have left subtree, so attach it to successor
            current.left = old.left;
        }
        current.count = 1 + size(current.left) + size(current.right);
        return current;
    }

    /**
     * Prints the keys in the BST in an inorder fashion
     *
     * @return iterator containing keys of BST in inorder traversal fashion
     */
    public Iterable<Key> iterator() {
        Queue<Key> queue = new LinkedList<>();
        inorder(root, queue);
        return queue;
    }

    private void inorder(Node node, Queue<Key> queue) {
        if (node == null) {
            return;
        }
        inorder(node.left, queue);
        queue.add(node.key);
        inorder(node.right, queue);
    }

    /**
     * Returns node associated with the maximum key in the BST
     * This is the node of the rightmost leaf node on the BST
     *
     * @return the node associated with the max key in the BST
     */
    public Node getMax() {
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    /**
     * Returns node associated with the smallest key in the BST
     * This is the node of the leftmost leaf node on the BST
     *
     * @return the node associated with the min key in the BST
     */
    public Node getMin() {
        return getMin(root);
    }

    private Node getMin(Node current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Returns the largest key <= the given key
     * <p>
     * Case 1. [k equals the key at root]
     * The floor of k is k.
     * <p>
     * Case 2. [k is less than the key at root]
     * The floor of k is in the left subtree.
     * <p>
     * Case 3. [k is greater than the key at root]
     * The floor of k is in the right subtree
     * (if there is any key ≤ k in right subtree);
     * otherwise it is the key in the root.
     *
     * @return the largest key <= the given key
     */
    public Key getFloor(Key key) {
        Node current = root;
        while (current != null) {
            if (current.key == key) {
                return key;
            }
            if (current.key.compareTo(key) < 0) {
                if (current.right != null && current.right.key.compareTo(key) > 0) {
                    return current.key;
                } else {
                    current = current.right;
                }
            } else {
                current = current.left;
            }
        }
        return null;
    }

    /**
     * Returns the smallest key >= the given key
     * Case 1. [k equals the key at root]
     * The ceiling of k is k.
     * <p>
     * Case 2. [k is less than the key at root]
     * The ceiling of k is in the right subtree.
     * <p>
     * Case 3. [k is greater than the key at root]
     * The ceiling of k is in the left subtree
     * (if there is any key >= k in left subtree);
     * otherwise it is the key in the root.
     *
     * @return the smallest key >= the given key
     */
    public Key getCeiling(Key key) {

        Node current = root;
        while (current != null) {
            if (current.key == key) {
                return key;
            }
            if (current.key.compareTo(key) > 0) {
                if (current.left != null && current.left.key.compareTo(key) < 0) {
                    return current.key;
                } else {
                    current = current.left;
                }
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    /**
     * Rank. How many keys < k
     *
     * @param key input key value
     * @return rank of the node in the given BST
     */
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    /**
     * To delete the minimum key:
     * ・Go left until finding a node with a null left link.
     * ・Replace that node by its right link.
     * ・Update subtree counts.
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

}