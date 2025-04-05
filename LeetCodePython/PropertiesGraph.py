"""
You are given a 2D integer array properties having dimensions n x m and an integer k.

Define a function intersect(a, b) that returns the number of distinct integers common to both arrays a and b.

Construct an undirected graph where each index i corresponds to properties[i]. There is an edge between node i and node j if and only if intersect(properties[i], properties[j]) >= k, where i and j are in the range [0, n - 1] and i != j.

Return the number of connected components in the resulting graph.

Example 1:
Input: properties = [[1,2],[1,1],[3,4],[4,5],[5,6],[7,7]], k = 1
Output: 3
Explanation:
The graph formed has 3 connected components:

Example 2:
Input: properties = [[1,2,3],[2,3,4],[4,3,5]], k = 2
Output: 1
Explanation:
The graph formed has 1 connected component:

Example 3:
Input: properties = [[1,1],[1,1]], k = 2
Output: 2

Explanation:
intersect(properties[0], properties[1]) = 1, which is less than k. This means there is no edge between properties[0] and properties[1] in the graph.

Constraints:
1 <= n == properties.length <= 100
1 <= m == properties[i].length <= 100
1 <= properties[i][j] <= 100
1 <= k <= m

hints:
1 How can we optimally find the intersection of two arrays? One way is to use len(set(a) & set(b)).
2 For connected components, think about using DFS, BFS, or DSU.

analysis:
union find:
Constructing the adjacency map: O(N⋅M) (where M is the average list size).
Checking common elements: O(N^2⋅m) in the worst case.
Union-Find operations (amortized): O(α(N)) per operation.
OverallO(N^2⋅M).
"""
from typing import List


class PropertiesGraph:
    def numberOfComponents(self, properties: List[List[int]], k: int) -> int:
        res = 0
        sz = len(properties)
        parent = list(range(sz))
        size = [1] * sz

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
            if root_a != root_b:
                if size[root_a] <= size[root_b]:
                    parent[root_a] = root_b
                    size[root_b] += size[root_a]
                else:
                    parent[root_b] = root_a
                    size[root_a] += size[root_b]

        idx_to_set = {}
        for i, ls in enumerate(properties):
            idx_to_set[i] = set(ls)
        for i in range(sz - 1):
            for j in range(sz):
                common_cnt = 0
                for num in idx_to_set[i]:
                    if num in idx_to_set[j]:
                        common_cnt += 1
                if common_cnt >= k:
                    union(i, j)
        for i in range(sz):
            if parent[i] == i:
                res += 1
        return res
