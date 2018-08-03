class Node{

	public Node next;
	public int value;

	Node(int data) {
		value = data;
		next = null;
	}


	public String toString() {
		return new String("Value: " + value);
	}
}

public class LinkedList {

		Node head;
		Node tail;
		int length;

		public LinkedList() {
			head = null;
			tail = null;
			length = 0;
		}

		public LinkedList( int headValue  ) {
			head = new Node( headValue );
			tail = null;
			head.next = tail;
			length = 1;
		}

		public void addNode( int num ) {
			if( head == null ) {
				head = new Node( num );
				tail = null;
				head.next = tail;
			} else {
				tail.next = new Node( num );
			}
				length++;
		}

		public static boolean isCyclic( Node head ) {

			if( head != null ) {
				Node slow = head;
				Node fast = head;

				while( fast != null ) {
					slow = slow.next;
					fast = fast.next;
					if(fast != null ) {
						fast = fast.next;
						if(slow == fast) {
							System.out.println( "Node Value : " + slow.value);
							int count = 1;
							Node tracker = slow.next;
							while ( tracker != slow ) {
								tracker = tracker.next;
								count++;
							}
							System.out.println("Loop Length : " + count + " nodes");

							Node start = head;
							while( start != slow ) {
								start = start.next;
								slow = slow.next;
							}

							System.out.println("Loop start : " + slow.value);

							return true;
						}
					}
				}
			}
			return false;
		}

		public static void main ( String[] args ) {
			Node one 	= new Node ( 1 );
			Node two 	= new Node ( 2 );
			Node three 	= new Node ( 3 );
			Node four 	= new Node ( 4 );
			Node five 	= new Node ( 5 );
			Node six 	= new Node ( 6 );
			Node seven 	= new Node ( 7 );
			Node eight 	= new Node ( 8 );
			Node nine 	= new Node ( 9 );

				one.next 	= two;
				two.next 	= three;
				three.next 	= four;
				four.next 	= five;
				five.next 	= six;
				six.next 	= seven;
				seven.next 	= three;
			//	eight.next 	= nine;
			//	nine.next 	= null;

			System.out.println( LinkedList.isCyclic( one ) );

		}

		/*

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
*/
}

