public class MatrixZerofy {

    /*
     * Zerofies the row and column containing a zero
     *
     */

    public static void main(String[] args) {
        int [][] matrix = {{1, 3, 6, 4},{8, 0 , 4, 9},{0 ,11, 17, 19}};
        zerofyMatrix(matrix);
    }

    /*
     * This  requires additional O(m + n) space
     * An array for rows and another one for columns
     */
    public static void zerofyMatrix(int[][] matrix) {
        boolean [] rows = new boolean[matrix.length];//Stores row containing zero
        boolean [] columns = new boolean[matrix[0].length];//Stores column containing zero

        for(int i = 0; i< matrix.length; ++i) {
            for(int j = 0; j < matrix[0].length; ++j) {
                if(matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }

        System.out.println("Original Matrix");
        print(matrix);

        for(int i = 0; i < rows.length; ++i) {
            if(rows[i]) {
                zerofyRows(matrix, i);
            }
        }

        for(int i = 0; i < columns.length; ++i) {
            if(columns[i]) {
                zerofyColumns(matrix, i);
            }
        }
        System.out.println("After zerofying Matrix");
        print(matrix);
    }

    /*
     * Zerofy the row and keep column changing
     */
    public static void zerofyRows(int [][] matrix, int row) {
        for(int i = 0; i < matrix[0].length; ++i) {
            matrix[row][i] = 0;
        }
    }


    /*
     * Zerofy the column and keep column changing
     */
    public static void zerofyColumns(int [][] matrix, int column) {
        for(int i = 0; i < matrix.length; ++i) {
            matrix[i][column] = 0;
        }
    }

    public static void print(int [][] matrix) {
        for(int i = 0; i< matrix.length; ++i) {
            for(int j = 0; j < matrix[0].length; ++j) {
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
     */
    public static void zerofyMatrixNoXtraSpace(int[][] matrix) {
        boolean isFirstCol = false;

        int rows = matrix.length;
        int cols = matrix[0].length;

        for(int i = 0; i < rows; ++i) {
             // Since first cell for both first row and first column is the same i.e. matrix[0][0]
      // We can use an additional variable for either the first row/column.
      // For this solution we are using an additional variable for the first column
      // and using matrix[0][0] for the first row.
            if(matrix[i][0] == 0) {
                isFirstCol = true;
            }

            for(int j = 1; j < cols; ++j) {
                // If an element is zero, we set the first element of the corresponding row and column to 0
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Iterate over the array once again and using the first row and first column, update the elements.
        for(int i = 1; i < rows; ++i) {
            for(int j = 1; j < cols; ++j) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // See if the first row needs to be set to zero as well
        if(matrix[0][0] == 0) {
            for(int i = 0; i < cols; ++i) {
                matrix[0][i] = 0;
            }
        }

        // See if the first column needs to be set to zero as well
        if(isFirstCol) {
            for(int j = 0; j < rows; ++j) {
                matrix[j][0] = 0;
            }
        }
    }
}
