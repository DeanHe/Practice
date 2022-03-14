"""
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Example
Given nums = [1, -1, 5, -2, 3], k = 3, return 4.

Explanation:
because the subarray [1, -1, 5, -2] sums to 3 and is the longest.
Given nums = [-2, -1, 2, 1], k = 1, return 2.

Explanation:
because the subarray [-1, 2] sums to 1 and is the longest.
Challenge
Can you do it in O(n) time?

Notice
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

analysis:
preSumIdx saves the first index i such that nums[:i] = target
similar SubarraySumEqualsK
"""
class MaximumSizeSubarraySumEqualsK(object):
    def maxSubArrayLen(self, nums, k):
        pre_sum, res = 0, 0
        pre_sum_idx = {0: -1}
        for i in range(len(nums)):
            pre_sum += nums[i]
            if pre_sum - k in pre_sum_idx:
                res = max(res, i - pre_sum_idx[pre_sum - k])
            if pre_sum not in pre_sum_idx:
                pre_sum_idx[pre_sum] = i
        return res
