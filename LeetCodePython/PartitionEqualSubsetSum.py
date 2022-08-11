"""
Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Example 1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

Constraints:
1 <= nums.length <= 200
1 <= nums[i] <= 100
"""
from typing import List


class PartitionEqualSubsetSum:
    def canPartition(self, nums: List[int]) -> bool:
        n = len(nums)
        total = sum(nums)
        if total % 2 != 0:
            return False
        target = total // 2
        dp = [[False] * (target + 1) for _ in range(n + 1)]
        dp[0][0] = True
        for i in range(1, n + 1):
            dp[i][0] = True
        for i in range(1, n + 1):
            for s in range(1, target + 1):
                dp[i][s] = dp[i - 1][s]
                if s >= nums[i - 1]:
                    dp[i][s] |= dp[i - 1][s - nums[i - 1]]
        return dp[n][target]