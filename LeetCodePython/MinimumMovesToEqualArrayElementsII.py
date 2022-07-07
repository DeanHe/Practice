"""
Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment or decrement an element of the array by 1.

Test cases are designed so that the answer will fit in a 32-bit integer.

Example 1:
Input: nums = [1,2,3]
Output: 2
Explanation:
Only two moves are needed (remember each move increments or decrements one element):
[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
Example 2:

Input: nums = [1,10,2,9]
Output: 16

Constraints:
n == nums.length
1 <= nums.length <= 105
-109 <= nums[i] <= 109
"""
from typing import List


class MinimumMovesToEqualArrayElementsII:
    def minMoves2(self, nums: List[int]) -> int:
        nums.sort()
        s, e, res = 0, len(nums) - 1, 0
        while s < e:
            res += nums[e] - nums[s]
            e -= 1
            s += 1
        return res