import java.util.concurrent.LinkedBlockingQueue;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class QueuedTasksWithFutures {
    public static void main(String[] args) {
        Consumer eventConsumer = new Consumer();
        Producer eventProducer = new Producer(eventConsumer);
        eventProducer.run();
        /*Thread pThread = new Thread(eventProducer);
        pThread.start();*/
        System.out.println("Finished initiating threads");
    }
}

//class Producer implements Runnable {
class Producer {
    String[] events = { "Visit", "Conversion", "Earth", "Venus", "Jupiter", "Saturn", "Neptune", "Pluto", "Mars", "Mercury", "finish" };
    Consumer eventConsumer;

    public Producer(Consumer consumer) {
        this.eventConsumer = consumer;
    }

    public void run() {
        try {
            for(int i = 0; i < events.length; i++) {
                //Random r = new Random();
                //int randomInt = r.nextInt(events.length);
                //System.out.println("index : " + i + " ::: Value : " + events[randomInt] );
                eventConsumer.input(events[i]);
            }
            eventConsumer.shutdown();
        } catch (Exception ie) {
            throw new RuntimeException(ie);
        }
    }
}

class Consumer {
    private final BlockingQueue<String> queue;
    private final Set<String> inflight;
    private transient ExecutorService service;


    public Consumer() {
        queue = new LinkedBlockingQueue<>(1);
        inflight = new HashSet<>();
        service = Executors.newFixedThreadPool(4);
    }

    public void input(String word) {
        System.out.println("Word Received : " + word);
        try {
             if(queue.offer(word, 1, TimeUnit.SECONDS) == true && inflight.size() < 4) {
                System.out.println("Available Queue : " + queue);
                CompletableFuture.runAsync(() -> process(), service)
                    .thenAccept(data -> {
                        inflight.remove(data);
                        System.out.println("After Set : " + queue);
                    });
            }
            //System.out.println("SUBMITTED");
        } catch(InterruptedException ie) {
            System.out.println("Offer interrupted");
        }
    }

    private String process() {
        try {
            synchronized(queue) {
                //System.out.println("Pre Polled Queue :::: " + queue + " :: " + Thread.currentThread().getName());
                String curr = queue.poll(5, TimeUnit.SECONDS);
                System.out.println("Polled Queue :::: " + queue + " :: " + Thread.currentThread().getName());
                inflight.add(curr);
                //System.out.println("Before Set : " + queue);
                consume(curr);
                return curr;
            }
         }catch (Exception ie) {
            throw new RuntimeException(ie);
        }
    }

    private void consume(String event) {
        Random r = new Random();
        int randomInt = r.nextInt(5);
        try {
            Thread.sleep(randomInt);
            System.out.println("Event :::::::  " + event  + " Consume : " + Thread.currentThread().getName());
        } catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
        //System.out.println("Event : " + event  + "::" + Thread.currentThread().getName());
    }

    public void shutdown() {
        service.shutdown();
    }
}
