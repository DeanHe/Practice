"""
Given an integer array nums, return the maximum possible sum of elements of the array such that it is divisible by three.

Example 1:
Input: nums = [3,6,5,1,8]
Output: 18
Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).

Example 2:
Input: nums = [4]
Output: 0
Explanation: Since 4 is not divisible by 3, do not pick any number.

Example 3:
Input: nums = [1,2,3,4,4]
Output: 12
Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).

Constraints:
1 <= nums.length <= 4 * 10^4
1 <= nums[i] <= 10^4

analysis:
DP

dp[0] means max sum divisible by 3
dp[1] means max sum divided by 3 with remainder 1
dp[2] means max sum divided by 3 with remainder 2

TC: O(N)
"""
from typing import List


class GreatestSumDivisibleByThree:
    def maxSumDivThree(self, nums: List[int]) -> int:
        dp = [0, 0, 0]
        for num in nums:
            for total in dp[:]:
                dp[(total + num) % 3] = max(dp[(total + num) % 3], total + num)
        return dp[0]