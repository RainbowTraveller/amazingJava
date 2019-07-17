import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        simpleAsyncTask();
        futuresGetDemo();
        futuresGetNowDemo();
        futuresThenAcceptDemo();
        statusCheck();
        piping();
    }

    public static void simpleAsyncTask() throws InterruptedException {
        System.out.println("In start of main " + Thread.currentThread());
        startAsyncTask(); // this is non-blocking
        System.out.println("At the end of main " + Thread.currentThread());
        Thread.sleep(2000);
    }

    private static void startAsyncTask() {
        // lambda expression
        CompletableFuture.runAsync(() -> System.out.println("running a little task " + Thread.currentThread()));

        /*
         * Traditional CompletableFuture.runAsync(new Runnable() { public void run() {
         * System.out.println("running a little task " + Thread.currentThread()); } });
         */
    }

    public static void futuresGetDemo() throws ExecutionException, InterruptedException {
        System.out.println("In start of main " + Thread.currentThread());
        final CompletableFuture<Integer> resultFuture = startAsyncTaskForGetDemo(2);
        System.out.println("At the end of main " + Thread.currentThread());
        System.out.println(resultFuture.get()); // bad idea, blocking call and why bother.
    }

    private static CompletableFuture<Integer> startAsyncTaskForGetDemo(int number) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            return number * 2;
        });
    }

    public static void futuresGetNowDemo() throws ExecutionException, InterruptedException {
        System.out.println("In start of main " + Thread.currentThread());
        final CompletableFuture<Integer> resultFuture = startAsyncTaskForGetNowDemo(2);
        System.out.println("At the end of main " + Thread.currentThread());
        sleep(1000);
        System.out.println(resultFuture.getNow(0)); // bad idea, blocking call and why bother.
        // getNow is a little bit better than get, but they both are bad.
    }

    private static CompletableFuture<Integer> startAsyncTaskForGetNowDemo(int number) {
        return CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.75) {
                System.out.println("taking a slow run this time");
                sleep(2000);
            }

            return number * 2;
        });
    }

    public static void futuresThenAcceptDemo() throws ExecutionException, InterruptedException {
        startAsyncTaskForThenAcceptDemo(2).thenAccept(result -> System.out.println(result));

    }

    private static CompletableFuture<Integer> startAsyncTaskForThenAcceptDemo(int number) {
        return CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.75) {
                System.out.println("taking a slow run this time");
                sleep(2000);
            }

            return number * 2;
        });
    }

    public static void statusCheck() throws ExecutionException, InterruptedException {
        final CompletableFuture<Integer> task = createTask();

        Thread.sleep(100);

        System.out.println(task.isDone());
        System.out.println(task.isCancelled());
        System.out.println(task.isCompletedExceptionally());
    }

    private static CompletableFuture<Integer> createTask() {
        // return CompletableFuture.supplyAsync(() -> 2);
        return CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("oops");
        });
    }

    public static void piping() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> task = new CompletableFuture<>();
        task
            .thenApply(e -> e * 2)
            .thenApply(e -> e + 1)
            .thenAccept(System.out::println);
        task.complete(10);
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
