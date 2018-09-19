import java.util.*;

public class CopyListWithRandomPointer {
	public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null){
    		return null;
    	}
    	RandomListNode preHead = new RandomListNode(0);
    	RandomListNode pre = preHead;
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
    		pre.next = copyHead;
    		pre = pre.next;
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
 
