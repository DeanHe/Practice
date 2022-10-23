"""
Given an integer array nums and an integer k, return the number of subarrays of nums where the greatest common divisor of the subarray's elements is k.

A subarray is a contiguous non-empty sequence of elements within an array.

The greatest common divisor of an array is the largest integer that evenly divides all the array elements.



Example 1:

Input: nums = [9,3,1,2,6,3], k = 3
Output: 4
Explanation: The subarrays of nums where 3 is the greatest common divisor of all the subarray's elements are:
- [9,3,1,2,6,3]
- [9,3,1,2,6,3]
- [9,3,1,2,6,3]
- [9,3,1,2,6,3]
Example 2:

Input: nums = [4], k = 7
Output: 0
Explanation: There are no subarrays of nums where 7 is the greatest common divisor of all the subarray's elements.


Constraints:
1 <= nums.length <= 1000
1 <= nums[i], k <= 10^9
"""
from typing import List


class NumberOfSubarraysWithGCDequalToK:
    def subarrayGCD(self, nums: List[int], k: int) -> int:
        res = 0

        def gcd(a, b):
            if a == 0:
                return b
            return gcd(b % a, a)

        for i, x in enumerate(nums):
            if x == k:
                res += 1
            for j in range(i - 1, -1, -1):
                y = nums[j]
                x = gcd(x, y)
                if x == k:
                    res += 1
        return res