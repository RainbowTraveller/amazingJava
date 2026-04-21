import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LRUCache {

  /**
   * LRU Cache is a cache which removes the least recently used element when the capacity is
   * overflown. We can use a double linked list to keep track of the recently used elements and a
   * hashmap to store the key and node reference for O(1) access. When we add a new element, we add
   * it at the start of the linked list and if the capacity is overflown, we remove the last node
   * from the linked list which is the least recently used element. When we access an element, we
   * move it to the start of the linked list indicating that it is recently used.
   */
  class DoubleLLNode {
    int key;
    int val;
    DoubleLLNode prev;
    DoubleLLNode next;

    public DoubleLLNode() {
      key = 0;
      val = 0;
      prev = null;
      next = null;
    }

    public DoubleLLNode(int key, int val) {
      this.key = key;
      this.val = val;
      this.prev = null;
      this.next = null;
    }

    public String toString() {
      return new String("Key : " + key + " Value : " + val);
    }
  }

  // HashMap to store key and node reference for O(1) access
  private Map<Integer, DoubleLLNode> cache;
  // Capacity of the cache
  private int capacity;
  // Double linked list to keep track of recently used elements
  private DoubleLLNode head;
  // Tail of the linked list to remove least recently used element when capacity is overflown
  private DoubleLLNode tail;

  /**
   * Constructor to initialize the cache with given capacity and create dummy head and tail nodes
   * for the linked list.
   *
   * @param capacity the capacity of the cache
   */
  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.cache = new HashMap<>();
    head = new DoubleLLNode();
    tail = new DoubleLLNode();
    head.next = tail;
    tail.prev = head;
  }

  /**
   * Add node in front of the linked list indicating that it is recently used.
   *
   * @param node the node to be added in front
   */
  private void addNodeInFront(DoubleLLNode node) {
    node.next = head.next;
    node.prev = head;

    head.next.prev = node;
    head.next = node;
  }

  /**
   * Remove the last node from the linked list which is the least recently used element and remove
   * it from the cache.
   *
   * @return the removed node
   */
  private DoubleLLNode removeNodeFromEnd() {
    DoubleLLNode stale = tail.prev;
    removeNode(stale);
    cache.remove(stale.key);
    return stale;
  }

  /**
   * Remove the given node from the linked list and update the references of the previous and next
   * nodes.
   *
   * @param node the node to be removed
   */
  private void removeNode(DoubleLLNode node) {
    DoubleLLNode currPrev = node.prev;
    DoubleLLNode currNext = node.next;
    currPrev.next = currNext;
    currNext.prev = currPrev;
    node.next = null;
    node.prev = null;
  }

  /**
   * Put the key and value in the cache and add the node in front of the linked list indicating that
   * it is recently used. If the key already exists, update the value and move the node to the
   * front. If the capacity is overflown, remove the last node from the linked list which is the
   * least recently used element.
   *
   * @param key the key to be added in the cache
   * @param value the value to be added in the cache
   */
  public void put(int key, int value) {
    // add and if capacity is overflown, remove last node which is stale
    if (cache.containsKey(key)) {
      DoubleLLNode desiredNode = cache.get(key);
      // Set new value
      desiredNode.val = value;
      // Put at the start, indicating recently used node
      removeNode(desiredNode);
      addNodeInFront(desiredNode);
    } else {
      DoubleLLNode notFoudNode = new DoubleLLNode(key, value);
      addNodeInFront(notFoudNode);
      cache.put(key, notFoudNode);
      if (cache.size() > capacity) {
        removeNodeFromEnd();
      }
    }
  }

  /**
   * Get the value of the key if it exists in the cache and move the node to the front of the linked
   * list indicating that it is recently used. If the key does not exist, return -1.
   *
   * @param key the key to be accessed in the cache
   * @return the value of the key if it exists in the cache, otherwise return -1
   */
  public int get(int key, int value) {
    // Check if the key exists or return -1;
    if (cache.containsKey(key)) {
      DoubleLLNode foundNode = cache.get(key);
      int val = foundNode.val;
      // Put at the start, indicating recently used node
      removeNode(foundNode);
      addNodeInFront(foundNode);
      return val;
    }
    return -1;
  }

  public String toString() {
    return cache.toString();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Please enter Cache Capacity : ");
    int c = sc.nextInt();
    LRUCache lru = new LRUCache(c);

    while (true) {
      System.out.println("Please enter Cache element : ");
      int key = sc.nextInt();
      int value = sc.nextInt();
      lru.put(key, value);
      System.out.println(lru);
    }
  }
}
