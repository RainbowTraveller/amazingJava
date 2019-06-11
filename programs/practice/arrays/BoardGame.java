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

    int[][] board1 = new int[][] {
        { 0,  0,  0, 0, -1 },
        { 0, -1, -1, 0,  0 },
        { 0,  0,  0, 0,  0 },
        { 0, -1,  0, 0,  0 },
        { 0,  0,  0, 0,  0 },
        { 0,  0,  0, 0,  0 },
    };

    int[][] board2 = new int[][] {
        {  0,  0,  0, 0, -1 },
        {  0, -1, -1, 0,  0 },
        {  0,  0,  0, 0,  0 },
        { -1, -1,  0, 0,  0 },
        {  0, -1,  0, 0,  0 },
        {  0, -1,  0, 0,  0 },
    };
    int [] start = {0, 0};
    System.out.println("Is Travel Possible ? : " + isTravelPossible(board1, start ));
    System.out.println("Is Travel Possible ? : " + isTravelPossible(board2, start));
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
            if(o != null && o instanceof Position) {
                Position p = (Position) o;
                return this.row == p.row && this.column == p.column;
            }
            return false;
        }


        public int hashCode() {
            return  row | (column << 15);
        }
    }


  public static List<Position> getPossibleMoves(int [][] board, int [] currentPosition) {
    List<Position> possibleMoves = new LinkedList<Position>();
    if(board != null && currentPosition != null) {
      int currRow = currentPosition[0];
      int currColumn = currentPosition[1];
      //currRow, currColumn + 1
      if(currRow >= 0 && currRow < board.length && (currColumn + 1) >= 0 && (currColumn + 1) < board[0].length && board[currRow][currColumn + 1] != -1) {
        possibleMoves.add(new Position(currRow, currColumn + 1));

      }
      //currRow, currColumn - 1
      if(currRow >= 0 && currRow < board.length && (currColumn - 1) >= 0 && (currColumn - 1) < board[0].length && board[currRow][currColumn - 1] != -1) {
        possibleMoves.add(new Position(currRow, currColumn - 1));

      }
      //currRow + 1, currColumn

      if(currRow + 1 >= 0 && currRow + 1 < board.length && currColumn >= 0 && currColumn < board[0].length && board[currRow + 1][currColumn] != -1) {
        possibleMoves.add(new Position(currRow + 1, currColumn));

      }
      //currRow - 1, currColumn
      if(currRow - 1 >= 0 && currRow - 1 < board.length && currColumn >= 0 && currColumn < board[0].length && board[currRow - 1][currColumn] != -1) {
        possibleMoves.add(new Position(currRow - 1, currColumn));
      }
    }
    return possibleMoves;
  }

    public static boolean isTravelPossible(int[][] board, int[] position) {

        Stack<Position> possibles = new Stack<Position>();
        int count = 0;
        int validPosition = 0;
        Set<Position> visited = new HashSet<Position>();

        if(position != null && board!= null) {

            Position startPoint = new Position(position[0], position[1]);
            possibles.push(startPoint);
            visited.add(startPoint);
            count++;
            while(!possibles.isEmpty()) {
                Position curr = possibles.pop();
                int[] currLocation = new int[2];
                currLocation[0] = curr.row;
                currLocation[1] = curr.column;

                //Get all valid neighbouring points
                List<Position> currMoves = getPossibleMoves(board, currLocation);

                for(Position p : currMoves) {
                    //Push them to stack only if they are not already visited
                    if(!visited.contains(p)) {
                        count++;
                        possibles.push(p);
                        visited.add(p);
                    }
                }
            }

            for(int i = 0; i < board.length; ++i) {
                for(int j = 0; j < board[0].length; ++j) {
                    if(board[i][j] == 0) {
                        validPosition++;
                    }
                }
            }
        }
        System.out.println("Count : " + count + " Valid Positions : " + validPosition);
        return count == validPosition;
    }
}

