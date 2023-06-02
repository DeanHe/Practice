"""
There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.

You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.

A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.

Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.

Example 1:
Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
Output: 3
Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).

Example 2:
Input: colors = "a", edges = [[0,0]]
Output: -1
Explanation: There is a cycle from 0 to 0.

Constraints:
n == colors.length
m == edges.length
1 <= n <= 10^5
0 <= m <= 10^5
colors consists of lowercase English letters.
0 <= aj, bj < n

hints:
1 Use topological sort.
2 let dp[u][c] := the maximum count of vertices with color c of any path starting from vertex u.
"""
from collections import defaultdict, deque
from typing import List


class LargestColorValueInaDirectedGraph:
    def largestPathValue(self, colors: str, edges: List[List[int]]) -> int:
        res = visited = 0
        n = len(colors)
        dp = [[0] * 26 for _ in range(n)]
        in_deg = defaultdict(int)
        graph = defaultdict(list)
        for s, e in edges:
            graph[s].append(e)
            in_deg[e] += 1
        q = deque()
        for i in range(n):
            if in_deg[i] == 0:
                q.append(i)
        while q:
            cur = q.popleft()
            color = ord(colors[cur]) - ord('a')
            print(color)
            dp[cur][color] += 1
            res = max(res, dp[cur][color])
            visited += 1
            for nb in graph[cur]:
                for nb_color in range(26):
                    dp[nb][nb_color] = max(dp[nb][nb_color], dp[cur][nb_color])
                in_deg[nb] -= 1
                if in_deg[nb] == 0:
                    q.append(nb)
        return res if visited == n else -1


