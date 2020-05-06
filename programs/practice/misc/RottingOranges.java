/*
 *      In a given grid, each cell can have one of three values:
 *       the value 0 representing an empty cell;
 *       the value 1 representing a fresh orange;
 *       the value 2 representing a rotten orange.
 *       Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *       Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 *
 */

import java.util.LinkedList;
import java.util.Deque;

public class RottingOranges {
    public static void main(String[] args) {
        RottingOranges ro = new RottingOranges();
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println("Steps required to rot all the grid : " + ro.orangesRotting(grid));
    }

    //Solution uses breadth first search
    //it is levelwise scanning a DAG where in first consider all the candidate neighbors of a node
    //and then proceed by adding them to queue for next iteration
    public int orangesRotting(int[][] grid) {
        if(grid != null) {
            Deque<Pair<Integer, Integer>> rottenQueue = new LinkedList<>();
            int goodOnes = 0;
            for (int i = 0; i < grid.length; i++ ) {
                for (int j =0; j < grid[0].length ; ++j ) {
                    if(grid[i][j] == 2) {
                        //Put rotten ones in the queue
                        rottenQueue.offer(new Pair<Integer,Integer>(i,j));
                    } else if (grid[i][j] == 1) {
                        //Count good ones for later to tally
                        goodOnes++;
                    }
                }
            }
            int steps = 0;
            while(!rottenQueue.isEmpty()) {
                int size = rottenQueue.size();
                boolean found = false;
                System.out.println(size);
                while(size > 0) {
                    Pair<Integer,Integer> currentRottenPosition = rottenQueue.poll();
                    size--;
                    int row = currentRottenPosition.getKey();
                    int col = currentRottenPosition.getValue();
                    for(int i = -1; i <= 1; ++i) {
                        for(int j = -1; j <= 1; ++j) {
                            //Note the condition here may come handy for many other similar cases
                            if(Math.abs(i) != Math.abs(j) && row + i >= 0 && row + i < grid.length && col + j >= 0 && col + j < grid[0].length && grid[row + i][ col + j] == 1) {
                                goodOnes--;// good ones turn rotten so adjust the count
                                found = true;// for each rotten node all the candidate neighbors turn bad in one go, so only record it as a single step
                                grid[row + i][col + j] = 2;
                                System.out.println("Good Ones " + goodOnes + " ROW :" + ( row  + i ) + " COL : " + ( col + j ) );
                                rottenQueue.offer(new Pair<Integer,Integer>(row + i,col + j));// recently turned rotten will affect their neighbors later so add them to queue
                            }
                        }
                    }
                }
                if(found) {
                    // Account for the steps
                    steps++;
                }
            }

            if(goodOnes == 0) {
                // if all good ones are done, we have the steps
                return steps;
            }
        }
        return -1;
    }
}

class Pair<K, V> {
    private K key;
    private V value;


    public Pair() {
        this.key = null;
        this.value = null;
    }

    public Pair(K key, V value) {
        this();
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "Key : " + this.key + " Value : " + this.value;
    }
}

