package LinkedList;

/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = findMid(head);
        ListNode secondHalfHead = mid.next;
        mid.next = null;
        ListNode reverseSecondHalfHead = reverse(secondHalfHead);
        ListNode p1 = head;
        ListNode p2 = reverseSecondHalfHead;
        while (p2 != null && p1 != p2) {
            ListNode post1 = p1.next;
            ListNode post2 = p2.next;
            p1.next = p2;
            p2.next = post1;
            p1 = post1;
            p2 = post2;
        }
    }

    private ListNode findMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode preHead = new ListNode(0);
        ListNode post = null;
        while (head != null) {
            post = head.next;
            head.next = preHead.next;
            preHead.next = head;
            head = post;
        }
        return preHead.next;
    }
}
