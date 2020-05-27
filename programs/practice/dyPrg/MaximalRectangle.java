/*  Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
*
*  Example:
*
*  Input:
*  [
*    ["1","0","1","0","0"],
*    ["1","0","1","1","1"],
*    ["1","1","1","1","1"],
*    ["1","0","0","1","0"]
*  ]
*  Output: 6
*
*
*  This is different from finding square matrix problem as in this case the maximal area is needed which can be dues to
*  different length and breadth
*/

public class MaximalRectangle {
    public static void main(String[] args) {

    }

    public int maximalRectangle(char[][] matrix) {

        int maxArea = 0;
        if (matrix != null && matrix.length > 0) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            int[][] tracker = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; ++j) {

                    if (matrix[i][j] == '1') {
                        // Traverse the matrix each row, columnwise and at each location note down the
                        // consecutive 1 encountered
                        // in the give row spanning across multiple columns
                        tracker[i][j] = j == 0 ? 1 : tracker[i][j - 1] + 1;
                        // So this is the width encountered so far
                        int width = tracker[i][j];
                        // From here we will traverse to the 0th row, in the given column j and do
                        // following
                        // 1. Get min of width encountered in this col and difference between current
                        // row and that row as length and breadth resp.
                        // to calculate area of maximal rectangle
                        // 2. from all such areas from current row and given column upto 0th row, we
                        // will record maxArea
                        for (int k = i; k >= 0; --k) {
                            // Get min of the width in the given col for each row upto 0th row
                            width = Math.min(width, tracker[k][j]);
                            // area = width * height, keep max one
                            maxArea = Math.max(maxArea, width * (i - k + 1));
                        }
                    }
                }
            }
        }
        return maxArea;
    }

}
