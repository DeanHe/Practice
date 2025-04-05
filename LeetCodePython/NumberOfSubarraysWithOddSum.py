"""
Given an array of integers arr, return the number of subarrays with an odd sum.

Since the answer can be very large, return it modulo 10^9 + 7.

Example 1:
Input: arr = [1,3,5]
Output: 4
Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
All sub-arrays sum are [1,4,9,3,8,5].
Odd sums are [1,9,3,5] so the answer is 4.

Example 2:
Input: arr = [2,4,6]
Output: 0
Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
All sub-arrays sum are [2,6,12,4,10,6].
All sub-arrays have even sum and the answer is 0.

Example 3:
Input: arr = [1,2,3,4,5,6,7]
Output: 16

Constraints:
1 <= arr.length <= 10^5
1 <= arr[i] <= 100

hints:
1 Can we use the accumulative sum to keep track of all the odd-sum sub-arrays ?
2 if the current accu sum is odd, we care only about previous even accu sums and vice versa.

Analysis:
DP
TC: O(N)
SC: O(N)
"""
from typing import List


class NumberOfSubarraysWithOddSum:
    def numOfSubarrays(self, arr: List[int]) -> int:
        res = 0
        MOD = 10 ** 9 + 7
        sz = len(arr)

        # dp[i][0] means # of subarray end with arr[i] has even sum;
        # dp[i][1] means # of subarray end with arr[i] has odd sum
        dp = [[0] * 2 for _ in range(sz + 1)]
        for i, num in enumerate(arr):
            if num % 2 == 0:
                dp[i + 1][0] = dp[i][0] + 1
                dp[i + 1][1] = dp[i][1]
            else:
                dp[i + 1][0] = dp[i][1]
                dp[i + 1][1] = dp[i][0] + 1
            res += dp[i + 1][1]
        return res % MOD