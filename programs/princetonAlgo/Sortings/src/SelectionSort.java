/*
Algorithm. ↑ scans from left to right.

Invariants.
・Entries the left of ↑(including ↑) fixed and in ascending order.
・No entry to right of ↑ is smaller than any entry to the left of ↑.

1122234476675547
       ↑

Proposition. Selection sort uses (N– 1) + (N– 2) + ... + 1 + 0 ~ N 2 / 2 compares
and N exchanges.

Running time insensitive to input. Quadratic time, even if input is sorted.
Data movement is minimal. Linear number of exchanges.

 */
public class SelectionSort {
    /*
    In iteration i, find index min of smallest remaining entry.
    Swap a[i] and a[min].
     */
    public static void sort(Comparable[] elements) {
        int len = elements.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; ++j) {
                if (Utils.isLess(elements[j], elements[min])) {
                    min = j;
                }
            }
            Utils.swap(elements, i, min);
        }
    }

    public static void main(String[] args) {
        String[] input = {"Comparable", "Comparator", "Alex", "Cameroon", "Steve", "Pat", "Dough", "Marnus"};
        SelectionSort.sort(input);
        for (String name : input) {
            System.out.println(name);
        }
    }

}