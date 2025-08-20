/**
 * Write a method to find common elements between two Lists
 *
 * <p>Method takes 2 ordered lists, returns an ordered list with elements common between the 2 lists
 *
 * <p>Example: list1 = [1,2,3,4,5,5], list2 = [2,5,5,6,7] Output: [2,5]
 *
 * <p>Example: [0,1], [3,4] Output: []
 */
public class CommonElements {
  public static void main(String[] args) {
    int[] list1 = {1, 2, 3, 4, 5, 5};
    int[] list2 = {2, 5, 5, 6, 7};

    System.out.println("Common Elements : ");
    for (int i : CommonElements.findCommonElements(list1, list2)) {
      System.out.println(i);
    }

    System.out.println();
    int[] list3 = {0, 1};
    int[] list4 = {3, 4};
    System.out.println("Common Elements : ");
    for (int j : CommonElements.findCommonElements(list3, list4)) {
      System.out.println(j);
    }
  }

  /**
   * Method to find common elements between two ordered lists
   *
   * @param list1 - first ordered list
   * @param list2 - second ordered list
   * @return - ordered list with common elements between the 2 lists
   */
  public static int[] findCommonElements(int[] list1, int[] list2) {

    int len1 = list1.length;
    int len2 = list2.length;
    int i = 0;
    int j = 0;
    int size = 0;
    int lastElement = Integer.MIN_VALUE;

    int[] temp = new int[len1 < len2 ? len1 : len2];

    while (i < len1 && j < len2) {
      if (list1[i] == list2[j]) {
        temp[size] = list1[i];
        while (i < len1 && list1[i] == temp[size]) i++;
        while (j < len2 && list2[j] == temp[size]) j++;
        size++;
      } else if (list1[i] > list2[j]) {
        j++;
      } else {
        i++;
      }
    }

    int[] result = new int[size];
    for (int k = 0; k < size; k++) {
      result[k] = temp[k];
    }
    return result;
  }
}
