public class MergeSort {

    private static void merge(Comparable[] elements, Comparable[] mem, int lo, int mid, int high) {
        for (int i = 0; i <= high; ++i) {
            mem[i] = elements[i];
        }
        int lowerIndex = lo;
        int highIndex = mid + 1;
        for (int k = lo; k <= high; ++k) {
            if (lowerIndex > mid) elements[k] = mem[highIndex++];
            else if (highIndex > high) elements[k] = mem[lowerIndex++];
            else if (Utils.isLess(mem[highIndex], mem[lowerIndex])) elements[k] = mem[highIndex++];
            else elements[k] = mem[lowerIndex++];
        }
    }

    private static void sort(Comparable[] elements, Comparable[] mem, int low, int high) {
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        sort(elements, mem, low, mid);
        sort(elements, mem, mid + 1, high);
        merge(elements, mem, low, mid, high);
    }

    public static void sort(Comparable[] elements) {
        Comparable[] mem = new Comparable[elements.length];
        sort(elements, mem, 0, elements.length - 1);
    }

    public static void main(String[] a) {
        String[] input = {"Comparable", "Comparator", "Alex", "Cameroon", "Steve", "Pat", "Dough", "Marnus"};
        MergeSort.sort(input);
        for (String name : input) {
            System.out.println(name);
        }
    }
}
