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
 */
public class CopyListWithRandomPointer {
	public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null){
    		return null;
    	}
    	RandomListNode preHead = new RandomListNode(0);
    	RandomListNode cur = preHead;
    	//origin node to copy node mapping ; visited
    	HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
    	while(head != null){
    	    RandomListNode copyHead;
    		// copy node
    		if(map.containsKey(head)){
    			copyHead = map.get(head);
    		} else {
    			copyHead = new RandomListNode(head.label);
    			map.put(head, copyHead);
    		}
    		//copy random link
    		if(head.random != null){
    			if(map.containsKey(head.random)){
    				copyHead.random = map.get(head.random);
    			} else {
    				copyHead.random = new RandomListNode(head.random.label);
    				map.put(head.random, copyHead.random);
    			}
    		}
    		cur.next = copyHead;
    		cur = cur.next;
    	    head = head.next;
    	}
    	return preHead.next;
    }
}

 //Definition for singly-linked list with a random pointer.
class RandomListNode {
	int label;
	RandomListNode next, random;

	RandomListNode(int x) {
		this.label = x;
	}
};
 
