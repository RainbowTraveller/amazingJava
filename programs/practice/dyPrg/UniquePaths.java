import java.util.Arrays;

public class UniquePaths {
    public static void main(String[] args) {
        UniquePaths up = new UniquePaths();
        // System.out.println("Unique Paths : Recursive : " + up.uniquePaths(10, 10));
        // System.out.println("Unique Paths : Memoisation : " + up.uniquePaths(10, 10));
        System.out.println("Unique Paths : Dyn : " + up.uniquePaths(10, 10));
    }

    public int uniquePaths(int m, int n) {
        //Recursive
        // int paths = getPaths(0, 0, m -1, n - 1);
        //Memoisation
        //int paths = getPathsMem(0, 0, m -1, n - 1);
        //Dyn
        int paths = getPathsDyn(m, n);
        return paths;
    }

    public int getPaths(int x, int y, int rows, int cols) {
        if(x < 0 || x > rows || y < 0 || y > cols) {
            return 0;
        }

        if(x == rows && y == cols) {
            return 1;
        }
        int right = getPaths(x, y + 1, rows, cols);
        int down = getPaths(x + 1, y, rows, cols);

        return right + down;
    }

    public int getPathsMem(int x, int y, int rows, int cols) {
        int[][] grid = new int[rows + 1][cols + 1];
        for(int i = 0; i <= rows; i++) {
            for(int j = 0; j <= cols; j++) {
                grid[i][j] = -1;
            }
        }
        int paths = helperMem(x, y, rows, cols, grid);
        return paths;
    }

    public int helperMem(int x, int y, int rows, int cols, int [][] grid) {
        if(x < 0 || x > rows || y < 0 || y > cols) {
            return 0;
        }
        if(x == rows && y == cols) {
            return 1;
        }
        int right = 0;
        if(y + 1 >= 0 && y + 1 <= cols && grid[x][y + 1] != -1) {
           right =  grid[x][y + 1];
        } else {
            right = helperMem(x, y + 1, rows, cols, grid);
        }

        int down = 0;
        if(x + 1 >= 0 && x + 1 <= rows && grid[x + 1][y] != -1) {
           down =  grid[x + 1][y];
        } else {
            down = helperMem(x + 1, y, rows, cols, grid);
        }
        grid[x][y] = right + down;
        return right + down;
    }

    public int getPathsDyn(int rows, int cols) {
        int[][] grid = new int[rows][cols];
        // for(int i = 0; i <= rows; i++) {
        //     for(int j = 0; j <= cols; j++) {
        //         grid[i][j] = 0;
        //     }
        // }
        //
        for(int[] arr : grid) {
          Arrays.fill(arr, 1);
        }

        for(int row = 1; row < rows; ++row) {
            for(int col = 1; col < cols; ++col) {
            grid[row][col] = grid[row- 1][col] + grid[row][col - 1];
          }
        }
        return grid[rows - 1][cols - 1];
      }
}

