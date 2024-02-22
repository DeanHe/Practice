"""
Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

Example 1:
Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]

Example 2:
Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83

Example 3:
Input: arr = [1], k = 1
Output: 1

Constraints:
1 <= arr.length <= 500
0 <= arr[i] <= 10^9
1 <= k <= arr.length

hints:
1 Think dynamic programming: dp[i] will be the answer for array A[0], ..., A[i-1].
2 For j = 1..k that keeps everything in bounds, dp[i] is the maximum of dp[i-j] + max(A[i-1], ..., A[i-j]) * j .

analysis:
DP
TC: O(N * k)
"""
from typing import List


class PartitionArrayForMaximumSum:
    def maxSumAfterPartitioning(self, arr: List[int], k: int) -> int:
        sz = len(arr)
        dp = [0] * (sz + 1)
        for i in range(1, sz + 1):
            most = 0
            for l in range(1, min(i, k) + 1):
                most = max(most, arr[i - l])
                dp[i] = max(dp[i], dp[i - l] + l * most)
        return dp[-1]