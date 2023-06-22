/**
 * Class implements the binary heap. Here an array is used where is the invariant is as follows
 * <p>
 * The invariants are as follows :
 * . Largest key is a[1], which is root of binary tree.
 * . Parent of node at k is at k/2.
 * . Children of node at k are at 2k and 2k+1
 *
 * @param <Key>
 */
public class BinaryHeapArray<Key extends Comparable<Key>> implements PriorityQueue<Key> {
    private Key[] heap;
    private int count;

    public BinaryHeapArray(int capacity) {
        heap = (Key[]) new Comparable[capacity + 1];
    }

    /**
     * Insert the given key to the priority queue
     * Add the node at the end and then bubble is up the heap:
     *
     * @param key new key to be inserted to the priority queue
     */
    @Override
    public void insert(Key key) {
        //Add new value at the end
        heap[++count] = key;
        //Fix heap invariant from bottom up
        heapBubbleUp(count);
    }

    /**
     * Deletes the key with the maximum priority from the underlying data structure in this priority queue
     * and returns that key element
     *
     * @return the key with maximum priority value
     */
    @Override
    public Key delete() {
        // Max value is always at the top
        Key key = heap[1];
        //Switch last node with first
        exchange(1, count);
        //which may break heap invariant, so fix it
        heapBubbleDown(1);
        heap[count--] = null; // Heap size is decreased
        return key;
    }

    /**
     * Checking if the queue is empty or not
     *
     * @return true if the queue is empty false otherwise
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the key from the priority queue with maximum priority
     *
     * @return the key with maximum priority
     */
    @Override
    public Key max() {
        return heap[1];
    }

    /**
     * No. of elements maintained by the queue at a given instance
     *
     * @return no. of elements in the queue
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Maintains the heap property of the collection starting from a given index
     * <p>
     * Scenario. Child's key becomes larger key than its parent's key.
     * To eliminate the violation:
     * ・Exchange key in child with key in parent.
     * ・Repeat until heap order restored.
     *
     * @param fromIndex the starting point to start building heap
     */
    private void heapBubbleUp(int fromIndex) {
        while (fromIndex > 1 && lessThan(fromIndex, fromIndex / 2)) {
            exchange(fromIndex, fromIndex / 2);
            fromIndex = fromIndex / 2;
        }
    }

    /**
     * Scenario. Parent's key becomes smaller than one (or both) of its children's.
     * To eliminate the violation:
     * ・Exchange key in parent with key in larger child.
     * ・Repeat until heap order restored.
     *
     * @param fromIndex
     */
    private void heapBubbleDown(int fromIndex) {
        while (2 * fromIndex <= count) { // Repeat till end is reached
            int childIndex = 2 * fromIndex; // Check left child
            // Determine which child is greater
            if (childIndex <= count && lessThan(childIndex, childIndex + 1)) childIndex++;
            //Compare with the parent
            if (lessThan(childIndex, fromIndex)) break; // If binary tree rule is maintained , nothing to worry
            exchange(childIndex, fromIndex);// if not exchange and establish invariant locally
            fromIndex = childIndex; // proceed checking down the tree as new value was pushed down
        }
    }

    private void exchange(int from, int to) {
        Key temp = heap[from];
        heap[from] = heap[to];
        heap[to] = temp;
    }

    private boolean lessThan(int from, int to) {
        return heap[from].compareTo(heap[to]) < 0;
    }

}
