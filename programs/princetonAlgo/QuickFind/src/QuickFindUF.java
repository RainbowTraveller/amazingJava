import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Part 1 on the Union Find discussion in terms of dynamic connectivity
 * Given a set of N objects.
 * ・Union command: connect two objects.
 * ・Find/connected query: is there a path connecting the two objects?
 *
 * This is greedy or eager approach, where the invariant is as follows
 * If two nodes are connected to each other the values at that index in the backup array DS is same
 */
public class QuickFindUF {
    // Data structure mapping the nodes. This is array of n nodes which is indexed from 0 to n - 1
    int [] nodes;
    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF quickFindUF = new QuickFindUF(n);
        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(!quickFindUF.isConnected(p, q)) {
                quickFindUF.union(p, q);
                StdOut.println(p + " : " + q);
            }
        }
    }

    /**
     * For implementation of Quick Find Algorithm for Union Find exercise we need to consider n nodes
     * that we want to consider and experiment with connectivity
     * @param n no. of nodes in the problem domain
     */
    public QuickFindUF(int n) {
        nodes = new int[n];
        //To begin with the array is initialized with index value of the node
        for(int i = 0; i < n; i++) {
            nodes[i] = i;
        }
    }

    /**
     * Checks if two nodes are connected to each other. While doing union we make sure that two connected nodes have same
     * value in the backed up DS at node's index
     * @param from index of the source node
     * @param to index of the destination node
     * @return true if there is a connected path from node 'from' to the node 'to'
     */
    public boolean isConnected(int from, int to) {
        return nodes[from] == nodes[to];
    }

    /**
     * Makes sure point from is connected to point to. This is achieved by replacing all the node values in the backing
     * DS with value of to maintaining invariant that all connected nodes have same value in the backed up array DS
     *
     * Running complexity : As can be seen, the algorithm is named Quick Find, indicating the find operation is faster
     * The union on the other hand needs entire array access for establishing invariant. So for N union commands, one
     * for each node, N array scans are required, leading to N * N complexity
     * @param from index of the source node
     * @param to index of the destination node
     */
    public void union(int from, int to) {
        if(nodes[from] != nodes[to]) {
            int toId = nodes[to];
            int fromId = nodes[from];
            for(int i = 0; i < nodes.length; ++i) {
                if(nodes[i] == fromId) {
                    nodes[i] = toId;
                }
            }
        }
    }
}