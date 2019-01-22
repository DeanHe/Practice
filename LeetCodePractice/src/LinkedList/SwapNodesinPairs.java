package LinkedList;

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
