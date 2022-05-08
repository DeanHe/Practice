"""
Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

Return true if there is a 132 pattern in nums, otherwise, return false.



Example 1:

Input: nums = [1,2,3,4]
Output: false
Explanation: There is no 132 pattern in the sequence.
Example 2:

Input: nums = [3,1,4,2]
Output: true
Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:

Input: nums = [-1,3,2,0]
Output: true
Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].


Constraints:
n == nums.length
1 <= n <= 2 * 10^5
-10^9 <= nums[i] <= 10^9

analysis:
use a backward monotonic increase stack to track 32 pattern
then find 12 by compare nums[i] @1 with rightMost @2
"""
from typing import List


class Pattern132:
    def find132pattern(self, nums: List[int]) -> bool:
        if len(nums) < 3:
            return False
        stack, right = [], float('-inf')
        for i in range(len(nums) - 1, -1, -1):
            # @1 < @2
            if nums[i] < right:
                return True
            # update @2
            while stack and nums[i] > stack[-1]:
                right = stack.pop()
            stack.append(nums[i])
        return False
