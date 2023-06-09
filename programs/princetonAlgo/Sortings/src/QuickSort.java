/*
Basic plan.
・Shuffle the array.
・Partition so that, for some j
– entry a[j] is in place
– no larger entry to the left of j
– no smaller entry to the right of j
・Sort each piece recursively.

Repeat until i and j pointers cross.
・Scan i from left to right so long as (a[i] < a[lo]).
・Scan j from right to left so long as (a[j] > a[lo]).
・Exchange a[i] with a[j].

When pointers cross.
・Exchange a[lo] with a[j].

 */
public class QuickSort {
    public static void sort(Comparable[] elements) {
        sort(elements, 0, elements.length - 1);
    }

    private static void sort(Comparable[] elements, int low, int high) {
        if (low >= high) return;
        //Partition around the pivot
        int pivotIndex = partition(elements, low, high);
        //Sort all the elements smaller than the pivot element
        sort(elements, low, pivotIndex);
        //Sort all the elements greater than the pivot element
        sort(elements, pivotIndex + 1, high);
    }

    private static int partition(Comparable[] elements, int low, int high) {
        // Select the first element as pivot
        Comparable pivot = elements[low];
        // left pointer
        int i = low + 1;
        // right pointer
        int j = high;
        while (true) {
            //Increment i till we find element greater than pivot
            while (Utils.isLess(elements[i++], pivot)) {
                if (i == high) break;
            }

            // decrement j till we find element smaller that pivot
            while (Utils.isLess(pivot, elements[j--])) {
                if (j == low) break;
            }

            if (i >= j) break;
            // Exchange the above 2 elements to maintain invariant of having smaller element to the left of the pivot
            // and greater elements to the right of the pivot
            Utils.swap(elements, i, j);
        }
        // finally put the pivot element in the right place
        Utils.swap(elements, low, j);
        // return the index of the pivot in the array
        return j;
    }

    public static void main(String[] a) {
        String[] input = {"Comparable", "Comparator", "Alex", "Cameroon", "Steve", "Pat", "Dough", "Marnus"};
        QuickSort.sort(input);
        for (String name : input) {
            System.out.println(name);
        }
    }
}
