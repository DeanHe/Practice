"""
Given two positive integers left and right, find the two integers num1 and num2 such that:

left <= nums1 < nums2 <= right .
nums1 and nums2 are both prime numbers.
nums2 - nums1 is the minimum amongst all other pairs satisfying the above conditions.
Return the positive integer array ans = [nums1, nums2]. If there are multiple pairs satisfying these conditions, return the one with the minimum nums1 value or [-1, -1] if such numbers do not exist.

A number greater than 1 is called prime if it is only divisible by 1 and itself.

Example 1:
Input: left = 10, right = 19
Output: [11,13]
Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
Since 11 is smaller than 17, we return the first pair.

Example 2:
Input: left = 4, right = 6
Output: [-1,-1]
Explanation: There exists only one prime number in the given range, so the conditions cannot be satisfied.

Constraints:
1 <= left <= right <= 10^6
"""
from collections import deque
from typing import List


class ClosestPrimeNumbersInRange:
    def closestPrimes(self, left: int, right: int) -> List[int]:
        primes = [True] * (right + 1)
        i = 2
        while i * i <= right:
            if primes[i]:
                for j in range(i * i, right + 1, i):
                    primes[j] = False
            i += 1
        last = -1
        res = []
        for i in range(max(2, left), right + 1):
            if primes[i]:
                if last != -1:
                    if len(res) == 0 or i - last < res[1] - res[0]:
                        res = [last, i]
                last = i
        return res if len(res) > 0 else [-1, -1]
