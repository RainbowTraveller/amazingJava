import java.util.List;
import java.util.LinkedList;

public class PascalTriangle {
    public static void main(String[] args) {
        System.out.println("4 : " + generate(4));
        System.out.println("5 : " + generate(5));
        System.out.println("8 : " + generate(8));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new LinkedList<>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> curr = new LinkedList<>();
            if (i == 0) {
                curr.add(1);

            } else {
                List<Integer> prev = triangle.get(i - 1);
                //System.out.println(prev);
                curr.add(prev.get(0));
                //System.out.println("0 : " + prev.get(0));
                for (int j = 1; j <= i - 1; j++) {
                    curr.add(prev.get(j - 1) + prev.get(j));
                    //System.out.println("Curr : " + prev.get(j - 1) + prev.get(j));
                }
                curr.add(prev.get(prev.size() - 1));
                //System.out.println("Last : " + prev.get(prev.size() - 1));
            }
            triangle.add(curr);
        }
        return triangle;
    }
}
