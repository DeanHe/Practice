"""
Given the array houses where houses[i] is the location of the ith house along a street and an integer k, allocate k mailboxes in the street.
Return the minimum total distance between each house and its nearest mailbox.
The test cases are generated so that the answer fits in a 32-bit integer.

Example 1:
Input: houses = [1,4,8,10,20], k = 3
Output: 5
Explanation: Allocate mailboxes in position 3, 9 and 20.
Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5

Example 2:
Input: houses = [2,3,5,12,18], k = 2
Output: 9
Explanation: Allocate mailboxes in position 3 and 14.
Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.

Constraints:
1 <= k <= houses.length <= 100
1 <= houses[i] <= 104
All the integers of houses are unique.

hint:
1 If k =1, the minimum distance is obtained allocating the mailbox in the median of the array houses.
2 Generalize this idea, using dynamic programming allocating k mailboxes.
"""
from functools import lru_cache
from typing import List


class Solution:
    def minDistance(self, houses: List[int], k: int) -> int:
        n, UPPER_BOUND = len(houses), 10 ** 7
        houses.sort()
        cost = [[0] * n for _ in range(n)]
        for s in range(n):
            for e in range(s, n):
                median = houses[(s + e) // 2]
                for i in range(s, e + 1):
                    cost[s][e] += abs(houses[i] - median)

        @lru_cache(None)
        def dfs(cur, group):
            if cur == n and group == k:
                return 0
            if cur == n or group == k:
                return UPPER_BOUND
            res = UPPER_BOUND
            for i in range(cur, n):
                res = min(res, cost[cur][i] + dfs(i + 1, group + 1))
            return res

        return dfs(0, 0)