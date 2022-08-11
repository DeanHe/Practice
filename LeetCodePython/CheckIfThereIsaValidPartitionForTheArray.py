"""
You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.

We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:

The subarray consists of exactly 2 equal elements. For example, the subarray [2,2] is good.
The subarray consists of exactly 3 equal elements. For example, the subarray [4,4,4] is good.
The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
Return true if the array has at least one valid partition. Otherwise, return false.

Example 1:
Input: nums = [4,4,4,5,6]
Output: true
Explanation: The array can be partitioned into the subarrays [4,4] and [4,5,6].
This partition is valid, so we return true.

Example 2:
Input: nums = [1,1,1,2]
Output: false
Explanation: There is no valid partition for this array.

Constraints:
2 <= nums.length <= 10^5
1 <= nums[i] <= 10^6

hint:
1 How can you reduce the problem to checking if there is a valid partition for a smaller array?
2 Use dynamic programming to reduce the problem until you have an empty array.
"""
from functools import lru_cache
from typing import List


class CheckIfThereIsaValidPartitionForTheArray:
    def validPartition(self, nums: List[int]) -> bool:
        n = len(nums)
        dp = [False] * (n + 1)
        dp[0] = True
        for i in range(2, n + 1):
            dp[i] |= nums[i - 1] == nums[i - 2] and dp[i - 2]
            if i > 2:
                dp[i] |= nums[i - 1] == nums[i - 2] == nums[i - 3] and dp[i - 3]
                dp[i] |= nums[i - 1] == nums[i - 2] + 1 == nums[i - 3] + 2 and dp[i - 3]
        return dp[n]

    def validPartition2(self, nums: List[int]) -> bool:
        n = len(nums)

        @lru_cache(None)
        def dfs(i):
            if i > n:
                return False
            if i == n:
                return True
            res = False
            if i + 1 < n:
                if nums[i] == nums[i + 1]:
                    res |= dfs(i + 2)
            if i + 2 < n:
                if nums[i] == nums[i + 1]:
                    res |= dfs(i + 2)
                    if nums[i + 1] == nums[i + 2]:
                        res |= dfs(i + 3)
                elif nums[i] + 2 == nums[i + 1] + 1 == nums[i + 2]:
                    res |= dfs(i + 3)
            return res

        return dfs(0)

