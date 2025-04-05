"""
ou are given a 0-indexed array nums consisting of positive integers. You can choose two indices i and j, such that i != j, and the sum of digits of the number nums[i] is equal to that of nums[j].

Return the maximum value of nums[i] + nums[j] that you can obtain over all possible indices i and j that satisfy the conditions.

Example 1:
Input: nums = [18,43,36,13,7]
Output: 54
Explanation: The pairs (i, j) that satisfy the conditions are:
- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
So the maximum sum that we can obtain is 54.

Example 2:
Input: nums = [10,12,19,14]
Output: -1
Explanation: There are no two numbers that satisfy the conditions, so we return -1.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9

hints:
1 What is the largest possible sum of digits a number can have?
2 Group the array elements by the sum of their digits, and find the largest two elements of each group.

Analysis:
TC: O(NlogM) where Let N be the size of nums, and let M be the maximum number in nums.
"""
import collections
import heapq
from typing import List


class MaxSumOfaPairWithEqualSumOfDigits:
    def maximumSum(self, nums: List[int]) -> int:
        digit_sum_max = {}
        res = 0

        def digit_sum(x):
            total = 0
            while x > 0:
                total += x % 10
                x //= 10
            return total

        for n in nums:
            total = digit_sum(n)
            if total not in digit_sum_max:
                digit_sum_max[total] = n
            else:
                res = max(res, n + digit_sum_max[total])
                digit_sum_max[total] = max(digit_sum_max[total], n)
        return res if res > 0 else -1

