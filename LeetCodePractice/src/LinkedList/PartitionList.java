package LinkedList;

/*
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.



Example 1:


Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Example 2:

Input: head = [2,1], x = 2
Output: [1,2]


Constraints:

The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode smaller = new ListNode(0);
        ListNode s = smaller;
        ListNode bigger = new ListNode(0);
        ListNode b = bigger;
        while(head != null){
            if(head.val >= x){
                b.next = head;
                b = b.next;
            } else {
                s.next = head;
                s = s.next;
            }
            head = head.next;
        }
        b.next = null;
        s.next = bigger.next;
        return smaller.next;
    }
}

