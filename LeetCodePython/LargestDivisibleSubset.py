"""
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

Example 1:
Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.

Example 2:
Input: nums = [1,2,4,8]
Output: [1,2,4,8]

Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 10^9
All the integers in nums are unique.

analysis:
similar to Longest Increasing subsequence
1.sort all array elements in increasing order. The purpose of sorting is to make sure that all divisors of an element appear before it.
2.Create an array divCount[] of same size as input array. divCount[i] stores size of divisible subset ending with arr[i] (In sorted array). The minimum value of divCount[i] would be 1.
3.Traverse all array elements. For every element, find a divisor arr[j] with largest value of divCount[j] and store the value of divCount[i] as divCount[j] + 1.

TC O(N ^ 2)
"""
from typing import List


class LargestDivisibleSubset:
    def largestDivisibleSubset(self, nums: List[int]) -> List[int]:
        res = []
        sz = len(nums)
        nums.sort()
        # means max length of subset ending with nums[i]
        # the previous index of element i in the largestDivisibleSubset ends with element i
        dp = [(1, -1)] * sz
        max_len = 1
        end = 0
        for i in range(1, sz):
            for j in range(i):
                if nums[i] % nums[j] == 0:
                    dp[i] = max(dp[i], (dp[j][0] + 1, j))
            if max_len < dp[i][0]:
                max_len = dp[i][0]
                end = i
        while end != -1:
            res.append(nums[end])
            end = dp[end][1]
        return res