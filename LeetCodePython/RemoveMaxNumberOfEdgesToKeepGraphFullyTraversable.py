"""
Alice and Bob have an undirected graph of n nodes and three types of edges:

Type 1: Can be traversed by Alice only.
Type 2: Can be traversed by Bob only.
Type 3: Can be traversed by both Alice and Bob.
Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.

Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse the graph.

Example 1:
Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
Output: 2
Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.

Example 2:
Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
Output: 0
Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.

Example 3:
Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
Output: -1
Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it's impossible to make the graph fully traversable.

Constraints:
1 <= n <= 10^5
1 <= edges.length <= min(105, 3 * n * (n - 1) / 2)
edges[i].length == 3
1 <= typei <= 3
1 <= ui < vi <= n
All tuples (typei, ui, vi) are distinct.

analysis:
check type 3 first
TC: O(E)
SC: O(E)
"""
from typing import List


class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable:
    def maxNumEdgesToRemove(self, n: int, edges: List[List[int]]) -> int:
        uf_alice = UnionFind(n)
        uf_bob = UnionFind(n)
        edges.sort(key=lambda x: -x[0])
        must = 0
        for e in edges:
            if e[0] == 1:
                if uf_alice.union(e[1], e[2]):
                    must += 1
            elif e[0] == 2:
                if uf_bob.union(e[1], e[2]):
                    must += 1
            else:
                must_alice = uf_alice.union(e[1], e[2])
                must_bob = uf_bob.union(e[1], e[2])
                if must_alice or must_bob:
                    must += 1
        if uf_alice.count == 1 and uf_bob.count == 1:
            return len(edges) - must
        return -1


class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n + 1))
        self.count = n

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
        if a_root != b_root:
            self.parent[a_root] = b_root
            self.count -= 1
            return True
        else:
            return False
