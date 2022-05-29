"""
Given the root of a binary tree, return the sum of values of its deepest leaves.


Example 1:
Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15

Example 2:
Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 19

Constraints:
The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 100

hint:
1 Traverse the tree to find the max depth.
2 Traverse the tree again to compute the sum required.
"""
import collections
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class DeepestLeavesSum:
    def deepestLeavesSum(self, root: Optional[TreeNode]) -> int:
        res, q = 0, collections.deque()
        q.append(root)
        while q:
            res, sz = 0, len(q)
            for _ in range(sz):
                cur = q.popleft()
                res += cur.val
                if cur.left:
                    q.append(cur.left)
                if cur.right:
                    q.append(cur.right)
        return res
