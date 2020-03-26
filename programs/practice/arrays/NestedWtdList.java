/**
 * // This is the interface that allows for creating nested lists. // You should
 * not implement it, or speculate about its implementation public interface
 * NestedInteger {
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list. public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list // Return null if this NestedInteger holds a single integer
 * public List<NestedInteger> getList(); }
 */
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {

        Map<Integer, List<Integer>> levelNumbers = new HashMap<Integer, List<Integer>>();
        getLevelsAndNumbers(1, levelNumbers, nestedList);
        int sum = 0;
        for (Integer key : levelNumbers.keySet()) {
            List<Integer> nosAtKeyLevel = levelNumbers.get(key);
            for (Integer i : nosAtKeyLevel) {
                sum += (key * i);
            }
        }
        return sum;
    }

    public void getLevelsAndNumbers(int level, Map<Integer, List<Integer>> levelNumbers, List<NestedInteger> input) {
        System.out.println("Level : " + level);
        for (NestedInteger item : input) {
            if (item.isInteger()) {
                if (levelNumbers.containsKey(level)) {
                    List<Integer> existing = levelNumbers.get(level);
                    existing.add(item.getInteger());

                } else {
                    List<Integer> levelwise = new ArrayList<Integer>();
                    levelwise.add(item.getInteger());
                    levelNumbers.put(level, levelwise);
                }
            } else {

                getLevelsAndNumbers(level + 1, levelNumbers, item.getList());
            }
        }
    }
}
