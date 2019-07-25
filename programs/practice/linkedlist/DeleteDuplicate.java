public class DeleteDuplicate {

    public static void deleteDuplicate(Node HEAD) { // Assuming the list is not sorted
        if(HEAD != null) {
            Node ptr = HEAD;
            HashSet<Integer> track = new HashSet<Integer>();
            track.put(HEAD.data);
            while(ptr.next != null) {
                if(track.contains(ptr.next.data)) {
                    ptr.next = ptr.next.next;
                } else {
                    track.put(ptr.next.data);
                    ptr = ptr.next;
                }
            }
        }
    }

    public static void deleteDuplicate(Node HEAD) { // Assuming the list is sorted, does not use extra buffer etc
        if(HEAD != null) {
            Node ptr = HEAD;
            while(ptr.next != null) {
                if(ptr.data == ptr.next.data) {
                    ptr.next = ptr.next.next;
                }
                ptr = ptr.next;
            }
        }
    }

    public static Node sortedInsert(Node HEAD, Node ptr) {
        if(ptr != null) {
            if(HEAD == null || HEAD.data >=  ptr.data) {
                ptr.next = HEAD;
                HEAD = ptr;
            } else {
                Node n = HEAD;
                while(n.next != null && ptr.data > n.next.data) {
                        n = n.next;
                    }
                }
                ptr.next = n.next;
                n.next = ptr;
            }
        }
        return HEAD;
    }

    public static void insertSort(Node HEAD) {
        if(HEAD != null) {
            Node current = HEAD;
            Node next = null;
            Node result = null;

            while(current != null) {
                next = current.next;
                result = sortedInsert(result, current);
                current = next;
            }
        }
        HEAD = result;
    }

}
