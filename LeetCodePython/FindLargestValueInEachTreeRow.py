"""
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

Example 1:
Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]

Example 2:
Input: root = [1,2,3]
Output: [1,3]


Constraints:
The number of nodes in the tree will be in the range [0, 10^4].
-2^31 <= Node.val <= 2^31 - 1
"""
from typing import Optional, List

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class FindLargestValueInEachTreeRow:
    def largestValues(self, root: Optional[TreeNode]) -> List[int]:
        res = []

        def dfs(cur, depth):
            if not cur:
                return
            if depth == len(res):
                res.append(cur.val)
            else:
                if res[depth] < cur.val:
                    res[depth] = cur.val
            dfs(cur.left, depth + 1)
            dfs(cur.right, depth + 1)

        dfs(root, 0)
        return res