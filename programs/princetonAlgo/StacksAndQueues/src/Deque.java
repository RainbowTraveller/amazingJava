import java.util.Iterator;
import java.util.NoSuchElementException;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;
    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return first == null;
    }

    // return the number of items on the deque
    public int size(){ return size;}

    // add the item to the front
    public void addFirst(Item item){
        if(item == null) {
            throw new IllegalArgumentException("Null argument can not be inserted");
        }
        if(isEmpty()) {
            first = new Node(item);
            last = first;
        } else {
            Node curr = new Node(item);
            curr.next = first;
            first.prev = curr;
            first = curr;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null) {
            throw new IllegalArgumentException("Null argument can not be inserted");
        }
        if(isEmpty()) {
            first = new Node(item);
            last = first;
        } else {
            Node curr = new Node(item);
            last.next = curr;
            curr.prev = last;
            last = curr;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Item itemToReturn = null;
        itemToReturn = first.item;
        Node oldFirst = first;
        first = first.next;
        if(first != null) first.prev = null;
        oldFirst = null;
        size--;
        //System.out.println("Removing first element : " + itemToReturn);
        if(size == 0) {
            first = last = null;
        }
        return itemToReturn;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Item itemToReturn = null;
        itemToReturn = last.item;
        Node oldLast = last;
        last = last.prev;
        if( last != null) last.next = null;
        oldLast = null;
        size--;
        //System.out.println("Removing Last element : " + itemToReturn);
        if(size == 0) {
            first = last = null;
        }
        return itemToReturn;
    }
    private void printDequeue() {
        System.out.println("List Elements");
        Node curr = first;
        while (curr != null) {
            System.out.println(curr.item.toString());
            curr = curr.next;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(isEmpty()) {
                throw new NoSuchElementException("Invalid iterator call : queue is empty");
            }
            Item curr = current.item;
            current = current.next;
            return curr;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation for Deque iterator is not supported");
        }
    }

    public static void main(String[] args) {
       /* Deque<Integer> integerDeque = new Deque<>();
        //Add first to empty list
        integerDeque.addFirst(10);
        integerDeque.addFirst(20);
        integerDeque.printDequeue();

        integerDeque.addFirst(30);
        integerDeque.printDequeue();
        System.out.println(integerDeque.size());

        integerDeque.removeFirst();
        integerDeque.printDequeue();
        System.out.println(integerDeque.size());

        integerDeque.removeLast();
        integerDeque.printDequeue();

        //integerDeque.removeLast();
        integerDeque.addLast(50);
        integerDeque.removeFirst();
        integerDeque.printDequeue();
        integerDeque.removeFirst();
        integerDeque.printDequeue();
        System.out.println(integerDeque.size());

        Deque<String> stringDeque = new Deque<>();
        //Add first to empty list
        stringDeque.addFirst("First");
        stringDeque.addFirst("Second");
        stringDeque.printDequeue();
        System.out.println(stringDeque.size());

        stringDeque.addFirst("Third");
        stringDeque.printDequeue();
        System.out.println(stringDeque.size());

        stringDeque.removeFirst();
        stringDeque.printDequeue();

        stringDeque.removeLast();
        stringDeque.printDequeue();
        System.out.println(stringDeque.size());

        //stringDeque.removeLast();
        stringDeque.addLast("Fifth");
        stringDeque.removeFirst();
        stringDeque.printDequeue();
        stringDeque.removeFirst();
        stringDeque.printDequeue();
        System.out.println(stringDeque.size());*/

        Deque<Integer> deque = new Deque<>();
        deque.isEmpty();
        deque.printDequeue();
        deque.addFirst(2);
        deque.removeLast();
        deque.printDequeue();
        deque.addFirst(4);
        deque.removeLast();
//        deque.printDequeue();
        Deque<Integer>.DequeIterator itr =(Deque<Integer>.DequeIterator)deque.iterator();
        System.out.println(itr.next());
    }

}