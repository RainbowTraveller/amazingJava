import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;

public class LongestIncreasingSequence {

    public static void longestIncreasingSequnce(int [] arr) {
        if(arr != null) {
            int len = arr.length;
            LinkedList<Integer> list = new LinkedList<Integer>();//list ending at each element
            //Map of lists keys on index
            LinkedHashMap<Integer, LinkedList<Integer>> listmap = new LinkedHashMap<Integer, LinkedList<Integer>>();
            sequenceHelper(arr, 0, list, listmap);
            LinkedList<Integer> best_list = new LinkedList<Integer>();
            for(LinkedList<Integer> cls : listmap.values()) {
                if(best_list.size() < cls.size()) {
                    //Check which one is longest and return that
                    best_list = cls;
                }
            }
            System.out.println(best_list);
        }
    }

    public static void sequenceHelper(int [] arr, int index, LinkedList<Integer> list, LinkedHashMap<Integer, LinkedList<Integer>> lists) {
        if(index >= arr.length) {
            return;
        } else {
            if(list.size() > 0 && list.getLast() > arr[index]) {
                //Previous list existed and now violation of increasing order,
                //so create new list
                list = new LinkedList<Integer>();
            }
            list.add(arr[index]);//so far so good, add the element to list
            lists.put(index, list);// and replace list from map at index
        }
        sequenceHelper(arr, index + 1, list, lists);
    }

    public static void main(String [] args) {
        int [] input = {10, 11, 0 , 0, 1 ,2, 3, -1, 2 ,3, 14, 5, 6, 7};
        longestIncreasingSequnce(input);
    }
}
