package com.interview;

import com.interview.Node;
/**
 *
 *
 */
class LinkedList {
	Node head;
	Node tail;

	LinkedList() {
		head = null;
		tail = null;
	}

	LinkedList(Node head) {
		this.head = head;
		Node tracker = head;
		while(tracker.next != null) {
			tracker = tracker.next;
		}
		tail = tracker;
	}

	public void add(int val) {
		if(tail == null) {
			head  = new Node(val);
			tail = head;
		} else {
			tail.next = new Node(val);
			tail = tail.next;
		}
	}


	public boolean equals(LinkedList newList) {
		boolean result = false;
		if(newList != null && this.head != null) {
			result = newList.length() != this.length() ? false : true;
			while(head != null && this.head != null ) {
				 result &= head.value == this.head.value;
			}
		}
		return result;
	}

	public int length() {
		int length = 0;
		Node tracker = head;
		while(tracker != null){
			length++;
			tracker = tracker.next;
		}
		return length;
	}

	public String toString() {
		Node tracker = head;
		String listStr = "";
		while(tracker != null) {
			listStr += tracker.value + ":";
			tracker = tracker.next;
		}
		return listStr;
	}

	public Node reverse(Node head) {
// Time complexity: : O(n)
// Space complexity: O(1)
		Node prev = null;
		Node next = null;
		Node curr = head;
		while(curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr  = next;
		}
		return prev;
	}
}

