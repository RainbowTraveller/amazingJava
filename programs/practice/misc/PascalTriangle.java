import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class PascalTriangle {
    public static void main(String[] args) {
        System.out.println("4 : " + generate(4));
        System.out.println("5 : " + generate(5));
        System.out.println("8 : " + generate(8));
        System.out.println("8 : " + getRow(8));
        System.out.println("8 : " + getRowSingleList(8));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new LinkedList<>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> curr = new LinkedList<>();
            if (i == 0) {
                curr.add(1);

            } else {
                List<Integer> prev = triangle.get(i - 1);
                // System.out.println(prev);
                curr.add(prev.get(0));
                // System.out.println("0 : " + prev.get(0));
                for (int j = 1; j <= i - 1; j++) {
                    curr.add(prev.get(j - 1) + prev.get(j));
                    // System.out.println("Curr : " + prev.get(j - 1) + prev.get(j));
                }
                curr.add(prev.get(prev.size() - 1));
                // System.out.println("Last : " + prev.get(prev.size() - 1));
            }
            triangle.add(curr);
        }
        return triangle;
    }

    /**
     * This prints a row in the PascalTriangle at a given inedex
     */
    public static List<Integer> getRow(int rowIndex) {
        return helper(rowIndex + 1);
    }

    /*
     * O(k^2) : recursion goes k level deep and at each level k times calculations
     * Space : 1 + 2 + 3 ..... so again O(k^2)
     */
    public static List<Integer> helper(int index) {
        if (index == 0) {
            // At index 0 add the top element
            List<Integer> pt = new LinkedList<Integer>();
            pt.add(1);
            return pt;
        } else {
            // get previous level list
            List<Integer> pt = helper(index - 1);
            List<Integer> curr = new LinkedList<Integer>();
            // iterate and add only valid elements
            for (int i = 0; i <= pt.size(); ++i) {
                // if index out of bound which is at both the ends, adjust to 0
                int left = i - 1 < 0 ? 0 : pt.get(i - 1);
                int right = i >= pt.size() ? 0 : pt.get(i);
                curr.add(left + right);
            }
            return curr;
        }
    }

    public static List<Integer> getRowSingleList(int rowIndex) {
        //Initialize to 1, the top element
        List<Integer> row =
            new ArrayList<Integer>(rowIndex + 1) {
              {
                add(1);
              }
        };

        for (int i = 0; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            row.add(1);
        }
        return row;
    }
}
