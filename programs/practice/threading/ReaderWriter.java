import java.util.List;
import java.util.LinkedList;
import java.lang.Math;
import java.lang.Thread;

/**
 * handling interruptedException
 * https://www.yegor256.com/2015/10/20/interrupted-exception.html
 * http://www.ibm.com/developerworks/library/j-jtp05236/
 */
public class ReaderWriter {
    public static void main(String[] args) {
        List<String> words = new LinkedList<String>();
        Writer w = new Writer(words);
        System.out.println("Writer Creted");
        Reader r1 = new Reader(words);
        System.out.println("Reader Creted");
        Reader r2 = new Reader(words);
        System.out.println("Reader Creted");
        Reader r3 = new Reader(words);
        System.out.println("Reader Creted");
        Reader r4 = new Reader(words);
        System.out.println("Reader Creted");

        Thread writerThread = new Thread(w);
        Thread readerThread1 = new Thread(r1);
        Thread readerThread2 = new Thread(r2);
        Thread readerThread3 = new Thread(r3);
        Thread readerThread4 = new Thread(r4);

        readerThread1.start();
        System.out.println("Reader Thread Started");
        readerThread2.start();
        System.out.println("Reader Thread Started");
        writerThread.start();
        System.out.println("Writer Thread Started");
        readerThread3.start();
        System.out.println("Reader Thread Started");
        readerThread4.start();
        System.out.println("Reader Thread Started");
    }
}

class Reader implements Runnable {
    private List<String> words;
    private int index;

    public Reader(List<String> list) {
        this.words = list;
        this.index = 0;
    }

    public void run() {
        while (true) {
            synchronized (words) {
                if (words.isEmpty() || index >= words.size()) {
                    try {
                        System.out.println("Waiting for Writer to writer a word");
                        words.wait();
                        System.out.println("Notified ...!");
                        System.out.println("Contents of Writer : " + words);

                    } catch (InterruptedException ie) {
                        System.out.println("Interruption Occurred!");
                    }
                }
                System.out.println("Word Read : " + words.get(index));
                index++;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                System.out.println("Interruption Occurred while Sleeping !");
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Writer implements Runnable {

    private List<String> words;
    private String[] source = { "Atom", "Uranus", "Mars", "Neptune", "Pluto", "Jupiter", "Venus" };

    public Writer(List<String> list) {
        words = list;
    }

    public boolean isBufferEmpty() {
        return words.isEmpty();
    }

    public boolean isSuitableOffset(int index) {
        return index < words.size();
    }

    public String getWord(int index) {
        if (index < words.size())
            return words.get(index);
        return null;
    }

    public List<String> getWords() {
        return words;
    }

    public void addWord(String w) {
        words.add(w);
    }

    public void run() {
        while (true) {
            synchronized (words) {
                int index = (int) (Math.random() * source.length);
                String word = source[index];
                addWord(word);
                System.out.println(" Word Written : " + word);
                System.out.println(words);
                words.notifyAll();
            }
            try {
                Thread.sleep(9000);
            } catch (InterruptedException ie) {
                System.out.println("Interruption Occurred while Sleeping !");
            }
        }
    }
}
