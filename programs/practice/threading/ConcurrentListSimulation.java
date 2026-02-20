import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentListSimulation {
  private final List<Integer> list = new ArrayList<>();

  // Controls access for Writers and Deleters
  private final Semaphore listLock = new Semaphore(1);
  // Specifically used to block Readers when a Deleter is active
  private final Semaphore deleterLock = new Semaphore(1);

  private final AtomicInteger activeReaders = new AtomicInteger(0);

  // READER: Blocks only for Deleters
  public void read(int index) throws InterruptedException {
    // Readers must acquire the deleterLock to ensure no Deleter is active
    // But multiple readers can pass through this "gate"
    synchronized (this) {
      if (activeReaders.incrementAndGet() == 1) {
        deleterLock.acquire(); // First reader blocks Deleters
      }
    }

    // Reading (Not blocked by Writers)
    if (index < list.size()) {
      System.out.println(Thread.currentThread().getName() + " read: " + list.get(index));
    }

    synchronized (this) {
      if (activeReaders.decrementAndGet() == 0) {
        deleterLock.release(); // Last reader allows Deleters
      }
    }
  }

  // WRITER: Blocks other Writers and Deleters, but NOT Readers
  public void append(int value) throws InterruptedException {
    listLock.acquire(); // Blocks other Writers and Deleters
    try {
      list.add(value);
      System.out.println(Thread.currentThread().getName() + " appended: " + value);
    } finally {
      listLock.release();
    }
  }

  // DELETER: Blocks EVERYONE
  public void deleteTail() throws InterruptedException {
    // 1. Stop new Readers and Writers from starting
    deleterLock.acquire();
    // 2. Ensure current Writer is finished
    listLock.acquire();

    try {
      if (!list.isEmpty()) {
        int removed = list.remove(list.size() - 1);
        System.out.println(Thread.currentThread().getName() + " deleted: " + removed);
      }
    } finally {
      listLock.release();
      deleterLock.release();
    }
  }

  public static void main(String[] args) {
    ConcurrentListSimulation simulation = new ConcurrentListSimulation();

    // Example Task Runner
    Runnable readerTask =
        () -> {
          try {
            simulation.read(0);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        };
    Runnable writerTask =
        () -> {
          try {
            simulation.append((int) (Math.random() * 100));
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        };
    Runnable deleterTask =
        () -> {
          try {
            simulation.deleteTail();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        };

    // Fire off threads to simulate concurrency
    for (int i = 0; i < 5; i++) {
      new Thread(writerTask, "Writer-" + i).start();
      new Thread(readerTask, "Reader-" + i).start();
      new Thread(deleterTask, "Deleter-" + i).start();
    }
  }
}
