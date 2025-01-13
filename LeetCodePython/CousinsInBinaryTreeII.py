"""
Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Return the root of the modified tree.

Note that the depth of a node is the number of edges in the path from the root node to it.

Example 1:
Input: root = [5,4,9,1,10,null,7]
Output: [0,0,0,7,7,null,11]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 5 does not have any cousins so its sum is 0.
- Node with value 4 does not have any cousins so its sum is 0.
- Node with value 9 does not have any cousins so its sum is 0.
- Node with value 1 has a cousin with value 7 so its sum is 7.
- Node with value 10 has a cousin with value 7 so its sum is 7.
- Node with value 7 has cousins with values 1 and 10 so its sum is 11.

Example 2:
Input: root = [3,1,2]
Output: [0,0,0]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 3 does not have any cousins so its sum is 0.
- Node with value 1 does not have any cousins so its sum is 0.
- Node with value 2 does not have any cousins so its sum is 0.

Constraints:
The number of nodes in the tree is in the range [1, 10^5].
1 <= Node.val <= 10^4

hints:
1 Use DFS two times.
2 For the first time, find the sum of values of all the levels of the binary tree.
3 For the second time, update the value of the node with the sum of the values of the current level - sibling node’s values.
"""
from collections import deque
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class CousinsInBinaryTreeII:
    def replaceValueInTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        root.val = 0
        q = deque([root])
        while q:
            sz = len(q)
            total = 0
            level = []
            for _ in range(sz):
                cur = q.popleft()
                level.append(cur)
                if cur.left:
                    q.append(cur.left)
                    total += cur.left.val
                if cur.right:
                    q.append(cur.right)
                    total += cur.right.val
            for node in level:
                tmp = total
                if node.left:
                    tmp -= node.left.val
                if node.right:
                    tmp -= node.right.val
                if node.left:
                    node.left.val = tmp
                if node.right:
                    node.right.val = tmp
        return root