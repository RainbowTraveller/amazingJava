/*
 *  Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 *  Note: The input string may contain letters other than the parentheses ( and ).
 *
 *  Example 1:
 *
 *  Input: "()())()"
 *  Output: ["()()()", "(())()"]
 *  Example 2:
 *
 *  Input: "(a)())()"
 *  Output: ["(a)()()", "(a())()"]
 *  Example 3:
 *
 *  Input: ")("
 *  Output: [""]
 *
 */
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class ValidParenthesis {
  public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // System.out.println("Please enter the bracket pattern :");
    // String pattern = sc.next();
    ValidParenthesis vp = new ValidParenthesis();

    // Only one valid string
    System.out.println(vp.minRemoveToMakeValid("()(()(()"));
    System.out.println(vp.minRemoveToMakeValid("(x)((y)"));
    System.out.println(vp.minRemoveToMakeValid("(a))((b)))"));

    // All possible valid strings
    System.out.println(vp.removeInvalidParentheses("()())()"));
    System.out.println(vp.removeInvalidParentheses("(a)())()"));
    System.out.println(vp.removeInvalidParentheses(")("));
    System.out.println(vp.removeInvalidParentheses("()()"));

    // Optimal approach
    System.out.println("Optimal approach :");
    System.out.println(vp.removeInvalidParenthesesOptimal("()())()"));
    System.out.println(vp.removeInvalidParenthesesOptimal("(a)())()"));
    System.out.println(vp.removeInvalidParenthesesOptimal(")("));
  }

  /**
   * Removes the minimum number of invalid parentheses to make the input string valid. This
   * implementation uses a stack to track the indexes of unmatched '(' and a set to track the
   * indexes of unmatched ')'. Finally, it constructs a new string excluding the characters at these
   * indexes. This approach returns ONE valid string.
   *
   * <p>The time complexity is O(n)
   *
   * <p>the space complexity is O(n).
   *
   * <p>Algorithm:
   *
   * <p>1. Trace the string from left to right
   *
   * <p>2. If '(' : put it's index on the stack
   *
   * <p>3 if ')' a. if stack is not empty pop matching / nearest '(' b. if stack is empty this is
   * non matching parenthesis so note it's index on the tracker set
   *
   * <p>4.Now if stack is not empty, it contains unmatched '(', so add their indexes too, to the set
   *
   * <p>5.now build a string not containing the characters at indexes in the set
   */
  public String minRemoveToMakeValid(String s) {
    System.out.println("Input String : " + s);
    String result = null;
    if (s != null) {
      // to track the indexes of non matching parenthesis
      Set<Integer> problemIndexes = new HashSet<>();
      // to track the indexes of '('
      Deque<Integer> stack = new LinkedList<>();
      // trace the string
      for (int i = 0; i < s.length(); ++i) {
        // if '(' push it's index on stack
        if (s.charAt(i) == '(') {
          stack.push(i);
        } else if (s.charAt(i) == ')') {
          // if ')' and stack is not empty pop the matching '('
          if (!stack.isEmpty()) {
            stack.pop();
          } else {
            // if stack is empty this is non matching ')' so note it's index
            problemIndexes.add(i);
          }
        }
      }
      while (!stack.isEmpty()) {
        problemIndexes.add(stack.pop());
      }

      StringBuffer sb = new StringBuffer();
      // build the result string
      for (int i = 0; i < s.length(); ++i) {
        // if this index is not in the problem indexes add it to result
        if (!problemIndexes.contains(i)) {
          sb.append(s.charAt(i));
        }
      }
      result = sb.toString();
    }
    return result;
  }

  /**
   * Checks if a string of parentheses is valid. A string is valid if the balance of parentheses
   * never drops below zero and is zero at the end.
   */
  private boolean isValid(String s) {
    int balance = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') {
        balance++;
      } else if (c == ')') {
        balance--;
      }
      if (balance < 0) {
        return false;
      }
    }
    return balance == 0;
  }

  /**
   * Removes the minimum number of invalid parentheses to make the input string valid. This
   * implementation uses a breadth-first search (BFS) approach to explore all possible strings by
   * removing one parenthesis at a time. It stops as soon as it finds valid strings at the current
   * level, ensuring that the minimum number of removals is achieved. This approach returns ALL
   * possible valid strings.
   *
   * <p>The time complexity is O(n * 2^n) in the worst case, where n is the length of the string.
   * This occurs when we have to explore all possible combinations of removals.
   *
   * <p>the space complexity is O(n * 2^n) in the worst case, due to the storage of all possible
   * strings in the queue and visited set.
   *
   * <p>Algorithm:
   *
   * <p>1. Use a queue to perform BFS and a set to track visited strings.
   *
   * <p>2. Start with the original string in the queue.
   *
   * <p>3. For each string, check if it's valid. If valid, add to results and set found flag.
   *
   * <p>4. If a valid string is found, skip further exploration of that level.
   *
   * <p>5. If not valid, generate new strings by removing one parenthesis at a time and add to the
   * queue if not visited.
   *
   * <p>6. Continue until the queue is empty.
   *
   * <p>7. Return the list of valid strings.
   *
   * <p>The algorithm ensures the minimum number of parentheses are removed by using a Breadth-First
   * Search (BFS) approach.
   *
   * <p>How BFS Guarantees a Minimal Solution A BFS algorithm explores all the nodes at the current
   * depth level before moving on to the next depth level. In the context of this problem:
   *
   * <p>Levels represent removals: Each level in the BFS tree corresponds to the number of
   * parentheses that have been removed from the original string. The initial string is at level 0
   * (zero removals). Strings with one parenthesis removed are at level 1, strings with two removed
   * are at level 2, and so on.
   *
   * <p>First valid string found is the answer: The BFS explores level by level, starting from the
   * original string (0 removals). The first time it encounters a valid string, you know it's a
   * string that required the fewest removals possible. Why? Because the BFS algorithm guarantees
   * that all strings with fewer removals (i.e., from shallower levels) have already been checked
   * and were found to be invalid.
   *
   * <p>Stopping the search: Once a valid string is found at a certain level, the algorithm adds it
   * to the results. It then continues to process all other strings at that same level to find any
   * other valid strings that also required the same minimum number of removals. After the entire
   * level is processed, the algorithm stops. It doesn't need to explore deeper levels because any
   * valid string found there would have required more removals, which is not what the problem asks
   * for.
   *
   * <p>This level-by-level exploration is the key to guaranteeing a solution with the minimum
   * number of removals. It's similar to finding the shortest path in an unweighted graph, where the
   * first path you find using BFS is always the shortest one.
   *
   * @param s the input string containing parentheses and possibly other characters
   * @return a list of all possible valid strings after removing the minimum number of invalid
   *     parentheses
   */
  public List<String> removeInvalidParentheses(String s) {
    System.out.println("Generating all possible valid strings for : " + s);
    List<String> results = new ArrayList<>();
    // Edge case: if the input string is null, return an empty list
    if (s == null) {
      return results;
    }

    // BFS setup, queue for strings to explore, set for visited strings
    Queue<String> queue = new LinkedList<>();
    // To avoid processing the same string multiple times
    Set<String> visited = new HashSet<>();

    // Start with the original string
    queue.add(s);
    // Mark the original string as visited
    visited.add(s);
    // Flag to indicate if we've found a valid string at the current level
    boolean found = false;

    while (!queue.isEmpty()) {
      // Get the next string from the queue
      String current = queue.poll();

      if (isValid(current)) {
        // If it's valid, add to results and set found flag
        results.add(current);
        found = true;
      }

      if (found) {
        // If a solution is found, we continue to process the rest of the queue at the current level
        // but we don't explore deeper levels.
        // This ensures we only get the minimum removal solutions.
        // That is if the original string is valid, we won't generate any new strings, because we
        // required to remove 0 parentheses.
        continue;
      }
      // Generate all possible states by removing one parenthesis at a time
      for (int i = 0; i < current.length(); i++) {
        // Only try to remove parentheses
        char c = current.charAt(i);
        if (c == '(' || c == ')') {
          // Create a new string by removing the character at index i
          // These are all substrings with one less parenthesis and corresponds to the next level in
          // BFS
          String next = current.substring(0, i) + current.substring(i + 1);
          if (!visited.contains(next)) {
            // If we haven't visited this string before, add it to the queue and mark as visited
            visited.add(next);
            queue.add(next);
          }
        }
      }
    }
    return results;
  }

  /**
   * The main function that orchestrates the process. It first calculates the number of parentheses
   * to remove and then calls the recursive helper function to find all valid strings. This approach
   * returns ALL possible valid strings.
   *
   * <p>The time complexity is O(2^n) in the worst case, where n is the length of the string.
   *
   * <p>the space complexity is O(n) for the recursion stack and the result set.
   *
   * @param s the input string containing parentheses and possibly other characters
   * @return a list of all possible valid strings after removing the minimum number of invalid
   */
  public List<String> removeInvalidParenthesesOptimal(String s) {
    Set<String> result = new HashSet<>();

    // Step 1: Pre-calculate the number of removals needed.
    int leftRem = 0;
    int rightRem = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') {
        leftRem++;
      } else if (c == ')') {
        if (leftRem > 0) {
          leftRem--;
        } else {
          rightRem++;
        }
      }
    }

    // Step 2: Use DFS with backtracking and pruning to generate valid strings.
    dfs(s, 0, 0, 0, leftRem, rightRem, "", result);

    return new ArrayList<>(result);
  }

  /**
   * A recursive helper function to build valid strings. It prunes the search space by only
   * exploring paths that remove the correct number of parentheses. The improved method is
   * essentially a pruned DFS (Depth-First Search), which is more efficient for this problem than a
   * blind BFS.
   *
   * <p>Calculate Removals: First, iterate through the string to count the number of unmatched left
   * and right parentheses.
   *
   * <p>Initialize leftRemovals = 0 and rightRemovals = 0.
   *
   * <p>Iterate through the string:
   *
   * <p>If char == '(', increment leftRemovals.
   *
   * <p>If char == ')':
   *
   * <p>If leftRemovals > 0, decrement leftRemovals (this ) has a matching ().
   *
   * <p>If leftRemovals == 0, increment rightRemovals (this ) is a rogue closing parenthesis). This
   * single pass gives you the exact number of invalid ( and ) that must be removed for a valid
   * string.
   *
   * <p>Recursive Search (DFS): Use a recursive function to generate all possible strings by
   * removing exactly leftRemovals and rightRemovals parentheses.
   *
   * <p>The function takes parameters: s (original string), index, leftCount (open parens in current
   * string), rightCount, leftRemovals, rightRemovals, and the result set.
   *
   * <p>Base Case: If index == s.length(), check if leftRemovals == 0 and rightRemovals == 0. If so,
   * add the built string to the result set.
   *
   * <p>Recursive Steps:
   *
   * <p>Case 1: Remove the current character. If the character is ( and leftRemovals > 0, or if the
   * character is ) and rightRemovals > 0, make a recursive call by skipping this character and
   * decrementing the appropriate removal count.
   *
   * <p>Case 2: Keep the current character. Append the current character and make a recursive call
   * to the next index. This branch must also include pruning:
   *
   * <p>If char == '(', increment leftCount.
   *
   * <p>If char == ')', increment rightCount only if leftCount > rightCount to ensure the expression
   * remains valid. This prevents exploring paths with invalid intermediate states (e.g., ) before
   * ().
   */
  private void dfs(
      String s,
      int index,
      int openCount,
      int closeCount,
      int leftRem,
      int rightRem,
      String currentString,
      Set<String> result) {

    // Base case: If we've processed the entire string.
    if (index == s.length()) {
      // A valid solution is found if all required removals have been made and the balance is zero.
      if (leftRem == 0 && rightRem == 0) {
        result.add(currentString);
      }
      return;
    }

    char c = s.charAt(index);

    // Case 1: Try to remove the current character.
    // This path is only explored if we still need to remove this type of parenthesis.
    // Here we skip the current character.
    if (c == '(' && leftRem > 0) {
      dfs(s, index + 1, openCount, closeCount, leftRem - 1, rightRem, currentString, result);
    }
    if (c == ')' && rightRem > 0) {
      dfs(s, index + 1, openCount, closeCount, leftRem, rightRem - 1, currentString, result);
    }

    // Case 2: Try to keep the current character.
    // We always keep non-parentheses characters.
    if (c != '(' && c != ')') {
      dfs(s, index + 1, openCount, closeCount, leftRem, rightRem, currentString + c, result);
    }

    // We only keep a parenthesis if it maintains a valid balance.
    // Here we include the current character.
    if (c == '(') {
      dfs(s, index + 1, openCount + 1, closeCount, leftRem, rightRem, currentString + c, result);
    } else if (c == ')') {
      // A closing parenthesis can be kept only if there's a matching open one.
      if (openCount > closeCount) {
        dfs(s, index + 1, openCount, closeCount + 1, leftRem, rightRem, currentString + c, result);
      }
    }
  }
}
