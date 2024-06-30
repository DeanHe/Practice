"""
You are given an integer array nums. In one move, you can pick an index i where 0 <= i < nums.length and increment nums[i] by 1.

Return the minimum number of moves to make every value in nums unique.

The test cases are generated so that the answer fits in a 32-bit integer.

Example 1:
Input: nums = [1,2,2]
Output: 1
Explanation: After 1 move, the array could be [1, 2, 3].

Example 2:
Input: nums = [3,2,1,2,1,7]
Output: 6
Explanation: After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.

Constraints:
1 <= nums.length <= 10^5
0 <= nums[i] <= 10^5

analysis:
Sort
TC: O(NlogN)
"""
from typing import List


class MinimumIncrementToMakeArrayUnique:
    def minIncrementForUnique(self, nums: List[int]) -> int:
        res = 0
        nums.sort()
        for i in range(1, len(nums)):
            if nums[i] <= nums[i - 1]:
                res += nums[i - 1] + 1 - nums[i]
                nums[i] = nums[i - 1] + 1
        return res

    def minIncrementForUnique2(self, nums: List[int]) -> int:
        res = 0
        most = max(nums)
        freq = [0] * (most + len(nums) + 1)
        for num in nums:
            freq[num] += 1
        for i, f in enumerate(freq):
            if f >= 1:
                dup = f - 1
                freq[i + 1] += dup
                freq[i] = 1
                res += dup
        return res

