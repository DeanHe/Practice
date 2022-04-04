"""
Given a binary tree, find the length of the longest consecutive sequence path.
The path could be start and end at any node in the tree

Example
    1
   / \
  2   0
 /
3
Return 4 // 0-1-2-3


Example 2:
    3
   / \
  2   2
2-3
"""


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class BinaryTreeLongestConsecutiveSequenceII:
    def longestConsecutive(self, root: TreeNode) -> int:
        def dfs(node):
            if not node:
                return 0, 0, 0
            left_max_len, left_max_inc, left_max_dec = dfs(node.left)
            right_max_len, right_max_inc, right_max_dec = dfs(node.right)
            max_len, max_inc, max_dec = 1, 1, 1
            if node.left and node.left.val == root.val + 1:
                max_inc = max(max_inc, left_max_inc + 1)
            if node.right and node.right.val == root.val + 1:
                max_inc = max(max_inc, right_max_inc + 1)
            if node.left and node.left.val == root.val - 1:
                max_dec = max(max_dec, left_max_dec + 1)
            if node.right and node.right.val == root.val - 1:
                max_dec = max(max_dec, right_max_dec + 1)
            max_len = max_inc + max_dec - 1
            max_len = max(max_len, left_max_len, right_max_len)
            return max_len, max_inc, max_dec

        res, _, _ = dfs(root)
        return res
