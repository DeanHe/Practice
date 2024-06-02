"""
You are given an array nums consisting of positive integers where all integers have the same number of digits.

The digit difference between two integers is the count of different digits that are in the same position in the two integers.

Return the sum of the digit differences between all pairs of integers in nums.

Example 1:
Input: nums = [13,23,12]
Output: 4
Explanation:
We have the following:
- The digit difference between 13 and 23 is 1.
- The digit difference between 13 and 12 is 1.
- The digit difference between 23 and 12 is 2.
So the total sum of digit differences between all pairs of integers is 1 + 1 + 2 = 4.

Example 2:
Input: nums = [10,10,10,10]
Output: 0
Explanation:
All the integers in the array are the same. So the total sum of digit differences between all pairs of integers will be 0.

Constraints:
2 <= nums.length <= 10^5
1 <= nums[i] < 10^9
All integers in nums have the same number of digits.

hints:
1 You can solve the problem for digits that are on the same position separately, and then sum up all the answers.
2 For each position, count the number of occurrences of each digit from 0 to 9 that appear on that position.
3 Let c be the number of occurrences of a digit on a position, that will contribute with c * (n - c) to the final answer, where n is the number of integers in nums.
"""
from typing import List


class Solution:
    def sumDigitDifferences(self, nums: List[int]) -> int:
        sz = len(nums)
        res = 0
        # suppose nums[i] has less than 10 total length
        digit_cnt = [[0] * 10 for _ in range(10)]
        for num in nums:
            i = 0
            while num > 0:
                digit_cnt[i][num % 10] += 1
                num //= 10
                i += 1
        for cnt in digit_cnt:
            for d in range(10):
                res += cnt[d] * (sz - cnt[d])
        return res // 2

