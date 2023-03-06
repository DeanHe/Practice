"""
You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays redEdges and blueEdges where:

redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.

Example 1:
Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
Output: [0,1,-1]

Example 2:
Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
Output: [0,1,-1]

Constraints:
1 <= n <= 100
0 <= redEdges.length, blueEdges.length <= 400
redEdges[i].length == blueEdges[j].length == 2
0 <= ai, bi, uj, vj < n

analysis:
BFS
TC: O(N + Edges)
SC: O(N + E)
"""
from collections import defaultdict, deque
from typing import List


class ShortestPathWithAlternatingColors:
    def shortestAlternatingPaths(self, n: int, redEdges: List[List[int]], blueEdges: List[List[int]]) -> List[int]:
        res = [-1] * n
        visited = [[False] * 2 for _ in range(n)]
        graph = defaultdict(list)
        for a, b in redEdges:
            graph[a].append((b, 0))
        for a, b in blueEdges:
            graph[a].append((b, 1))
        # cur: steps: color
        q = deque([(0, 0, -1)])
        res[0] = 0
        visited[0][0] = visited[0][1] = True
        while q:
            cur, steps, color = q.popleft()
            if cur in graph:
                for nb, nb_color in graph[cur]:
                    if not visited[nb][nb_color] and nb_color != color:
                        if res[nb] == -1:
                            res[nb] = steps + 1
                        visited[nb][nb_color] = True
                        q.append((nb, steps + 1, nb_color))
        return res

