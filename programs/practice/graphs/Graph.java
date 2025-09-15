/*
 * Logic works only for DAG and not for cyclic graphs
 */
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    c.addEdge('a', 'e');
    System.out.println(c);
    c.topographicalSort();
  }

  // No. of Vertices
  private Set<E> vertices;
  // Set of edges
  private Map<E, List<E>> edges;

  /** Constructor */
  public Graph() {
    // Set of vertices in the graph
    vertices = new HashSet<E>();
    // Create array of lists as we have finite no. of vertices
    // This is a mapping between a vertex and other vertices it is connected to
    edges = new LinkedHashMap<E, List<E>>();
  }

  /**
   * Add an edge from vertex v to vertex d
   *
   * @param v starting point of the edge
   * @param e ending point vertex of the edge
   */
  public void addEdge(E v, E d) {
    // Get if already present or create a new list of linked vertices
    // for a given source vertex
    List<E> neighbors = edges.getOrDefault(v, new LinkedList<E>());
    // Add destination vertex to the list
    neighbors.add(d);
    // Update the edge map
    edges.put(v, neighbors);
    // Add source vertex as well
    vertices.add(v);

    // Add destination vertex
    vertices.add(d);
    // This is important step
    // Create an empty list of connected vertices for current destination point d
    // if such a mapping does not already exist
    if (!edges.containsKey(d)) {
      edges.put(d, new LinkedList<E>());
    }
  }

  /**
   * Get all the vertices in the graph
   *
   * @return set of all vertices
   */
  public Set<E> getVertices() {
    return vertices;
  }

  /**
   * Get all the edges in the graph
   *
   * @return map of all edges
   */
  public Map<E, List<E>> getE() {
    return edges;
  }

  /**
   * String representation of the graph
   *
   * @return string containing vertices and edges on this graph
   */
  public String toString() {
    StringBuffer graph = new StringBuffer();
    for (E v : edges.keySet()) {
      List<E> neighbors = edges.get(v);
      for (E neighbor : neighbors) {
        graph.append(v.toString() + " : " + neighbor.toString() + ",");
      }
    }
    graph.deleteCharAt(graph.length() - 1);
    return graph.toString();
  }

  /**
   * Function implementing topographical sort This is applicable for DAG only and not for graphs
   * with cycles This makes sure that the vertices are sorted (visited) in a systematic order such
   * that a source vertex is always visited before the destination vertex
   */
  public void topographicalSort() {

    // Stack to keep track of vertices
    Deque<E> order = new LinkedList<E>();
    // Tracking visited vertices
    Map<E, Boolean> visited = new HashMap<E, Boolean>();

    // Initialize as not visited
    for (E vertex : vertices) {
      visited.put(vertex, false);
    }

    for (E vertex : vertices) {
      if (!visited.get(vertex)) {
        topographicalSortUtil(vertex, order, visited);
      }
    }

    StringBuffer s = new StringBuffer();
    System.out.println("Topographical Sort : ");
    while (!order.isEmpty()) {
      s.append(order.pop());
    }

    if (s.length() == vertices.size()) {
      System.out.println(s);
    } else {
      System.out.println("Invalid Graph");
    }
  }

  /**
   * This is crucial implementation of the logic
   *
   * <p>Algorithm:
   *
   * <p>1. Mark current vertex as visited
   *
   * <p>2. Get the neighbors and call the function on each of them
   *
   * <p>3. Finally when all of them are visited add current one to stack ensuring that a vertex is
   * on the top of its neighbors
   */
  private void topographicalSortUtil(E v, Deque<E> order, Map<E, Boolean> visited) {
    // Mark the vertex as visited
    visited.put(v, true);
    // Get list of neighbors
    List<E> neighbors = edges.get(v);
    // For each neighbor call topographicalSortUtil again if it is not already visited.
    for (E neighbor : neighbors) {
      if (!visited.get(v)) {
        System.out.println("Outer : " + v);
        topographicalSortUtil(neighbor, order, visited);
      }
    }
    order.push(v);
  }
}
