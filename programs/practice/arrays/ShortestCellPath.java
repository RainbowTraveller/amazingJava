/*
 *
 *  Shortest Cell Path
 *  In a given grid of 0s and 1s, we have some starting row and column sr, sc and a target row and column tr, tc.
 *  Return the length of the shortest path from sr, sc to tr, tc that walks along 1 values only.
 *  Each location in the path, including the start and the end, must be a 1. Each subsequent location in the path must be 4-directionally adjacent to the previous location.
 *  It is guaranteed that grid[sr][sc] = grid[tr][tc] = 1, and the starting and target positions are different.
 *  If the task is impossible, return -1.
 *
 *  1 1 1 1
 *  0 0 0 1
 *  1 1 1 1
 */

import java.io.*;
import java.util.*;

public class ShortestCellPath {

  static class Location {
    int row;
    int col;

    public Location(int row, int col) {

      this.row = row;
      this.col = col;
    }

    public int hashcode() {
      return row | (col << 15);
    }

    public boolean equals(Object o) {
      if (o instanceof Location) {
        Location curr = (Location) o;
        return curr.row == this.row && curr.col == this.col;
      }
      return false;
    }

    public String toString() {
      return " Row : " + row + " Col : " + col;
    }
  }

  // Classic breadth first search, visiting all neighbors, if destination is found
  // return the level or increment level or path value
  static int shortestCellPath(int[][] grid, int sr, int sc, int tr, int tc) {
    int shortestPath = 0;

    if (grid != null) {
      Deque<Location> queue = new LinkedList<>();
      Location start = new Location(sr, sc);
      queue.offer(start);
      int[][] indexes = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

      while (!queue.isEmpty()) {
        int size = queue.size();
        while (size > 0) {
          Location curr = queue.poll();
          int row = curr.row;
          int col = curr.col;
          grid[row][col] = -1; // Using grid ifself to keep track of visited

          if (row == tr && col == tc) {
            return shortestPath;
          }

          for (int i = 0; i < indexes.length; ++i) {
            int newRow = row + indexes[i][0];
            int newCol = col + indexes[i][1];
            if (newRow >= 0
                && newRow < grid.length
                && newCol >= 0
                && newCol < grid[0].length
                && grid[newRow][newCol] != 0
                && grid[newRow][newCol] != -1) {
              Location newLocation = new Location(newRow, newCol);
              queue.offer(newLocation);
            }
          }
          size--;
        }
        shortestPath++;
      }
    }
    return -1;
  }

  /**
   * Recursive approach to find the shortest path
   *
   * <p>Time complexity is O(m*n) where m and n are the dimensions of the grid
   *
   * <p>Space complexity is O(m*n) for the recursion stack
   */
  public static int shortestPathIterating(int[][] grid, int x, int y, int len, int dx, int dy) {
    // Check if out of bound
    // if value is 0 meaning water, can not be cross
    // if -1 in case already visited in this iteration
    if (x < 0
        || x >= grid.length
        || y < 0
        || y >= grid[0].length
        || grid[x][y] == 0
        || grid[x][y] == -1) {
      return Integer.MAX_VALUE;
    }

    if (x == dx && y == dy) {
      return len;
    }

    // Mark visited
    grid[x][y] = -1;
    // Explore in all direcrtions
    int one = shortestPathIterating(grid, x + 1, y, len + 1, dx, dy);
    int two = shortestPathIterating(grid, x - 1, y, len + 1, dx, dy);
    int three = shortestPathIterating(grid, x, y - 1, len + 1, dx, dy);
    int four = shortestPathIterating(grid, x, y + 1, len + 1, dx, dy);

    // Get the minimum path so far
    int min = Math.min(Math.min(one, two), Math.min(three, four));

    // System.out.println("1 : " + one + " 2 : " + two + " 3 " + three + " 4 " + four + " Min : " +
    // min);
    // All possible paths from this positions are explored
    // restore its value so that is can be considered in other subsequent paths
    grid[x][y] = 1;
    return min;
  }

  /** Utility function to print the grid */
  public static void printGrid(int[][] grid) {
    for (int i = 0; i < grid.length; ++i) {
      for (int j = 0; j < grid[0].length; ++j) {
        System.out.print(" " + grid[i][j]);
      }
      System.out.println("");
    }
  }

  public static void main(String[] args) {
    int[][] grid = {{1, 1, 1, 1}, {0, 0, 0, 1}, {1, 1, 1, 1}};
    // System.out.println(shortestCellPath(grid, 0, 0, 2, 0));
    System.out.println(shortestPathIterating(grid, 0, 0, 0, 2, 0));
  }
}
