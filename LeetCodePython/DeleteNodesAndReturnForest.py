"""
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.

Example 1:
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]

Example 2:
Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]

Constraints:
The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.

analysis:
DFS post order traverse
TC: O(N)
"""
from collections import deque
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class DeleteNodesAndReturnForest:
    def delNodes(self, root: Optional[TreeNode], to_delete: List[int]) -> List[TreeNode]:
        res = []
        if not root:
            return res
        to_delete = set(to_delete)

        def dfs(cur, is_root):
            if not cur:
                return None
            need_to_delete = cur.val in to_delete
            if is_root and not need_to_delete:
                res.append(cur)
            cur.left = dfs(cur.left, need_to_delete)
            cur.right = dfs(cur.right, need_to_delete)
            return None if need_to_delete else cur

        dfs(root, True)
        return res

    def delNodesBFS(self, root: Optional[TreeNode], to_delete: List[int]) -> List[TreeNode]:
        res = []
        if not root:
            return res
        to_delete = set(to_delete)

        q = deque([root])
        while q:
            cur = q.popleft()
            if cur.left:
                q.append(cur.left)
                if cur.left.val in to_delete:
                   cur.left = None
            if cur.right:
                q.append(cur.right)
                if cur.right.val in to_delete:
                    cur.right = None
            if cur.val in to_delete:
                if cur.left:
                    res.append(cur.left)
                if cur.right:
                    res.append(cur.right)
        if root.val not in to_delete:
            res.append(root)
        return res


