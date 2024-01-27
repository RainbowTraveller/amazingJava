import java.util.List;
import java.util.LinkedList;

public class Subsets {
    public static void main(String[] args) {
        Subsets subset = new Subsets();
        int[] input = {1,2,3};
        List<List<Integer>> result = subset.getSubsets(input);
        for(List<Integer> currSet : result ) {
            System.out.println(currSet);
        }
    }

    public List<List<Integer>> getSubsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if(nums != null && nums.length > 0) {
            helper(result, new LinkedList<Integer>(), 0, nums);
            result.add(new LinkedList<Integer>());
        }
        return result;
    }

    public void helper(List<List<Integer>> result, LinkedList<Integer> curr, int index, int[] nums ) {
        for(int i = index ; i < nums.length; ++i) {
            curr.add(nums[i]);
            result.add(new LinkedList<Integer>(curr));
            helper(result, curr, i + 1, nums);
            curr.removeLast();
        }
    }
}
