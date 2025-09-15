/*
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b.
 *  Any server can reach any other server directly or indirectly through the network.
 *
 *  A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 *
 *  Return all critical connections in the network in any order.
 *
 *  Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 *  Output: [[1,3]]
 *  Explanation: [[3,1]] is also accepted.
 *
 *  Explanation : https://www.youtube.com/watch?v=RYaakWv5m6o and https://www.youtube.com/watch?v=erlX-1MJlv8
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CriticalConnections {
  LinkedList<Integer>[] neighbors;
  List<List<Integer>> critical;
  int[] visitedTimes;
  int[] lowestVisitedTimeNeighbors;
  int time;

  public static void main(String[] args) {
    int n = 4;
    List<List<Integer>> connections = new LinkedList<>();
    connections.add(Arrays.asList(0, 1));
    connections.add(Arrays.asList(1, 2));
    connections.add(Arrays.asList(2, 0));
    connections.add(Arrays.asList(1, 3));
    CriticalConnections cc = new CriticalConnections();
    System.out.println("Critical Connections: " + cc.criticalConnections(n, connections));
  }

  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    // Adjacency list of node and corresponding vertices
    neighbors = new LinkedList[n];
    // Critical connections when removed disconnect the graph
    critical = new LinkedList<>();
    // Time when this node was visited, can be looked as id as well
    visitedTimes = new int[n];
    // A node with lowest visiting time that is reachable from this node
    lowestVisitedTimeNeighbors = new int[n];
    // Time
    time = 0;

    if (connections != null && connections.size() > 0) {
      // Prepare the graph in term of adjacency list
      preprareGraph(connections);
      // Visited tracker
      boolean[] visited = new boolean[n];
      // Tarjan Algorithm using DFS
      tarjanDFSTraversal(visited, 0, -1);
    }
    return critical;
  }

  /*
   *  Refer to time variable which is increasing monotonically. So when a node is visited then note it's time at 2 places visitedTimes and
   *  lowestVisitedTimeNeighbors ( as at this time we don't know any other node which can be visited having lowest visiting time )
   *
   *  Now start DFS
   *
   *  When a neighbor
   *      check if it is parent, if yes ignore and continue
   *      check if it is not visited
   *          call DFS recursively
   *          when it return check if it's lowest visited time is more than current's corr. time if that is the case we have critical edge
   *          (This indicates the neighbour belongs to different strongly connected component )
   *
   *      then update current nodes lowest visited time neighbor with min(current, visited) (visited or not visited)
   */
  private void tarjanDFSTraversal(boolean[] visited, int currNode, int parentNode) {
    // Mark this node as visited
    visited[currNode] = true;
    visitedTimes[currNode] = lowestVisitedTimeNeighbors[currNode] = time++;

    for (int neighbor : neighbors[currNode]) {
      if (neighbor == parentNode) continue;
      if (!visited[neighbor]) {
        tarjanDFSTraversal(visited, neighbor, currNode);
        lowestVisitedTimeNeighbors[currNode] =
            Math.min(lowestVisitedTimeNeighbors[currNode], lowestVisitedTimeNeighbors[neighbor]);
        if (visitedTimes[currNode] < lowestVisitedTimeNeighbors[neighbor]) {
          critical.add(Arrays.asList(currNode, neighbor));
        }
      } else {
        lowestVisitedTimeNeighbors[currNode] =
            Math.min(lowestVisitedTimeNeighbors[currNode], visitedTimes[neighbor]);
      }
    }
  }

  /**
   * Pretty simple, just go through the connections given and in the array for given index i, add to
   * its adjacency list the neighbor and vice versa for neighbor
   */
  private void preprareGraph(List<List<Integer>> connections) {
    // Make sure to have empty list for each index neighbor
    for (int i = 0; i < neighbors.length; ++i) {
      neighbors[i] = new LinkedList<>();
    }

    for (List<Integer> curr : connections) {
      int node1 = curr.get(0);
      int node2 = curr.get(1);
      neighbors[node1].add(node2);
      neighbors[node2].add(node1);
    }
  }
}
