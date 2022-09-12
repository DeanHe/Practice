"""
Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.

Example 1:
Input: nums = [3,2,1]
Output: 1
Explanation:
The first distinct maximum is 3.
The second distinct maximum is 2.
The third distinct maximum is 1.

Example 2:
Input: nums = [1,2]
Output: 2
Explanation:
The first distinct maximum is 2.
The second distinct maximum is 1.
The third distinct maximum does not exist, so the maximum (2) is returned instead.

Example 3:
Input: nums = [2,2,3,1]
Output: 1
Explanation:
The first distinct maximum is 3.
The second distinct maximum is 2 (both 2's are counted together since they have the same value).
The third distinct maximum is 1.

Constraints:
1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1

Follow up: Can you find an O(n) solution?
"""
from typing import List


class Solution:
    def thirdMax(self, nums: List[int]) -> int:
        candidates = [float('-inf'), float('-inf'), float('-inf')]
        for n in nums:
            if n not in candidates:
                if n > candidates[0]:
                    candidates = [n, candidates[0], candidates[1]]
                elif n > candidates[1]:
                    candidates = [candidates[0], n, candidates[1]]
                elif n > candidates[2]:
                    candidates = [candidates[0], candidates[1], n]
        return max(candidates) if float('-inf') in candidates else candidates[2]