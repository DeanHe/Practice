"""
You are given an undirected connected graph with n nodes labeled from 0 to n - 1 and a 2D integer array edges where edges[i] = [ui, vi, wi] denotes an undirected edge between node ui and node vi with weight wi, and an integer k.

You are allowed to remove any number of edges from the graph such that the resulting graph has at most k connected components.

The cost of a component is defined as the maximum edge weight in that component. If a component has no edges, its cost is 0.

Return the minimum possible value of the maximum cost among all components after such removals.

Example 1:
Input: n = 5, edges = [[0,1,4],[1,2,3],[1,3,2],[3,4,6]], k = 2
Output: 4

Explanation:
Remove the edge between nodes 3 and 4 (weight 6).
The resulting components have costs of 0 and 4, so the overall maximum cost is 4.

Example 2:
Input: n = 4, edges = [[0,1,5],[1,2,5],[2,3,5]], k = 1
Output: 5

Explanation:
No edge can be removed, since allowing only one component (k = 1) requires the graph to stay fully connected.
That single component’s cost equals its largest edge weight, which is 5.

Constraints:
1 <= n <= 5 * 10^4
0 <= edges.length <= 10^5
edges[i].length == 3
0 <= ui, vi < n
1 <= wi <= 10^6
1 <= k <= n
The input graph is connected.

hints:
1 Sort the edges and do binary search on the candidate maximum weight
2 Use DFS or DSU to count the number of connected components when keeping only edges with weight <= mid
"""
from typing import List


class MinimizeMaximumComponentCost:
    def minCost(self, n: int, edges: List[List[int]], k: int) -> int:
        if n <= k:
            return 0
        edges.sort(key=lambda x: x[2])
        groups = n
        parent = list(range(n))

        def find_root(x):
            root = x
            while parent[root] != root:
                root = parent[root]
            while parent[x] != root:
                fa = parent[x]
                parent[x] = root
                x = fa
            return root

        def union(a, b):
            root_a = find_root(a)
            root_b = find_root(b)
            if root_a == root_b:
                return False
            if root_a < root_b:
                parent[root_b] = parent[root_a]
            else:
                parent[root_a] = parent[root_b]
            return True

        for a, b, w in edges:
            if union(a, b):
                groups -= 1
            if groups <= k:
                return w
        return -1
