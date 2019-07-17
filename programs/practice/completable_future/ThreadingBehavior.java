import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

public class ThreadingBehavior {
    private static ForkJoinPool pool;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        simpleThreading();
        changingPool();
        chainingWithChangingPool();
    }

    public static void simpleThreading() throws ExecutionException, InterruptedException {
        final CompletableFuture<Integer> task = startAsyncTask(2);

        Thread.sleep(100);

        task.thenAccept(result -> System.out.println(result + " : " + Thread.currentThread()));
        // when main hits this line, if the task is completed, then the execution of the
        // lambda runs in the main thread. However, when main hits this line, if the
        // task is not
        // completed, then the lambda may run into a different thread. Non-blocking.

        // A stage that is being attached to a resolved or rejected CompletableFuture
        // complete in the
        // calling thread. However, a state that is being attached to a pending
        // CompletableFuture will
        // run eventually when that resolves or rejects in the other thread.

        Thread.sleep(3000);

    }

    private static CompletableFuture<Integer> startAsyncTask(int number) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("running in thread " + Thread.currentThread());
            if (Math.random() > 0.75) {
                System.out.println("taking a slow run this time");
                sleep(2000);
            }
            return number * 2;
        });
    }

    public static void changingPool() throws ExecutionException, InterruptedException {
        pool = new ForkJoinPool(100);
        final CompletableFuture<Integer> task = startAsyncTask(2);
        Thread.sleep(100);
        task.thenAccept(result -> System.out.println(result + " : " + Thread.currentThread()));
        Thread.sleep(3000);
        pool.shutdown();
    }

    private static CompletableFuture<Integer> startAsyncTaskWithPool(int number) {
        Supplier<Integer> doubleValue = () -> {
            System.out.println("running in thread " + Thread.currentThread());
            if (Math.random() > 0.75) {
                System.out.println("taking a slow run this time");
                sleep(2000);
            }
            return number * 2;
        };
        if (Math.random() > 0.5) {
            System.out.println("using custom pool");
            return CompletableFuture.supplyAsync(doubleValue, pool);
        } else {
            System.out.println("using common pool");
            return CompletableFuture.supplyAsync(doubleValue);
        }
    }

    public static void chainingWithChangingPool() throws ExecutionException, InterruptedException {
        pool = new ForkJoinPool(100);

        final CompletableFuture<Integer> task = startAsyncTask(2);

        Thread.sleep(100);

        task.thenApply(result -> result * 2.2)
                .thenAccept(result -> System.out.println(result + " : " + Thread.currentThread()))
                .thenRun(() -> System.out.println("Let's celebrate"));

        Thread.sleep(3000);
        pool.shutdown();
    }

    private static boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
