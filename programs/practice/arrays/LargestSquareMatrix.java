import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class MatrixSizer {

    /*
     * Complete the 'largestMatrix' function below.
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */
    public static int largestMatrix(List<List<Integer>> arr) {
        int maxSize = 0;
        if( arr != null) {
            int rows = arr.size();
            int columns = arr.get(0).size();
            for(int i = 0; i < rows; ++i) {
                List<Integer> currRow = arr.get(i);
                for(int j = 0; j < columns; ++j) {
                    int currNum = currRow.get(j);
                    if( currNum == 1) {
                        int currSize = getMetrixSize(i, j, arr, 1);
                        maxSize = Math.max( currSize, maxSize);
                    }
                }
            }
        }
        return maxSize;
    }

    public static int getMetrixSize(int row, int column, List<List<Integer>> arr, int level) {
        int currLevel = 0;
        if( row < 0 || row >= arr.size() || column < 0 || column >= arr.get(0).size() || arr.get(row).get(column) == 0) {
            return level - 1;
        }

        int level_1 = getMetrixSize(row, column + 1, arr, level + 1);
        int level_2 = getMetrixSize(row + 1, column, arr, level + 1);
        int level_3 = getMetrixSize(row + 1, column + 1, arr, level + 1);
        int tempLevel = Math.min(level_1, level_2);
        currLevel =  Math.min(tempLevel, level_3);
        return currLevel;
    }

}

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
public class LargestSquareMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        System.out.println("Enter No. of rows : ");
        int arrRows = Integer.parseInt(bufferedReader.readLine().trim());
        System.out.println("Enter No. of columns : ");
        int arrColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> arr = new ArrayList<>();

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

        System.out.println(String.valueOf(result));
        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        bufferedReader.close();
        //bufferedWriter.close();
    }
}

