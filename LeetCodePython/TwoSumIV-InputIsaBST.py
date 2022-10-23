"""
Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: root = [5,3,6,2,4,null,7], k = 9
Output: true

Example 2:
Input: root = [5,3,6,2,4,null,7], k = 28
Output: false


Constraints:
The number of nodes in the tree is in the range [1, 104].
-10^4 <= Node.val <= 10^4
root is guaranteed to be a valid binary search tree.
-10^5 <= k <= 10^5
"""
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class TwoSumIVInputIsaBST:
    def findTarget(self, root: Optional[TreeNode], k: int) -> bool:
        visited = set()

        def dfs(cur):
            if not cur:
                return False
            if k - cur.val in visited:
                return True
            visited.add(cur.val)
            return dfs(cur.left) or dfs(cur.right)

        return dfs(root)