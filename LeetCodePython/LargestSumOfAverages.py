"""
You are given an integer array nums and an integer k. You can partition the array into at most k non-empty adjacent subarrays. The score of a partition is the sum of the averages of each subarray.

Note that the partition must use every integer in nums, and that the score is not necessarily an integer.

Return the maximum score you can achieve of all the possible partitions. Answers within 10-6 of the actual answer will be accepted.

Example 1:
Input: nums = [9,1,2,3,9], k = 3
Output: 20.00000
Explanation:
The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.

Example 2:
Input: nums = [1,2,3,4,5,6,7], k = 4
Output: 20.50000

Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 10^4
1 <= k <= nums.length
"""
from itertools import accumulate
from typing import List


class Solution:
    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        pre_sum = list(accumulate(nums, initial=0))
        memo = {}

        # largest sum of avg for nums[:end] partitioned to groups
        def dfs(end, groups):
            if (end, groups) in memo:
                return memo[end, groups]
            if groups == 1:
                memo[end, groups] = pre_sum[end] / end
                return memo[end, groups]
            res = 0
            for s in range(groups - 1, end):
                res = max(res, dfs(s, groups - 1) + (pre_sum[end] - pre_sum[s]) / (end - s))
            memo[end, groups] = res
            return res

        return dfs(len(nums), k)

