import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Deque;

class Graph<E> {

    public static void main(String[] args) {
        Graph<Integer> g = new Graph();
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println(g);
        g.topographicalSort();

        Graph<Character> c = new Graph();
        c.addEdge('e', 'b');
        c.addEdge('e', 'a');
        c.addEdge('d', 'a');
        c.addEdge('d', 'b');
        c.addEdge('c', 'd');
        c.addEdge('a', 'b');
        System.out.println(c);
        c.topographicalSort();
    }
    //No. of Vertices
    private Set<E> vertices;
    //Set of edges
    private Map<E, List<E>> edges;


    public Graph() {
        vertices = new HashSet<E>();
        //Create array of lists as we have finite no. of vertices
        edges = new LinkedHashMap<E, List<E>>();
    }

    public void addEdge(E v,E d) {
        List<E> neighbors = edges.getOrDefault(v, new LinkedList<E>());
        neighbors.add(d);
        edges.put(v, neighbors);
        vertices.add(v);

        //Add destination vertex
        vertices.add(d);
        if(!edges.containsKey(d)) {
            edges.put(d, new LinkedList<E>());
        }
    }

    public Set<E> getVertices() {
        return vertices;
    }

    public Map<E, List<E>> getE() {
        return edges;
    }

    public String toString() {
        StringBuffer graph = new StringBuffer();
        for(E v : edges.keySet()) {
            List<E> neighbors = edges.get(v);
            for(E neighbor : neighbors) {
                graph.append( v.toString() + " : " + neighbor.toString() + ",");
            }
        }
        graph.deleteCharAt(graph.length() - 1);
        return graph.toString();
    }


    public void topographicalSort() {

        //Stack to keep track of vertices
        Deque<E> order = new LinkedList<E>();
        //Tracking visited vertices
        Map<E, Boolean> visited = new HashMap<E, Boolean>();

        //Initialize as not visited
        for(E vertex : vertices) {
            visited.put(vertex, false);
        }

        for(E vertex : vertices) {
            if( !visited.get(vertex)) {
                topographicalSortUtil(vertex, order, visited);
            }
        }

        System.out.println("Topographical Sort : ");
        while(!order.isEmpty()) {
            System.out.print(order.pop() + " ");
        }
        System.out.println();
    }

    /*
     * 1. Mark current vertex as visited
     * 2. Get the neighbors and call the function on each of them
     * 3. Finally when all of them are visited add current one to stack
     *      ensuring that a vertex is on the top of its neighbors
     */
    private void topographicalSortUtil(E v, Deque<E> order, Map<E,Boolean> visited) {
        visited.put(v, true);
        List<E> neighbors = edges.get(v);
        for(E neighbor : neighbors) {
            if( !visited.get(v)) {
                System.out.println("Outer : "  + v);
                topographicalSortUtil( neighbor, order, visited );
            }
        }
        order.push(v);
    }
}
