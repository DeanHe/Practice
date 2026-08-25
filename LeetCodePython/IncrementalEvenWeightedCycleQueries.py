"""
You are given a positive integer n.

There is an undirected graph with n nodes labeled from 0 to n - 1. Initially, the graph has no edges.

You are also given a 2D integer array edges, where edges[i] = [ui, vi, wi] represents an edge between nodes ui and vi with weight wi. The weight wi is either 0 or 1.

Process the edges in edges in the given order. For each edge, add it to the graph only if, after adding it, the sum of the weights of the edges in every cycle in the resulting graph is even.

Return an integer denoting the number of edges that are successfully added to the graph.

Example 1:
Input: n = 3, edges = [[0,1,1],[1,2,1],[0,2,1]]
Output: 2

Explanation:
[0, 1, 1]: We add the edge between vertex 0 and vertex 1 with weight 1.
[1, 2, 1]: We add the edge between vertex 1 and vertex 2 with weight 1.
[0, 2, 1]: The edge between vertex 0 and vertex 2 (the dashed edge in the diagram) is not added because the cycle 0 - 1 - 2 - 0 has total edge weight 1 + 1 + 1 = 3, which is an odd number.

Example 2:
Input: n = 3, edges = [[0,1,1],[1,2,1],[0,2,0]]
Output: 3

Explanation:
[0, 1, 1]: We add the edge between vertex 0 and vertex 1 with weight 1.
[1, 2, 1]: We add the edge between vertex 1 and vertex 2 with weight 1.
[0, 2, 0]: We add the edge between vertex 0 and vertex 2 with weight 0.
Note that the cycle 0 - 1 - 2 - 0 has total edge weight 1 + 1 + 0 = 2, which is an even number.

Constraints:
3 <= n <= 5 * 10^4
1 <= edges.length <= 5 * 10^4
edges[i] = [ui, vi, wi]
0 <= ui < vi < n
All edges are distinct.
wi = 0 or wi = 1

hints:
1 Model as parity constraints: assign bits to nodes, 0-edge requires same bit, 1-edge different.
2 Use DSU to track connected components and relative parities; reject if contradiction on add.
3 Adding edge merges or checks existing path parity matches new edge weight.

analysis:
union find, parity
define parity[x] = XOR of weights from node x to root

when Adding a edge forms a cycle.
Cycle XOR: (existing path u -> v) ^ new edge weight = (parity[u] ^ parity[v]) ^ w
cycle XOR = 0 means (parity[u] ^ parity[v] ^ w) == 0 -> parity[u] ^ parity[v] == w

TC:O(N)
"""
from typing import List


class IncrementalEvenWeightedCycleQueries:
    def numberOfEdgesAdded(self, n: int, edges: List[List[int]]) -> int:
        count = 0
        self.parent = list(range(n))
        self.size = [1] * n
        self.parity = [0] * n
        for a, b, w in edges:
            root_a, parity_a = self.find_root_parity(a)
            root_b, parity_b = self.find_root_parity(b)
            if root_a == root_b:
                if parity_a ^ parity_b == w:
                    count += 1
            else:
                # union
                self.parent[root_a] = self.parent[root_b]
                self.size[root_b] += self.size[root_a]
                self.size[root_a] = 0
                # set parity
                self.parity[root_a] = self.parity[a] ^ self.parity[b] ^ w
                count += 1
        return count

    def find_root_parity(self, x):
        st = []
        root = x
        while root != self.parent[root]:
            st.append((x, self.parity[root]))
            root = self.parent[root]
        xor = 0
        while st:
            cur, parity = st.pop()
            xor ^= parity
            self.parity[cur] = xor
        return root, self.parity[x]

