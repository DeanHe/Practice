"""
You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of ith node. The root of the tree is node 0. Find the kth ancestor of a given node.

The kth ancestor of a tree node is the kth node in the path from that node to the root node.

Implement the TreeAncestor class:

TreeAncestor(int n, int[] parent) Initializes the object with the number of nodes in the tree and the parent array.
int getKthAncestor(int node, int k) return the kth ancestor of the given node node. If there is no such ancestor, return -1.

Example 1:
Input
["TreeAncestor", "getKthAncestor", "getKthAncestor", "getKthAncestor"]
[[7, [-1, 0, 0, 1, 1, 2, 2]], [3, 1], [5, 2], [6, 3]]
Output
[null, 1, 0, -1]

Explanation
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
treeAncestor.getKthAncestor(3, 1); // returns 1 which is the parent of 3
treeAncestor.getKthAncestor(5, 2); // returns 0 which is the grandparent of 5
treeAncestor.getKthAncestor(6, 3); // returns -1 because there is no such ancestor

Constraints:
1 <= k <= n <= 5 * 104
parent.length == n
parent[0] == -1
0 <= parent[i] < n for all 0 < i < n
0 <= node < n
There will be at most 5 * 104 queries.

hint:
1 The queries must be answered efficiently to avoid time limit exceeded verdict.
2 Use sparse table (dynamic programming application) to travel the tree upwards in a fast way.
"""
from math import log2
from typing import List


class TreeAncestor:

    def __init__(self, n: int, parent: List[int]):
        # at most 16 for this problem
        depth = 1 + int(log2(n))
        # ith node's 2^d parent
        self.dp = [[-1] * depth for _ in range(n)]
        for d in range(depth):
            for i in range(n):
                if d == 0:
                    self.dp[i][d] = parent[i]
                elif self.dp[i][d - 1] != -1:
                    self.dp[i][d] = self.dp[self.dp[i][d - 1]][d - 1]

    def getKthAncestor(self, node: int, k: int) -> int:
        while k > 0 and node != -1:
            d = int(log2(k))
            node = self.dp[node][d]
            k -= (1 << d)
        return node




# Your TreeAncestor object will be instantiated and called as such:
# obj = TreeAncestor(n, parent)
# param_1 = obj.getKthAncestor(node,k)