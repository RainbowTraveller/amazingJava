public class MinimumPathSum {
	public static void main(String[] args) {
		int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		System.out.println("Minimum Path Sum (Recursive): " + minPathSumResursive(grid));
	}

	/**
	 * Recursive approach to find the minimum path sum in a grid.
	 * <p> Time complexity: O(2^(m+n)) where m is number of rows and n is number of columns.
     * <p> Space complexity: O(m+n) due to recursion stack.
     *
	 * @param grid 2D array representing the grid with non-negative integers.
	 * @return Minimum path sum from top-left to bottom-right corner.
	 */
	public static int minPathSumResursive(int[][] grid) {
		return minPathSumHelper(grid, 0, 0);
	}

	/**
     * Helper function for the recursive approach.
     *
     * @param grid 2D array representing the grid.
     * @param row Current row index.
     * @param col Current column index.
     * @return Minimum path sum from the current position to the bottom-right corner.
     */
	public static minPathSumHelper(int[][] grid, int row, int col) {
        if (row >= grid.length || col >= grid[0].length) {j
            return Integer.MAX_VALUE;
        }
        if( row == grid.length - 1 && col == grid[0].length - 1) {
            return grid[row][col];
        }
        return grid[row][col] + Math.min(minPathSumHelper(grid, row + 1, col), minPathSumHelper(grid, row, col + 1));
    }

    /**
     * Dynamic programming approach to find the minimum path sum in a grid.
     * <p> Time complexity: O(m*n) where m is number of rows and n is number of columns.
     * <p> Space complexity: O(m*n) for the DP table.
     *
     * @param grid 2D array representing the grid with non-negative integers.
     * @return Minimum path sum from top-left to bottom-right corner.
     */
    public int minPathSum(int[][] grid) {
        int[][] dptracker = new int[grid.length][grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                // if bottom row : add grid and next column value
                if (i == grid.length - 1 && j != grid[0].length - 1) {
                    dptracker[i][j] = grid[i][j] + dptracker[i][j + 1];
                } else if (i != grid.length - 1 && j == grid[0].length - 1) {
                    // if last column : add grid and next row value
                    dptracker[i][j] = grid[i][j] + dptracker[i + 1][j];
                } else if( i != grid.length - 1 && j != grid[0].length - 1) {
                    // else take min of next row and next column
                    dptracker[i][j] = grid[i][j] + Math.min(dptracker[i + 1][j], dptracker[i][ j + 1]);
                } else {
                    // bottom right cell
                    dptracker[i][j] = grid[i][j];
                }
            }
        }
        return dptracker[0][0];
    }
}
