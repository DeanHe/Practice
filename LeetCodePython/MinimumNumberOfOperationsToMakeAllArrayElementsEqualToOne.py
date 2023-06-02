"""
You are given a 0-indexed array nums consisiting of positive integers. You can do the following operation on the array any number of times:

Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.
Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.

The gcd of two integers is the greatest common divisor of the two integers.

Example 1:
Input: nums = [2,6,3,4]
Output: 4
Explanation: We can do the following operations:
- Choose index i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums = [2,6,1,4].
- Choose index i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums = [2,1,1,4].
- Choose index i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums = [1,1,1,4].
- Choose index i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums = [1,1,1,1].

Example 2:
Input: nums = [2,10,6,14]
Output: -1
Explanation: It can be shown that it is impossible to make all the elements equal to 1.

Constraints:
2 <= nums.length <= 50
1 <= nums[i] <= 10^6

hints:
1 Note that if you have at least one occurrence of 1 in the array, then you can make all the other elements equal to 1 with one operation each.
2 Try finding the shortest subarray with a gcd equal to 1.
"""
from collections import Counter
from typing import List


class Solution:
    def minOperations(self, nums: List[int]) -> int:
        def gcd(a, b):
            if a == 0:
                return b
            return gcd(b % a, a)

        size = len(nums)
        cnt = Counter(nums)
        if 1 in cnt:
            return size - cnt[1]
        res = float('inf')
        for l in range(size):
            g = nums[l]
            for r in range(l + 1, size):
                g = gcd(g, nums[r])
                if g == 1:
                    res = min(res, r - l + size - 1)
        return res if res != float('inf') else -1
