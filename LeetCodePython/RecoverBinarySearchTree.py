"""
You are given the root of a binary search tree (bst), where exactly two nodes of the tree were swapped by mistake.
Recover the tree without changing its structure.

Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?



Example 1:
Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the bst valid.

Example 2:
Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the bst valid.


Constraints:
The number of nodes in the tree is in the range [2, 1000].
-2^31 <= Node.val <= 2^31 - 1

analysis:
use the inorder traversal to find the two nodes out of order
"""
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    first, second, pre = None, None, TreeNode(float('-inf'))

    def recoverTree(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        self.inorder(root)
        self.first.val, self.second.val = self.second.val, self.first.val

    def inorder(self, root):
        if not root:
            return root
        self.inorder(root.left)
        if not self.first and root.val < self.pre.val:
            self.first = self.pre
        if self.first and root.val < self.pre.val:
            self.second = root
        self.pre = root
        self.inorder(root.right)