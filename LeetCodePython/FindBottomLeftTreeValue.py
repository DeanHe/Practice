"""
Given the root of a binary tree, return the leftmost value in the last row of the tree.

Example 1:
Input: root = [2,1,3]
Output: 1

Example 2:
Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7

Constraints:
The number of nodes in the tree is in the range [1, 104].
-2^31 <= Node.val <= 2^31 - 1

analysis:
DFS or BFS
TC: O(N)
SC: O(N) recursion stack
"""
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class FindBottomLeftTreeValue:
    def findBottomLeftValue(self, root: Optional[TreeNode]) -> int:
        self.max_depth = -1
        self.res = 0

        def dfs(node, lvl):
            if not node:
                return
            if self.max_depth < lvl:
                self.max_depth = lvl
                self.res = node.val
            dfs(node.left, lvl + 1)
            dfs(node.right, lvl + 1)

        dfs(root, 0)
        return self.res
