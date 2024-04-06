"""
Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.

After doing so, return the head of the final linked list.  You may return any such answer.

(Note that in the examples below, all sequences are serializations of ListNode objects.)

Example 1:
Input: head = [1,2,-3,3,1]
Output: [3,1]
Note: The answer [1,2,1] would also be accepted.

Example 2:
Input: head = [1,2,3,-3,4]
Output: [1,2,4]

Example 3:
Input: head = [1,2,3,-3,-2]
Output: [1]

Constraints:
The given linked list will contain between 1 and 1000 nodes.
Each node in the linked list has -1000 <= node.val <= 1000.

hints:
1 Convert the linked list into an array.
2 While you can find a non-empty subarray with sum = 0, erase it.
3 Convert the array into a linked list.

analysis:
presum
TC:O(N)
"""
from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class RemoveZeroSumConsecutiveNodesFromLinkedList:
    def removeZeroSumSublists(self, head: Optional[ListNode]) -> Optional[ListNode]:
        map = {}
        dummy = ListNode()
        dummy.next = head
        cur = dummy
        pre_sum = 0
        while cur:
            pre_sum += cur.val
            if pre_sum in map:
                pre = map[pre_sum]
                cur = pre.next
                tmp = pre_sum + cur.val
                while tmp != pre_sum:
                    del map[tmp]
                    cur = cur.next
                    tmp += cur.val
                pre.next = cur.next
            else:
                map[pre_sum] = cur
            cur = cur.next
        return dummy.next

    def removeZeroSumSublists2(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode()
        last = {0: dummy}
        dummy.next = head
        pre_sum = 0
        while head:
            pre_sum += head.val
            last[pre_sum] = head
            head = head.next
        pre_sum = 0
        head = dummy
        while head:
            pre_sum += head.val
            if pre_sum in last:
                head.next = last[pre_sum].next
            head = head.next
        return dummy.next