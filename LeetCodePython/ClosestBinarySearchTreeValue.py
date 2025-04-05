"""
Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target. If there are multiple answers, print the smallest.

Example 1:
Input: root = [4,2,5,1,3], target = 3.714286
Output: 4

Example 2:
Input: root = [1], target = 4.428571
Output: 1

Constraints:
The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 10^9
-10^9 <= target <= 10^9

Analysis:
TC: O(height of tree)
"""
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class ClosestBinarySearchTreeValue:
    def closestValue(self, root: Optional[TreeNode], target: float) -> int:
        res = root.val
        while root:
            cur = root.val
            if abs(cur - target) < abs(res - target):
                res = cur
            elif abs(cur - target) == abs(res - target):
                res = min(res, cur)
            if cur == target:
                return cur
            elif cur < target:
                root = root.right
            else:
                root = root.left
        return res