"""
You are given an integer array nums.

The factor score of an array is defined as the product of the LCM and GCD of all elements of that array.

Return the maximum factor score of nums after removing at most one element from it.

Note that both the LCM and GCD of a single number are the number itself, and the factor score of an empty array is 0.

The term lcm(a, b) denotes the least common multiple of a and b.

The term gcd(a, b) denotes the greatest common divisor of a and b.

Example 1:
Input: nums = [2,4,8,16]
Output: 64
Explanation:
On removing 2, the GCD of the rest of the elements is 4 while the LCM is 16, which gives a maximum factor score of 4 * 16 = 64.

Example 2:
Input: nums = [1,2,3,4,5]
Output: 60
Explanation:
The maximum factor score of 60 can be obtained without removing any elements.

Example 3:
Input: nums = [3]
Output: 9

Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 30

hints:
1 Use brute force approach with two loops.
2 Optimize using prefix and suffix arrays.

Analysis:
TC: O(N^2)
"""
from typing import List


class FindTheMaximumFactorScoreOfArray:
    def maxScore(self, nums: List[int]) -> int:
        res = self.calculate_arr_gcd(nums) * self.calculate_arr_lcm(nums)
        if len(nums) == 1:
            return res
        for i in range(len(nums)):
            truncated_arr = nums[:i] + nums[i + 1:]
            res = max(res, self.calculate_arr_gcd(truncated_arr) * self.calculate_arr_lcm(truncated_arr))
        return res

    def calculate_arr_gcd(self, arr):
        res = arr[0]
        for i in range(1, len(arr)):
            res = self.gcd(res, arr[i])
        return res

    def calculate_arr_lcm(self, arr):
        res = arr[0]
        for i in range(1, len(arr)):
            res = self.lcm(res, arr[i])
        return res

    def gcd(self, a, b):
        if a == 0:
            return b
        return self.gcd(b % a, a)

    def lcm(self, a, b):
        greatest_common_divisor = self.gcd(a, b)
        return a * b // greatest_common_divisor