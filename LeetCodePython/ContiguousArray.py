"""
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

Example 1:
Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.

Example 2:
Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.
"""
from typing import List


class ContiguousArray:
    def findMaxLength(self, nums: List[int]) -> int:
        total, res = 0, 0
        idx = {0: -1}
        for i, n in enumerate(nums):
            if n == 1:
                total += 1
            else:
                total -= 1
            if total in idx:
                res = max(res, i - idx[total])
            else:
                idx[total] = i
        return res
