import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.security.KeyPair;

/**
 * Class representing an Unordered Max Priority Queue implementation
 * The higher the priority higher are the chances of the element to get removed from the queue.
 * This is naive implementation with capacity limit as it uses array as part of underlying implementation. This is a
 * generic class and can be parameterized with any class implementing Comparable interface.
 * <p>
 * Running Complexities :
 * <p>
 * Insert : 1 : We insert at first available position managed by counter index
 * Del Max : N : We need to scan entire array to find max element and then delete it
 * Max : N : Same as above as the elements are stored in random order
 *
 * @param <Key>
 */
public class UnOrderedMaxPriorityQueue<Key extends Comparable<Key>> implements PriorityQueue<Key> {
    // then press Enter. You can now see whitespace characters in your code.
    private Key[] queue;
    private int count;

    /**
     * Create an empty priority queue
     */
    public UnOrderedMaxPriorityQueue(int capacity) {
        queue = (Key[]) new Comparable[capacity];
    }

    /**
     * Create a priority queue with given input keys
     *
     * @param keys arrays of the keys to be maintained in the priority queue
     */
    public UnOrderedMaxPriorityQueue(Key[] keys) {

    }

    /**
     * Insert the given key to the priority queue
     *
     * @param key new key to be inserted to the priority queue
     */
    public void insert(Key key) {
        queue[count++] = key;
    }

    /**
     * Deletes the key with the maximum priority from the underlying data structure in this priority queue
     * and returns that key element
     *
     * @return the key with maximum priority value
     */
    public Key delete() {
        int maxIndex = 0;
        for (int i = 1; i < count; ++i) {
            if (lessThan(maxIndex, i)) maxIndex = i;
        }
        exchange(maxIndex, count - 1);
        return queue[--count];
    }

    private boolean lessThan(int from, int to) {
        return queue[from].compareTo(queue[to]) < 0;
    }

    private void exchange(int from, int to) {
        Key temp = queue[from];
        queue[from] = queue[to];
        queue[to] = temp;
    }


    /**
     * Checking if the queue is empty or not
     *
     * @return true if the queue is empty false otherwise
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the key from the priority queue with maximum priority
     *
     * @return the key with maximum priority
     */
    public Key max() {
        int maxIndex = 0;
        for (int i = 1; i < count; ++i) {
            if (lessThan(maxIndex, i)) maxIndex = i;
        }
        return queue[maxIndex];
    }

    /**
     * No. of elements maintained by the queue at a given instance
     *
     * @return no. of elements in the queue
     */
    public int size() {
        return count;
    }

    public static void main(String[] args) {
    }
}