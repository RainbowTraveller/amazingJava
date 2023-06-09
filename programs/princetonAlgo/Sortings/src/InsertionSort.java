/*
Algorithm. ↑ scans from left to right.
Invariants.
・Entries to the left of ↑ (including ↑) are in ascending order.
・Entries to the right of ↑ have not yet been seen.


112245739485723
      ↑ not seen yet


To sort a randomly-ordered array with distinct keys, insertion sort uses ~ ¼ N 2 compares and ~ ¼ N 2 exchanges on average.
 */
public class InsertionSort {
    /*
    In iteration i, swap a[i] with each larger entry to its left.
     */
    public static void sort(Comparable[] elements) {
        int len = elements.length;
        for (int i = 0; i < len; ++i) {
            for (int j = i; j > 0; --j) {
                if (Utils.isLess(elements[j], elements[j - 1])) {
                    Utils.swap(elements, j, j - 1);
                } else {
                    break; // if left of i is already sorted, no need to look any further
                }
            }
        }
    }
    public static void main(String[] args) {
        String[] input = {"Comparable", "Comparator", "Alex", "Cameroon", "Steve", "Pat", "Dough", "Marnus"};
        InsertionSort.sort(input);
        for (String name : input) {
            System.out.println(name);
        }
    }
}
