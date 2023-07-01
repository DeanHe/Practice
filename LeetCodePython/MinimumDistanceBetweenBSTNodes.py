"""
Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.

Example 1:
Input: root = [4,2,6,1,3]
Output: 1

Example 2:
Input: root = [1,0,48,null,null,12,49]
Output: 1

Constraints:
The number of nodes in the tree is in the range [2, 10^4].
0 <= Node.val <= 10^5

Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/

analysis:
as it is BST, in order traverse compares two adjacent nodes is the sorted order, this way guarantees to find the minimum diff
TC:O(N)
SC: O(1)
"""
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class MinimumDistanceBetweenBSTNodes:
    def getMinimumDifference(self, root: Optional[TreeNode]) -> int:
        self.res = float('inf')
        self.prev = None

        def inorder(cur):
            if not cur:
                return

            inorder(cur.left)

            if self.prev:
                self.res = min(self.res, cur.val - self.prev.val)
            self.prev = cur

            inorder(cur.right)

        inorder(root)
        return self.res

