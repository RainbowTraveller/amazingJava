import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.List;
import java.util.LinkedList;
import java.lang.InterruptedException;
import java.util.concurrent.ExecutionException;
import java.util.Random;

public class Threading {
    public static void main(String[] args) throws Exception {
        int coreCount = Runtime.getRuntime().availableProcessors();
        //Create thread pool
        ExecutorService service = Executors.newFixedThreadPool(coreCount);

        List<Future<Integer>> allFutures = new LinkedList<Future<Integer>>();
        //Submit task for execution
        for(int i = 0; i < 10; i++) {
            Future<Integer> future = service.submit( new Task() );
            allFutures.add(future);
        }
        System.out.println("Thread name : " + Thread.currentThread().getName());

        for(int i = 0; i < 10; i++) {
            try {
                Future<Integer> future = allFutures.get(i);
                System.out.println("Result from : " + i  + " + future : " + future.get());
            } catch(InterruptedException e) {
                e.printStackTrace();
            } catch(ExecutionException ee) {
                ee.printStackTrace();
            }
        }
        return;
    }

    static class Task implements Callable<Integer> {
        public Integer call() {
            return new Random().nextInt();
        }
    }
}
