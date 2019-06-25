/*
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv,
 * vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
 * In DFS, we print a vertex and then recursively call DFS for its adjacent vertices. In topological sorting, we need to print a vertex before its adjacent vertices.
 * For example, in the given graph, the vertex ‘5’ should be printed before vertex ‘0’, but unlike DFS, the vertex ‘4’ should also be printed before vertex ‘0’.
 * So Topological sorting is different from DFS.
 */

import java.util.List;
import java.util.Deque;
import java.util.LinkedList;

public class TopographicalSort {

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println(g);
        g.topographicalSort();
    }
}

class Graph {

    //No. of Vertices
    private int v;
    //Set of edges
    private List<Integer> [] e;


    public Graph(int v) {
        this.v = v;
        //Create array of lists as we have finite no. of
        //vertices NB : drop <Integer> from LinkedList
        e = new LinkedList[v];

        //for each vertex create an empty list of
        //neighboring vertices
        for(int i = 0; i < v ; ++i) {
            e[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int v, int d) {
        //Check if both source and destination
        //vertices are within the bound
        if( v < this.v && d < this.v) {
            e[v].add(d);
        }
    }
    public int getV() {
        return v;
    }

    public List<Integer>[] getE() {
        return e;
    }

    public String toString() {
        StringBuffer graph = new StringBuffer();
        for(int i = 0; i < v; ++i) {
            List<Integer> neighbors = e[i];
            for(Integer to : neighbors) {
                graph.append( i + " : " + to + ", ");
            }
        }
        graph.deleteCharAt(graph.length() - 1);
        return graph.toString();
    }


    public void topographicalSort() {

        //Stack to keep track of vertices
        Deque<Integer> order = new LinkedList<Integer>();
        //Tracking visited vertices
        boolean [] visited = new boolean[v];

        //Initialize as not visited
        for(int i = 0; i < v; ++i) {
            visited[i] = false;
        }

        for(int i = 0; i < v; ++i) {
            if( !visited[i] ) {
                topographicalSortUtil(i, order, visited);
            }
        }

        System.out.print("Topographical Sort : ");
        while(!order.isEmpty()) {
            System.out.print(order.pop() + " ");
        }
    }

    /*
     * 1. Mark current vertex as visited
     * 2. Get the neighbors and call the function on each of them
     * 3. Finally when all of them are visited add current one to stack
     *      ensuring that a vertex is on the top of its neighbors
     */
    private void topographicalSortUtil(int v, Deque<Integer> order, boolean[] visited) {
        visited[v] = true;
        List<Integer> neighbors = e[v];
        for(Integer neighbor : neighbors) {
            if( !visited[neighbor] ) {
                topographicalSortUtil( neighbor, order, visited );
            }
        }
        order.push(v);
    }
}
