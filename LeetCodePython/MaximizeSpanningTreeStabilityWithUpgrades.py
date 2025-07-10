"""
You are given an integer n, representing n nodes numbered from 0 to n - 1 and a list of edges, where edges[i] = [ui, vi, si, musti]:

ui and vi indicates an undirected edge between nodes ui and vi.
si is the strength of the edge.
musti is an integer (0 or 1). If musti == 1, the edge must be included in the spanning tree. These edges cannot be upgraded.
You are also given an integer k, the maximum number of upgrades you can perform. Each upgrade doubles the strength of an edge, and each eligible edge (with musti == 0) can be upgraded at most once.

The stability of a spanning tree is defined as the minimum strength score among all edges included in it.

Return the maximum possible stability of any valid spanning tree. If it is impossible to connect all nodes, return -1.

Note: A spanning tree of a graph with n nodes is a subset of the edges that connects all nodes together (i.e. the graph is connected) without forming any cycles, and uses exactly n - 1 edges.

Example 1:
Input: n = 3, edges = [[0,1,2,1],[1,2,3,0]], k = 1
Output: 2

Explanation:
Edge [0,1] with strength = 2 must be included in the spanning tree.
Edge [1,2] is optional and can be upgraded from 3 to 6 using one upgrade.
The resulting spanning tree includes these two edges with strengths 2 and 6.
The minimum strength in the spanning tree is 2, which is the maximum possible stability.

Example 2:
Input: n = 3, edges = [[0,1,4,0],[1,2,3,0],[0,2,1,0]], k = 2
Output: 6

Explanation:
Since all edges are optional and up to k = 2 upgrades are allowed.
Upgrade edges [0,1] from 4 to 8 and [1,2] from 3 to 6.
The resulting spanning tree includes these two edges with strengths 8 and 6.
The minimum strength in the tree is 6, which is the maximum possible stability.

Example 3:
Input: n = 3, edges = [[0,1,1,1],[1,2,1,1],[2,0,1,1]], k = 0
Output: -1
Explanation:
All edges are mandatory and form a cycle, which violates the spanning tree property of acyclicity. Thus, the answer is -1.

Constraints:
2 <= n <= 10^5
1 <= edges.length <= 10^5
edges[i] = [ui, vi, si, musti]
0 <= ui, vi < n
ui != vi
1 <= si <= 10^5
musti is either 0 or 1.
0 <= k <= n
There are no duplicate edges.

hints:
1 Sort the edges array in descending order of weights.
2 Try using binary search on ans.
3 Implement a chk function which first adds all the edges with must = 1, and then adds the edges with must = 0, using any remaining upgrades greedily.
4 Use a DSU with path compression and union by size/rank to maintain connected components.
5 Don't forget the case where you cannot form an MST because more than one component remains after processing all edges.
"""
import math
from typing import List


class MaximizeSpanningTreeStabilityWithUpgrades:
    def maxStability(self, n: int, edges: List[List[int]], k: int) -> int:
        initial_uf = UnionFind(n)
        must_min_w = math.inf
        for a, b, w, must in edges:
            if must == 1:
                must_min_w = min(must_min_w, w)
                if not initial_uf.union(a, b):
                    return -1

        def can_form_mst(target):
            if must_min_w < target:
                return False
            uf = UnionFind(n)
            uf.parent = initial_uf.parent[:]
            uf.size = initial_uf.size[:]
            to_upgrade = []
            for a, b, w, must in edges:
                if must == 0:
                    if w >= target:
                        uf.union(a, b)
                    elif 2 * w >= target:
                        to_upgrade.append((a, b))
            upgrade_cnt = k
            for a, b in to_upgrade:
                if uf.find_root(a) != uf.find_root(b):
                    if upgrade_cnt <= 0:
                        return False
                    uf.union(a, b)
                    upgrade_cnt -= 1
            return all(uf.find_root(i) == uf.find_root(0) for i in range(n))

        s = -1
        e = 2 * max(w for _, _, w, _ in edges)
        while s + 1 < e:
            mid = (s + e) // 2
            if can_form_mst(mid):
                s = mid
            else:
                e = mid
        if can_form_mst(e):
            return e
        return s


class UnionFind:
    def __init__(self, n: int):
        self.parent = list(range(n))
        self.size = [1] * n

    def find_root(self, x: int) -> int:
        root = x
        while root != self.parent[root]:
            root = self.parent[root]
        while self.parent[x] != root:
            fa = self.parent[x]
            self.parent[x] = root
            x = fa
        return root

    def union(self, a: int, b: int) -> bool:
        a_root, b_root = self.find_root(a), self.find_root(b)
        if a_root == b_root:
            return False
        if self.size[b_root] < self.size[a_root]:
            a_root, b_root = b_root, a_root
        self.parent[a_root] = b_root
        self.size[b_root] += self.size[a_root]
        self.size[a_root] = 0
        return True
