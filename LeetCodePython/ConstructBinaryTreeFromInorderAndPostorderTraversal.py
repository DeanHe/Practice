"""
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

Example 1:
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: inorder = [-1], postorder = [-1]
Output: [-1]

Constraints:
1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
"""
from typing import List, Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class ConstructBinaryTreeFromInorderAndPostorderTraversal:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        n = len(inorder)
        idx = {}
        for i, val in enumerate(inorder):
            idx[val] = i

        def dfs(in_s, in_e, po_s, po_e):
            if in_s > in_e or po_s > po_e:
                return
            cur = TreeNode(postorder[po_e])
            i = idx[cur.val]
            cur.left = dfs(in_s, i - 1, po_s, po_s + i - 1 - in_s)
            cur.right = dfs(i + 1, in_e, po_s + i - in_s, po_e - 1)
            return cur

        return dfs(0, n - 1, 0, n - 1)