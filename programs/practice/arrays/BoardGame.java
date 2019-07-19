// Given a board and an end position for the player, write a function to determine if it is possible to travel from every open cell on the board to the given end position.

// board1 = [
//     [ 0,  0,  0, 0, -1 ],
//     [ 0, -1, -1, 0,  0 ],
//     [ 0,  0,  0, 0,  0 ],
//     [ 0, -1,  0, 0,  0 ],
//     [ 0,  0,  0, 0,  0 ],
//     [ 0,  0,  0, 0,  0 ],
// ]

// board2 = [
//     [  0,  0,  0, 0, -1 ],
//     [  0, -1, -1, 0,  0 ],
//     [  0,  0,  0, 0,  0 ],
//     [ -1, -1,  0, 0,  0 ],
//     [  0, -1,  0, 0,  0 ],
//     [  0, -1,  0, 0,  0 ],
// ]

// end = (0, 0)

// Expected output:
// board1, end -> True
// board2, end -> False

import java.io.*;
import java.util.*;

class BoardGame {
    public static void main(String[] args) {

        int[][] board1 = new int[][] { { 0, 0, 0, 0, -1 }, { 0, -1, -1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, -1, 0, 0, 0 },
                { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, };

        int[][] board2 = new int[][] { { 0, 0, 0, 0, -1 }, { 0, -1, -1, 0, 0 }, { 0, 0, 0, 0, 0 }, { -1, -1, 0, 0, 0 },
                { 0, -1, 0, 0, 0 }, { 0, -1, 0, 0, 0 }, };
        int[] start = { 0, 0 };
        System.out.println("Is Travel Possible ? : " + isTravelPossible(board1, start[0], start[1]));
        System.out.println("Is Travel Possible ? : " + isTravelPossible(board2, start[0], start[1]));
        System.out.println("Is Travel Possible ? : " + isVisitPossible(board1, start[0], start[1]));
        System.out.println("Is Travel Possible ? : " + isVisitPossible(board2, start[0], start[1]));
    }

    static class Position {
        int row;
        int column;

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public String toString() {
            return "(" + row + ", " + column + ")";
        }

        public boolean equals(Object o) {
            if (o != null && o instanceof Position) {
                Position p = (Position) o;
                return this.row == p.row && this.column == p.column;
            }
            return false;
        }

        public int hashCode() {
            return row | (column << 15);
        }
    }

    public static List<Position> getPossibleMoves(int[][] board, int currRow, int currColumn) {
        List<Position> possibleMoves = new LinkedList<Position>();
        if (board != null) {
            if (currRow >= 0 && currRow < board.length) {
                // currRow, currColumn + 1
                if ((currColumn + 1) >= 0 && (currColumn + 1) < board[0].length
                        && board[currRow][currColumn + 1] != -1) {
                    possibleMoves.add(new Position(currRow, currColumn + 1));
                }
                // currRow, currColumn - 1
                if ((currColumn - 1) >= 0 && (currColumn - 1) < board[0].length
                        && board[currRow][currColumn - 1] != -1) {
                    possibleMoves.add(new Position(currRow, currColumn - 1));
                }
            }
            if (currColumn >= 0 && currColumn < board[0].length) {
                // currRow + 1, currColumn
                if (currRow + 1 >= 0 && currRow + 1 < board.length && board[currRow + 1][currColumn] != -1) {
                    possibleMoves.add(new Position(currRow + 1, currColumn));
                }
                // currRow - 1, currColumn
                if (currRow - 1 >= 0 && currRow - 1 < board.length && board[currRow - 1][currColumn] != -1) {
                    possibleMoves.add(new Position(currRow - 1, currColumn));
                }
            }
        }
        return possibleMoves;
    }

    public static boolean isTravelPossible(int[][] board, int row, int col) {

        Stack<Position> possibles = new Stack<Position>();
        int count = 0;
        int validPosition = 0;
        Set<Position> visited = new HashSet<Position>();

        if (board != null) {

            Position startPoint = new Position(row, col);
            possibles.push(startPoint);
            visited.add(startPoint);
            count++;
            while (!possibles.isEmpty()) {
                Position curr = possibles.pop();

                // Get all valid neighbouring points
                List<Position> currMoves = getPossibleMoves(board, curr.row, curr.column);

                for (Position p : currMoves) {
                    // Push them to stack only if they are not already visited
                    if (!visited.contains(p)) {
                        count++;
                        possibles.push(p);
                        visited.add(p);
                    }
                }
            }

            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    if (board[i][j] == 0) {
                        validPosition++;
                    }
                }
            }
        }
        System.out.println("Count : " + count + " Valid Positions : " + validPosition);
        return count == validPosition;
    }

    public static boolean isVisitPossible(int[][] board, int x, int y) {
        int validPosition = 0;
        int count = visitHelper(board, x ,y);
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 0 || board[i][j] == -2) {
                    validPosition++;
                }
            }
        }
        System.out.println("Count : " + count + " Valid Positions : " + validPosition);
        return count == validPosition;
    }

    public static int visitHelper(int[][] board, int x, int y) {
        int count = 0;
        if(board != null) {
            if(x >= 0 && x < board.length && y >=0 && y < board[0].length && board[x][y] != -1 && board[x][y] != -2 ) {
                board[x][y] = -2;
                count =  1 + visitHelper(board, x + 1,y) + visitHelper(board, x - 1, y) + visitHelper(board, x, y + 1) + visitHelper(board, x, y - 1);
            }
        }
        return count;
    }
}
