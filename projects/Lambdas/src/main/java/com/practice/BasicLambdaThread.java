import java.util.*;

class BasicLambdaThread {
  public static void main(String[] args) {
    new Thread(new Runnable() {
      public void run() {
        System.out.println("In another thread");
      }
    }).start();

    new Thread(() -> System.out.println("also in another thread")).start();
  }
}
