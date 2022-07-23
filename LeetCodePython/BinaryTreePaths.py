"""
Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.

Example 1:
Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]

Example 2:
Input: root = [1]
Output: ["1"]

Constraints:
The number of nodes in the tree is in the range [1, 100].
-100 <= Node.val <= 100
"""
from typing import Optional, List

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class BinaryTreePaths:
    def binaryTreePaths(self, root: Optional[TreeNode]) -> List[str]:
        res = []
        if root is None:
            return res

        def dfs(cur, path):
            if root is None:
                return
            path += str(cur.val)
            if cur.left is None and cur.right is None:
                res.append(path)
            if cur.left:
                dfs(cur.left, path + "->")
            if cur.right:
                dfs(cur.right, path + "->")

        dfs(root, "")
        return res
