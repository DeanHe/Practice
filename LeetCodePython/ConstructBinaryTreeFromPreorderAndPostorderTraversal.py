"""
Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.

If there exist multiple answers, you can return any of them.

Example 1:
Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]

Example 2:
Input: preorder = [1], postorder = [1]
Output: [1]

Constraints:
1 <= preorder.length <= 30
1 <= preorder[i] <= preorder.length
All the values of preorder are unique.
postorder.length == preorder.length
1 <= postorder[i] <= postorder.length
All the values of postorder are unique.
It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.

Analysis:
TC:O(N)
"""
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class ConstructBinaryTreeFromPreorderAndPostorderTraversal:

    def __init__(self):
        self.pre_idx = 0
        self.post_idx = 0

    def constructFromPrePost(self, preorder: List[int], postorder: List[int]) -> Optional[TreeNode]:

        def dfs():
            root = TreeNode(preorder[self.pre_idx])
            self.pre_idx += 1
            if root.val != postorder[self.post_idx]:
                root.left = dfs()
            if root.val != postorder[self.post_idx]:
                root.right = dfs()
            self.post_idx += 1
            return root

        return dfs()

    def constructFromPrePostII(self, preorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        if len(preorder) != len(postorder):
            return None

        postorder_map = {}
        for i, n in enumerate(postorder):
            postorder_map[n] = i

        def dfs(pre_s, pre_e, post_s, post_e):
            if pre_s > pre_e or post_s > post_e:
                return None
            root = TreeNode(preorder[pre_s])
            if pre_s == pre_e:
                return root
            left_node_postorder_idx = postorder_map[preorder[pre_s + 1]]
            left_tree_size = left_node_postorder_idx - post_s + 1
            root.left = dfs(pre_s + 1, pre_s + left_tree_size, post_s, left_node_postorder_idx)
            root.right = dfs(pre_s + left_tree_size + 1, pre_e, left_node_postorder_idx + 1, post_e - 1)
            return root

        return dfs(0, len(preorder) - 1, 0, len(postorder) - 1)
