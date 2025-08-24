/*
 * Input : a square matrix
 * This contains only 0s and 1s.
 *
 * Output : dimension of the largest square matrix which contains all 1s
 *
 * 3
 * 3
 * 1 1 0
 * 1 1 0
 * 1 0 1
 * 2
 *
 * 4
 * 4
 * 1 1 1 0
 * 0 1 1 1
 * 0 1 1 1
 * 0 1 1 1
 * 3
 *
 * 3
 * 3
 * 1 0 1
 * 1 0 0
 * 0 0 1
 * 1
 */
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LargestSquareMatrix {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    // BufferedWriter bufferedWriter = new BufferedWriter(new
    // FileWriter(System.getenv("OUTPUT_PATH")));

    System.out.println("Enter No. of rows : ");
    int arrRows = Integer.parseInt(bufferedReader.readLine().trim());
    System.out.println("Enter No. of columns : ");
    int arrColumns = Integer.parseInt(bufferedReader.readLine().trim());

    List<List<Integer>> arr = new ArrayList<>();

    System.out.println(
        "Please enter the matrix elements in row - column format delimited by space");
    for (int i = 0; i < arrRows; i++) {
      String[] arrRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

      List<Integer> arrRowItems = new ArrayList<>();

      for (int j = 0; j < arrColumns; j++) {
        int arrItem = Integer.parseInt(arrRowTempItems[j]);
        arrRowItems.add(arrItem);
      }

      arr.add(arrRowItems);
    }

    int result = MatrixSizer.largestMatrix(arr);
    int resultDynamic = MatrixSizer.getLargestMatrixSizeDynamically(arr);

    System.out.println("Max square matrix size : " + String.valueOf(result));
    System.out.println("Max square matrix size (dynamic): " + String.valueOf(resultDynamic));
    // bufferedWriter.write(String.valueOf(result));
    // bufferedWriter.newLine();

    bufferedReader.close();
    // bufferedWriter.close();
  }
}

class MatrixSizer {

  /*
   * Complete the 'largestMatrix' function below. The function is expected to
   * return an INTEGER. The function accepts 2D_INTEGER_ARRAY arr as parameter.
   * @param arr: 2D list of integers containing only 0s and 1s
   * @return: size of largest square matrix containing all 1s
   */
  public static int largestMatrix(List<List<Integer>> arr) {
    int maxSize = 0;
    if (arr != null) {
      int rows = arr.size();
      int columns = arr.get(0).size();
      // Scan the matrix
      for (int i = 0; i < rows; ++i) {
        List<Integer> currRow = arr.get(i);
        for (int j = 0; j < columns; ++j) {
          int currNum = currRow.get(j);
          // Process only when 1 is encountered
          if (currNum == 1) {
            // check maximum square matrix starting at this position
            int currSize = getMetrixSize(i, j, arr, 1);
            // Get the maximum size so far
            maxSize = Math.max(currSize, maxSize);
          }
        }
      }
    }
    return maxSize;
  }

  /**
   * level is important parameter, when 1 is encountered we send level 1 meaning that we have
   * obtained 1 * 1 matrix At each subsequent level or call we pass level incremented by 1 if we
   * find that the element at this position is 0, we return a level that is 1 less than level passed
   * to us. This indicates that if at all the n * n matrix is at previous level
   *
   * @param row current row
   * @param column current column
   * @param arr input matrix
   * @param level current level <br>
   *     Note: DO NOT USE THIS APPROACH FOR LARGE MATRIX AS IT IS RECURSIVE AND CAN CAUSE STACK
   *     OVERFLOW
   */
  private static int getMetrixSize(int row, int column, List<List<Integer>> arr, int level) {
    int currLevel = 0;
    if (row < 0
        || row >= arr.size()
        || column < 0
        || column >= arr.get(0).size()
        || arr.get(row).get(column) == 0) {
      return level - 1;
    }

    // only check 2 * 2 matrix recursively
    int level_1 = getMetrixSize(row, column + 1, arr, level + 1);
    int level_2 = getMetrixSize(row + 1, column, arr, level + 1);
    int level_3 = getMetrixSize(row + 1, column + 1, arr, level + 1);
    int tempLevel = Math.min(level_1, level_2);
    currLevel = Math.min(tempLevel, level_3);
    // Choose minimum of the values obtained so far as that is the true indicator of
    // matrix size at this
    // level
    return currLevel;
  }

  /**
   * Dynamic programming approach to find largest square matrix of 1s
   *
   * @param arr input matrix
   * @return size of largest square matrix of 1s
   */
  public static int getLargestMatrixSizeDynamically(List<List<Integer>> arr) {
    int maxSize = 0;
    if (arr != null) {
      if (arr.size() > 0 && arr.get(0) != null && arr.get(0).size() != 0) {
        // Tracker of same size
        int rows = arr.size();
        int columns = arr.get(0).size();
        // Allocate matrix with one row and column extra
        int[][] tracker = new int[rows + 1][columns + 1];
        for (int i = 1; i <= rows; ++i) {
          List<Integer> currRow = arr.get(i - 1);
          for (int j = 1; j <= columns; ++j) {
            int currNum = currRow.get(j - 1);
            // Set everything to 0
            tracker[i][j] = 0;
            // If original element is 1 then process
            if (currNum == 1) {
              if (i - 1 >= 0 && j - 1 >= 0) {
                // if surroundings is within the bound, get minimums of surroundings elements
                // and add 1 to it
                // which means surrounding has min x by x matrix this elements adds 1 more
                // making it x + 1 by x + 1 where x is min of surround 3
                tracker[i][j] =
                    Math.min(tracker[i - 1][j - 1], Math.min(tracker[i][j - 1], tracker[i - 1][j]))
                        + 1;
                // Check for max so far
                maxSize = Math.max(tracker[i][j], maxSize);
              }
            }
          }
        }
        // Print Tracker
        System.out.println("Tracker matrix : ");
        for (int i = 0; i < rows + 1; ++i) {
          for (int j = 0; j < columns + 1; ++j) {
            System.out.print(tracker[i][j] + " ");
          }
          System.out.println();
        }
      }
    }
    return maxSize;
  }
}
