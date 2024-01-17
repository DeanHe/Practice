"""
You are given an integer array nums sorted in non-decreasing order.

Build and return an integer array result with the same length as nums such that result[i] is equal to the summation of absolute differences between nums[i] and all the other elements in the array.

In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).

Example 1:
Input: nums = [2,3,5]
Output: [4,3,5]
Explanation: Assuming the arrays are 0-indexed, then
result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.

Example 2:
Input: nums = [1,4,6,8,10]
Output: [24,15,13,15,21]


Constraints:
2 <= nums.length <= 10^5
1 <= nums[i] <= nums[i + 1] <= 10^4

hints:
1 Absolute difference is the same as max(a, b) - min(a, b). How can you use this fact with the fact that the array is sorted?
2 For nums[i], the answer is (nums[i] - nums[0]) + (nums[i] - nums[1]) + ... + (nums[i] - nums[i-1]) + (nums[i+1] - nums[i]) + (nums[i+2] - nums[i]) + ... + (nums[n-1] - nums[i]).
3 It can be simplified to (nums[i] * i - (nums[0] + nums[1] + ... + nums[i-1])) + ((nums[i+1] + nums[i+2] + ... + nums[n-1]) - nums[i] * (n-i-1)). One can build prefix and suffix sums to compute this quickly.

analysis:
prefix sum on the fly
TC: O(N)
"""
from typing import List


class SumOfAbsoluteDifferencesInaSortedArray:
    def getSumAbsoluteDifferences(self, nums: List[int]) -> List[int]:
        sz = len(nums)
        total = sum(nums)
        pre_sum = 0
        res = []
        for i in range(len(nums)):
            suffix_sum = total - pre_sum - nums[i]
            left_cnt = i
            right_cnt = sz - 1 - i
            res.append(left_cnt * nums[i] - pre_sum + suffix_sum - right_cnt * nums[i])
            pre_sum += nums[i]
        return res