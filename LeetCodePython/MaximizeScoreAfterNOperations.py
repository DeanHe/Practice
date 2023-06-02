"""
You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.

In the ith operation (1-indexed), you will:

Choose two elements, x and y.
Receive a score of i * gcd(x, y).
Remove x and y from nums.
Return the maximum score you can receive after performing n operations.

The function gcd(x, y) is the greatest common divisor of x and y.

Example 1:
Input: nums = [1,2]
Output: 1
Explanation: The optimal choice of operations is:
(1 * gcd(1, 2)) = 1

Example 2:
Input: nums = [3,4,6,8]
Output: 11
Explanation: The optimal choice of operations is:
(1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11

Example 3:
Input: nums = [1,2,3,4,5,6]
Output: 14
Explanation: The optimal choice of operations is:
(1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14

Constraints:
1 <= n <= 7
nums.length == 2 * n
1 <= nums[i] <= 10^6

hints:
1 Find every way to split the array until n groups of 2. Brute force recursion is acceptable.
2 Calculate the gcd of every pair and greedily multiply the largest gcds.

dfs + memorization
"""
from functools import cache
from math import gcd
from typing import List


class MaximizeScoreAfterNOperations:
    def maxScore(self, nums: List[int]) -> int:
        n = len(nums)

        @cache
        def dfs(op, state):
            if op > n // 2:
                return 0
            res = 0
            for i in range(n):
                for j in range(i + 1, n):
                    pick = (1 << i) | (1 << j)
                    if (state & pick) == 0:
                        res = max(res, op * gcd(nums[i], nums[j]) + dfs(op + 1, state | pick))
            return res

        return dfs(1, 0)