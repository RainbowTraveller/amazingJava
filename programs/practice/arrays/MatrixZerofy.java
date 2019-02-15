public class MatrixZerofy {

    /*
     * Zerofies the row and column containing a zero
     *
     */

    public static void main(String[] args) {
        int [][] matrix = {{1, 3, 6},{8, 0 , 4},{0 ,11, 17}};
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
        for(int i = 0; i < matrix.length; ++i) {
            matrix[row][i] = 0;
        }
    }

    /*
     * Zerofy the column and keep column changing
     */
    public static void zerofyColumns(int [][] matrix, int column) {
        for(int i = 0; i < matrix[0].length; ++i) {
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
}
