"""
You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.

The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.

You are also given two integers node1 and node2.

Return the index of the node that can be reached from both node1 and node2, such that the maximum between the distance from node1 to that node, and from node2 to that node is minimized. If there are multiple answers, return the node with the smallest index, and if no possible answer exists, return -1.

Note that edges may contain cycles.

Example 1:
Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
Output: 2
Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.

Example 2:
Input: edges = [1,2,-1], node1 = 0, node2 = 2
Output: 2
Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.

Constraints:
n == edges.length
2 <= n <= 105
-1 <= edges[i] < n
edges[i] != i
0 <= node1, node2 < n

hint:
1 How can you find the shortest distance from one node to all nodes in the graph?
2 Use BFS to find the shortest distance from both node1 and node2 to all nodes in the graph.
Then iterate over all nodes, and find the node with the minimum max distance.
"""
import collections
from typing import List


class FindClosestNodeToGivenTwoNodes:
    def closestMeetingNode(self, edges: List[int], node1: int, node2: int) -> int:
        n = len(edges)
        dist1 = [float('inf')] * n
        dist2 = [float('inf')] * n

        def bfs(src, dist):
            q = collections.deque()
            q.append(src)
            dist[src] = 0
            while q:
                size = len(q)
                for _ in range(size):
                    cur = q.popleft()
                    nxt = edges[cur]
                    if nxt != -1 and dist[nxt] == float('inf'):
                        q.append(nxt)
                        dist[nxt] = dist[cur] + 1

        bfs(node1, dist1)
        bfs(node2, dist2)

        res, max_dist = -1, float('inf')
        for i in range(n):
            if dist1[i] == float('inf') or dist2[i] == float('inf'):
                continue
            if max_dist > max(dist1[i], dist2[i]):
                max_dist = max(dist1[i], dist2[i])
                res = i
        return res
