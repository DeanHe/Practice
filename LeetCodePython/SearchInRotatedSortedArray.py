"""
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-10^4 <= target <= 10^4

solution:
1 first check mid is in left monotone or right monotone
2 check if target in range [start, mid] or [mid, end]
3 otherwise move the opposite pointer

double layer condition checking
"""
from typing import List


class SearchInRotatedSortedArray:
    def search(self, nums: List[int], target: int) -> int:
        s, e = 0, len(nums) - 1
        while s + 1 < e:
            mid = s + (e - s) // 2
            if nums[mid] == target:
                return mid
            elif nums[s] < nums[mid]:
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
            return s
        if nums[e] == target:
            return e
        return -1
