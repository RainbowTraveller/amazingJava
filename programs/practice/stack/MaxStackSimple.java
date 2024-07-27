/*
 *   Design a max stack that supports push, pop, top, peekMax and popMax.
 *
 *   push(x) -- Push element x onto stack.
 *   pop() -- Remove the element on top of the stack and return it.
 *   top() -- Get the element on the top.
 *   peekMax() -- Retrieve the maximum element in the stack.
 *   popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 *   Example 1:
 *   MaxStack stack = new MaxStack();
 *   stack.push(5);
 *   stack.push(1);
 *   stack.push(5);
 *   stack.top(); -> 5
 *   stack.popMax(); -> 5
 *   stack.top(); -> 1
 *   stack.peekMax(); -> 5
 *   stack.pop(); -> 1
 *   stack.top(); -> 5
 *
 */

import java.util.Deque;
import java.util.LinkedList;

public class MaxStackSimple {
  // Stores all the elements
  private Deque<Integer> mainStack;
  // stored max element so far in repeated manner
  private Deque<Integer> maxStack;

  public MaxStackSimple() {
    mainStack = new LinkedList<Integer>();
    maxStack = new LinkedList<Integer>();
  }

  public void push(int x) {
    // e.g. after pushing -2, -45, -82, 29
    // max Stack : -2, -2, -2, 29
    // maxStack and mainStack contain same no, of elements
    // a maxStack elements at i is max element found so far in main stack till index
    // i
    int max = maxStack.isEmpty() ? x : maxStack.getLast();
    maxStack.add(max > x ? max : x);
    mainStack.add(x);
  }

  public int pop() {
    // Remove elements from both stack in tandom
    maxStack.removeLast();
    return mainStack.removeLast();
  }

  public int top() {
    // get don't remove
    return mainStack.getLast();
  }

  public int peekMax() {
    // get don't remove
    return maxStack.getLast();
  }

  public int popMax() {
    Deque<Integer> tempStack = new LinkedList<Integer>();
    int max = maxStack.getLast();
    // Copy all the elements from main stack till max element
    // to another temporary stack
    while (top() != max) {
      // Remember : pop() is removing elements from both stacks
      tempStack.add(pop());
    }
    // remove actual max element from main stack
    pop();
    // Restore all the removed elements
    while (!tempStack.isEmpty()) {
      // Remember : Push will create the maxStack in proper form again with new max
      // element
      push(tempStack.removeLast());
    }
    return max;
  }

  public static void main(String[] args) {
    // Test code here
    MaxStackSimple maxStack = new MaxStackSimple();
    maxStack.push(5);
    maxStack.push(1);
    maxStack.push(5);
    System.out.println(maxStack.top());
    System.out.println(maxStack.popMax());
    System.out.println(maxStack.top());
    System.out.println(maxStack.peekMax());
    System.out.println(maxStack.pop());
    System.out.println(maxStack.top());
  }
}
