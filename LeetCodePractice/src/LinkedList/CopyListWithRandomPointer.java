package LinkedList;

import java.util.*;
/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.


Example 1:


Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
Example 2:


Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
Example 3:



Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
Example 4:

Input: head = []
Output: []
Explanation: Given linked list is empty (null pointer), so return null.


Constraints:

-10000 <= Node.val <= 10000
Node.random is null or pointing to a node in the linked list.
Number of Nodes will not exceed 1000.

analysis:
1 using hashMap save origin node to  copy
SC O(N), TC O(N)
2 modify the linked-list to connect original and copy
SC O(1), TC O(N)
 */
public class CopyListWithRandomPointer {
	public Node copyRandomList(Node head) {
        if(head == null){
    		return null;
    	}
    	Node dummy = new Node(0);
    	Node cur = dummy;
    	//origin node to copy node mapping ; visited
    	Map<Node, Node> map = new HashMap<>();
    	while(head != null){
    	    Node copy;
    		// copy node
    		if(map.containsKey(head)){
    			copy = map.get(head);
    		} else {
    			copy = new Node(head.val);
    			map.put(head, copy);
    		}
    		//copy random link
    		if(head.random != null){
    			if(map.containsKey(head.random)){
    				copy.random = map.get(head.random);
    			} else {
    				copy.random = new Node(head.random.val);
    				map.put(head.random, copy.random);
    			}
    		}
    		cur.next = copy;
    		cur = cur.next;
    	    head = head.next;
    	}
    	return dummy.next;
    }

	public Node copyRandomListII(Node head) {
		if(head == null){
			return null;
		}
		Node cur = head, post;
		// First round: make copy of each node,
		// and link origin -> copy together side-by-side in a single list.
		while(cur != null){
			post = cur.next;
			Node copy = new Node(cur.val);
			cur.next = copy;
			copy.next = post;
			cur = post;
		}
		// Second round: assign random pointers for the copy nodes.
		cur = head;
		while(cur != null){
			if(cur.random != null){
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}
		// Third round: restore the original list, and extract the copy list.
		cur = head;
		Node dummy = new Node(0);
		Node copy, pre = dummy;
		while(cur != null){
			post = cur.next.next;
			// extract the copy
			copy = cur.next;
			pre.next = copy;
			pre = copy;
			// restore the original list
			cur.next = post;
			cur = post;
		}
		return dummy.next;
	}

	//Definition for singly-linked list with a random pointer.
	private class Node {
		int val;
		Node next, random;

		public Node(int x) {
			this.val = x;
		}
	}
}
 
