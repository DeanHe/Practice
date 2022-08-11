"""
Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.

We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.

Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.

Example 1:
Input: arr = [2,4]
Output: 3
Explanation: We can make these trees: [2], [4], [4, 2, 2]

Example 2:
Input: arr = [2,4,5,10]
Output: 7
Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].

Constraints:
1 <= arr.length <= 1000
2 <= arr[i] <= 109
All the values of arr are unique.
"""
from typing import List


class BinaryTreesWithFactors:
    def numFactoredBinaryTrees(self, arr: List[int]) -> int:
        MOD = 10 ** 9 + 7
        n = len(arr)
        arr.sort()
        dp = [0] * n
        idx = {}
        for i, val in enumerate(arr):
            idx[val] = i
        for i in range(0, n):
            for j in range(0, i):
                if arr[i] % arr[j] == 0:
                    right = arr[i] // arr[j]
                    if right in idx:
                        dp[i] = (dp[i] + dp[j] * dp[idx[right]]) % MOD
        return sum(dp) % MOD