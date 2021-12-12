package LinkedList;
/*
Given a node from a Circular Linked List which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the circular list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the original given node.



Example 1:




Input: head = [3,4,1], insertVal = 2
Output: [3,4,1,2]
Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.



Example 2:

Input: head = [], insertVal = 1
Output: [1]
Explanation: The list is empty (given head is
null
). We create a new single circular list and return the reference to that single node.
Example 3:

Input: head = [1], insertVal = 0
Output: [1,0]
Constraints:

0 <= Number of Nodes <= 5 * 10^4
-10^6 <= Node.val <= 10^6
-10^6 <= insertVal <= 10^6

analysis：

The insertion position is within 3 condition,
1. cur go through a round back to head.
2.  cur.val <= insertVal <= cur.next.val
3. insertVal super max or insertVal super small, insert into the fall down position.
Note: Pay attention to corner case, head == null. DO NOT forget to have cur = cur.next in while loop.
Time Complexity: O(n). n = circular length.
Space: O(1).
 */
public class InsertIntoaSortedCircularLinkedList {
    public ListNode insert(ListNode head, int x) {
        ListNode node = new ListNode(x);
        if(head == null){
            node.next = node;
            return node;
        }
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if(cur.val < cur.next.val){
                if(cur.val <= x && x <= cur.next.val){
                    break;
                }
            } else if(cur.val > cur.next.val){
                if(x > cur.val || x < cur.next.val){
                    break;
                }
            } else {
                if(cur.next == head){
                    break;
                }
            }
            cur = cur.next;
        }
        node.next = cur.next;
        cur.next = node;
        return head;
    }
}
