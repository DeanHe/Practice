"""
Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
Return any array that satisfies this condition.


Example 1:
Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

Example 2:
Input: nums = [0]
Output: [0]


Constraints:
1 <= nums.length <= 5000
0 <= nums[i] <= 5000
"""
from typing import List


class SortArrayByParity:
    def sortArrayByParity(self, nums: List[int]) -> List[int]:
        s, e = 0, len(nums) - 1
        while s < e:
            if nums[s] % 2 == 1 and nums[e] % 2 == 0:
                nums[s], nums[e] = nums[e], nums[s]
            if nums[s] % 2 == 0:
                s += 1
            if nums[e] % 2 == 1:
                e -= 1
        return nums