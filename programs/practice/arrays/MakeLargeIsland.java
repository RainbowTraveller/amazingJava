import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakeLargeIsland {
  public int largestIsland(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    Map<Integer, Integer> areaMap = new HashMap<>();
    int islandId = 2; // Start from 2 to avoid confusion with 0 and 1
    // Step 1: Identify and label islands, and calculate their areas
    // We will use DFS to label the islands and calculate their areas
    // We will also store the area of each island in a map with the islandId as the key
    // We will also keep track of the maximum area found so far
    int maxArea = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          int area = findIslandArea(grid, i, j, islandId);
          areaMap.put(islandId, area);
          maxArea = Math.max(maxArea, area);
          islandId++; // Increment the islandId for the next island
        }
      }
    }
    // Step 2: Try changing each 0 to 1 and calculate the potential area of the island
    // We will check the four neighbors of the cell and sum up the areas of the unique islands that
    // are connected to it
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; ++j) {
        if (grid[i][j] == 0) {
          Set<Integer> uniqueIslands = new HashSet<>();
          // Check the four neighbors
          if (i > 0 && grid[i - 1][j] > 1) {
            uniqueIslands.add(grid[i - 1][j]);
          }
          if (i < m - 1 && grid[i + 1][j] > 1) {
            uniqueIslands.add(grid[i + 1][j]);
          }
          if (j > 0 && grid[i][j - 1] > 1) {
            uniqueIslands.add(grid[i][j - 1]);
          }
          if (j < n - 1 && grid[i][j + 1] > 1) {
            uniqueIslands.add(grid[i][j + 1]);
          }
          int potentialArea = 1; // Start with area of the new island
          for (int id : uniqueIslands) {
            potentialArea += areaMap.get(id); // Add the area of the connected islands
          }
          maxArea = Math.max(maxArea, potentialArea); // Update max area if needed
        }
      }
    }
    return maxArea;
  }

  private int findIslandArea(int[][] grid, int i, int j, int islandId) {
    int m = grid.length;
    int n = grid[0].length;
    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
      return 0;
    }

    grid[i][j] = islandId; // Mark the cell with the islandId
    // Recursively find the area of the island by exploring its neighbors
    // We will explore in four directions: up, down, left, right
    return 1
        + findIslandArea(grid, i - 1, j, islandId)
        + findIslandArea(grid, i + 1, j, islandId)
        + findIslandArea(grid, i, j - 1, islandId)
        + findIslandArea(grid, i, j + 1, islandId);
  }
}
