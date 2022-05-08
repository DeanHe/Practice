"""
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.



Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]


Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
"""
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class ConstructBinaryTreeFromPreorderAndInorderTraversal:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        length = len(preorder)
        val_to_idx = {}
        for i, val in enumerate(inorder):
            val_to_idx[val] = i

        def helper(pre_s, pre_e, in_s, in_e):
            if pre_s > pre_e or in_s > in_e:
                return None
            root = TreeNode(preorder[pre_s])
            idx = val_to_idx[root.val]
            root.left = helper(pre_s + 1, pre_s + (idx - in_s), in_s, idx - 1)
            root.right = helper(pre_s + (idx - in_s) + 1, pre_e, idx + 1, in_e)
            return root

        return helper(0, length - 1, 0, length - 1)
