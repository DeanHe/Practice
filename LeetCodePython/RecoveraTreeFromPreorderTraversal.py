"""
We run a preorder depth-first search (DFS) on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.

If a node has only one child, that child is guaranteed to be the left child.

Given the output traversal of this traversal, recover the tree and return its root.

Example 1:
Input: traversal = "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]

Example 2:
Input: traversal = "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]

Example 3:
Input: traversal = "1-401--349---90--88"
Output: [1,401,null,349,88,90]

Constraints:
The number of nodes in the original tree is in the range [1, 1000].
1 <= Node.val <= 10^9

hints:
1 Do an iterative depth first search, parsing dashes from the string to inform you how to link the nodes together.

"""
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class RecoveraTreeFromPreorderTraversal:
    def __init__(self):
        self.index = 0

    def recoverFromPreorder(self, traversal: str) -> Optional[TreeNode]:
        return self.dfs(traversal, 0)

    def dfs(self, traversal, depth):
        if self.index >= len(traversal):
            return None
        dash_cnt = 0
        while self.index + dash_cnt < len(traversal) and traversal[self.index + dash_cnt] == '-':
            dash_cnt += 1
        if dash_cnt != depth:
            return None
        self.index += dash_cnt
        value = 0
        while self.index < len(traversal) and traversal[self.index].isdigit():
            value = value * 10 + int(traversal[self.index])
            self.index += 1
        root = TreeNode(value)
        root.left = self.dfs(traversal, depth + 1)
        root.right = self.dfs(traversal, depth + 1)
        return root
