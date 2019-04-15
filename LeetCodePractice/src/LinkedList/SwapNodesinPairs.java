package LinkedList;
/*Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.*/
public class SwapNodesinPairs {
		      
	public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
        	return head;	        	
        }
        ListNode fake = new ListNode(0);
        fake.next = head;
        
        ListNode pre = fake;
        ListNode cur = head;
        
        while(cur != null && cur.next != null){
        	ListNode temp = cur.next;
        	cur.next = temp.next;
        	temp.next = cur;
        	pre.next = temp;
        	pre = cur;
        	cur = cur.next;        	
        }
        
        return fake.next;	        
    }
}
