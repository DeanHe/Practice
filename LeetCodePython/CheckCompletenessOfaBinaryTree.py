"""
Given the root of a binary tree, determine if it is a complete binary tree.

In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example 1:
Input: root = [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:
Input: root = [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.

Constraints:
The number of nodes in the tree is in the range [1, 100].
1 <= Node.val <= 1000
"""
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class CheckCompletenessOfaBinaryTree:
    def isCompleteTree(self, root: Optional[TreeNode]) -> bool:

        def node_cnt(cur):
            if not cur:
                return 0
            l, r = node_cnt(cur.left), node_cnt(cur.right)
            # for x = 2^k -1, x has a property that x & (x+1) == 0
            if l & (l + 1) == 0 and l / 2 <= r <= l:
                return l + r + 1
            if r & (r + 1) == 0 and r <= l <= 2 * r + 1:
                return l + r + 1
            return -1

        return node_cnt(root) > 0