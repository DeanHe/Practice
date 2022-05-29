"""
You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.
Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.


Example 1:
Input: graph = [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]

Example 2:
Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]

Constraints:
n == graph.length
1 <= n <= 12
0 <= graph[i].length < n
graph[i] does not contain i.
If graph[a] contains b, then graph[b] contains a.
The input graph is always connected.

analysis:
bfs
DP dist[i][state] means the shortest steps to reach state with last node in i
TC: O(N * 2^N)
"""
import collections
from typing import List


class ShortestPathVisitingAllNodes:
    def shortestPathLength(self, graph: List[List[int]]) -> int:
        n = len(graph)
        dist = [[-1] * (1 << n) for _ in range(n)]
        q = collections.deque()
        for i in range(0, n):
            q.append((i, 1 << i))
            dist[i][1 << i] = 0
        while q:
            node, state = q.popleft()
            d = dist[node][state]
            if state == (1 << n) - 1:
                return d
            for nb in graph[node]:
                nb_state = state | 1 << nb
                if dist[nb][nb_state] == -1 or d + 1 < dist[nb][nb_state]:
                    dist[nb][nb_state] = d + 1
                    q.append((nb, nb_state))