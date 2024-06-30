"""
There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively. You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
You must connect one node from the first tree with another node from the second tree with an edge.
Return the minimum possible diameter of the resulting tree.
The diameter of a tree is the length of the longest path between any two nodes in the tree.

Example 1:
Input: edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]
Output: 3
Explanation:
We can obtain a tree of diameter 3 by connecting node 0 from the first tree with any node from the second tree.

Example 2:
Input: edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]
Output: 5
Explanation:
We can obtain a tree of diameter 5 by connecting node 0 from the first tree with node 0 from the second tree.

Constraints:
1 <= n, m <= 10^5
edges1.length == n - 1
edges2.length == m - 1
edges1[i].length == edges2[i].length == 2
edges1[i] = [ai, bi]
0 <= ai, bi < n
edges2[i] = [ui, vi]
0 <= ui, vi < m
The input is generated such that edges1 and edges2 represent valid trees.

hints:
1 Suppose that we connected node a in tree1 with node b in tree2. The diameter length of the resulting tree will be the largest of the following 3 values:
The diameter of tree 1.
The diameter of tree 2.
The length of the longest path that starts at node a and that is completely within Tree 1 + The length of the longest path that starts at node b and that is completely within Tree 2 + 1.
The added one in the third value is due to the additional edge that we have added between trees 1 and 2.
2 Values 1 and 2 are constant regardless of our choice of a and b. Therefore, we need to pick a and b in such a way that minimizes value 3.
3 If we pick a and b optimally, they will be in the diameters of Tree 1 and Tree 2, respectively. Exactly which nodes of the diameter should we pick?
4 a is the center of the diameter of tree 1, and b is the center of the diameter of tree 2.

Analysis:
Height of an unrooted tree is the minimum height among all possible roots.
smallest height of undirected graph(tree) is (diameter + 1) / 2

to calculate Diameter: Find one farthest point i from 0, then Find one farthest point j from i.
dist of [i, j] is a diameter

BFS
"""
from collections import defaultdict, deque
from typing import List


class FindMinimumDiameterAfterMergingTwoTrees:
    def minimumDiameterAfterMerge(self, edges1: List[List[int]], edges2: List[List[int]]) -> int:
        def diameter(edges):
            if not edges:
                return 0
            graph = defaultdict(list)
            for a, b in edges:
                graph[a].append(b)
                graph[b].append(a)

            def longest_path(start):
                step = farthest_end = 0
                q = deque([start])
                visited = set()
                while q:
                    for _ in range(len(q)):
                        cur = q.popleft()
                        visited.add(cur)
                        farthest_end = cur
                        for nb in graph[cur]:
                            if nb not in visited:
                                q.append(nb)
                    step += 1
                return farthest_end, step - 1

            v1, _ = longest_path(0)
            _, d = longest_path(v1)
            return d

        d1 = diameter(edges1)
        d2 = diameter(edges2)
        return max(d1, d2, (d1 + 1) // 2 + (d2 + 1) // 2 + 1)



