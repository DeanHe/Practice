"""
You are given an array nums which is a
permutation
 of [0, 1, 2, ..., n - 1]. The score of any permutation of [0, 1, 2, ..., n - 1] named perm is defined as:

score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... + |perm[n - 1] - nums[perm[0]]|

Return the permutation perm which has the minimum possible score. If multiple permutations exist with this score, return the one that is
lexicographically smallest
 among them.

Example 1:
Input: nums = [1,0,2]
Output: [0,1,2]
Explanation:
The lexicographically smallest permutation with minimum cost is [0,1,2]. The cost of this permutation is |0 - 0| + |1 - 2| + |2 - 1| = 2.

Example 2:
Input: nums = [0,2,1]
Output: [0,2,1]
Explanation:
The lexicographically smallest permutation with minimum cost is [0,2,1]. The cost of this permutation is |0 - 1| + |2 - 2| + |1 - 0| = 2.

Constraints:
2 <= n == nums.length <= 14
nums is a permutation of [0, 1, 2, ..., n - 1].

hints:
1 The score function is cyclic, so we can always set perm[0] = 0 for the smallest lexical order.
2 It’s similar to the Traveling Salesman Problem. Use Dynamic Programming.
3 Use a bitmask to track which elements have been assigned to perm.

analysis:
DP + bit mask
TC: O(n * n * 2^n)
"""
from collections import defaultdict
from functools import lru_cache
from math import inf
from typing import List


class FindTheMinimumCostArrayPermutation:
    def findPermutation(self, nums: List[int]) -> List[int]:
        n = len(nums)
        perm_map = defaultdict(list)

        @lru_cache(None)
        def dfs(state, pre):
            if state == (1 << n) - 1:
                return abs(pre - nums[0])
            least_cost, least_cost_cand = inf, -1
            for i in range(n):
                if state & (1 << i) == 0:
                    cost = dfs(state | (1 << i), i) + abs(pre - nums[i])
                    if cost < least_cost:
                        least_cost, least_cost_cand = cost, i
            perm_map[(state, pre)] = [least_cost_cand] + perm_map[(state | (1 << least_cost_cand), least_cost_cand)]
            return least_cost

        dfs(1, 0)
        return [0] + perm_map[(1, 0)]


