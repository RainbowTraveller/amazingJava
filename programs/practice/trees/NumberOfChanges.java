/**
 * Existing tree a(1) / \ b(2) c(3) / \ \ d(4) e(5) f(6)
 *
 * <p>New tree a(1) \ c(3) \ f(66) expected: 4
 *
 * <p>Existing tree a(1) / \ b(2) c(3) / \ \ d(4) e(5) g(7)
 *
 * <p>New tree a(1) / \ b(2) h(8) / | \ \ e(5) d(4) f(6) g(7)
 *
 * <p>expected: 5
 */
class Node {
  public String key;
  public int value;
  public List<Node> children;

  public Node(String key, int value) {
    this.key = key;
    this.value = value;
    this.children = new ArrayList<>();
  }
}

public class NumberOfChanges {
  /**
   * This function take two menu trees and return the minimum number of nodes that need to be
   * changed to transform the existing menu tree into the new menu tree. The changes include
   * modifying the value of a node or adding/removing nodes.
   *
   * @param existingMenu: the root of the existing menu tree
   * @param newMenu: the root of the new menu tree
   * @return: the minimum number of nodes that need to be changed
   */
  public int countDifferentNodes(Node existingMenu, Node newMenu) {
    int diffCount = 0;
    boolean isParentChanged = false;
    if (existingMenu.value != newMenu.value) {
      diffCount++;
    }

    if (existingMenu.key != newMenu.key) {
      diffCount++;
      isParentChanged = true;
    }

    helper(existingMenu.children, newMenu.children, diffCount, isParentChanged);
  }

  /**
   * Helper function to recursively compare two lists of nodes and count the differences. The
   * function updates the diffCount variable to keep track of the number of differences found.
   *
   * @param exists: list of nodes from the existing menu tree
   * @param latest: list of nodes from the new menu tree
   * @param diff: current count of differences
   * @param isParentChanged: boolean indicating if the parent node has changed
   */
  public void helper(List<Node> exists, List<Node> latest, int diff, boolean isParentChanged) {
    for (Node exitNode : exists) {

      boolean nodeFound = false;
      for (Node newNode : latest) {
        if (exitNode.key == newNode.key) {

          if (exitNode.value != newNode.value) {
            diff++;
          }
          helper(exitNode.children, newNode.children, diffCount, exitNode.value == newNode.value);
          nodeFound = true;
        }
      }
      if (!nodeFound) {
        diff += 2;
      }
    }
  }

  public static void main(String[] args) {
    Main solution = new Main();
  }
}
