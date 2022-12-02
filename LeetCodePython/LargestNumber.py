"""
Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.

Example 1:
Input: nums = [10,2]
Output: "210"

Example 2:
Input: nums = [3,30,34,5,9]
Output: "9534330"

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 10^9
"""
from functools import cmp_to_key
from typing import List


class LargestNumber:
    def largestNumber(self, nums: List[int]) -> str:
        nums = list(map(str, nums))
        cmp = lambda x, y: ((x + y) > (y + x)) - ((x + y) < (y + x))
        nums.sort(key=cmp_to_key(cmp))
        return str(int(''.join(nums[::-1])))