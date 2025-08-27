/**
 * This is the interface that allows for creating nested lists. You should not implement it, or
 * speculate about its implementation public interface NestedInteger
 *
 * <p>
 *
 * @return true if this NestedInteger holds a single integer, rather than a nested list. public
 *     boolean isInteger();
 *     <p>
 * @return the single integer that this NestedInteger holds, if it holds a single integer Return
 *     null if this NestedInteger holds a nested list public Integer getInteger();
 *     <p>
 * @return the nested list that this NestedInteger holds, if it holds a nested list Return null if
 *     this NestedInteger holds a single integer public List<NestedInteger> getList(); }
 */

/**
 * Given inferface of list contains mix of integers and list the goal is to collect a map of level
 * and all intergers at that level. Then return the sum of intergers multuplied by the level
 */
public class NestListSum {
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

  /**
   * Recursive method to populate the map of level and numbers at that level
   *
   * @param level current level in the nested list
   * @param levelNumbers map of level and list of integers at that level
   * @param input current list of NestedInteger to process
   */
  public void getLevelsAndNumbers(
      int level, Map<Integer, List<Integer>> levelNumbers, List<NestedInteger> input) {
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
