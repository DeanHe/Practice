package LinkedList;

import java.util.*;

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
