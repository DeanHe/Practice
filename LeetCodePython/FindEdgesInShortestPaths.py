"""
You are given an undirected weighted graph of n nodes numbered from 0 to n - 1. The graph consists of m edges represented by a 2D array edges, where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and bi with weight wi.

Consider all the shortest paths from node 0 to node n - 1 in the graph. You need to find a boolean array answer where answer[i] is true if the edge edges[i] is part of at least one shortest path. Otherwise, answer[i] is false.

Return the array answer.

Note that the graph may not be connected.

Example 1:
Input: n = 6, edges = [[0,1,4],[0,2,1],[1,3,2],[1,4,3],[1,5,1],[2,3,1],[3,5,3],[4,5,2]]
Output: [true,true,true,false,true,true,true,false]

Explanation:
The following are all the shortest paths between nodes 0 and 5:
The path 0 -> 1 -> 5: The sum of weights is 4 + 1 = 5.
The path 0 -> 2 -> 3 -> 5: The sum of weights is 1 + 1 + 3 = 5.
The path 0 -> 2 -> 3 -> 1 -> 5: The sum of weights is 1 + 1 + 2 + 1 = 5.

Example 2:
Input: n = 4, edges = [[2,0,1],[0,1,1],[0,3,4],[3,2,2]]
Output: [true,false,false,true]
Explanation:
There is one shortest path between nodes 0 and 3, which is the path 0 -> 2 -> 3 with the sum of weights 1 + 2 = 3.

Constraints:
2 <= n <= 5 * 10^4
m == edges.length
1 <= m <= min(5 * 10^4, n * (n - 1) / 2)
0 <= ai, bi < n
ai != bi
1 <= wi <= 10^5
There are no repeated edges.

hints:
1 Find all the shortest paths starting from nodes 0 and n - 1 to all other nodes.
2 How to use the above calculated shortest paths to check if an edge is part of at least one shortest path from 0 to n - 1?

analysis:
TC O(E + V + ElogV)
"""
import heapq
from collections import defaultdict
from typing import List


class FindEdgesInShortestPaths:
    def findAnswer(self, n: int, edges: List[List[int]]) -> List[bool]:
        res = []
        graph = defaultdict(list)
        for a, b, cost in edges:
            graph[a].append((cost, b))
            graph[b].append((cost, a))

        def dijkstra(start):
            dist = {}
            pq = [(0, start)]
            while pq:
                d, cur = heapq.heappop(pq)
                if cur not in dist:
                    dist[cur] = d
                for cost, nb in graph[cur]:
                    if nb not in dist:
                        heapq.heappush(pq, (d + cost, nb))
            return dist

        dist_first = dijkstra(0)
        dist_last = dijkstra(n - 1)
        if n - 1 not in dist_first:
            return [False] * len(edges)
        shortest_dist = dist_first[n - 1]
        for a, b, cost in edges:
            if dist_first[a] + cost + dist_last[b] == shortest_dist or dist_first[b] + cost + dist_last[a] == shortest_dist:
                res.append(True)
            else:
                res.append(False)
        return res


