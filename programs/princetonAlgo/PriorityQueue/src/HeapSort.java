import java.util.concurrent.CountedCompleter;

public class HeapSort {
    public static void sort(Comparable[] elements) {

        int count = elements.length;
        for (int k = count / 2; k >= 1; k--)
            heapBubbleDown(elements, k, count);
        while (count > 1) {
            swap(elements, 1, count);
            heapBubbleDown(elements, 1, --count);
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
    private static void heapBubbleDown(Comparable[] elements, int fromIndex, int count) {
        while (2 * fromIndex <= count) { // Repeat till end is reached
            int childIndex = 2 * fromIndex; // Check left child
            // Determine which child is greater
            if (childIndex <= count && lessThan(elements, childIndex, childIndex + 1)) childIndex++;
            //Compare with the parent
            if (lessThan(elements, childIndex, fromIndex))
                break; // If binary tree rule is maintained , nothing to worry
            swap(elements, childIndex, fromIndex);// if not exchange and establish invariant locally
            fromIndex = childIndex; // proceed checking down the tree as new value was pushed down
        }
    }

    private static boolean lessThan(Comparable[] elements, int i, int j) {
        return elements[i].compareTo(elements[j]) < 0;
    }

    public static void swap(Comparable[] collection, int indexA, int indexB) {
        Comparable comparable = collection[indexA];
        collection[indexA] = collection[indexB];
        collection[indexB] = comparable;
    }
}
