package LinkedList;
/*Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4*/
public class MergeTwoSortedLists {
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(0);
        ListNode current = preHead;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        if(temp1 == null){
		    current.next = temp2;
		    return preHead.next;
		} 
		if(temp2 == null){
		    current.next = temp1;
		    return preHead.next;
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
		return preHead.next;
    }
}
