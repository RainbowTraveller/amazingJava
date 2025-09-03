/*  Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 *  For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 *  Input: head = [-10,-3,0,5,9]
 *  Output: [0,-3,9,-10,null,5]
 *  Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 *  Example 2:
 *
 *  Input: head = []
 *  Output: []
 *  Example 3:
 *
 *  Input: head = [0]
 *  Output: [0]
 *  Example 4:
 *
 *  Input: head = [1,3]
 *  Output: [3,1]
 */

public class SortedListBalancedBST {
  public static void main(String[] args) {

    ListNode head = new ListNode(-10);
    head.next = new ListNode(-3);
    head.next.next = new ListNode(0);
    head.next.next.next = new ListNode(5);
    head.next.next.next.next = new ListNode(9);
    SortedListBalancedBST solution = new SortedListBalancedBST();
  }

  /** Definition for singly-linked list. */
  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode middle = findMiddle(head);
    TreeNode node = new TreeNode(middle.val);

    if (head == middle) {
      return node;
    }
    node.left = sortedListToBST(head);
    node.right = sortedListToBST(middle.next);

    return node;
  }

  /** Method uses fast and slow pointers to find out the middle of the list. */
  public ListNode findMiddle(ListNode head) {
    ListNode prev = null;
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    // Separate the list into 2 halves
    if (prev != null) {
      prev.next = null;
    }

    return slow;
  }
}
