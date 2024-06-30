"""
Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

Example 1:
Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.

Example 2:
Input: root = [2,1,3]
Output: [2,1,3]

Constraints:
The number of nodes in the tree is in the range [1, 10^4].
1 <= Node.val <= 10^5

hints:
1 Convert the tree to a sorted array using an in-order traversal.
2 Construct a new balanced tree from the sorted array recursively.
"""
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class BalanceaBinarySearchTree:
    def balanceBST(self, root: TreeNode) -> TreeNode:
        ls = []
        def in_order_traverse(node):
            if not node:
                return
            in_order_traverse(node.left)
            ls.append(node.val)
            in_order_traverse(node.right)

        def dfs(start, end):
            if start > end:
                return
            mid = (start + end) // 2
            cur = TreeNode(ls[mid])
            cur.left = dfs(start, mid - 1)
            cur.right = dfs(mid + 1, end)
            return cur

        in_order_traverse(root)
        return dfs(0, len(ls) - 1)