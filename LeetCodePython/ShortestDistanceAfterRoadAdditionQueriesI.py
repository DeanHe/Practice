"""
You are given an integer n and a 2D integer array queries.

There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.

queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each query, you need to find the length of the shortest path from city 0 to city n - 1.

Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.

Example 1:
Input: n = 5, queries = [[2,4],[0,2],[0,4]]
Output: [3,2,1]

Explanation:
After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.
After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.
After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.

Example 2:
Input: n = 4, queries = [[0,3],[0,2]]
Output: [1,1]

Explanation:
After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.
After the addition of the road from 0 to 2, the length of the shortest path remains 1.

Constraints:
3 <= n <= 500
1 <= queries.length <= 500
queries[i].length == 2
0 <= queries[i][0] < queries[i][1] < n
1 < queries[i][1] - queries[i][0]
There are no repeated roads among the queries.

hints:
1 Maintain the graph and use an efficient shortest path algorithm after each update.
2 We use BFS/Dijkstra for each query.

Analysis:
DP
"""
import math
from collections import defaultdict, deque
from typing import List


class ShortestDistanceAfterRoadAdditionQueriesI:
    def shortestDistanceAfterQueries(self, n: int, queries: List[List[int]]) -> List[int]:
        res = []
        pre_nodes = defaultdict(list)
        dp = list(range(n))
        for start, end in queries:
            pre_nodes[end].append(start)
            for i in range(end, n):
                dp[i] = min(dp[i], dp[i - 1] + 1)
                for pre in pre_nodes[i]:
                    dp[i] = min(dp[i], dp[pre] + 1)
            res.append(dp[n - 1])
        return res

    def shortestDistanceAfterQueriesBFS(self, n: int, queries: List[List[int]]) -> List[int]:
        res = []
        graph = defaultdict(list)
        for i in range(n - 1):
            graph[i].append(i + 1)

        def bfs():
            dist = 0
            q = deque([0])
            visited = {0}
            while q:
                for _ in range(len(q)):
                    cur = q.popleft()
                    if cur == n - 1:
                        return dist
                    for nb in graph[cur]:
                        if nb not in visited:
                            q.append(nb)
                            visited.add(nb)
                dist += 1
            return -1

        for start, end in queries:
            graph[start].append(end)
            res.append(bfs())
        return res
