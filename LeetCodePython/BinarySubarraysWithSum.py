"""
Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.

A subarray is a contiguous part of the array.

Example 1:
Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]

Example 2:
Input: nums = [0,0,0,0,0], goal = 0
Output: 15

Constraints:
1 <= nums.length <= 3 * 10^4
nums[i] is either 0 or 1.
0 <= goal <= nums.length
"""
from typing import List


class BinarySubarraysWithSum:
    def numSubarraysWithSum(self, nums: List[int], goal: int) -> int:
        res = 0
        sz = len(nums)
        sum_cnt = [0] * (sz + 1)
        sum_cnt[0] = 1
        total = 0
        for n in nums:
            total += n
            if total >= goal:
                res += sum_cnt[total - goal]
            sum_cnt[total] += 1
        return res