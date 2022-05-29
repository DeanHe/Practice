"""
Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.
You can return the answer in any order.

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Example 2:
Input: root = [1], target = 1, k = 3
Output: []


Constraints:
The number of nodes in the tree is in the range [1, 500].
0 <= Node.val <= 500
All the values Node.val are unique.
target is the value of one of the nodes in the tree.
0 <= k <= 1000
"""
import collections
from typing import List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class AllNodesDistanceKinBinaryTree:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        if k == 0:
            return [target.val]
        step, res = 0, []
        graph, q, visited = collections.defaultdict(list), collections.deque(), set()

        def dfs(cur, parent):
            if not cur:
                return
            if parent:
                graph[cur].append(parent)
            if cur.left:
                graph[cur].append(cur.left)
            if cur.right:
                graph[cur].append(cur.right)
            dfs(cur.left, cur)
            dfs(cur.right, cur)

        dfs(root, None)
        q.append(target)
        visited.add(target)
        while q:
            sz = len(q)
            for _ in range(sz):
                cur = q.popleft()
                for nb in graph[cur]:
                    if nb not in visited:
                        q.append(nb)
                        visited.add(nb)
            step += 1
            if step == k:
                while q:
                    res.append(q.popleft().val)
                return res
        return res
