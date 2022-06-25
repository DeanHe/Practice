"""
Given an unsorted integer array nums, return the smallest missing positive integer.

You must implement an algorithm that runs in O(n) time and uses constant extra space.

Example 1:
Input: nums = [1,2,0]
Output: 3

Example 2:
Input: nums = [3,4,-1,1]
Output: 2

Example 3:
Input: nums = [7,8,9,11,12]
Output: 1

Constraints:
1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1

hint:
1 Think about how you would solve the problem in non-constant space. Can you apply that logic to the existing space?
2 We don't care about duplicates or non-positive integers
3 Remember that O(2n) = O(n)
"""
from typing import List


class FirstMissingPositive:
    def firstMissingPositive(self, nums: List[int]) -> int:
        n = len(nums)
        #delete those useless elements
        for i in range(n):
            if nums[i] < 1 or nums[i] > n:
                nums[i] = 0
        #if nums[i] is in the range of
        #[1 : n], we use index (nums[i] - 1) to record that we have
        #seen nums[i] by adding n + 1 to nums[nums[i] - 1]
        for i in range(n):
            if 1 <= nums[i] % (n + 1) <= n:
                idx = nums[i] % (n + 1) - 1
                nums[idx] += n + 1
        for i in range(1, n):
            if nums[i] <= n:
                return i + 1
        return n + 1
