"""
An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.

Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .

Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.

Example 1:
Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
Output: [false,true]
Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.

Example 2:
Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
Output: [true,false]
Exaplanation: The above figure shows the given graph.

Constraints:
2 <= n <= 105
1 <= edgeList.length, queries.length <= 105
edgeList[i].length == 3
queries[j].length == 3
0 <= ui, vi, pj, qj <= n - 1
ui != vi
pj != qj
1 <= disi, limitj <= 109
There may be multiple edges between two nodes.

hint:
1 All the queries are given in advance. Is there a way you can reorder the queries to avoid repeated computations?

analysis:
Time complexity O(NlogN) + O(MlogM)
"""
from typing import List

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

class CheckingExistenceOfEdgeLengthLimitedPaths:
    def distanceLimitedPathsExist(self, n: int, edgeList: List[List[int]], queries: List[List[int]]) -> List[bool]:
        res = [None] * len(queries)
        queries = sorted((w, a, b, i) for i, (a, b, w) in enumerate(queries))
        edgeList = sorted((w, a, b) for a, b, w in edgeList)
        uf = UnionFind(n)
        j = 0
        for w, a, b, i in queries:
            while j < len(edgeList) and edgeList[j][0] < w:
                _, x, y = edgeList[j]
                uf.union(x, y)
                j += 1
            res[i] = uf.find_root(a) == uf.find_root(b)
        return res
