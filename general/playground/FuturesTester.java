import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FuturesTester {

    private static final ExecutorService threadpool = Executors.newFixedThreadPool(5);

    public static void main (String[] args) throws InterruptedException, ExecutionException {

        int from = Integer.valueOf(args[0]);
        int to = Integer.valueOf(args[1]);
        List<Task> tasks = new ArrayList<Task>();
        for(int i = from; i < to; ++i) {
            tasks.add(new Task(i));
        }
        List<Future<Integer>> futures = threadpool.invokeAll(tasks);
        threadpool.shutdown();
        System.out.println("...........finished");
    }

    private static class Task implements Callable<Integer> {

        private int value;

        public Task(int value) {
           this.value = value;
        }

        public Integer call() {
            try {
                while(value > 0) {
                    value --;
                    Thread.sleep(1000);
                    System.out.println(value);
                }
            } catch (InterruptedException ie ) {
                 ie.printStackTrace();
            }
            return value;
        }
    }
}

