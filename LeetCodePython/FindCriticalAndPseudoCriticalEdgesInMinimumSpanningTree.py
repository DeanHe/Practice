"""
Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the minimum possible total edge weight.

Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST). An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.

Note that you can return the indices of the edges in any order.

Example 1:
Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
Output: [[0,1],[2,3,4,5]]
Explanation: The figure above describes the graph.
The following figure shows all the possible MSTs:
Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.

Example 2:
Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
Output: [[],[0,1,2,3]]
Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.

Constraints:
2 <= n <= 100
1 <= edges.length <= min(200, n * (n - 1) / 2)
edges[i].length == 3
0 <= ai < bi < n
1 <= weighti <= 1000
All pairs (ai, bi) are distinct.

hints:
1 Use the Kruskal algorithm to find the minimum spanning tree by sorting the edges and picking edges from ones with smaller weights.
2 Use a disjoint set to avoid adding redundant edges that result in a cycle.
3 To find if one edge is critical, delete that edge and re-run the MST algorithm and see if the weight of the new MST increases.
4 To find if one edge is non-critical (in any MST), include that edge to the accepted edge list and continue the MST algorithm, then see if the resulting MST has the same weight of the initial MST of the entire graph.
"""
from typing import List


class FindCriticalAndPseudoCriticalEdgesInMinimumSpanningTree:
    def findCriticalAndPseudoCriticalEdges(self, n: int, edges: List[List[int]]) -> List[List[int]]:
        critical, pseudo_critical = [], []
        new_edges = [e.copy() for e in edges]
        for i, e in enumerate(new_edges):
            e.append(i)
        new_edges.sort(key=lambda x: x[2])

        def buildMST(pick, skip):
            uf = UnionFind(n)
            cost = 0
            if pick:
                uf.union(pick[0], pick[1])
                cost += pick[2]
            for e in new_edges:
                if e != skip:
                    if uf.union(e[0], e[1]):
                        cost += e[2]
            return cost if uf.cnt == 1 else float('inf')

        min_cost = buildMST(None, None)
        for e in new_edges:
            cost_skip = buildMST(None, e)
            if min_cost < cost_skip:
                critical.append(e[3])
            else:
                cost_pick = buildMST(e, None)
                if cost_pick == min_cost:
                    pseudo_critical.append(e[3])
        return [critical, pseudo_critical]


class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        self.cnt = n

    def findRoot(self, x):
        root = x
        while self.parent[root] != root:
            root = self.parent[root]
        while self.parent[x] != root:
            fa = self.parent[x]
            self.parent[x] = root
            x = fa
        return root

    def union(self, a, b):
        root_a, root_b = self.findRoot(a), self.findRoot(b)
        if root_a != root_b:
            self.cnt -= 1
            self.parent[root_a] = root_b
            return True
        return False
