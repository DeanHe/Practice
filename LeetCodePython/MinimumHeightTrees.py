"""
A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Example 1:
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

Example 2:
Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]

Constraints:
1 <= n <= 2 * 10^4
edges.length == n - 1
0 <= ai, bi < n
ai != bi
All the pairs (ai, bi) are distinct.
The given input is guaranteed to be a tree and there will be no repeated edges.

hints:
1 How many MHTs can a graph have at most?

analysis:
BFS
TC: O(N)
"""
from collections import defaultdict, deque
from typing import List


class MinimumHeightTrees:
    def findMinHeightTrees(self, n: int, edges: List[List[int]]) -> List[int]:
        if n == 1:
            return [0]
        res = []
        deg = defaultdict(int)
        graph = defaultdict(list)
        q = deque()
        for a, b in edges:
            graph[a].append(b)
            graph[b].append(a)
            deg[a] += 1
            deg[b] += 1
        # add leaves to queue first
        for i in range(n):
            if deg[i] == 1:
                q.append(i)
        while q:
            res.clear()
            sz = len(q)
            for _ in range(sz):
                cur = q.popleft()
                res.append(cur)
                deg[cur] = 0
                for nb in graph[cur]:
                    if deg[nb] == 0:
                        continue
                    deg[nb] -= 1
                    if deg[nb] == 1:
                        q.append(nb)
        return res


