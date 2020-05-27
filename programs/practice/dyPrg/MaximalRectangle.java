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

import java.util.Arrays;

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

    public int maximalRectangleDyn(char[][] matrix) {
        int maxArea = 0;
        if (matrix != null || matrix.length > 0) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            //We require left end, right end and height to determine the area of max rectangle,so we keep
            //3 arrays
            //We modify the values in them as we iterate through the rows per column and
            //at the end for each row we calculate the maxArea so far
            int[] heights = new int[cols];
            int[] lefts = new int[cols];
            int[] rights = new int[cols];
            Arrays.fill(rights, cols);

            for (int i = 0; i < rows; ++i) {
                int currLeft = 0, currRight = cols;
                //Height
                //This is tracking continuous stream of 1s in a column
                //if 0 is found then the streak breaks, so that is the max height at that point
                //otherwise add 1 to found in the previous row marking presence of stream of 1s
                for (int j = 0; j < cols; ++j) {
                    if (matrix[i][j] == '1') {
                        heights[j]++;
                    } else {
                        heights[j] = 0;
                    }
                }

                //Left bound
                //Here we need to have largest col index with 1 so far
                //We assume that currLeft is 0 and if 1 is present get max of currLeft and value at given index which is populated
                //in the previous row tracing. Because for this col, if previous row contains 0 at an index > current j we can not form a straight left line bound
                //if current value is 0 then make current index value 0 making it minimum possible so that for next row it will be taken into consideration
                //and mark currLeft = j + 1 because obviously current element being 0, left bound can not be here
                for (int j = 0; j < cols; ++j) {
                    if (matrix[i][j] == '1') {
                        lefts[j] = Math.max(lefts[j], currLeft);
                    } else {
                        lefts[j] = 0;
                        currLeft = j + 1;
                    }
                }


                //Right
                //this logic is similar to left but we traverse from right to left
                //and instead of marking right[j] 0 we make it max which is cols if current element is 0
                //because we consider min value as we want leftmost col with possible 1s for a given height
                for (int j = cols - 1; j >= 0; --j) {
                    if (matrix[i][j] == '1') {
                        rights[j] = Math.min(rights[j], currRight);
                    } else {
                        rights[j] = cols;
                        currRight = j;
                    }
                }

                //Calculate area
                for (int j = 0; j < cols; ++j) {
                    maxArea = Math.max(maxArea, (rights[j] - lefts[j]) * heights[j]);
                }
            }

        }
        return maxArea;
    }
}
