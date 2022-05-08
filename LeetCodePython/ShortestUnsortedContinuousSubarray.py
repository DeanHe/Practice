"""
Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.

Return the shortest such subarray and output its length.

Example 1:
Input: nums = [2,6,4,8,10,9,15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

Example 2:
Input: nums = [1,2,3,4]
Output: 0

Example 3:
Input: nums = [1]
Output: 0


Constraints:
1 <= nums.length <= 10^4
-10^5 <= nums[i] <= 10^5

Follow up: Can you solve it in O(n) time complexity?

analysis:
TC: O(N)
SC: O(1)
"""
from typing import List


class ShortestUnsortedContinuousSubarray:
    def findUnsortedSubarray(self, nums: List[int]) -> int:
        if len(nums) < 2:
            return 0
        pre, e = nums[0], 0
        # find the largest index not in place
        for i in range(len(nums)):
            if nums[i] < pre:
                e = i
            else:
                pre = nums[i]
        pre, s = nums[-1], len(nums) - 1
        # find the smallest index not in place
        for i in range(len(nums) - 1, -1, -1):
            if pre < nums[i]:
                s = i
            else:
                pre = nums[i]
        if e != 0:
            return e - s + 1
        else:
            return 0
