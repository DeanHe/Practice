"""
You are given an integer array nums. In one operation, you can replace any element in nums with any integer.

nums is considered continuous if both of the following conditions are fulfilled:

All elements in nums are unique.
The difference between the maximum element and the minimum element in nums equals nums.length - 1.
For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.

Return the minimum number of operations to make nums continuous.

Example 1:
Input: nums = [4,2,5,3]
Output: 0
Explanation: nums is already continuous.

Example 2:
Input: nums = [1,2,3,5,6]
Output: 1
Explanation: One possible solution is to change the last element to 4.
The resulting array is [1,2,3,5,4], which is continuous.

Example 3:
Input: nums = [1,10,100,1000]
Output: 3
Explanation: One possible solution is to:
- Change the second element to 2.
- Change the third element to 3.
- Change the fourth element to 4.
The resulting array is [1,2,3,4], which is continuous.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9

hints:
1 Sort the array.
2 For every index do a binary search to get the possible right end of the window and calculate the possible answer.

analysis:
sliding window
TC: O(NlogN)
"""
from typing import List


class Solution:
    def minOperations(self, nums: List[int]) -> int:
        sz = len(nums)
        res = sz
        nums = sorted(set(nums))
        r = 0
        # for each nums[l] count how many elements are in range (nums[l], nums[l] + sz), they are good to keep
        for l in range(len(nums)):
            while r < len(nums) and nums[r] < nums[l] + sz:
                r += 1
            keep = r - l
            res = min(res, sz - keep)
        return res
