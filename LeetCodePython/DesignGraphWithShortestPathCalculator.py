"""
There is a directed weighted graph that consists of n nodes numbered from 0 to n - 1. The edges of the graph are initially represented by the given array edges where edges[i] = [fromi, toi, edgeCosti] meaning that there is an edge from fromi to toi with the cost edgeCosti.

Implement the Graph class:

Graph(int n, int[][] edges) initializes the object with n nodes and the given edges.
addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to, edgeCost]. It is guaranteed that there is no edge between the two nodes before adding this one.
int shortestPath(int node1, int node2) returns the minimum cost of a path from node1 to node2. If no path exists, return -1. The cost of a path is the sum of the costs of the edges in the path.


Example 1:
Input
["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
[[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
Output
[null, 6, -1, null, 6]

Explanation
Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
g.shortestPath(3, 2); // return 6. The shortest path from 3 to 2 in the first diagram above is 3 -> 0 -> 1 -> 2 with a total cost of 3 + 2 + 1 = 6.
g.shortestPath(0, 3); // return -1. There is no path from 0 to 3.
g.addEdge([1, 3, 4]); // We add an edge from node 1 to node 3, and we get the second diagram above.
g.shortestPath(0, 3); // return 6. The shortest path from 0 to 3 now is 0 -> 1 -> 3 with a total cost of 2 + 4 = 6.


Constraints:
1 <= n <= 100
0 <= edges.length <= n * (n - 1)
edges[i].length == edge.length == 3
0 <= fromi, toi, from, to, node1, node2 <= n - 1
1 <= edgeCosti, edgeCost <= 10^6
There are no repeated edges and no self-loops in the graph at any point.
At most 100 calls will be made for addEdge.
At most 100 calls will be made for shortestPath.

hints:
1 After adding each edge, update your graph with the new edge, and you can calculate the shortest path in your graph each time the shortestPath method is called.
2 Use dijkstra’s algorithm to calculate the shortest paths.

anlaysis:
1 Dijkstra's Algorithm
TC: O(N + M(V + E * logV))

2 Floyd-Warshall Algorithm
TC: O(V^3 + N*V^2 + M)
"""
import heapq
from math import inf
from typing import List

class DijkstraGraph:

    def __init__(self, n: int, edges: List[List[int]]):
        self.graph = [[] for _ in range(n)]
        for s, e, cost in edges:
            self.graph[s].append((e, cost))

    def addEdge(self, edge: List[int]) -> None:
        s, e, cost = edge
        self.graph[s].append((e, cost))

    def shortestPath(self, node1: int, node2: int) -> int:
        dist = {}
        pq = [(0, node1)]
        while pq:
            d, cur = heapq.heappop(pq)
            if cur == node2:
                return d
            if cur not in dist:
                dist[cur] = d
                for nb, cost in self.graph[cur]:
                    if nb not in dist:
                        heapq.heappush(pq, (d + cost, nb))
        return -1

class FloydGraph:

    def __init__(self, n: int, edges: List[List[int]]):
        self.graph = [[inf] * n for _ in range(n)]
        for s, e, cost in edges:
            self.graph[s][e] = cost
        for i in range(n):
            self.graph[i][i] = 0
        for k in range(n):
            for i in range(n):
                for j in range(n):
                    self.graph[i][j] = min(self.graph[i][j], self.graph[i][k] + self.graph[k][j])

    def addEdge(self, edge: List[int]) -> None:
        s, e, cost = edge
        n = len(self.graph)
        for i in range(n):
            for j in range(n):
                self.graph[i][j] = min(self.graph[i][j], self.graph[i][s] + cost + self.graph[e][j])

    def shortestPath(self, node1: int, node2: int) -> int:
        if self.graph[node1][node2] == inf:
            return -1
        return self.graph[node1][node2]



# Your Graph object will be instantiated and called as such:
# obj = Graph(n, edges)
# obj.addEdge(edge)
# param_2 = obj.shortestPath(node1,node2)