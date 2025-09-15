/* On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 *
 *  A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 *  The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 *  Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 *  Input: board = [[1,2,3],[4,0,5]]
 *  Output: 1
 *  Explanation: Swap the 0 and the 5 in one move.
 *
 *  Input: board = [[1,2,3],[5,4,0]]
 *  Output: -1
 *  Explanation: No number of moves will make the board solved.
 *
 *  Input: board = [[4,1,2],[5,0,3]]
 *  Output: 5
 *  Explanation: 5 is the smallest number of moves that solves the board.
 *
 *  An example path:
 *  After move 0: [[4,1,2],[5,0,3]]
 *  After move 1: [[4,1,2],[0,5,3]]
 *  After move 2: [[0,1,2],[4,5,3]]
 *  After move 3: [[1,0,2],[4,5,3]]
 *  After move 4: [[1,2,0],[4,5,3]]
 *  After move 5: [[1,2,3],[4,5,0]]
 *  Input: board = [[3,2,4],[1,5,0]]
 *  Output: 14
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SlidingPuzzle {

  public static void main(String[] args) {
    // Accept the initial position of matrix
    // int[][] board = { { 1, 2, 3 }, { 4, 0, 5 } };
    // int[][] board = { { 4, 1, 2 }, { 5, 0, 3 } };
    int[][] board = {{3, 2, 4}, {1, 5, 0}};
    // int[][] board = { { 1, 2, 3 }, { 4, 0, 5 } };
    SlidingPuzzle sp = new SlidingPuzzle();
    System.out.println("Steps to solve the puzzle : " + sp.slidingPuzzle(board));
  }

  public int slidingPuzzle(int[][] board) {
    // System.out.println("Flattened : " + flattenedBoard);
    // System.out.println("Checker : " + decode(encode(flattenedBoard)));

    // Track visited states
    Set<Integer> oldStates = new HashSet<>();
    // current queued states to be considered
    Deque<Integer> stateQueue = new LinkedList<>();

    // Destination
    int[][] finalState = {{1, 2, 3}, {4, 5, 0}};
    int encodedFinalState = encode(flattenBoard(finalState));
    // System.out.println("Checker : " + encodedFinalState);

    // Starting point of the board state
    int encodedCurrentState = encode(flattenBoard(board));
    stateQueue.offer(encodedCurrentState);
    int steps = -1;

    while (!stateQueue.isEmpty()) {
      steps++;
      int stateCount = stateQueue.size();
      // System.out.println("STEPS ::::::: " + steps);
      // System.out.println("STATES ::::::: " + stateCount);
      // Consider only states at next level
      while (stateCount > 0) {
        int currentState = stateQueue.poll();
        // Process only if not visited
        if (!oldStates.contains(currentState)) {
          if (currentState == encodedFinalState) {
            // System.out.println("Current : " + currentState + " Encoded State : " +
            // encodedFinalState);
            return steps;
          }
          // Marks as visited
          oldStates.add(currentState);
          // Get next reachable states and add them to queue
          stateQueue.addAll(getNextStates(currentState));
        }
        stateCount--;
      }
    }
    return -1;
  }

  /*
   * original board looks like
   * 1, 2, 3
   * 4, 0, 5
   *
   * We have flattened board as say [ 1, 2, 3, 4, 0 ,5 ]
   * at each of this position what are the possible positions that a 0 (if present) can go to ?
   * (1,3) (-1, 1, 3) (-1,3) (1,-3), (-1,3,1) (-1,-3)
   * which are equivalent to up, down, left and right movements in the above 2D board
   * So we maintain a list of possible direction values
   * e.g. at index 0,0 => possible positions are (0,1) and (1,0) which are at a distance 1 and 3 in flattened array
   * but we have to be careful for index (0, 2) and (1,0) in 2 D array which are index 2 and 3 in flattened array.
   * Though in flattened array we have next ( from 2 -> 3 index ) and prev ( from 3 -> 2 ) that is not valid position in 2D array
   * So we have to avoid when index is 2 and direction is 1 or index is 3 and direction is -1
   * Also calculate only possible positions for element 0 and not any other element
   *
   */
  private List<Integer> getNextStates(int currentState) {
    List<Integer> directions = new ArrayList<>();
    directions.add(-3);
    directions.add(3);
    directions.add(-1);
    directions.add(1);

    List<Integer> newStateCodes = new LinkedList<>();
    List<Integer> flattenedBoard = decode(currentState);
    for (int stateIndex = 0; stateIndex < flattenedBoard.size(); stateIndex++) {
      // Only positions for element 0
      if (flattenedBoard.get(stateIndex) == 0) {
        for (int index : directions) {
          // Corner cases
          if ((stateIndex == 2 && index == 1) || (stateIndex == 3 && index == -1)) {
            continue;
          }
          // Check if the next possible position is within the bounds
          if (stateIndex + index < 6 && stateIndex + index >= 0) {
            // Swap
            swapElements(stateIndex, index, flattenedBoard);
            // System.out.println("Flattened : " + flattenedBoard);
            // Record state
            newStateCodes.add(encode(flattenedBoard));
            // Restore for next state
            swapElements(stateIndex, index, flattenedBoard);
            // System.out.println("Flattened : " + flattenedBoard);
          }
        }
      }
    }
    // System.out.println("New States : " + newStateCodes);
    return newStateCodes;
  }

  // Swaps elements from position a to be within flattened board
  private void swapElements(int stateIndex, int directionIndex, List<Integer> flattenedBoard) {
    int temp = flattenedBoard.get(stateIndex);
    flattenedBoard.set(stateIndex, flattenedBoard.get(stateIndex + directionIndex));
    flattenedBoard.set(stateIndex + directionIndex, temp);
  }

  // Make 2 D array into a simple list
  // This helps processing easier as we can encode and decode it
  // conveniently
  private List<Integer> flattenBoard(int[][] board) {
    List<Integer> flattened = new LinkedList<Integer>();
    for (int row = 0; row < board.length; ++row) {
      for (int col = 0; col < board[0].length; col++) {
        flattened.add(board[row][col]);
      }
    }
    return flattened;
  }

  // We know that the board size is limited so we can convert the board
  // status into a number which will be easier to store as a part of set or list
  // and hence easy for processing as well
  // e.g.
  // [ 1, 2, 3 ] ==> flattening ==> [ 1,2,3,4,5,0 ] ==> encoding ==> 123450
  // [4, ,5 0]
  private int encode(List<Integer> flattenedBoard) {
    int encoded = 0;
    // Each number add to the previously obtained no. * 10
    for (Integer num : flattenedBoard) {
      encoded *= 10;
      encoded += num;
    }
    return encoded;
  }

  // Again just reverse the procedure of constructing a number
  // get individual digits
  private List<Integer> decode(int encoded) {
    List<Integer> flattened = new LinkedList<Integer>();
    while (encoded >= 0 && flattened.size() < 6) {
      // reminder gives each individual digit
      flattened.add(0, encoded % 10);
      encoded = encoded / 10;
    }
    return flattened;
  }
}
