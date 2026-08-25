"""
Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.

There may be duplicates in the original array.

Note: An array A rotated by x positions results in an array B of the same length such that B[i] == A[(i+x) % A.length] for every valid index i.

Example 1:
Input: nums = [3,4,5,1,2]
Output: true
Explanation: [1,2,3,4,5] is the original sorted array.
You can rotate the array by x = 2 positions to begin on the element of value 3: [3,4,5,1,2].

Example 2:
Input: nums = [2,1,3,4]
Output: false
Explanation: There is no sorted array once rotated that can make nums.

Example 3:
Input: nums = [1,2,3]
Output: true
Explanation: [1,2,3] is the original sorted array.
You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.

Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 100

analysis:
Find Smallest Element:
in a sorted array that has been rotated,
all elements should be in non-decreasing order, except for one place where the largest element will be followed by the smallest element due to the rotation.
This results in at most one "inversion" — a pair where a number is greater than the next one.
TC:O(N)
"""
from typing import List


class CheckIfArrayIsSortedAndRotated:
    def check(self, nums: List[int]) -> bool:
        sz = len(nums)
        if sz <= 1:
            return True
        inverse_cnt = 0
        for i in range(1, sz):
            if nums[i - 1] > nums[i]:
                inverse_cnt += 1
        # Also check between the last and the first element due to rotation
        if nums[0] < nums[-1]:
            inverse_cnt += 1
        return inverse_cnt <= 1