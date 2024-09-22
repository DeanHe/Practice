"""
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.



Example 1:
Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Example 2:
Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.

Constraints:
The number of nodes in the tree is n.
2 <= n <= 10^5
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue

hints:
1 The shortest path between any two nodes in a tree must pass through their Lowest Common Ancestor (LCA). The path will travel upwards from node s to the LCA and then downwards from the LCA to node t.
2 Find the path strings from root → s, and root → t. Can you use these two strings to prepare the final answer?
3 Remove the longest common prefix of the two path strings to get the path LCA → s, and LCA → t. Each step in the path of LCA → s should be reversed as 'U'.

analysis:
DFS + LCA
TC: O(N)
"""
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class StepByStepDirectionsFromaBinaryTreeNodeToAnother:
    def getDirections(self, root: Optional[TreeNode], startValue: int, destValue: int) -> str:
        res = []
        self.root_to_start = []
        self.root_to_end = []

        def dfs(node, ls):
            if not node:
                return
            if node.val == startValue:
                self.root_to_start = ls.copy()
            elif node.val == destValue:
                self.root_to_end = ls.copy()
            ls.append('L')
            dfs(node.left, ls)
            ls.pop()
            ls.append('R')
            dfs(node.right, ls)
            ls.pop()

        dfs(root, [])

        # calculate prefix
        lca = 0
        while lca < min(len(self.root_to_start), len(self.root_to_end)):
            if self.root_to_start[lca] == self.root_to_end[lca]:
                lca += 1
            else:
                break

        for j in range(lca, len(self.root_to_start)):
            res.append('U')
        res.extend(self.root_to_end[lca:])
        return ''.join(res)


