/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
 * Given K lists which are already sorted. Head pointers of these lists are stored in
 * an array. Merge them to form a single list of sorted elements
 */

class MergeKSortedLists {
	public static void main(String [] args) {

	}

	/*
	 * This is divide and conquer approach. Reduce the lists to be merged by
	 * half in each iteration and 2 lists can be merged in O(n) time
	 * So total complexity is O(N log k) : N = total no. of elements and k = no. of lists
	 */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists != null && lists.length > 0) {
			//Consider lists at extreme ends and converge to the middle
            int start = 0, end = lists.length - 1;
            while( end != 0) {
                while( start < end) {
					//0 and n - 1 lists merged and stored at => 0
					//1 and n - 2 lists merged and stored at => 1
                    lists[start] = mergeTwoLists(lists[start], lists[end]);
                    start++;
                    end--;
                }

				//Consider the last case when only 0th and 1st lists are remaining
				//they will combine into list at index 0 and then end = 0
				//till that time end > 0 always
                if(end != 0) {
					//above loop breaks when start > end e.g. start = 5 and end = 4
					//here start was 4 and became 5 so last valid merged lists is stored at
					//index start - 1 = end. So just make start = 0 again and in new iteration
					//consider lists from 0 to new end which is half the original no. of lists
                    start = 0;
                }
            }
            return lists[0];
        }
        return null;
    }


	/*
	 * Function to merge 2 sorted lists( this can be an independent question)
	 */
    public ListNode mergeTwoLists(ListNode one, ListNode two) {
		//VERY IMPORTANT : don't shy to create a dummy node
        ListNode head = new ListNode(-1);
        ListNode headTracker = head;

		//Process till any one of the list is exhausted
        while(one != null && two != null) {
			//Choose which node to attach next
            if(one.val <= two.val) {
                headTracker.next = one;
                one = one.next;
            } else {
                headTracker.next = two;
                two = two.next;
            }
			//Point to this node just attached
            headTracker = headTracker.next;
			//Set its next to null, detaching it from the original list
            headTracker.next = null;
        }

		//Check which list is empty, select the one which is not
        ListNode node = one == null ? two : one;

        if(node != null) {
			//Attach it to the end of already generated list
			//This will be ok as original list is already sorted
            headTracker.next = node;
        }
        return head.next;
    }

    public void printList(ListNode node) {
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
