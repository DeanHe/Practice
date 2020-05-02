package LinkedList;

import java.util.*;
/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

        get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
        put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

        The cache is initialized with a positive capacity.

        Follow up:
        Could you do both operations in O(1) time complexity?

        Example:

        LRUCache cache = new LRUCache( 2 *//* capacity *//* );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

        solution:
        create Double LinkList Node (k,v)
        remove head.next when reach capacity
        move the node to tail.pre for get operation
        create private method insertBeforeTail
*/
public class LRUcache {
	private class Node {
        Node pre;
        Node next;
        int val;
        int key;
        public Node(int k, int v){
            key = k;
            val = v;
        }
    }
    private int capacity;
    private HashMap<Integer, Node> map = new HashMap<>();
    // point to the least recent one
    private Node head = new Node(-1, -1);
    // point to the most recent one
    private Node tail = new Node(-1, -1);

    // @param capacity, an integer
    public LRUcache(int capacity) {
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;
    }

    private void insertBeforeTail(Node n){
        tail.pre.next = n;
        n.next = tail;
        n.pre = tail.pre;
        tail.pre = n;
    }
    
    // @return an integer
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node n = map.get(key);
        n.pre.next = n.next;
        n.next.pre = n.pre;
        insertBeforeTail(n);
        return n.val;
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // write your code here
        if(get(key) != -1){
            //exist node
            Node n = map.get(key);
            n.val = value;
        } else {
            // not exist node
            if(map.size() == capacity){
                // map full, need to remove least recent one
                map.remove(head.next.key);
                head.next = head.next.next;
                head.next.pre = head;
            }
            //add new node to map
            Node n = new Node(key, value);
            map.put(key, n);
            //add to latest
            insertBeforeTail(n);
        }
    }
}
