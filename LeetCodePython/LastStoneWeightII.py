"""
You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the smallest possible weight of the left stone. If there are no stones left, return 0.

Example 1:
Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation:
We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.

Example 2:
Input: stones = [31,26,33,21,40]
Output: 5

Constraints:
1 <= stones.length <= 30
1 <= stones[i] <= 100

hint:
1 Think of the final answer as a sum of weights with + or - sign symbols infront of each weight. Actually, all sums with 1 of each sign symbol are possible.
2 Use dynamic programming: for every possible sum with N stones, those sums +x or -x is possible with N+1 stones, where x is the value of the newest stone.
(This overcounts sums that are all positive or all negative, but those don't matter.)
"""
from typing import List


class LastStoneWeightII:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        total = sum(stones)
        max_small = 0
        dp = [[False] * (len(stones) + 1) for _ in range(total + 1)]
        for i in range(len(stones) + 1):
            dp[0][i] = True
        for s_small in range(total // 2 + 1):
            for i in range(1, len(stones) + 1):
                if dp[s_small][i - 1] or (s_small >= stones[i - 1] and dp[s_small - stones[i - 1]][i - 1]):
                    dp[s_small][i] = True
                    max_small = max(max_small, s_small)
        return total - 2 * max_small