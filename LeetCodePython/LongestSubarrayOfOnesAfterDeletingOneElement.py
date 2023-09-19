"""
Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.



Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.


Constraints:
1 <= nums.length <= 10^5
nums[i] is either 0 or 1.

hints:
1 Maintain a sliding window where there is at most one zero on it.
"""
from typing import List


class LongestSubarrayOfOnesAfterDeletingOneElement:
    def longestSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        s = res = zero_cnt = 0
        for e in range(n):
            if nums[e] == 0:
                zero_cnt += 1
            while zero_cnt > 1:
                if nums[s] == 0:
                    zero_cnt -= 1
                s += 1
            res = max(res, e - s)
        return res