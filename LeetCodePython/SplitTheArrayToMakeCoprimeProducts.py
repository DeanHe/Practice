"""
You are given a 0-indexed integer array nums of length n.

A split at an index i where 0 <= i <= n - 2 is called valid if the product of the first i + 1 elements and the product of the remaining elements are coprime.

For example, if nums = [2, 3, 3], then a split at the index i = 0 is valid because 2 and 9 are coprime, while a split at the index i = 1 is not valid because 6 and 3 are not coprime. A split at the index i = 2 is not valid because i == n - 1.
Return the smallest index i at which the array can be split validly or -1 if there is no such split.

Two values val1 and val2 are coprime if gcd(val1, val2) == 1 where gcd(val1, val2) is the greatest common divisor of val1 and val2.

Example 1:
Input: nums = [4,7,8,15,3,5]
Output: 2
Explanation: The table above shows the values of the product of the first i + 1 elements, the remaining elements, and their gcd at each index i.
The only valid split is at index 2.

Example 2:
Input: nums = [4,7,15,8,3,5]
Output: -1
Explanation: The table above shows the values of the product of the first i + 1 elements, the remaining elements, and their gcd at each index i.
There is no valid split.

Constraints:
n == nums.length
1 <= n <= 10^4
1 <= nums[i] <= 10^6

hints:
1 Find the prime factorization of the left and right sides and check if they share a prime divisor.
"""
from collections import defaultdict
from typing import List


class SplitTheArrayToMakeCoprimeProducts:
    def findValidSplit(self, nums: List[int]) -> int:
        def factorize(num):
            res = []
            i = 2
            while i < 1000:
                if num % i == 0:
                    res.append(i)
                    while num % i == 0:
                        num //= i
                i += 1 + (i % 2)
            if num > 1:
                res.append(num)
            return res

        pre, post = defaultdict(int), defaultdict(int)
        for n in nums:
            for f in factorize(n):
                post[f] += 1
        for i in range(len(nums) - 1):
            for f in factorize(nums[i]):
                post[f] -= 1
                if post[f] == 0:
                    if f in pre:
                        del pre[f]
                else:
                    pre[f] += 1
                if not pre:
                    return i
        return -1


