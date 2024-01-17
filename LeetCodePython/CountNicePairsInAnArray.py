"""
You are given an array nums that consists of non-negative integers. Let us define rev(x) as the reverse of the non-negative integer x. For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it satisfies all of the following conditions:

0 <= i < j < nums.length
nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.

Example 1:
Input: nums = [42,11,1,97]
Output: 2
Explanation: The two pairs are:
 - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.

Example 2:
Input: nums = [13,10,35,24,76]
Output: 4

Constraints:
1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9

hints:
1 The condition can be rearranged to (nums[i] - rev(nums[i])) == (nums[j] - rev(nums[j])).
2 Transform each nums[i] into (nums[i] - rev(nums[i])). Then, count the number of (i, j) pairs that have equal values.
3 Keep a map storing the frequencies of values that you have seen so far. For each i, check if nums[i] is in the map. If it is, then add that count to the overall count. Then, increment the frequency of nums[i].
"""
from collections import defaultdict
from typing import List


class CountNicePairsInAnArray:
    def countNicePairs(self, nums: List[int]) -> int:
        def rev(x):
            res = 0
            while x > 0:
                res = res * 10 + x % 10
                x //= 10
            return res

        res = 0
        MOD = 10 ** 9 + 7
        cnt = defaultdict(int)
        for num in nums:
            x = num - rev(num)
            res = (res + cnt[x]) % MOD
            cnt[x] += 1
        return res

