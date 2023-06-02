"""
You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.

Return the number of complete connected components of the graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

A connected component is said to be complete if there exists an edge between every pair of its vertices.

Example 1:
Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output: 3
Explanation: From the picture above, one can see that all of the components of this graph are complete.

Example 2:
Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
Output: 1
Explanation: The component containing vertices 0, 1, and 2 is complete since there is an edge between every pair of two vertices. On the other hand, the component containing vertices 3, 4, and 5 is not complete since there is no edge between vertices 4 and 5. Thus, the number of complete components in this graph is 1.

Constraints:
1 <= n <= 50
0 <= edges.length <= n * (n - 1) / 2
edges[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
There are no repeated edges.

hints:
1 Find the connected components of an undirected graph using depth-first search (DFS) or breadth-first search (BFS).
2 For each connected component, count the number of nodes and edges in the component.
3 A connected component is complete if and only if the number of edges in the component is equal to m*(m-1)/2, where m is the number of nodes in the component.
"""
from typing import List


class CountTheNumberOfCompleteComponents:
    def countCompleteComponents(self, n: int, edges: List[List[int]]) -> int:
        res = 0
        parent = list(range(n))
        edge_cnt = [0] * n
        node_cnt = [0] * n

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
            root_a, root_b = find_root(a), find_root(b)
            edge_cnt[root_b] += 1
            if root_a != root_b:
                parent[root_a] = parent[root_b]
                node_cnt[root_b] += node_cnt[root_a]
                edge_cnt[root_b] += edge_cnt[root_a]

        for a, b in edges:
            union(a, b)
        for i in range(n):
            if parent[i] == i and node_cnt[i] > 0 and edge_cnt[i] == node_cnt[i] * (node_cnt[i] - 1) / 2:
                res += 1
        return res