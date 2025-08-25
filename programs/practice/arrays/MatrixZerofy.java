public class MatrixZerofy {

  /*
   * Zerofies the row and column containing a zero
   *
   */

  public static void main(String[] args) {
    int[][] matrix = {
      {1, 3, 6, 4},
      {8, 0, 4, 9},
      {0, 11, 17, 19}
    };
    zerofyMatrix(matrix);
  }

  /*
   * This  requires additional O(m + n) space
   * An array for rows and another one for columns
   */
  public static void zerofyMatrix(int[][] matrix) {
    boolean[] rows = new boolean[matrix.length]; // Stores row containing zero
    boolean[] columns = new boolean[matrix[0].length]; // Stores column containing zero

    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[0].length; ++j) {
        if (matrix[i][j] == 0) {
          rows[i] = true;
          columns[j] = true;
        }
      }
    }

    System.out.println("Original Matrix");
    print(matrix);

    for (int i = 0; i < rows.length; ++i) {
      if (rows[i]) {
        zerofyRows(matrix, i);
      }
    }

    for (int i = 0; i < columns.length; ++i) {
      if (columns[i]) {
        zerofyColumns(matrix, i);
      }
    }
    System.out.println("After zerofying Matrix");
    print(matrix);
  }

  /*
   * Zerofy the row and keep column changing
   */
  public static void zerofyRows(int[][] matrix, int row) {
    for (int i = 0; i < matrix[0].length; ++i) {
      matrix[row][i] = 0;
    }
  }

  /*
   * Zerofy the column and keep column changing
   */
  public static void zerofyColumns(int[][] matrix, int column) {
    for (int i = 0; i < matrix.length; ++i) {
      matrix[i][column] = 0;
    }
  }

  public static void print(int[][] matrix) {
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[0].length; ++j) {
        System.out.print(" " + matrix[i][j]);
      }
      System.out.println();
    }
  }

  /*
     * Go through the matrix and look out for 0s. When we find one
     * then update the corr. 0th Row and column element to 0
     * we should not consider 0th row and column in this case, treat it separately
     * Once this is in place we traverse the matrix once again from 1,1
     * if we have either 0th row element or column element marked as 0, we make
     * that i,j element 0.
     *
     * No need of extra space
     * Time complexity O(m*n)
     * Space complexity O(1)
     * The core idea is to use the first row and first column of the matrix itself as auxiliary storage to keep track of which rows and columns should be zeroed.

  Step 1: Mark Rows and Columns
  The first part of the code iterates through the matrix to mark the first row and first column.

  The code uses a nested loop to go through every element matrix[i][j].

  If matrix[i][j] is 0, it sets the corresponding elements in the first row (matrix[0][j]) and the first column (matrix[i][0]) to 0. This acts as a marker or flag.

  The Special Case: The first cell of the first row and the first column is the same: matrix[0][0]. This means matrix[0][0] has to act as a flag for both the first row and the first column. To avoid this conflict, the code uses a separate boolean variable, isFirstCol, to specifically track whether the first column needs to be zeroed out. The value of matrix[0][0] is then exclusively used to mark the first row.

  Step 2: Zero Out the Rest of the Matrix
  After the first pass, the first row and column are populated with "flag" zeros. The second part of the code uses these flags to set the remaining cells to zero.

  A new nested loop iterates from matrix[1][1] to the end of the matrix.

  For each element matrix[i][j], it checks if its corresponding flag is set in the first row (matrix[0][j]) or the first column (matrix[i][0]).

  If either of these flags is 0, it means the entire row i or column j should be zeroed out, so matrix[i][j] is set to 0.

  Step 3: Zero Out the First Row and Column
  The final part handles the special case of the first row and first column themselves.

  It checks the flag for the first row, which is matrix[0][0]. If it's 0, the entire first row is zeroed out.

  It then checks the isFirstCol flag. If it's true, the entire first column is zeroed out.

  This completes the process, and the matrix is now correctly modified in-place with a space complexity of O(1).
     *
     */
  public static void zerofyMatrixNoXtraSpace(int[][] matrix) {
    boolean isFirstCol = false;

    int rows = matrix.length;
    int cols = matrix[0].length;

    for (int i = 0; i < rows; ++i) {
      // Since first cell for both first row and first column is the same i.e. matrix[0][0]
      // We can use an additional variable for either the first row/column.
      // For this solution we are using an additional variable for the first column
      // and using matrix[0][0] for the first row.
      if (matrix[i][0] == 0) {
        isFirstCol = true;
      }

      for (int j = 1; j < cols; ++j) {
        // If an element is zero, we set the first element of the corresponding row and column to 0
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    // Iterate over the array once again and using the first row and first column, update the
    // elements.
    for (int i = 1; i < rows; ++i) {
      for (int j = 1; j < cols; ++j) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }
    }
    // See if the first row needs to be set to zero as well
    if (matrix[0][0] == 0) {
      for (int i = 0; i < cols; ++i) {
        matrix[0][i] = 0;
      }
    }

    // See if the first column needs to be set to zero as well
    if (isFirstCol) {
      for (int j = 0; j < rows; ++j) {
        matrix[j][0] = 0;
      }
    }
  }
}
