/*
 *  According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in
 *  1970."
 *  Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
 *  using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * Write a function to compute the next state (after one update) of the board given its current state.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 */

public class Life {
  public static void main(String[] args) {

    Life l = new Life();
    int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};

    l.gameOfLife(board);
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[0].length; ++j) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  /*
   * This is in place logic, when we modify a cell, we mark it either 2 if it
   * changes from 0 to 1 or mark it -2 if it changes from 1 to 0. This value is
   * also checked in the helper function so that we can apply logic to all cells
   * depending on there original neighbors and not after modification.
   *
   * Running : O(m * n) Space : O(1)
   */
  public void gameOfLife(int[][] board) {
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[0].length; ++j) {
        int liveCount = getLiveNeighbors(i, j, board);
        if (board[i][j] == 0 && liveCount == 3) {
          board[i][j] = 2;
        } else if (board[i][j] == 1 && (liveCount < 2 || liveCount > 3)) {
          board[i][j] = -2;
        }
      }
    }

    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[0].length; ++j) {
        if (board[i][j] == -2) {
          board[i][j] = 0;
        } else if (board[i][j] == 2) {
          board[i][j] = 1;
        }
      }
    }
  }

  /*
   * Get all the neighbors with having positive value or are alive
   */
  private int getLiveNeighbors(int x, int y, int[][] board) {
    int cnt = 0;
    /*
     * Handy logic to visit all 8 neighbors
     */
    for (int i = -1; i < 2; ++i) {
      for (int j = -1; j < 2; ++j) {
        // Check it not out of bound
        if (x + i >= 0 && x + i < board.length && y + j >= 0 && y + j < board[0].length) {
          if (board[x + i][y + j] == 1 || board[x + i][y + j] == -2) {
            cnt++;
          }
        }
      }
    }

    // This is counted as well so decrement count by 1
    if (board[x][y] == 1) {
      cnt -= 1;
    }
    return cnt;
  }
}
