/*
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * Example:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */

public class Boggle2 {
    public static void main(String[] args) {
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        Boggle2 b = new Boggle2();
        //System.out.println(b.exist(board, "ABCCED"));
        //System.out.println(b.exist(board, "SEE"));
        System.out.println(b.exist(board, "ABCB"));
    }
    public boolean exist(char[][] board, String word) {
        boolean result = false;
        if (word != null && board != null) {
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    if (board[i][j] == word.charAt(0)) {
                        result = result | helper(word, i, j, 0, board);
                    }
                }
            }
        }
        return result;
    }

    public boolean helper(String word, int x, int y, int ptr, char[][] board) {
        boolean result = false;

        //Check out of bound or already considered
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '$') {
            return false;
        }

        //Current character does not match return false
        if (!(board[x][y] == word.charAt(ptr))) {
            return false;
        }

        //Word ended so match found
        if (ptr == word.length() - 1) {
            return true;
        }

        char curr = board[x][y];
        board[x][y] = '$';

        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                //To avoid (-1, 1), (1, -1), (-1, -1) and (1, 1)
                if (Math.abs(i) != Math.abs(j)) {
                    if (helper(word, x + i, y + j, ptr + 1, board)) {
                        return true;
                    }
                }
            }
        }
        board[x][y] = curr;
        return result;
    }
}
