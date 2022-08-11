"""
You are given an undirected graph defined by an integer n, the number of nodes, and a 2D integer array edges, the edges in the graph, where edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi. You are also given an integer array queries.

Let incident(a, b) be defined as the number of edges that are connected to either node a or b.

The answer to the jth query is the number of pairs of nodes (a, b) that satisfy both of the following conditions:

a < b
incident(a, b) > queries[j]
Return an array answers such that answers.length == queries.length and answers[j] is the answer of the jth query.

Note that there can be multiple edges between the same two nodes.

Example 1:
Input: n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
Output: [6,5]
Explanation: The calculations for incident(a, b) are shown in the table above.
The answers for each of the queries are as follows:
- answers[0] = 6. All the pairs have an incident(a, b) value greater than 2.
- answers[1] = 5. All the pairs except (3, 4) have an incident(a, b) value greater than 3.

Example 2:
Input: n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]], queries = [1,2,3,4,5]
Output: [10,10,9,8,6]


Constraints:
2 <= n <= 2 * 104
1 <= edges.length <= 105
1 <= ui, vi <= n
ui != vi
1 <= queries.length <= 20
0 <= queries[j] < edges.length

hint:
1 We want to count pairs (x,y) such that degree[x] + degree[y] - occurrences(x,y) > k
2 Think about iterating on x, and counting the number of valid y to pair with x.
3 You can consider at first that the (- occurrences(x,y)) isn't there, or it is 0 at first for all y. Count the valid y this way.
4 Then you can iterate on the neighbors of x, let that neighbor be y, and update occurrences(x,y).
5 When you update occurrences(x,y), the left-hand side decreases. Once it reaches k, then y is not valid for x anymore, so you should decrease the answer by 1.

"""
import collections
from typing import List


class CountPairsOfNodes:
    def countPairs(self, n: int, edges: List[List[int]], queries: List[int]) -> List[int]:
        deg, res = [0] * (n + 1), [0] * len(queries)
        common = collections.Counter((min(a, b), max(a, b)) for a, b in edges)
        for a, b in edges:
            deg[a] += 1
            deg[b] += 1
        sorted_deg = sorted(deg)
        for i, limit in enumerate(queries):
            s, e = 1, n
            while s < e:
                if limit < sorted_deg[s] + sorted_deg[e]:
                    res[i] += e - s
                    e -= 1
                else:
                    s += 1
            for (a, b), common_cnt in common.items():
                if deg[a] + deg[b] - common_cnt <= limit < deg[a] + deg[b]:
                    res[i] -= 1
        return res