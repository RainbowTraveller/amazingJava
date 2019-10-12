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

            for(int i = 0; i < numCourses ; ++i) {
                g.addVertex(i);
            }

            for(int i = 0; i < prerequisites.length ; ++i) {
                g.addEdge(prerequisites[i][1], prerequisites[i][0]);
            }

           // g.correction();

            StringBuffer order = new StringBuffer();
            g.topographicalSort(order);
            //System.out.println(order);
            String[] orderArray = order.toString().split(",");
            if(orderArray.length > 0 && orderArray.length == numCourses) {
                courseorder = new int[orderArray.length];
                for(int i = 0; i < orderArray.length; ++i) {
                    //System.out.println("Hell yeah..: " + order.charAt(i));
                    courseorder[i] = Integer.valueOf(orderArray[i]);
                }
            } else {
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
            indegree = new HashMap<>();
        }

        public void addVertex(V a) {
            vertices.add(a);
            indegree.put(a,0);
            edges.put(a, new LinkedList<>());
        }

        public void addEdge(V a, V b) {
            List<V> neighbors = edges.getOrDefault(a, new LinkedList<V>());
            neighbors.add(b);
            edges.put(a, neighbors);
            indegree.put(b, indegree.getOrDefault(b, 0) + 1);
        }

        public void correction() {
            V [] verticesA = (V [])edges.keySet().toArray();
            for(V v : vertices) {
                if(!edges.containsKey(v)) {
                    List<V> temp = new LinkedList<V>();
                    temp.add(verticesA[0]);
                    edges.put(v, temp);
                    indegree.put(verticesA[0], indegree.getOrDefault(verticesA[0], 0) + 1);
                }
            }
        }
        public void topographicalSort(StringBuffer sb) {
            Deque<V> order= new LinkedList<V>();
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
                    indegree.put(n, indegree.get(n) - 1);
                    if(indegree.get(n) == 0) {
                        order.offer(n);
                    }
                }
            }
       }
    }
}

