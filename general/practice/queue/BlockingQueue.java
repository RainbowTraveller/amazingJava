import java.util.concurrent.LinkedBlockingQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class BlockingQueue {
	public static void main(String[] args) {
		// instantiate blocking queue
		// instantiate a producer and consumer class
		// then start threads to produce and consume events
		//
		//
		// Shared Queue
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		Producer eventProducer = new Producer(queue);
		Consumer eventConsumer = new Consumer(queue);

		Thread pThread = new Thread(eventProducer);
		pThread.start();
		Thread cThread = new Thread(eventConsumer);
		cThread.start();
	}
}

class Producer implements Runnable {
	String[] events = {"Visit", "Visit", "Conversion", "Visit", "Conversion", "Visit", "Conversion", "Visit", "Visit", "finish"};
	private final LinkedBlockingQueue<String> pqueue;

	public Producer(LinkedBlockingQueue<String> pqueue) {
		this.pqueue = pqueue;
	}

	public void run() {
		produce();
	}

	private void produce() {
	    for(String event: events) {
			//Object is added to the shared queue
			pqueue.add(event);
	    }
	}
}

class Consumer implements Runnable {
	private final LinkedBlockingQueue<String> cqueue;
	private Map<String, Integer> eventCountTracker;

	public Consumer(LinkedBlockingQueue<String> cqueue) {
		this.cqueue = cqueue;
		this.eventCountTracker = new HashMap<String, Integer>();
	}

	public void run() {
		try {
			while(cqueue.peek() != null) {
				try{
					String event = cqueue.take();
					if(event.equalsIgnoreCase("finish")) {
						break;
					}
					/*try {
						Thread.sleep(10);
					} catch(InterruptedException ie) {
						System.out.println("Wait inturrupted");
					}*/
					consume(event);
				} catch(InterruptedException ie) {
					System.out.println("Take method interrupted");
				}
			}
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
		int eventCount = 1;
		if(eventCountTracker.containsKey(event)) {
			eventCount = eventCountTracker.get(event);
			eventCount++;
		}
		eventCountTracker.put(event,eventCount);
	}

	private void result() {
		for(String event : eventCountTracker.keySet()) {
			int count = eventCountTracker.get(event);
			System.out.println(count + " " + event);
		}
	}
}
