"""
Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.

Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.

A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Example 1:
Input: n = 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]

Example 2:
Input: n = 3
Output: [[0,0,0]]


Constraints:
1 <= n <= 20
"""
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    cache = {}

    def allPossibleFBT(self, n: int) -> List[Optional[TreeNode]]:
        res = []
        if n % 2 == 0:
            return res
        if n == 1:
            res.append(TreeNode(0))
            return res
        if n in Solution.cache:
            return Solution.cache[n]
        n -= 1
        for i in range(1, n, 2):
            left_pool = self.allPossibleFBT(i)
            right_pool = self.allPossibleFBT(n - i)
            for left in left_pool:
                for right in right_pool:
                    root = TreeNode(0)
                    root.left = left
                    root.right = right
                    res.append(root)
        Solution.cache[n] = res
        return res
