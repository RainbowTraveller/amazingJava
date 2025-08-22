public class IslandPerimeter {

  private int[][] topology = {
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
  };
  private int perimeter = 0;

  public static void main(String[] args) {
    IslandPerimeter ip = new IslandPerimeter();
    ip.getIslandPerimeter();
  }

  /**
   * Calculate the perimeter of the island in the grid The grid is represented by a 2D array where 1
   * represents land and 0 represents water The perimeter is calculated by checking each land cell
   * and counting the number of edges that are either adjacent to water or the edge of the grid
   */
  public void getIslandPerimeter() {
    for (int i = 0; i < topology.length; ++i) {
      for (int j = 0; j < topology[0].length; ++j) {
        if (topology[i][j] == 1) {
          int curr = 0;
          // i + 1, j
          // If we are at the bottom edge of the grid or the cell below is water, increment curr
          if (i + 1 >= topology.length || topology[i + 1][j] == 0) {
            curr++;
          }
          // i - 1, j
          // If we are at the top edge of the grid or the cell above is water, increment curr
          if (i - 1 < 0 || topology[i - 1][j] == 0) {
            curr++;
          }
          // i, j + 1
          // If we are at the right edge of the grid or the cell to the right is water, increment
          // curr
          if (j + 1 >= topology[0].length || topology[i][j + 1] == 0) {
            curr++;
          }
          // i, j - 1
          // If we are at the left edge of the grid or the cell to the left is water, increment curr
          if (j - 1 < 0 || topology[i][j - 1] == 0) {
            curr++;
          }
          perimeter += curr;
        }
      }
    }
    System.out.println(" Perimiter " + perimeter);
  }
}
