package LinkedList;
/*
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?
*/
// two pointer
public class RemoveNthNodeFromEndofList {

	public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n == 0){
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        for(int i = 0; i < n; i++){
            fast = fast.next;
            if(fast == null){
                head = head.next;
                return head;
            }
        }
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
