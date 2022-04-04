"""
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false

Constraints:
1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
nums is guaranteed to be rotated at some pivot.
-10^4 <= target <= 10^4

Follow up:
This is a follow up problem to Search in Rotated Sorted array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?
"""
from typing import List


class SearchInRotatedSortedArrayII:
    def search(self, nums: List[int], target: int) -> bool:
        s, e = 0, len(nums) - 1
        while s + 1 < e:
            mid = s + (e - s) // 2
            if nums[mid] == target:
                return True
            elif nums[s] == nums[mid] == nums[e]:
                s += 1
                e -= 1
            elif nums[s] <= nums[mid]:
                if nums[s] <= target <= nums[mid]:
                    e = mid
                else:
                    s = mid
            else:
                if nums[mid] <= target <= nums[e]:
                    s = mid
                else:
                    e = mid
        if nums[s] == target:
            return True
        if nums[e] == target:
            return True
        return False
