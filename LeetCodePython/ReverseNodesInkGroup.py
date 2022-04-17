"""
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

k is a positive integer and is less than or equal to the length of the linked list.
If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]

Example 2:
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]

Constraints:
The number of nodes in the list is n.
1 <= k <= n <= 5000
0 <= Node.val <= 1000

Follow-up: Can you solve the problem in O(1) extra memory space?

analysis:
l, r : define reversing range
pre, cur : used in reversing, standard reverse linked linked list method
jump : used to connect last node in previous k-group to first node in following k-group
"""
from typing import Optional


# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class ReverseNodesInkGroup:
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        if k == 1:
            return head
        dummy = jump = ListNode()
        dummy.next = l = r = head
        while True:
            cnt = 0
            while r and cnt < k:
                r = r.next
                cnt += 1
            # if size k satisfied, reverse the inner linked list
            if cnt == k:
                pre, cur = r, l
                for _ in range(k):
                    # standard reversing
                    cur.next, cur, pre = pre, cur.next, cur
                # connect two k-groups
                jump.next, jump, l = pre, l, r
            else:
                return dummy.next
