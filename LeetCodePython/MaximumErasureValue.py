"""
You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

Example 1:
Input: nums = [4,2,4,5,6]
Output: 17
Explanation: The optimal subarray here is [2,4,5,6].

Example 2:
Input: nums = [5,2,1,2,5,2,1,2,5]
Output: 8
Explanation: The optimal subarray here is [5,2,1] or [1,2,5].

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 104

hint:
1 The main point here is for the subarray to contain unique elements for each index. Only the first subarrays starting from that index have unique elements.
2 This can be solved using the two pointers technique
"""
import collections
from typing import List


class MaximumErasureValue:
    def maximumUniqueSubarray(self, nums: List[int]) -> int:
        last_dup_idx = -1
        res = 0
        pre_sum = [0] * (len(nums) + 1)
        last_idx = {}
        for i, n in enumerate(nums):
            if n in last_idx:
                last_dup_idx = max(last_dup_idx, last_idx[n])
            pre_sum[i + 1] = pre_sum[i] + n
            res = max(res, pre_sum[i + 1] - pre_sum[last_dup_idx + 1])
            last_idx[n] = i
        return res