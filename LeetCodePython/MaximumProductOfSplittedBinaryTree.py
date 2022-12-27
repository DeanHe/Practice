"""
Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 10^9 + 7.
Note that you need to maximize the answer before taking the mod and not after taking it.

Example 1:
Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)

Example 2:
Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)

Constraints:
The number of nodes in the tree is in the range [2, 5 * 10^4].
1 <= Node.val <= 10^4
"""
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class MaximumProductOfSplittedBinaryTree:
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        mod = 10 ** 9 + 7
        self.res = total = 0

        def dfs(node):
            if not node:
                return 0
            left_sum, right_sum = dfs(node.left), dfs(node.right)
            self.res = max(self.res, left_sum * (total - left_sum), right_sum * (total - right_sum))
            return left_sum + right_sum + node.val

        total = dfs(root)
        dfs(root)
        return self.res % mod