"""
You are given an integer array nums and a positive integer k. Return the sum of the maximum and minimum elements of all subsequences of nums with at most k elements.

A non-empty subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

Since the answer may be very large, return it modulo 10^9 + 7.

Example 1:
Input: nums = [1,2,3], k = 2
Output: 24
Explanation:
The subsequences of nums with at most 2 elements are:

Subsequence	Minimum	Maximum	Sum
[1]	1	1	2
[2]	2	2	4
[3]	3	3	6
[1, 2]	1	2	3
[1, 3]	1	3	4
[2, 3]	2	3	5
Final Total	 	 	24
The output would be 24.

Example 2:
Input: nums = [5,0,6], k = 1
Output: 22
Explanation:
For subsequences with exactly 1 element, the minimum and maximum values are the element itself. Therefore, the total is 5 + 5 + 0 + 0 + 6 + 6 = 22.

Example 3:
Input: nums = [1,1,1], k = 2
Output: 12
Explanation:
The subsequences [1, 1] and [1] each appear 3 times. For all of them, the minimum and maximum are both 1. Thus, the total is 12.


Constraints:
1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
1 <= k <= min(100, nums.length)

hints:
1 Sort the array.

Analysis:
The total of number of times nums[i] is maximum would be the same as nums[n - i - 1] is minimum.
the count is to find number of ways to choose at most k - 1 elements from number of elements from we can choose.
"""
from math import comb
from typing import List


class MaximumAndMinimumSumsOfAtMostSizeKSubsequences:
    def minMaxSums(self, nums: List[int], k: int) -> int:
        res = 0
        MOD = 10 ** 9 + 7
        nums.sort()
        cnt = 1
        for i in range(len(nums)):
            # add min count and max count
            res += cnt * (nums[i] + nums[-i-1])
            cnt = 2 * cnt - comb(i, k - 1)
        return res % MOD
