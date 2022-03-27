"""
Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.

If the tree has more than one mode, return them in any order.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:


Input: root = [1,null,2,2]
Output: [2]
Example 2:

Input: root = [0]
Output: [0]


Constraints:

The number of nodes in the tree is in the range [1, 104].
-10^5 <= Node.val <= 10^5


Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
"""

# Definition for a binary tree node.
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class FindModeInBinarySearchTree:
    pre, max_cnt, cur_cnt, res = None, 0, 0, []

    def findMode(self, root: Optional[TreeNode]) -> List[int]:
        self.dfs(root)
        return self.res

    def dfs(self, root):
        if not root:
            return
        self.dfs(root.left)
        if root.val == self.pre:
            self.cur_cnt += 1
        else:
            self.cur_cnt = 1
        if self.cur_cnt == self.max_cnt:
            self.res.append(root.val)
        elif self.cur_cnt > self.max_cnt:
            self.res = [root.val]
            self.max_cnt = self.cur_cnt
        self.pre = root.val
        self.dfs(root.right)
