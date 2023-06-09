public class Utils {

    public static boolean isLess(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void swap(Comparable[] collection, int indexA, int indexB) {
        Comparable comparable = collection[indexA];
        collection[indexA] = collection[indexB];
        collection[indexB] = comparable;
    }
}
