import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item [] queue;
    private int size;
    private int length;
    private int index;
    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        size = 0;
        length = 1;
        index = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;

    }

    // add the item
    public void enqueue(Item item) {
        if(item == null ) {
            throw new IllegalArgumentException("Can not add a null element");
        }
        if( length == size ) {
            resize(2 * length);
        }
        if(index < queue.length) {
            queue[index++] = item;
            size++;
        }
    }
    private void resize(int capacity) {
        Item [] copy = (Item[]) new Object[capacity];
        int nonNullSize = 0;
        for (int i = 0; i < length; i++)
            if(queue[i] != null)
                copy[nonNullSize++] = queue[i];
        queue = copy;
        length = capacity;
        size = nonNullSize;
    }

    // remove and return a random item
    public Item dequeue() {
        Item item = null;
        int index = -1;
        do {
            index = StdRandom.uniformInt(length);
            item = queue[index];
        } while(item == null);
        queue[index] = null;
        size--;
        if(isEmpty()) {
            this.index = 0;
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        Item item = null;
        int index = -1;
        do {
            index = StdRandom.uniformInt(length);
            item = queue[index];
        } while(item == null);
        return item;
    }

    private void printQueue() {
        System.out.println("Size : " + size + " Length : " + length);
        for(Item item : queue) {
            System.out.println(item);
        }
    }

    private void printQueueRandom() {
        System.out.println(" : Random : ");
        System.out.println("Size : " + size + " Length : " + length);
        RandomizeQueueIterator<Item> randomizeQueueIterator = (RandomizeQueueIterator<Item>)this.iterator();
        while(randomizeQueueIterator.hasNext()) {
            System.out.println(randomizeQueueIterator.next());
        }
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizeQueueIterator<Item>();
    }

    private class RandomizeQueueIterator<Item> implements Iterator {
        int sizeofQueue = size;
        int lengthOfQueue = length;
        boolean [] tracker = new boolean[length];

        @Override
        public boolean hasNext() {
            return sizeofQueue > 0;
        }

        @Override
        public Object next() {
            Item item = null;
            int index = -1;

            if(sizeofQueue == 0) {
                throw new NoSuchElementException("Queue exhausted");
            }

            do {
                //System.out.println("length of queue : " + lengthOfQueue + " Size : " + sizeofQueue );
                index = StdRandom.uniformInt(lengthOfQueue);
                //System.out.println("Index : " + index);
                if(!tracker[index]) {
                    item = (Item) queue[index];
                } else {
                    item = null;
                }
            } while(item == null);
            sizeofQueue--;
            tracker[index] = true;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Iterator does not support the remove method");

        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        /*RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(10);
        randomizedQueue.enqueue(20);
        randomizedQueue.enqueue(30);
        randomizedQueue.enqueue(40);
        randomizedQueue.printQueue();
        randomizedQueue.printQueueRandom();
        System.out.println("------Sampling:-----");
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println("------Removing:-----");
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        randomizedQueue.printQueue();
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        randomizedQueue.printQueue();*/

        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(11);
        queue.enqueue(43);
        queue.enqueue(180);
        queue.enqueue(756);
        queue.enqueue(94);
        queue.enqueue(466);
        queue.enqueue(91);
        queue.printQueue();
        System.out.println("Using Iterator");
        RandomizedQueue.RandomizeQueueIterator iterator = (RandomizedQueue.RandomizeQueueIterator) queue.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
