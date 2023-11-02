import java.util.List;
import java.util.ArrayList;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class MatrixTranspose {

  /** Worker class responsible for exchanging numbers from a given set of rows */
  static class Worker implements Runnable {
    // rows to be worked on by this worker
    List<Integer> rows;
    // Matrix Under Consideration
    int[][] matrix;
    // COls for each row
    int cols;

    @java.lang.Override
    public java.lang.String toString() {
      return "Worker{"
          + "rows="
          + rows
          + ", matrix="
          + java.util.Arrays.toString(matrix)
          + ", cols="
          + cols
          + '}';
    }

    public Worker(int[][] matrix) {
      rows = new ArrayList<>();
      this.matrix = matrix;
      cols = matrix.length;
    }

    public void addRow(int row) {
      // Add row to the worker
      rows.add(row);
    }

    public void run() {
      for (int row : rows) { // Fetch row no. from the list
        for (int col = 0; col < cols; col++) { // Scan all columns
          if (col != row && col < row) { // important condition to check
            synchronized (matrix[row]) { // Lock based on entire row array
              // System.out.println("Thread name : " + Thread.currentThread().getName());
              // System.out.println("Working on row : " + row);
              int temp = matrix[row][col];
              matrix[row][col] = matrix[col][row];
              matrix[col][row] = temp;
            }
            try {
              Thread.sleep(1000);
            } catch (InterruptedException ie) {
              ie.printStackTrace();
            }
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    //    int[][] matrix = {
    //      {4, 5, 8, 0, 8, 8, 3, 8},
    //      {1, 6, 7, 9, 9, 3, 0, 3},
    //      {9, 3, 2, 1, 4, 5, 6, 9},
    //      {3, 4, 3, 5, 3, 2, 2, 7},
    //      {4, 5, 2, 3, 6, 3, 5, 4},
    //      {2, 6, 5, 6, 4, 6, 3, 0},
    //      {2, 6, 5, 6, 4, 6, 3, 0},
    //      {9, 3, 2, 1, 4, 5, 6, 9},
    //    };

    int[][] matrix = {
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 9}
    };

    //    int[] [] matrix = {
    //            {1,2,3,7},
    //            {4,5,6,8},
    //            {7,8,9,10},
    //            {1,2,3,0},
    //    };
    System.out.println("Before");
    printMatrix(matrix);

    // List of workers
    List<Worker> workers = new ArrayList<>();

    for (int i = 0; i < 4; i++) {
      // Adding 4 workers
      workers.add(new Worker(matrix));
    }

    int workerIndex = 0;
    // Assign each worker a row to work on
    for (int row = 0; row < matrix.length; row++) {
      int mod = workerIndex % workers.size();
      //      System.out.println("Index : " + mod);
      Worker currWorker = workers.get(mod);
      currWorker.addRow(row);
      workerIndex++;
    }

    // PART A : Classic Thread Implementation
    // Starting workers manually
    //    Thread[] threads = new Thread[workers.size()];
    //    int index = 0;
    //    // Start each thread and initiate processing
    //    for (Worker worker : workers) {
    //      Thread workerThread = new Thread(worker);
    //      workerThread.start();
    //      System.out.println(workerThread.getName());
    //      threads[index++] = workerThread;
    //    }

    //    transpose(matrix); // This is typical regular method
    //    Wait for threads to finish
    //    waitForThreadsToFinish(threads);

    // PART B : Using Thread pool
    ExecutorService service = Executors.newFixedThreadPool(4);
    for (int i = 0; i < 4; i++) {
      Worker currWorker = workers.get(i);
      //      System.out.println(currWorker);
      service.execute(currWorker);
    }

    System.out.println("After");
    try {
      // This is important by default the threads execute in the background and
      // control returns to main thread and subsequent code is executed
      service.awaitTermination(60, TimeUnit.SECONDS);
    } catch (InterruptedException ie) {
      service.shutdownNow();
      // Preserve interrupt status
      Thread.currentThread().interrupt();
    }
    // Program won't return to terminal without shutdown call
    service.shutdownNow();
    printMatrix(matrix);
  }

  /**
   * Simple method to exchange rows and cols of a square matrix
   *
   * @param matrix interger double dimention array
   */
  public static void transpose(int[][] matrix) {
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[0].length; ++j) {
        if (i != j && i < j) {
          int temp = matrix[i][j];
          matrix[i][j] = matrix[j][i];
          matrix[j][i] = temp;
        }
      }
    }
  }

  /**
   * Prints supplied matrix in proper row and col format
   *
   * @param matrix interger double dimention array
   */
  public static void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[0].length; ++j) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void waitForThreadsToFinish(Thread... threads) {
    try {
      for (Thread thread : threads) {
        thread.join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
