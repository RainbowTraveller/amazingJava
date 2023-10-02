import java.util.concurrent.LinkedBlockingQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;

public class BlockingQueue {
    public static void main(String[] args) {
        // instantiate blocking queue
        // instantiate a producer and consumer class
        // then start threads to produce and consume events
        //
        //
        CountDownLatch startSignal = new CountDownLatch(1);
        // Shared Queue
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        Producer eventProducer = new Producer(queue, startSignal);
        Consumer eventConsumer = new Consumer(queue, startSignal);

        Thread pThread = new Thread(eventProducer);
        Thread cThread = new Thread(eventConsumer);
        pThread.start();
        cThread.start();
        startSignal.countDown();
    }
}

class Producer implements Runnable {
    String[] events = { "Visit", "Visit", "Conversion", "Visit", "Conversion", "Visit", "Conversion", "Visit", "Visit",
            "Jupiter", "Saturn", "Neptune", "Jupiter", "Saturn", "Neptune", "finish" };
    private final LinkedBlockingQueue<String> pqueue;
    private final CountDownLatch startSignal;

    public Producer(LinkedBlockingQueue<String> pqueue, CountDownLatch startSignal) {
        this.pqueue = pqueue;
        this.startSignal = startSignal;
    }

    public void run() {
        try {
            startSignal.await();
            produce();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    private void produce() {
        for (String event : events) {
            // Object is added to the shared queue
            pqueue.add(event);
        }
    }
}

class Consumer implements Runnable {
    private final LinkedBlockingQueue<String> cqueue;
    private Map<String, Integer> eventCountTracker;
    private final CountDownLatch startSignal;

    public Consumer(LinkedBlockingQueue<String> cqueue, CountDownLatch startSignal) {
        this.cqueue = cqueue;
        this.eventCountTracker = new HashMap<String, Integer>();
        this.startSignal = startSignal;
    }

    public void run() {
        try {
            startSignal.await();
            while (cqueue.peek() != null) {
                try {
                    String event = cqueue.take();
                    if (event.equalsIgnoreCase("finish")) {
                        break;
                    }
                    /*
                     * try { Thread.sleep(10); } catch(InterruptedException ie) {
                     * System.out.println("Wait inturrupted"); }
                     */
                    consume(event);
                } catch (InterruptedException ie) {
                    System.out.println("Take method interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        } catch (NoSuchElementException nsee) {
            System.out.println("Events exhausted");
        } finally {
            result();
        }
    }

    /*
     * Keeps track of the each string count
     */
    private void consume(String event) {
        int eventCount = eventCountTracker.getOrDefault(event, 0);
        eventCountTracker.put(event, eventCount + 1);
    }

    private void result() {
        for (String event : eventCountTracker.keySet()) {
            int count = eventCountTracker.get(event);
            System.out.println(count + " " + event);
        }
    }
}
