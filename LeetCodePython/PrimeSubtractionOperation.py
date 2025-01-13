"""
You are given a 0-indexed integer array nums of length n.

You can perform the following operation as many times as you want:

Pick an index i that you haven’t picked before, and pick a prime p strictly less than nums[i], then subtract p from nums[i].
Return true if you can make nums a strictly increasing array using the above operation and false otherwise.

A strictly increasing array is an array whose each element is strictly greater than its preceding element.

Example 1:
Input: nums = [4,9,6,10]
Output: true
Explanation: In the first operation: Pick i = 0 and p = 3, and then subtract 3 from nums[0], so that nums becomes [1,9,6,10].
In the second operation: i = 1, p = 7, subtract 7 from nums[1], so nums becomes equal to [1,2,6,10].
After the second operation, nums is sorted in strictly increasing order, so the answer is true.

Example 2:
Input: nums = [6,8,11,12]
Output: true
Explanation: Initially nums is sorted in strictly increasing order, so we don't need to make any operations.

Example 3:
Input: nums = [5,8,3]
Output: false
Explanation: It can be proven that there is no way to perform operations to make nums sorted in strictly increasing order, so the answer is false.


Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 1000
nums.length == n

hints:
1 Think about if we have many primes to subtract from nums[i]. Which prime is more optimal?
2 The most optimal prime to subtract from nums[i] is the one that makes nums[i] the smallest as possible and greater than nums[i-1].

analysis:
TC: O(N * M)
"""
from typing import List


class PrimeSubtractionOperation:
    def primeSubOperation(self, nums: List[int]) -> bool:

        def get_primes(n):
            prime = [True] * (n + 1)
            prime[0] = prime[1] = False
            for i in range(2, int(n ** 0.5) + 1):
                if prime[i]:
                    for j in range(i * i, n + 1, i):
                        prime[j] = False
            return [i for i, val in enumerate(prime) if val]

        primes = get_primes(1000)
        n = len(nums)
        last = nums[-1]
        for i in range(n - 2, -1, -1):
            if nums[i] < last:
                last = nums[i]
            else:
                can_subtract = False
                for p in primes:
                    if 0 < nums[i] - p < last:
                        last = nums[i] - p
                        can_subtract = True
                        break
                if not can_subtract:
                    return False
        return True



