"""
Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.

A subarray is a contiguous part of an array.

Example 1:
Input: nums = [1], k = 1
Output: 1

Example 2:
Input: nums = [1,2], k = 4
Output: -1

Example 3:
Input: nums = [2,-1,2], k = 3
Output: 3

Constraints:
1 <= nums.length <= 10^5
-10^5 <= nums[i] <= 10^5
1 <= k <= 10^9

Analysis:
preSum, monotonic stack
given [:end] preSum find the largest [:start] preSum such that [start + 1 : end] sum >= K
TC: O(N)
"""
import math
from collections import deque
from typing import List


class ShortestSubarrayWithSumAtLeastK:
    def shortestSubarray(self, nums: List[int], k: int) -> int:
        sz = len(nums)
        res = math.inf
        pre_sum = [0]
        for i in range(sz):
            pre_sum.append(pre_sum[-1] + nums[i])
        q = deque()
        for i in range(sz + 1):
            while q and k <= pre_sum[i] - pre_sum[q[0]]:
                res = min(res, i - q.popleft())
            while q and pre_sum[i] <= pre_sum[q[-1]]:
                q.pop()
            q.append(i)
        return res if res != math.inf else -1