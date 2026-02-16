"""
You are given a directed, weighted graph with n nodes labeled from 0 to n - 1, and an array edges where edges[i] = [ui, vi, wi] represents a directed edge from node ui to node vi with cost wi.

Each node ui has a switch that can be used at most once: when you arrive at ui and have not yet used its switch, you may activate it on one of its incoming edges vi → ui reverse that edge to ui → vi and immediately traverse it.

The reversal is only valid for that single move, and using a reversed edge costs 2 * wi.

Return the minimum total cost to travel from node 0 to node n - 1. If it is not possible, return -1.

Example 1:
Input: n = 4, edges = [[0,1,3],[3,1,1],[2,3,4],[0,2,2]]
Output: 5

Explanation:
Use the path 0 → 1 (cost 3).
At node 1 reverse the original edge 3 → 1 into 1 → 3 and traverse it at cost 2 * 1 = 2.
Total cost is 3 + 2 = 5.

Example 2:
Input: n = 4, edges = [[0,2,1],[2,1,1],[1,3,1],[2,3,3]]
Output: 3

Explanation:
No reversal is needed. Take the path 0 → 2 (cost 1), then 2 → 1 (cost 1), then 1 → 3 (cost 1).
Total cost is 1 + 1 + 1 = 3.

Constraints:
2 <= n <= 5 * 10^4
1 <= edges.length <= 10^5
edges[i] = [ui, vi, wi]
0 <= ui, vi <= n - 1
1 <= wi <= 1000

hints:
1 Do we only need to reverse at most one edge for each node? If so, can we add reversed edges for each node and use the one that helps in the shortest path?
2 Add reverse edges: {u, v, w} -> {v, u, 2 * w}, and use Dijkstra.

analysis:
Let N be the number of vertices and M be the number of edges.
TC: O(N+MlogM)
"""
import heapq
from collections import defaultdict
from typing import List


class MinimumCostPathWithEdgeReversals:
    def minCost(self, n: int, edges: List[List[int]]) -> int:
        dist = {}
        graph = defaultdict(list)
        for a, b, w in edges:
            graph[a].append((b, w))
            graph[b].append((a, 2 * w))
        pq = [(0, 0)]
        while pq:
            d, cur = heapq.heappop(pq)
            if cur not in dist:
                if cur == n - 1:
                    return d
                dist[cur] = d
                for nb, cost in graph[cur]:
                    if nb not in dist:
                        heapq.heappush(pq, (d + cost, nb))
        return -1


