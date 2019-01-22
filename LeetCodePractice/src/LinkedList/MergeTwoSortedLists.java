package LinkedList;

public class MergeTwoSortedLists {
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode current = result;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        if(temp1 == null){
		    current.next = temp2;
		    return result.next;
		} 
		if(temp2 == null){
		    current.next = temp1;
		    return result.next;
		}
		while (temp1 != null && temp2 != null) {
            if(temp1.val < temp2.val){
            	current.next = temp1;
            	temp1 = temp1.next;
            } else {
                current.next = temp2;
            	temp2 = temp2.next;
            }
            current = current.next;
		}
		
		if(temp1 != null){
		    current.next = temp1;
		} 
	    if(temp2 != null) {
		    current.next = temp2;
		}
		
		return result.next;
    }
}
