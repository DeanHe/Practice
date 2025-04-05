"""
You are given an integer array nums and two integers, k and m.

Return the maximum sum of k non-overlapping subarrays of nums, where each subarray has a length of at least m.

Example 1:
Input: nums = [1,2,-1,3,3,4], k = 2, m = 2
Output: 13

Explanation:
The optimal choice is:
Subarray nums[3..5] with sum 3 + 3 + 4 = 10 (length is 3 >= m).
Subarray nums[0..1] with sum 1 + 2 = 3 (length is 2 >= m).
The total sum is 10 + 3 = 13.

Example 2:
Input: nums = [-10,3,-1,-2], k = 4, m = 1
Output: -10
Explanation:
The optimal choice is choosing each element as a subarray. The output is (-10) + 3 + (-1) + (-2) = -10.

Constraints:
1 <= nums.length <= 2000
-104 <= nums[i] <= 10^4
1 <= k <= floor(nums.length / m)
1 <= m <= 3

hints:
1 Dynamic Programming
2 Prefix Sum
3 Let dp[i][j] be the maximum sum with i subarrays for the first j elements
"""
import math
from typing import List


class SumOfKSubarraysWithLengthAtLeastM:

    def maxSum(self, nums: List[int], k: int, m: int) -> int:
        n = len(nums)
        pre_sum = [0] * (n + 1)
        for i in range(n):
            pre_sum[i + 1] = pre_sum[i] + nums[i]
        dp = [[-math.inf] * (k + 1) for _ in range(n + 1)]
        for i in range(n):
            dp[i][0] = 0
        for cnt in range(1, k + 1):
            best = -math.inf
            # [start, end] is the current subarray, best means the minimum loss
            for end in range(m * cnt, n + 1):
                start = end - m
                if start >= 0:
                    best = max(best, dp[start][cnt - 1] - pre_sum[start])
                if end == cnt * m:
                    dp[end][cnt] = pre_sum[end] + best
                else:
                    dp[end][cnt] = max(dp[end - 1][cnt], pre_sum[end] + best)
        return int(dp[n][k])
