package LinkedList;
/*
sort a linked list using insertion sort.


A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list


Algorithm of Insertion sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5

space complexity: O(1)
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while(head != null){
            while(pre.next != null && pre.next.val < head.val){
                pre = pre.next;
            }
            // insert between pre and pre.next;
            ListNode temp = new ListNode(head.val);
            temp.next = pre.next;
            pre.next = temp;
            head = head.next;
            pre = dummy;
        }
        return dummy.next;
    }
}
