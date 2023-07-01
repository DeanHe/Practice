"""
You are given a binary array nums.

A subarray of an array is good if it contains exactly one element with the value 1.

Return an integer denoting the number of ways to split the array nums into good subarrays. As the number may be too large, return it modulo 10^9 + 7.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [0,1,0,0,1]
Output: 3
Explanation: There are 3 ways to split nums into good subarrays:
- [0,1] [0,0,1]
- [0,1,0] [0,1]
- [0,1,0,0] [1]

Example 2:
Input: nums = [0,1,0]
Output: 1
Explanation: There is 1 way to split nums into good subarrays:
- [0,1,0]

Constraints:
1 <= nums.length <= 10^5
0 <= nums[i] <= 1

hints:
1 If the array consists of only 0s answer is 0.
2 In the final split, exactly one separation point exists between two consecutive 1s.
3 In how many ways can separation points be put?

analysis:
Math
TC: O(N)
"""
from typing import List


class WaysToSplitArrayIntoGoodSubarrays:
    def numberOfGoodSubarraySplits(self, nums: List[int]) -> int:
        size = len(nums)
        MOD = 10 ** 9 + 7
        res = 1
        zero_cnt = 0
        # first 1 index
        i = 0
        while i < size and nums[i] == 0:
            i += 1
        if i >= size:
            return 0
        while i < size:
            if nums[i] == 1:
                res = (res * (zero_cnt + 1) % MOD)
                zero_cnt = 0
            else:
                zero_cnt += 1
            i += 1
        return res
