"""
Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

Example 1:
Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]

Example 2:
Input: root = [2,1,1]
Output: [[1]]

Example 3:
Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]

Constraints:
The number of the nodes in the tree will be in the range [1, 5000]
-200 <= Node.val <= 200

analysis:
pre-order serialization
TC: O(N)
"""
from collections import defaultdict
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class FindDuplicateSubtrees:
    def findDuplicateSubtrees(self, root: Optional[TreeNode]) -> List[Optional[TreeNode]]:
        res = []
        serial_cnt = defaultdict(int)

        def dfs(cur, serial):
            if not cur:
                return '#'
            left_serial, right_serial = dfs(cur.left, serial), dfs(cur.right, serial)
            serial += ','.join([str(cur.val), left_serial, right_serial])
            serial_cnt[serial] += 1
            if serial_cnt[serial] == 2:
                res.append(cur)
            return serial

        dfs(root, '')
        return res
