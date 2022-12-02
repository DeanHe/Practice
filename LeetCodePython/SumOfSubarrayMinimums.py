"""
Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

Example 1:
Input: arr = [3,1,2,4]
Output: 17
Explanation:
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.

Sum is 17.
Example 2:
Input: arr = [11,81,94,43,3]
Output: 444

Constraints:
1 <= arr.length <= 3 * 10^4
1 <= arr[i] <= 3 * 10^4

analysis:
dp[i] means sum of subarrayMins end at arr[i]
"""
from typing import List


class Solution:
    def sumSubarrayMins(self, arr: List[int]) -> int:
        stack, res, mod = [], 0, 10 ** 9 + 7
        dp = [0] * len(arr)
        for i, n in enumerate(arr):
            while stack and n <= arr[stack[-1]]:
                stack.pop()
            if stack:
                dp[i] = dp[stack[-1]] + n * (i - stack[-1])
            else:
                dp[i] = n * (i + 1)
            stack.append(i)
            res += dp[i]
            res %= mod
        return res
