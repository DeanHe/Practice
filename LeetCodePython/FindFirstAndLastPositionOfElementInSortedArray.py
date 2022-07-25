"""
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
"""
from typing import List


class FindFirstAndLastPositionOfElementInSortedArray:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if not nums:
            return [-1, -1]

        def binary_search(leftward):
            s, e = 0, len(nums) - 1
            while s + 1 < e:
                mid = s + (e - s) // 2
                if nums[mid] == target:
                    if leftward:
                        e = mid
                    else:
                        s = mid
                elif nums[mid] < target:
                    s = mid
                else:
                    e = mid
            if leftward:
                if nums[s] == target:
                    return s
                if nums[e] == target:
                    return e
            else:
                if nums[e] == target:
                    return e
                if nums[s] == target:
                    return s
            return -1

        return [binary_search(True), binary_search(False)]