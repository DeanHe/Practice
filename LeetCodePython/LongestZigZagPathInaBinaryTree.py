"""
You are given the root of a binary tree.

A ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
Change the direction from right to left or from left to right.
Repeat the second and third steps until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.



Example 1:


Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).

Example 2:
Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).

Example 3:
Input: root = [1]
Output: 0


Constraints:
The number of nodes in the tree is in the range [1, 5 * 10^4].
1 <= Node.val <= 100
"""
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class LongestZigZagPathInaBinaryTree:
    def longestZigZag(self, root: Optional[TreeNode]) -> int:
        self.res = 0

        def dfs(cur):
            if not cur:
                return 0, 0
            l, r = dfs(cur.left), dfs(cur.right)
            self.res = max(self.res, l[1] + 1, r[0] + 1)
            return l[1] + 1, r[0] + 1

        dfs(root)
        return self.res - 1