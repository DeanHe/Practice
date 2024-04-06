"""
You are given a binary array nums.

We call a subarray alternating if no two adjacent elements in the subarray have the same value.

Return the number of alternating subarrays in nums.

Example 1:
Input: nums = [0,1,1,1]
Output: 5

Explanation:
The following subarrays are alternating: [0], [1], [1], [1], and [0,1].

Example 2:
Input: nums = [1,0,1,0]
Output: 10

Explanation:
Every subarray of the array is alternating. There are 10 possible subarrays that we can choose.

Constraints:
1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
"""
from typing import List


class Solution:
    def countAlternatingSubarrays(self, nums: List[int]) -> int:
        res = 0
        sz = len(nums)
        cur = 0
        for r in range(sz):
            if r > 0 and nums[r - 1] != nums[r]:
                cur += 1
            else:
                cur = 1
            res += cur
        return res
