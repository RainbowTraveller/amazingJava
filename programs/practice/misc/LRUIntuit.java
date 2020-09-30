import java.io.*;
import java.util.*;

/*
 * LRU Cache
 *  Capacity
 *  Get the data if present -> 29, 10, 15,
 *  Add the data -> 17, 29, 10
 */

public class LRUIntuit {
    public static void main(String[] args) {

        Cache LRUCache = new Cache(3);
        System.out.println(LRUCache.get(18));
        LRUCache.set(18, 200);
        System.out.println(LRUCache.get(18));
        LRUCache.set(18, 300);
        System.out.println(LRUCache.get(18));
        LRUCache.set(15, 100);
        System.out.println(LRUCache.get(15));
        LRUCache.set(10, 400);
        LRUCache.set(11, 500);
        System.out.println(LRUCache.get(11));
    }

    static class LRUNode {
        int key;
        int value;
        LRUNode prev;
        LRUNode next;

        public LRUNode() {
            prev = null;
            next = null;
        }

        public LRUNode(int key, int val) {
            this();
            this.key = key;
            this.value = val;
        }
    }

    static class Cache {
        int capacity;
        int size;
        Map<Integer, LRUNode> track;
        LRUNode head;
        LRUNode tail;

        public Cache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            track = new HashMap<>();
            head = new LRUNode();
            tail = new LRUNode();
            head.next = tail;
            tail.prev = head;
        }

        private void removeLast() {
            LRUNode temp = tail.prev;
            track.remove(temp.key);
            //NPL here
            temp.prev.next = tail;
            tail.prev = temp.prev;
            temp.next = null;
            temp.prev = null;
        }

        private LRUNode removeNode(LRUNode node) {
            LRUNode prevNode = node.prev;
            LRUNode nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            node.prev = null;
            node.next = null;
            track.remove(node.key);
            return node;
        }

        private void addToFront(LRUNode node) {
            node.next = head.next;
            node.prev = head;
            head.next = node;
            track.put(node.key, node);
        }

        public Integer get(int key) {
            /*
             * check in the map if present return move it to front
             *
             */
            if (track.containsKey(key)) {
                LRUNode currNode = track.get(key);
                removeNode(currNode);
                addToFront(currNode);
                return currNode.value;
            }

            return null;
        }

        public void set(int key, int val) {
            /**
             * if already present remove the object set new val add to front else if
             * capacity is reached remove last add to front
             */

            if (track.containsKey(key)) {
                LRUNode currNode = track.get(key);
                removeNode(currNode);
                currNode.value = val;
                addToFront(currNode);

            } else {
                if (size == capacity) {
                    removeLast();
                    size--;
                }
                addToFront(new LRUNode(key, val));
                size++;
            }
        }

    }
}
