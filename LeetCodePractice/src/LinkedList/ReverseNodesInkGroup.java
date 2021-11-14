package LinkedList;
/*Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
*/
public class ReverseNodesInkGroup {

	/**
     * @param head a ListNode
     * @param k an integer
     * @return a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode post;
        int count = 0;
        while(head != null){
            count++;
            post = head.next;
            if(count == k){
                pre = reverse(pre, post);
                count = 0;
            }
            head = post;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode pre, ListNode post){
        ListNode last = pre.next;
        ListNode cur = last.next;
        while(cur != post){
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        return last;
    }
}
