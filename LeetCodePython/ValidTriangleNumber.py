"""
Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:
Input: nums = [2,2,3,4]
Output: 3
Explanation: Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3

Example 2:
Input: nums = [4,2,3,4]
Output: 4

Constraints:
1 <= nums.length <= 1000
0 <= nums[i] <= 1000

analysis:
sort + two pointers
TC: O(NlogN)
"""
from typing import List


class Solution:
    def triangleNumber(self, nums: List[int]) -> int:
        res = 0
        nums.sort()
        for r in range(len(nums) - 1, 1, -1):
            l, m = 0, r - 1
            while l < m:
                if nums[l] + nums[m] > nums[r]:
                    res += m - l
                    m -= 1
                else:
                    l += 1
        return res