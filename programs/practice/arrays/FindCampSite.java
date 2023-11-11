import java.util.Arrays;

/*
You are planning a science expedition, and you need to pick a base camp location.
The environment has open spaces (' ') and impassable mountains ('#').
There are several points of interest ('P') that need to be explored.
Each exploration takes an entire day (neglect travel time),
and the science team must return to camp each night after exploring a point of interest.
Fuel is expensive so you need to minimize total distance traveled.
What is the best location for the base camp?


Example:
#############
# P      #  #
#        #P #
# ###    #  #
#  P#  P    #
#############

Inputs: Grid of terrain, List of POIs

1. Visit each possible valid location
2. Calculate distance from there to POIs
3. Return minimum
4. Can go in 4 directions

Steps :
  1. Scan the Grid
  2. Skip a points if POI or mountain
  3. Call helper to calculate possible value of distance
          Travrse in 4 directions
               1. mountain
               2. increment step if empty location : mark is mountain

  N, M, P << MN

  O(NM * NM)
  O(NMP)


*/

public class FindCampSite {

  public static void main(String[] args) {
    char[][] grid = {
      {'#', '#', '#', '#', '#', '#', '#', '#', '#'},
      {'#', ' ', '#', ' ', ' ', '#', '#', ' ', '#'},
      {'#', ' ', ' ', '#', ' ', '#', '#', ' ', '#'},
      {'#', ' ', ' ', '#', ' ', ' ', '#', ' ', '#'},
      {'#', ' ', 'P', ' ', '#', ' ', '#', ' ', '#'},
      {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
      {'#', ' ', 'P', ' ', ' ', '#', '#', '#', '#'},
      {'#', '#', '#', '#', '#', '#', '#', '#', '#'}
    };

    System.out.println("Camp Site Location : " + findBaseCamp(grid));
  }

  private static class Point {
    int x;
    int y;

    public Point() {
      x = 0;
      y = 0;
    }

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public void setX(int x) {
      this.x = x;
    }

    public void setY(int y) {
      this.y = y;
    }

    public String toString() {
      return "X : " + x + " Y: " + y;
    }
  }

  /**
   * Takes a grid mapping of a landscape, indicating either of the following 3 1. Hill / Mounutain
   * 2. Plain Land 3. Point of interest The aim it to find a plain land to set up a base camp.
   * During the camp we need to make a trip to POI. We need to go to POI and return back on the same
   * day. So we need a point such that this distance is minimum. To simplify, we traverse from each
   * POI to other empty points and gather the data for distance
   *
   * @param grid
   * @return
   */
  public static Point findBaseCamp(char[][] grid) {
    // Min distance
    int minDistance = Integer.MAX_VALUE;
    Point baseCamp = new Point();
    int[][] distanceTracker = new int[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; ++i) {
      Arrays.fill(distanceTracker[i], 0);
    }

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 'P') {
          findDistance(i, j, grid, distanceTracker);
        }
      }
    }

    for (int i = 0; i < grid.length; i++) {
      System.out.println();
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == ' ') {
          int currDistance = distanceTracker[i][j];
          //          System.out.print("i: " + i + " j: " + j + " distance : " + currDistance);
          //          System.out.print("i:" + i + "j:" + j + ":" + currDistance + " ");
          //          System.out.print(" " + currDistance + " ");
          if (currDistance < minDistance) {
            baseCamp.setX(i);
            baseCamp.setY(j);
            minDistance = currDistance;
          }
        }
      }
    }
    return baseCamp;
  }

  public static void findDistance(int row, int col, char[][] grid, int[][] tracker) {
    calculateDistaceFromPOI(row + 1, col, grid, tracker, 1);
    calculateDistaceFromPOI(row - 1, col, grid, tracker, 1);
    calculateDistaceFromPOI(row, col + 1, grid, tracker, 1);
    calculateDistaceFromPOI(row, col - 1, grid, tracker, 1);
  }

  public static void calculateDistaceFromPOI(
      int row, int col, char[][] grid, int[][] tracker, int distance) {
    if (grid[row][col] == ' ') {
      grid[row][col] = '#';
      tracker[row][col] += distance;
      calculateDistaceFromPOI(row + 1, col, grid, tracker, distance + 1);
      calculateDistaceFromPOI(row - 1, col, grid, tracker, distance + 1);
      calculateDistaceFromPOI(row, col + 1, grid, tracker, distance + 1);
      calculateDistaceFromPOI(row, col - 1, grid, tracker, distance + 1);
      grid[row][col] = ' ';
    }
  }
}
