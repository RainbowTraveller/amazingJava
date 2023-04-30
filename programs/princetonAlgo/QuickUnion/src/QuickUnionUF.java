/**
 * Part 2 on the Union Find discussion in terms of dynamic connectivity
 * Given a set of N objects.
 * ・Union command: connect two objects.
 * ・Find/connected query: is there a path connecting the two objects?
 *
 * This is lazy approach, where the invariant is as follows
 * 1. nodes[i] points to the index of the node which is root of this node at index i
 * 2. If two nodes are connected to each other, then they have same root i.e. if p and q are connected then nodes[p] and
 * nodes[q] have same value
 */
public class QuickUnionUF {
    int [] nodes;
    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnionUF quickUnionUF = new QuickUnionUF(n);
        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(!quickUnionUF.isConnected(p, q)) {
                quickUnionUF.union(p, q);
                StdOut.println(p + " : " + q);
            }
        }
    }

    /**
     * For implementation of Quick Union Algorithm for Union Find exercise we need to consider n nodes
     * that we want to consider and experiment with connectivity
     * @param n no. of nodes in the problem domain
     */
    public QuickUnionUF(int n) {
        nodes = new int[n];
        for(int i = 0; i < n; ++i) {
            nodes[i] = i;
        }
    }

    /**
     * Helper function for finding the root. It just traces the array one level at a time and returns a node where the
     * index value is same as the value at the index, indicating the root node
     * @param index this is the node for which the root node needs to be found
     * @return index of the root node
     */
    private int getRoot(int index) {
       while(index < nodes.length && index != nodes[index]) {
          index = nodes[index];
       }
       return nodes[index];
    }

    /**
     * Makes sure point from is connected to point to. This is achieved by replacing all the node values in the backing
     * DS with value of to maintaining invariant that all connected nodes have same value in the backed up array DS. The
     * key difference here is that the value at an index is the index of the root node.
     * Running complexity : As can be seen, the algorithm is named Quick Union, indicating the Union operation is faster
     * Worst case the tree will be linear and then the traversal will be from leaf to root to fine the root. So it will
     * be linear time N
     * @param p index of the source node
     * @param q index of the destination node
     */
    public void union(int p, int q) {
        int pRoot = getRoot(p);
        int qRoot = getRoot(q);
        nodes[pRoot] = qRoot;
    }

    /**
     * Checks if two nodes are connected to each other. While doing union we make sure that two connected nodes have same
     * root eventually.To achieve this we need to fetch the roots for both the nodes and then assign one to other
     * So in this case we fetch the roots for both the nodes and then compare them
     * @param p index of the source node
     * @param q index of the destination node
     * @return true if there is a connected path from node 'from' to the node 'to'
     */
    public boolean isConnected(int p, int q) {
       int pRoot = getRoot(p);
       int qRoot = getRoot(q);
       return pRoot == qRoot;
    }
}