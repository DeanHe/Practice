"""
Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.

Return the number of pseudo-palindromic paths going from the root node to leaf nodes.

Example 1:
Input: root = [2,3,1,3,1,null,1]
Output: 2
Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).

Example 2:
Input: root = [2,1,1,1,3,null,null,null,null,null,1]
Output: 1
Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).

Example 3:
Input: root = [9]
Output: 1

Constraints:
The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 9

hint:
1 Note that the node values of a path form a palindrome if at most one digit has an odd frequency (parity).
2 Use a Depth First Search (DFS) keeping the frequency (parity) of the digits. Once you are in a leaf node check if at most one digit has an odd frequency (parity).

analysis:
TC: O(N)
SC: O(height of tree)
"""
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class PseudoPalindromicPathsInaBinaryTree:
    def pseudoPalindromicPaths (self, root: Optional[TreeNode]) -> int:
        def dfs(cur, mask):
            if not cur:
                return 0
            mask ^= 1 << (cur.val - 1)
            res = dfs(cur.left, mask) + dfs(cur.right, mask)
            if cur.left is None and cur.right is None: # is leaf
                if mask & (mask - 1) == 0: # check that at most one digit has an odd frequency
                    res += 1
            return res
        return dfs(root, 0)