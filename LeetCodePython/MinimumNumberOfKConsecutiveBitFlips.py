"""
You are given a binary array nums and an integer k.

A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.

Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not possible, return -1.

A subarray is a contiguous part of an array.

Example 1:
Input: nums = [0,1,0], k = 1
Output: 2
Explanation: Flip nums[0], then flip nums[2].

Example 2:
Input: nums = [1,1,0], k = 2
Output: -1
Explanation: No matter how we flip subarrays of size 2, we cannot make the array become [1,1,1].

Example 3:
Input: nums = [0,0,0,1,0,1,1,0], k = 3
Output: 3
Explanation:
Flip nums[0],nums[1],nums[2]: nums becomes [1,1,1,1,0,1,1,0]
Flip nums[4],nums[5],nums[6]: nums becomes [1,1,1,1,1,0,0,0]
Flip nums[5],nums[6],nums[7]: nums becomes [1,1,1,1,1,1,1,1]

Constraints:
1 <= nums.length <= 10^5
1 <= k <= nums.length
"""
from typing import List


class MinimumNumberOfKConsecutiveBitFlips:
    def minKBitFlips(self, nums: List[int], k: int) -> int:
        sz = len(nums)
        # current flipped state (odd/even)
        flipped = 0
        res = 0
        is_flipped = [0] * sz
        for i in range(sz):
            if i >= k:
                flipped ^= is_flipped[i - k]
            if flipped == nums[i]:
                if i + k > sz:
                    return -1
                is_flipped[i] = 1
                flipped ^= 1
                res += 1
        return res