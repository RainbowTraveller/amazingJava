/* Given a nested list of elements in the nested form
 * e.g. {1, 2, {1, 2, 3}, {}, {4, 5}, {}, 9}
 *
 * Write an iterator which will flatten this list meaning next() will return only integer
 * So using such an iterator the output will be : 1, 2, 1, 2, 3, 4, 5, 9
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class IteratorAdaptor {
    static class FlatIterator<T> {

		//Original List
        private List<List<T>> containerList;
		//Current valid list available
        private List<T> currentList;
		//tracking for current valid list
        int index;
		//tracking for list of lists
        int listIndex;

        public FlatIterator(List<List<T>> input) {
            if(input != null) {
                containerList = input;
                currentList = null;
                index = 0;
                listIndex = 0;
            }
        }

        boolean hasNext() {
            //Current list is already present : return true
            //Current list is null, check for next available List from last index in container list
            if( currentList != null && index < currentList.size()) {
				//There is already one list which we have extracted from original list
				//and also has valid index
                return true;
            } else if(containerList != null) {
				//point to first element in the list
				currentList = null;
				index = 0;
				//Get a list which has elements
				//or return false if no more list is present in the
				//container list
                while( listIndex < containerList.size() ) {
                    currentList =  containerList.get(listIndex);
					//point to next list in the container
					listIndex++;
                    if(currentList != null &&  currentList.size() > 0 ) {
                        return true;
                    }
                }
            }
            return false;
        }

        T next() {
			T current = null;
			if(hasNext()) {
				current = currentList.get(index);
				index++;
			}
			return current;
        }
    }

    public static void main(String args[] ) throws Exception {

        // [[], [1, 2], [], [], [], [], [3, 4, 5] ...] ->
        /*
          1
          2
          3
          ..
        */

        List<Integer> ints1 = new ArrayList<>();
        ints1.add(1);
        ints1.add(2);

        List<Integer> ints2 = new ArrayList<>();
        ints2.add(3);
        ints2.add(4);
        ints2.add(5);

        List<Integer> ints3 = new ArrayList<>();
        ints3.add(6);

        List<List<Integer>> list = new ArrayList<>();
        list.add(ints1);
        list.add(ints2);
        list.add(new ArrayList<Integer>());
        list.add(new ArrayList<Integer>());
        list.add(new ArrayList<Integer>());
        list.add(ints3);


        FlatIterator<Integer> iterator = new FlatIterator<Integer>(list);

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
