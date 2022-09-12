"""
Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

Example 1:
Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

Example 2:
Input: n = 1
Output: [[1]]

Constraints:
1 <= n <= 8
"""
from typing import Optional, List

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class UniqueBinarySearchTreesII:
    def generateTrees(self, n: int) -> List[Optional[TreeNode]]:

        def dfs(s, e):
            res = []
            if s > e:
                res.append(None)
                return res
            for i in range(s, e + 1):
                left_candidates, right_candidates = dfs(s, i - 1), dfs(i + 1, e)
                for l in left_candidates:
                    for r in right_candidates:
                        root = TreeNode(i)
                        root.left, root.right =l, r
                        res.append(root)
            return res

        return dfs(1, n)