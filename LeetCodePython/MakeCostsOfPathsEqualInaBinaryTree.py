"""
You are given an integer n representing the number of nodes in a perfect binary tree consisting of nodes numbered from 1 to n. The root of the tree is node 1 and each node i in the tree has two children where the left child is the node 2 * i and the right child is 2 * i + 1.

Each node in the tree also has a cost represented by a given 0-indexed integer array cost of size n where cost[i] is the cost of node i + 1. You are allowed to increment the cost of any node by 1 any number of times.

Return the minimum number of increments you need to make the cost of paths from the root to each leaf node equal.

Note:
A perfect binary tree is a tree where each node, except the leaf nodes, has exactly 2 children.
The cost of a path is the sum of costs of nodes in the path.

Example 1:
Input: n = 7, cost = [1,5,2,2,3,3,1]
Output: 6
Explanation: We can do the following increments:
- Increase the cost of node 4 one time.
- Increase the cost of node 3 three times.
- Increase the cost of node 7 two times.
Each path from the root to a leaf will have a total cost of 9.
The total increments we did is 1 + 3 + 2 = 6.
It can be shown that this is the minimum answer we can achieve.

Example 2:
Input: n = 3, cost = [5,3,3]
Output: 0
Explanation: The two paths already have equal total costs, so no increments are needed.

Constraints:
3 <= n <= 10^5
n + 1 is a power of 2
cost.length == n
1 <= cost[i] <= 10^4

hints:
1 The path from the root to a leaf that has the maximum cost should not be modified.
2 The optimal way is to increase all other paths to make their costs equal to the path with maximum cost.

TC: O(N)
"""
from typing import List


class MakeCostsOfPathsEqualInaBinaryTree:
    def minIncrements(self, n: int, cost: List[int]) -> int:
        self.res = 0

        def min_cost_to_any_leaf(idx):
            if idx > len(cost):
                return 0
            l, r = min_cost_to_any_leaf(2 * idx), min_cost_to_any_leaf(2 * idx + 1)
            self.res += abs(l - r)
            return cost[idx - 1] + max(l, r)

        min_cost_to_any_leaf(1)
        return self.res