"""
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.

Example 1:
Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: The tree is shown above.
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.
Hence, answer[0] = 8, and so on.

Example 2:
Input: n = 1, edges = []
Output: [0]

Example 3:
Input: n = 2, edges = [[1,0]]
Output: [1,1]

Constraints:
1 <= n <= 3 * 10^4
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
The given input represents a valid tree.

analysis:
if a and b are neighboring nodes
res[a] - res[b] = # of node in subtree(b) - # of node in subtree(a)
res[a] = res[b] + # of node in subtree(b) - # of node in subtree(a)
res[a] = res[b] + n - # of node in subtree(a) - # of node in subtree(a)

TC: O(N)
"""
from collections import defaultdict
from typing import List


class SumOfDistancesInTree:
    def sumOfDistancesInTree(self, n: int, edges: List[List[int]]) -> List[int]:
        res = [0] * n
        sub_cnt = [1] * n
        graph = defaultdict(set)
        for a, b in edges:
            graph[a].add(b)
            graph[b].add(a)

        def post_order(cur, parent):
            for child in graph[cur]:
                if child != parent:
                    post_order(child, cur)
                    sub_cnt[cur] += sub_cnt[child]
                    res[cur] += res[child] + sub_cnt[child]

        def pre_order(cur, parent):
            for child in graph[cur]:
                if child != parent:
                    res[child] = res[cur] + n - sub_cnt[child] - sub_cnt[child]
                    pre_order(child, cur)

        post_order(0, -1)
        pre_order(0, -1)
        return res

