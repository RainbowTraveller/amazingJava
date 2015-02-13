import java.util.Random;
import java.util.LinkedList;
/*
 * This class implements Trinary tree. In addition to classic BST, it adds a
 * middle node, which contains same value as that of the parent node
 */
public class TrinaryTree {
    public Node root;//ROOT

    /*
     * Constructor
     */
    public TrinaryTree() {
        root = null;
    }
    /*
     * Constructor with data for root node
     */
    public TrinaryTree(int data) {
        root = new Node(data);
    }

    /*
     * Inserts new node in the tree depending on its value
     *
     * @param data number to be inserted into the tree
     */
    public void insert(int data) {
        //Calls private helper funtion
        root = insert(root, data);
    }

    /*
     * Inserts new node in the tree depending on its value
     * This is helper function to facilitate recursion
     *
     * @param ptr current node reference, starts with root
     * @param data number to be inserted into the tree
     */
    private Node insert(Node ptr, int data) {
        if(ptr == null) {
            ptr = new Node(data);
        } else if(ptr.data > data) {
            //follow left subtree
            ptr.left = insert(ptr.left, data);
        } else if(ptr.data < data) {
            //follow right subtree
            ptr.right = insert(ptr.right, data);
        } else {
            //follow middle subtree
            ptr.middle = insert(ptr.middle, data);
        }
        return ptr;
    }

    /*
     * Deletes node containing data
     * @param data number to be deleted from the tree
     */
    public void delete(int data) {
        //Calls helper funtion
        delete(root, data);
    }

    /*
     * Deletes node containing data
     * This is helper function to facilitate recursion
     * @param ptr current node reference, starts with root
     * @param data number to be deleted from the tree
     */
    private Node delete(Node ptr, int data) {
        if(ptr == null) {
            return null;
        } else if(ptr.data > data) {
            //Continue searching left subtree
            ptr.left = delete(ptr.left, data);
        } else if (ptr.data < data) {
            //Continue searching right subtree
            ptr.right = delete(ptr.right, data);
        } else if(ptr.data == data) {
            //if leaf node, just remove it
            if(ptr.left == null && ptr.right == null) {
                removeNode(ptr);
                ptr = null;
            } else if (ptr.left == null) {
                //replace by right subtree
                Node temp = ptr.right;
                removeNode(ptr);
                ptr = temp;
            } else if(ptr.right == null) {
                //replace by left subtree
                Node temp = ptr.left;
                removeNode(ptr);
                ptr = temp;
            } else if(ptr.left != null && ptr.right != null) {
                //replace by the max value from left subtree to maintain constraint
                Node maxNode = findMax(ptr.left);
                if(maxNode != ptr.left) {
                    maxNode.left = ptr.left;
                }
                maxNode.right = ptr.right;
                removeNode(ptr);
                ptr = maxNode;
            }
        }
        return ptr;
    }
    /*
     * Removes a node from the tree
     * It traverses the middle link and removes all duplicates as well
     *
     * @param ptr node reference to be deleted
     *
     */
    private void removeNode(Node ptr) {
        if(ptr == null) {
            return;
        } else {
            removeNode(ptr.middle);
            ptr.left = null;
            ptr.middle = null;
            ptr.right = null;
        }
    }

    /*
     * Returns the node reference containing maximum value from a given tree
     *
     * @param ptr root node reference of the tree
     */
    private Node findMax(Node ptr) {
        Node maxNode = ptr;
        Node maxParent = null;
        while(maxNode.right != null) {
           maxParent = maxNode;
           maxNode = maxNode.right;
        }
        if(maxParent != null) {
            // disconnect from the current position
            maxParent.right = null;
        }
        return maxNode;
    }

    /*
     * Prints the tree in inorder fashion
     */
    public void print(){
        //Calls helper funtion
       inorderPrinting(root);
       System.out.println();
    }

    /*
     * Prints the tree in inorder fashion
     * This is helper funtion
     *
     * @param ptr node refernce
     */
    private void inorderPrinting(Node ptr) {
        if(ptr == null) {
            return;
        } else {
            inorderPrinting(ptr.left);
            Node temp = ptr.middle;
            System.out.print(" " + ptr.data + " ");
            //indicates children nodes with same values as {}
            while(temp != null) {
                System.out.print(" { " + temp.data + " } ");
                temp  = temp.middle;
            }
            inorderPrinting(ptr.right);
        }
    }

    /*
     * Inner class representing node of the tree
     */
    class Node {
        public int data;
        public Node left;
        public Node middle;
        public Node right;

        public Node() {
            this(0);
        }
        public Node(int data) {
            this.data = data;
            left = null;
            middle = null;
            right = null;
        }
    }

    /*
     * Demos insertion operation
     */
    public static void insertionTest() {
        System.out.println(": Insertion Demo :");
        TrinaryTree tt = new TrinaryTree(50);
        LinkedList<Integer> ll = new LinkedList<Integer>();
        Random rng = new Random();
        for(int i=0; i<10; ++i) {
            int randomint = rng.nextInt(100);
            System.out.println("\nInserting : " + randomint);
            tt.insert(randomint);
            tt.print();
            if(randomint % 2 == 0) {
                System.out.println("\nInserting : " + randomint);
                tt.insert(randomint);
            }
            tt.print();
            ll.add(randomint);
        }
        System.out.println("\n : Inorder Traversal of Final Tree : ");
        tt.print();
    }

    /*
     * Demos deletion operation
     */
    public static void deletionTest() {
        System.out.println(": Deletion Demo :");
        TrinaryTree tt = new TrinaryTree(50);
        LinkedList<Integer> ll = new LinkedList<Integer>();
        Random rng = new Random();
        for(int i=0; i<10; ++i) {
            int randomint = rng.nextInt(100);
            tt.insert(randomint);
            if(randomint % 2 == 0) {
                tt.insert(randomint);
            }
            ll.add(randomint);
        }
        System.out.println(": Original Tree :(inorder) ");
        tt.print();
        System.out.println();
        for(int i = 0; i < ll.size(); ++i) {
            int numToDelete = ll.get(rng.nextInt(ll.size()));
            System.out.println("DELETING : " + numToDelete);
            tt.delete(numToDelete);
            tt.print();
        }
    }

    public static void main(String args[]) {
        insertionTest();
        deletionTest();
    }
}
