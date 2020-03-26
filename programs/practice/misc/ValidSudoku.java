/*
 *  Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 *  Each row must contain the digits 1-9 without repetition.
 *  Each column must contain the digits 1-9 without repetition.
 *  Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 */
public class ValidSudoku {
    public static void main(String[] args) {

    }

    public boolean isValidSudoku(char[][] board) {

        Set<Integer>[] rowset = new HashSet[9];
        Set<Integer>[] colset = new HashSet[9];
        Set<Integer>[] boxset = new HashSet[9];

        for (int i = 0; i < board.length; ++i) {
            rowset[i] = new HashSet<Integer>();
            colset[i] = new HashSet<Integer>();
            boxset[i] = new HashSet<Integer>();
        }

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                char curr = board[i][j];
                if (curr != '.') {
                    int num = (int) curr;
                    int boxIndex = (i / 3) * 3 + j / 3; // This is the only tricky part to identify
                    if (rowset[i].contains(num) || colset[j].contains(num) || boxset[boxIndex].contains(num)) {
                        return false;
                    }
                    rowset[i].add(num);
                    colset[j].add(num);
                    boxset[boxIndex].add(num);
                }
            }
        }
        return true;
    }
}
