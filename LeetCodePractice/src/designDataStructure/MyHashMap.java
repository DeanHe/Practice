package designDataStructure;
/*
Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);
hashMap.put(2, 2);
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found)

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.
 */
public class MyHashMap {
    final ListNode[] nodes;
     /** Initialize your data structure here. */
    public MyHashMap() {
        nodes = new ListNode[10000];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int i = idx(key);
        if(nodes[i] == null){
            nodes[i] = new ListNode(-1, -1);
        }
        ListNode pre = find(nodes[i], key);
        if(pre.next == null){
            pre.next = new ListNode(key, value);
        } else {
            pre.next.val = value;
        }

    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int i = idx(key);
        ListNode res = find(nodes[i], key);
        if(res == null || res.next == null || res.next.key != key){
            return -1;
        } else {
            return res.next.val;
        }
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int i = idx(key);
        if(nodes[i] == null){
            return;
        }
        ListNode pre = find(nodes[i], key);
        if(pre.next != null){
            pre.next = pre.next.next;
        }
    }

    private int idx(int key){
        return Integer.hashCode(key) % nodes.length;
    }

    /**find the pre node of node with key, if not pre is the last node of the bucket */
    private ListNode find(ListNode bucket, int key){
        ListNode cur = bucket, pre = null;
        while(cur != null && cur.key != key){
            pre = cur;
            cur = cur.next;
        }
        return pre;
    }

    private class ListNode {
        int key, val;
        ListNode next;

        public ListNode(int k, int v){
            key = k;
            val = v;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

