"""
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]

Constraints:
2 <= nums.length <= 10^5
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
"""
from typing import List


class ProductOfArrayExceptSelf:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        pre, n = 1, len(nums)
        res = []
        for i in range(0, n):
            res.append(pre)
            pre *= nums[i]
        pre = 1
        for i in range(n - 1, -1, -1):
            res[i] *= pre
            pre *= nums[i]
        return res