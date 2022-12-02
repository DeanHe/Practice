"""
You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.

You have to perform m independent queries on the tree where in the ith query you do the following:

Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.

Note:

The queries are independent, so the tree returns to its initial state after each query.
The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.


Example 1:


Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
Output: [2]
Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
The height of the tree is 2 (The path 1 -> 3 -> 2).
Example 2:


Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
Output: [3,2,3,2]
Explanation: We have the following queries:
- Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
- Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
- Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
- Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).


Constraints:
The number of nodes in the tree is n.
2 <= n <= 10^5
1 <= Node.val <= n
All the values in the tree are unique.
m == queries.length
1 <= m <= min(n, 10^4)
1 <= queries[i] <= n
queries[i] != root.val

hints:
1 Try pre-computing the answer for each node from 1 to n, and answer each query in O(1).
2 The answers can be precomputed in a single tree traversal after computing the height of each subtree.

analysis:
TC: O(N)
SC: O(N)
"""
import collections
from functools import lru_cache
from typing import Optional, List

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class HeightOfBinaryTreeAfterSubtreeRemovalQueries:
    def treeQueries(self, root: Optional[TreeNode], queries: List[int]) -> List[int]:
        depths, heights, siblings = collections.defaultdict(int), collections.defaultdict(int), collections.defaultdict(list)
        res = []

        def dfs(node, d):
            if not node:
                return -1
            depths[node.val] = d
            h = max(dfs(node.left, d + 1), dfs(node.right, d + 1)) + 1
            heights[node.val] = h
            return h

        dfs(root, 0)
        for n, depth in depths.items():
            siblings[depth].append((-heights[n], n))
            siblings[depth].sort()
            # keep only 2 candidates
            if len(siblings[depth]) > 2:
                siblings[depth].pop()
        for q in queries:
            depth = depths[q]
            if len(siblings[depth]) == 1:
                res.append(depth - 1)
                # q is the longest
            elif siblings[depth][0][1] == q:
                res.append(-siblings[depth][1][0] + depth)
            else:
                res.append(-siblings[depth][0][0] + depth)
        return res


    def treeQueries2(self, root: Optional[TreeNode], queries: List[int]) -> List[int]:
        res = {}

        def height(node):
            return 1 + max(height(node.left), height(node.right)) if node else 0

        def dfs(node, depth, max_height_existed):
            if not node:
                return
            res[node.val] = max_height_existed
            dfs(node.left, depth + 1, max(max_height_existed, depth + height(node.right)))
            dfs(node.right, depth + 1, max(max_height_existed, depth + height(node.left)))

        dfs(root, 0, 0)
        return [res[q] for q in queries]