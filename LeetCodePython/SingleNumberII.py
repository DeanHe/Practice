"""
Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.

You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:
Input: nums = [2,2,3,2]
Output: 3

Example 2:
Input: nums = [0,1,0,1,0,1,99]
Output: 99

Constraints:
1 <= nums.length <= 3 * 104
-231 <= nums[i] <= 231 - 1
Each element in nums appears exactly three times except for one element which appears once.
"""
from typing import List


class SingleNumberII:
    def singleNumber(self, nums: List[int]) -> int:
        res = 0
        for i in range(32):
            total = 0
            for n in nums:
                total += (n >> i) & 1
            rem = total % 3
            if i == 31 and rem:
                res -= 1 << 31
            else:
                res |= rem << i
        return res