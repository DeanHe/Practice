"""
You are given an integer array nums. In one move, you can choose one element of nums and change it by any value.
Return the minimum difference between the largest and smallest value of nums after performing at most three moves.

Example 1:
Input: nums = [5,3,2,4]
Output: 0
Explanation: Change the array [5,3,2,4] to [2,2,2,2].
The difference between the maximum and minimum is 2-2 = 0.

Example 2:
Input: nums = [1,5,0,10,14]
Output: 1
Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
The difference between the maximum and minimum is 1-0 = 1.

Constraints:
1 <= nums.length <= 105
-109 <= nums[i] <= 109

hint:
1 The minimum difference possible is obtained by removing three elements between the three smallest and three largest values in the array.
"""
from typing import List


class minDifference:
    def minDifference(self, nums: List[int]) -> int:
        n, res = len(nums), float('inf')
        if n < 5:
            return 0
        nums.sort()
        for i in range(4):
            res = min(res, nums[n + i - 4] - nums[i])
        return res