"""
There is an undirected weighted graph with n vertices labeled from 0 to n - 1.

You are given the integer n and an array edges, where edges[i] = [ui, vi, wi] indicates that there is an edge between vertices ui and vi with a weight of wi.

A walk on a graph is a sequence of vertices and edges. The walk starts and ends with a vertex, and each edge connects the vertex that comes before it and the vertex that comes after it. It's important to note that a walk may visit the same edge or vertex more than once.

The cost of a walk starting at node u and ending at node v is defined as the bitwise AND of the weights of the edges traversed during the walk. In other words, if the sequence of edge weights encountered during the walk is w0, w1, w2, ..., wk, then the cost is calculated as w0 & w1 & w2 & ... & wk, where & denotes the bitwise AND operator.

You are also given a 2D array query, where query[i] = [si, ti]. For each query, you need to find the minimum cost of the walk starting at vertex si and ending at vertex ti. If there exists no such walk, the answer is -1.

Return the array answer, where answer[i] denotes the minimum cost of a walk for query i.

Example 1:
Input: n = 5, edges = [[0,1,7],[1,3,7],[1,2,1]], query = [[0,3],[3,4]]
Output: [1,-1]

Explanation:
To achieve the cost of 1 in the first query, we need to move on the following edges: 0->1 (weight 7), 1->2 (weight 1), 2->1 (weight 1), 1->3 (weight 7).
In the second query, there is no walk between nodes 3 and 4, so the answer is -1.

Example 2:
Input: n = 3, edges = [[0,2,7],[0,1,15],[1,2,6],[1,2,1]], query = [[1,2]]
Output: [0]

Explanation:
To achieve the cost of 0 in the first query, we need to move on the following edges: 1->2 (weight 1), 2->1 (weight 6), 1->2 (weight 1).

Constraints:
1 <= n <= 10^5
0 <= edges.length <= 10^5
edges[i].length == 3
0 <= ui, vi <= n - 1
ui != vi
0 <= wi <= 10^5
1 <= query.length <= 10^5
query[i].length == 2
0 <= si, ti <= n - 1

hints:
1 The intended solution uses Disjoint Set Union.
2 Notice that, if u and v are not connected then the answer is -1, otherwise we can use all the edges from the connected component where both belong to.

analysis:
Union Find
Within each group, traversing any two nodes results in the same minimum cost as we traverse all edges in the group.
Consequently, for each group of interconnected vertices, the minimum cost remains constant, equivalent to the AND operation of all connecting edges within the group.

TC: O(N)
"""
from typing import List


class MinimumCostWalkInWeightedGraph:
    def minimumCost(self, n: int, edges: List[List[int]], query: List[List[int]]) -> List[int]:
        parent = list(range(n))
        size = [1] * n
        min_cost = [(1 << 17) - 1] * n
        res = []

        def find_root(x):
            root = x
            while parent[root] != root:
                root = parent[root]
            while parent[x] != root:
                fa = parent[x]
                parent[x] = root
                x = fa
            return root
        def union(a, b, cost):
            root_a =find_root(a)
            root_b = find_root(b)
            if size[root_a] < size[root_b]:
                parent[root_a] = root_b
                size[root_b] += size[root_a]
                size[root_a] = 0
            else:
                parent[root_b] = root_a
                size[root_a] += size[root_b]
                size[root_b] = 0
            min_cost[root_a] = min_cost[root_b] = min_cost[root_a] & min_cost[root_b] & cost

        for a, b, cost in edges:
            union(a, b, cost)
        for a, b in query:
            if a == b:
                res.append(0)
            else:
                root_a = find_root(a)
                root_b = find_root(b)
                if root_a != root_b:
                    res.append(-1)
                else:
                    res.append(min_cost[root_a])
        return res
