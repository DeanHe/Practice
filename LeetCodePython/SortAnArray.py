"""
Given an array of integers nums, sort the array in ascending order and return it.

You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.

Example 1:
Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).

Example 2:
Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessairly unique.

Constraints:
1 <= nums.length <= 5 * 10^4
-5 * 10^4 <= nums[i] <= 5 * 10^4
"""
from typing import List


class SortAnArray:
    def sortArray(self, nums: List[int]) -> List[int]:

        def quick_sort(s, e):
            if s >= e:
                return
            l, r = s, e
            mid = (r - l) // 2 + l
            pivot = nums[mid]
            while l <= r:
                while l <= r and nums[l] < pivot:
                    l += 1
                while l <= r and pivot < nums[r]:
                    r -= 1
                if l <= r:
                    nums[l], nums[r] = nums[r], nums[l]
                    l += 1
                    r -= 1
            quick_sort(s, r)
            quick_sort(l, e)

        quick_sort(0, len(nums) - 1)
        return nums

