"""
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.



Example 1:


Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
Example 2:

Input: root = [1,2]
Output: 1


Constraints:

The number of nodes in the tree is in the range [1, 10^4].
-100 <= Node.val <= 100
"""

from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0

        def dfs(node):
            if not node:
                return 0, 0
            l, l_max = dfs(node.left)
            r, r_max = dfs(node.right)
            return max(l, r) + 1, max(l_max, r_max, l + r + 1)

        _, res = dfs(root)
        return res - 1

    def diameterOfBinaryTreeGlobalVariable(self, root: Optional[TreeNode]) -> int:
        res = 0

        def dfs(node):
            if not node:
                return 0
            nonlocal res
            l = dfs(node.left)
            r = dfs(node.right)
            res = max(res, l + r + 1)
            return max(l, r) + 1

        dfs(root)
        return res
