"""
There exist two undirected trees with n and m nodes, labeled from [0, n - 1] and [0, m - 1], respectively.

You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.

Node u is target to node v if the number of edges on the path from u to v is even. Note that a node is always target to itself.

Return an array of n integers answer, where answer[i] is the maximum possible number of nodes that are target to node i of the first tree if you had to connect one node from the first tree to another node in the second tree.

Note that queries are independent from each other. That is, for every query you will remove the added edge before proceeding to the next query.

Example 1:
Input: edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]]
Output: [8,7,7,8,8]
Explanation:
For i = 0, connect node 0 from the first tree to node 0 from the second tree.
For i = 1, connect node 1 from the first tree to node 4 from the second tree.
For i = 2, connect node 2 from the first tree to node 7 from the second tree.
For i = 3, connect node 3 from the first tree to node 0 from the second tree.
For i = 4, connect node 4 from the first tree to node 4 from the second tree.

Example 2:
Input: edges1 = [[0,1],[0,2],[0,3],[0,4]], edges2 = [[0,1],[1,2],[2,3]]
Output: [3,6,6,6,6]
Explanation:
For every i, connect node i of the first tree with any node of the second tree.

Constraints:
2 <= n, m <= 10^5
edges1.length == n - 1
edges2.length == m - 1
edges1[i].length == edges2[i].length == 2
edges1[i] = [ai, bi]
0 <= ai, bi < n
edges2[i] = [ui, vi]
0 <= ui, vi < m
The input is generated such that edges1 and edges2 represent valid trees.

hints:
1 Compute an array even where even[u] is the number of nodes at an even distance from node u, for every u of the first tree.
2 Compute an array odd where odd[u] is the number of nodes at an odd distance from node u, for every u of the second tree.
3 answer[i] = even[i]+ max(odd[1], odd[2], …, odd[m - 1])

Analysis:
DFS to count the neighbors with distance less than k for each node
"""
from collections import defaultdict
from typing import List


class MaximizeTheNumberOfTargetNodesAfterConnectingTreesII:
    def maxTargetNodes(self, edges1: List[List[int]], edges2: List[List[int]]) -> List[int]:
        def create_graph(edge):
            g = defaultdict(list)
            for a, b in edge:
                g[a].append(b)
                g[b].append(a)
            return g

        g1 = create_graph(edges1)
        g2 = create_graph(edges2)

        def cnt_of_nodes_with_parity(cur, pre, graph, parity, even):
            even_cnt = even
            parity[cur] = even
            for nb in graph[cur]:
                if nb != pre:
                    even_cnt += cnt_of_nodes_with_parity(nb, cur, graph, parity, not even)
            return even_cnt

        g1_nodes = (len(edges1) + 1)
        g2_nodes = (len(edges2) + 1)
        parity1 = [False] * g1_nodes
        parity2 = [False] * g2_nodes
        res = [0] * g1_nodes
        even_cnt1 = cnt_of_nodes_with_parity(0, -1, g1, parity1, True)
        even_cnt2 = cnt_of_nodes_with_parity(0, -1, g2, parity2, True)
        for i in range(g1_nodes):
            if parity1[i]:
                res[i] = even_cnt1
            else:
                # odd_cnt1
                res[i] = g1_nodes - even_cnt1
            res[i] += max(even_cnt2, g2_nodes - even_cnt2)
        return res

