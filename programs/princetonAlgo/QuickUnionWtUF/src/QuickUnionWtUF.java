/**
 * Part 3 on the Union Find discussion in terms of dynamic connectivity
 * Given a set of N objects.
 * ・Union command: connect two objects.
 * ・Find/connected query: is there a path connecting the two objects?
 *
 *  We minimized the traversal from N * N to N in quick union. To reduce it further, we need to keep the height of the
 *  tree as minimum as possible to control the N. To achieve this we track size of the tree rooted at each node
 */
public class QuickUnionWtUF {
    int [] nodes;
    int [] sz;
    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnionWtUF quickUnionWtUF = new QuickUnionWtUF(n);
        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(!quickUnionWtUF.isConnected(p, q)) {
                quickUnionWtUF.union(p, q);
                StdOut.println(p + " : " + q);
            }
        }
    }

    /**
     * For implementation of Quick Union Algorithm for Union Find exercise we need to consider n nodes
     * that we want to consider and experiment with connectivity
     * @param n no. of nodes in the problem domain
     */
    public QuickUnionWtUF(int n) {
        nodes = new int[n];
        sz = new int[n];
        for(int i = 0; i < n; ++i) {
            nodes[i] = i;
            sz[i] = 1;
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
            /**
             * This is the improvement to further improve the algorithm. We make root of a node point to its
             * grandparent that way halving the path length
             */
            nodes[index] = nodes[nodes[index]];
            index = nodes[index];
        }
        return nodes[index];
    }


    /**
     * Makes sure point from is connected to point to. This is achieved by replacing all the node values in the backing
     * DS with value of to maintaining invariant that all connected nodes have same value in the backed up array DS. The
     * key difference here is that the value at the index is the index of the root node. Moreover the size element comes
     * into the picture. When we always choose a tree with the smaller height to be child of the tree with greater
     * height. That way we keep the height of the overall tree in check.
     * Further improvement : While performing union operation, we traverse the tree bottom up. In this process we also
     * visit immediate parent on the way to the root. To further optimize the tree height, we can point the parents on
     * way to be children of the root.
     * Running complexity : Due to height control mechanism we make the traverse time of the order of lg N
     * @param p index of the source node
     * @param q index of the destination node
     */
    public void union(int p, int q) {
        int pRoot = getRoot(p);
        int qRoot = getRoot(q);
        if(pRoot == qRoot) return;
        if(sz[pRoot] > sz[qRoot]) {
            nodes[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            nodes[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
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