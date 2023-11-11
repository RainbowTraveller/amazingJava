/*  There are a total of n courses you have to take, labeled from 0 to n-1.
*
*  Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
*
*  Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
*
*  There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
*
*  Example 1:
*
*  Input: 2, [[1,0]]
*  Output: [0,1]
*  Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
*               course 0. So the correct course order is [0,1] .
*  Example 2:
*
*  Input: 4, [[1,0],[2,0],[3,1],[3,2]]
*  Output: [0,1,2,3] or [0,2,1,3]
*  Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
*               courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
*               So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
*/

import java.util.*;

public class CourseScheduling {

    public static void main( String[] args ) {
        CourseScheduling cs = new CourseScheduling();
        int n = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int [] order = cs.findOrder(n, prerequisites);
        System.out.println("Order : " +       Arrays.toString(order));

    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] courseorder = null;
        if(prerequisites != null && prerequisites.length > 0) {
            Graph<Integer> g = new Graph<Integer>();

            //Add all vertices
            for(int i = 0; i < numCourses ; ++i) {
                g.addVertex(i);
            }

            //Add all edges as per the prerequisites
            //for [1, 0] ==> means 0 is prereq for 1, so 0 must be completed before 1
            //so we consider 0 first and then process its children
            for(int i = 0; i < prerequisites.length ; ++i) {
                g.addEdge(prerequisites[i][1], prerequisites[i][0]);
            }

            StringBuffer order = new StringBuffer();
            g.topographicalSort(order);
            //System.out.println(order);
            String[] orderArray = order.toString().split(",");
            // order length should match the total no. of courses
            if(orderArray.length > 0 && orderArray.length == numCourses) {
                courseorder = new int[orderArray.length];
                for(int i = 0; i < orderArray.length; ++i) {
                    //System.out.println("Hell yeah..: " + order.charAt(i));
                    courseorder[i] = Integer.valueOf(orderArray[i]);
                }
            } else {
                    //Empty course order indicating no scheduling possible
                    courseorder = new int[0];
            }
        } else {
            courseorder = new int[numCourses];
            for(int i = 0; i < numCourses ; ++i) {
                courseorder[i] = i;
            }
        }
        return courseorder;
    }

    static class Graph<V> {
        Set<V> vertices;
        Map<V, List<V>> edges;
        Map<V, Integer> indegree;

        public Graph() {
            vertices = new HashSet<V>();
            edges = new HashMap<>();
            //How many edges are directed to this node
            indegree = new HashMap<>();
        }

        public void addVertex(V a) {
            vertices.add(a);
            indegree.put(a,0);
            //Very important to avoid NullPointerException
            edges.put(a, new LinkedList<>());
        }

        public void addEdge(V a, V b) {
            List<V> neighbors = edges.getOrDefault(a, new LinkedList<V>());
            neighbors.add(b);
            edges.put(a, neighbors);
            indegree.put(b, indegree.getOrDefault(b, 0) + 1);
        }

        public void topographicalSort(StringBuffer sb) {
            Deque<V> order= new LinkedList<V>();
            // Starting with a node with indegree 0
            // indicating it has no prereq and can be taken first
            for(V curr : vertices) {
                if(indegree.get(curr) == 0) {
                    //System.out.println(curr);
                    order.offer(curr);
                }
            }

            while(!order.isEmpty()) {
                V v = order.pollFirst();
                sb.append(v);
                sb.append(',');
                //System.out.println(v);
                List<V> neighbors = edges.get(v);
                for(V n : neighbors) {
                    //Reduce the in-degree of all neighbors
                    //and all with 0 in-degree to queue
                    //In case of cycle the queue will be empty
                    //before all nodes are covered
                    indegree.put(n, indegree.get(n) - 1);
                    if(indegree.get(n) == 0) {
                        order.offer(n);
                    }
                }
            }
       }
    }
}

