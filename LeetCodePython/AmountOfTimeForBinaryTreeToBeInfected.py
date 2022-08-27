"""
You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

Each minute, a node becomes infected if:

The node is currently uninfected.
The node is adjacent to an infected node.
Return the number of minutes needed for the entire tree to be infected.

Example 1:
Input: root = [1,5,3,null,4,10,6,9,2], start = 3
Output: 4
Explanation: The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.

Example 2:
Input: root = [1], start = 1
Output: 0
Explanation: At minute 0, the only node in the tree is infected so we return 0.


Constraints:
The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 105
Each node has a unique value.
A node with a value of start exists in the tree.

hint:
1 Convert the tree to an undirected graph to make it easier to handle.
2 Use BFS starting at the start node to find the distance between each node and the start node. The answer is the maximum distance.
"""

# Definition for a binary tree node.
import collections
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class AmountOfTimeForBinaryTreeToBeInfected:
    def amountOfTime(self, root: Optional[TreeNode], start: int) -> int:
        graph = collections.defaultdict(list)

        def dfs(pre, cur):
            if pre:
                graph[cur.val].append(pre.val)
            if cur.left:
                graph[cur.val].append(cur.left.val)
                dfs(cur, cur.left)
            if cur.right:
                graph[cur.val].append(cur.right.val)
                dfs(cur, cur.right)

        dfs(None, root)
        q = collections.deque([start])
        res = -1
        visited = set([start])
        while q:
            sz = len(q)
            res += 1
            for _ in range(sz):
                cur = q.popleft()
                for nb in graph[cur]:
                    if nb not in visited:
                        q.append(nb)
                        visited.add(nb)
        return res

