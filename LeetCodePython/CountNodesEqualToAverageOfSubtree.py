"""
Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

Note:
The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
A subtree of root is a tree consisting of root and all of its descendants.

Example 1:
Input: root = [4,8,5,0,1,null,6]
Output: 5
Explanation:
For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
For the node with value 0: The average of its subtree is 0 / 1 = 0.
For the node with value 1: The average of its subtree is 1 / 1 = 1.
For the node with value 6: The average of its subtree is 6 / 1 = 6.

Example 2:
Input: root = [1]
Output: 1
Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.

Constraints:
The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 1000

hints:
1 What information do we need to calculate the average? We need the sum of the values and the number of values.
2 Create a recursive function that returns the size of a node’s subtree, and the sum of the values of its subtree.
"""
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class CountNodesEqualToAverageOfSubtree:
    def averageOfSubtree(self, root: Optional[TreeNode]) -> int:
        self.res = 0

        def dfs(cur):
            if not cur:
                return 0, 0
            l_sum, l_cnt = dfs(cur.left)
            r_sum, r_cnt = dfs(cur.right)
            total = cur.val + l_sum + r_sum
            cnt = 1 + l_cnt + r_cnt
            if cur.val == total // cnt:
                self.res += 1
            return total, cnt

        dfs(root)
        return self.res