"""
Given the root of a binary tree, return the sum of all left leaves.

A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.

Example 2:
Input: root = [1]
Output: 0

Constraints:
The number of nodes in the tree is in the range [1, 1000].
-1000 <= Node.val <= 1000
"""
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class SumOfLeftLeaves:
    def sumOfLeftLeaves(self, root: Optional[TreeNode]) -> int:

        def dfs(cur, is_left):
            if cur is None:
                return 0
            if cur.left is None and cur.right is None and is_left:
                return cur.val
            return dfs(cur.left, True) + dfs(cur.right, False)

        return dfs(root.left, True) + dfs(root.right, False)