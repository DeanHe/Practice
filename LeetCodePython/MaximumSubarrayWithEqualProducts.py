"""
You are given an array of positive integers nums.

An array arr is called product equivalent if prod(arr) == lcm(arr) * gcd(arr), where:

prod(arr) is the product of all elements of arr.
gcd(arr) is the GCD of all elements of arr.
lcm(arr) is the LCM of all elements of arr.
Return the length of the longest product equivalent subarray of nums.

A subarray is a contiguous non-empty sequence of elements within an array.

The term gcd(a, b) denotes the greatest common divisor of a and b.

The term lcm(a, b) denotes the least common multiple of a and b.

Example 1:
Input: nums = [1,2,1,2,1,1,1]
Output: 5
Explanation:
The longest product equivalent subarray is [1, 2, 1, 1, 1], where prod([1, 2, 1, 1, 1]) = 2, gcd([1, 2, 1, 1, 1]) = 1, and lcm([1, 2, 1, 1, 1]) = 2.

Example 2:
Input: nums = [2,3,4,5,6]
Output: 3
Explanation:
The longest product equivalent subarray is [3, 4, 5].

Example 3:
Input: nums = [1,2,3,1,4,5,1]
Output: 5

Constraints:
2 <= nums.length <= 100
1 <= nums[i] <= 10

hints:
1 What is the maximum possible lcm?
2520 = lcm(1 to 10)
"""
from typing import List


class Solution:
    def maxLength(self, nums: List[int]) -> int:

        def gcd(a, b):
            if a == 0:
                return b
            return gcd(b % a, a)

        def lcm(a, b):
            return a * b / gcd(a, b)

        res = 0
        sz = len(nums)
        for i in range(sz):
            product = nums[i]
            gcd_val = nums[i]
            lcm_val = nums[i]
            for j in range(i + 1, sz):
                product *= nums[j]
                gcd_val = gcd(gcd_val, nums[j])
                lcm_val = lcm(lcm_val, nums[j])
                if 2520 * gcd_val * lcm_val < product:
                    break
                if gcd_val * lcm_val == product:
                    res = max(res, j - i + 1)
        return res

