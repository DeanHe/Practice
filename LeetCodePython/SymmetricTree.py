"""
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true
Example 2:


Input: root = [1,2,2,null,3,null,3]
Output: false


Constraints:

The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100


Follow up: Could you solve it both recursively and iteratively?
"""
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:

        def dfs(a: TreeNode, b: TreeNode):
            if a is None or b is None:
                if a is None and b is None:
                    return True
                return False
            if a.val != b.val:
                return False
            return dfs(a.left, b. right) and dfs(a.right, b.left)

        return root is None or dfs(root.left, root.right)

    def isSymmetricIterative(self, root: Optional[TreeNode]) -> bool:
        if not root:
            return True
        stack = [[root.left, root.right]]
        while stack:
            l, r = stack.pop()
            if l is None and r is None:
                continue
            if l is None or r is None:
                return False
            if l.val == r.val:
                stack.append([l.left, r.right])
                stack.append([l.right, r.left])
            else:
                return False
        return True