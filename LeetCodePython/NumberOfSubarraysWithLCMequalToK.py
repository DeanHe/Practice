"""
Given an integer array nums and an integer k, return the number of subarrays of nums where the least common multiple of the subarray's elements is k.
A subarray is a contiguous non-empty sequence of elements within an array.
The least common multiple of an array is the smallest positive integer that is divisible by all the array elements.


Example 1:
Input: nums = [3,6,2,7,1], k = 6
Output: 4
Explanation: The subarrays of nums where 6 is the least common multiple of all the subarray's elements are:
- [3,6,2,7,1]
- [3,6,2,7,1]
- [3,6,2,7,1]
- [3,6,2,7,1]

Example 2:
Input: nums = [3], k = 2
Output: 0
Explanation: There are no subarrays of nums where 2 is the least common multiple of all the subarray's elements.

Constraints:
1 <= nums.length <= 1000
1 <= nums[i], k <= 1000
"""
from typing import List


class NumberOfSubarraysWithLCMequalToK:
    def subarrayLCM(self, nums: List[int], k: int) -> int:
        n = len(nums)
        res = 0
        for e in range(n):
            cur_lcm = nums[e]
            for s in range(e, -1, -1):
                cur_lcm = self.lcm(cur_lcm, nums[s])
                if cur_lcm == k:
                    res += 1
                if cur_lcm > k:
                    break
        return res

    def gcd(self, a, b):
        if a == 0:
            return b
        return self.gcd(b % a, a)

    def lcm(self, a, b):
        greatest_common_divisor = self.gcd(a, b)
        return a * b // greatest_common_divisor
