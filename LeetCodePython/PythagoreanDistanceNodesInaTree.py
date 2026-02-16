"""
You are given an integer n and an undirected tree with n nodes numbered from 0 to n - 1. The tree is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi] indicates an undirected edge between ui and vi.

You are also given three distinct target nodes x, y, and z.

For any node u in the tree:

Let dx be the distance from u to node x
Let dy be the distance from u to node y
Let dz be the distance from u to node z
The node u is called special if the three distances form a Pythagorean Triplet.

Return an integer denoting the number of special nodes in the tree.

A Pythagorean triplet consists of three integers a, b, and c which, when sorted in ascending order, satisfy a2 + b2 = c2.

The distance between two nodes in a tree is the number of edges on the unique path between them.



Example 1:

Input: n = 4, edges = [[0,1],[0,2],[0,3]], x = 1, y = 2, z = 3

Output: 3

Explanation:

For each node, we compute its distances to nodes x = 1, y = 2, and z = 3.

Node 0 has distances 1, 1, and 1. After sorting, the distances are 1, 1, and 1, which do not satisfy the Pythagorean condition.
Node 1 has distances 0, 2, and 2. After sorting, the distances are 0, 2, and 2. Since 02 + 22 = 22, node 1 is special.
Node 2 has distances 2, 0, and 2. After sorting, the distances are 0, 2, and 2. Since 02 + 22 = 22, node 2 is special.
Node 3 has distances 2, 2, and 0. After sorting, the distances are 0, 2, and 2. This also satisfies the Pythagorean condition.
Therefore, nodes 1, 2, and 3 are special, and the answer is 3.

Example 2:

Input: n = 4, edges = [[0,1],[1,2],[2,3]], x = 0, y = 3, z = 2

Output: 0

Explanation:

For each node, we compute its distances to nodes x = 0, y = 3, and z = 2.

Node 0 has distances 0, 3, and 2. After sorting, the distances are 0, 2, and 3, which do not satisfy the Pythagorean condition.
Node 1 has distances 1, 2, and 1. After sorting, the distances are 1, 1, and 2, which do not satisfy the Pythagorean condition.
Node 2 has distances 2, 1, and 0. After sorting, the distances are 0, 1, and 2, which do not satisfy the Pythagorean condition.
Node 3 has distances 3, 0, and 1. After sorting, the distances are 0, 1, and 3, which do not satisfy the Pythagorean condition.
No node satisfies the Pythagorean condition. Therefore, the answer is 0.

Example 3:

Input: n = 4, edges = [[0,1],[1,2],[1,3]], x = 1, y = 3, z = 0

Output: 1

Explanation:

For each node, we compute its distances to nodes x = 1, y = 3, and z = 0.

Node 0 has distances 1, 2, and 0. After sorting, the distances are 0, 1, and 2, which do not satisfy the Pythagorean condition.
Node 1 has distances 0, 1, and 1. After sorting, the distances are 0, 1, and 1. Since 02 + 12 = 12, node 1 is special.
Node 2 has distances 1, 2, and 2. After sorting, the distances are 1, 2, and 2, which do not satisfy the Pythagorean condition.
Node 3 has distances 1, 0, and 2. After sorting, the distances are 0, 1, and 2, which do not satisfy the Pythagorean condition.
Therefore, the answer is 1.

Constraints:
4 <= n <= 10^5
edges.length == n - 1
edges[i] = [ui, vi]
0 <= ui, vi, x, y, z <= n - 1
x, y, and z are pairwise distinct.
The input is generated such that edges represent a valid tree.

hints:
1 Run a breadth-first search independently from x, y, and z. This gives you three distance arrays with full coverage.
2 For each node, collect its three distances, sort them, and evaluate the Pythagorean condition on the sorted value.
"""
from collections import defaultdict, deque
from typing import List


class PythagoreanDistanceNodesInaTree:
    def specialNodes(self, n: int, edges: List[List[int]], x: int, y: int, z: int) -> int:
        res = 0
        graph = defaultdict(list)
        for a, b in edges:
            graph[a].append(b)
            graph[b].append(a)

        def bfs(src):
            dist = [-1] * n
            q = deque([src])
            d = 0
            while q:
                sz = len(q)
                for _ in range(sz):
                    cur = q.popleft()
                    dist[cur] = d
                    for nb in graph[cur]:
                        if dist[nb] == -1:
                            q.append(nb)
                d += 1
            return dist

        dist_x = bfs(x)
        dist_y = bfs(y)
        dist_z = bfs(z)
        for i in range(n):
            most = max(dist_x[i], dist_y[i], dist_z[i])
            if dist_x[i] * dist_x[i] + dist_y[i] * dist_y[i] + dist_z[i] * dist_z[i] == most * most:
                res += 1
        return res
