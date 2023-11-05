"""
You are given three integers n, m and k. Consider the following algorithm to find the maximum element of an array of positive integers:


You should build the array arr which has the following properties:

arr has exactly n integers.
1 <= arr[i] <= m where (0 <= i < n).
After applying the mentioned algorithm to arr, the value search_cost is equal to k.
Return the number of ways to build the array arr under the mentioned conditions. As the answer may grow large, the answer must be computed modulo 109 + 7.

Example 1:
Input: n = 2, m = 3, k = 1
Output: 6
Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]

Example 2:
Input: n = 5, m = 2, k = 3
Output: 0
Explanation: There are no possible arrays that satisify the mentioned conditions.

Example 3:
Input: n = 9, m = 1, k = 1
Output: 1
Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]

Constraints:
1 <= n <= 50
1 <= m <= 100
0 <= k <= n

hints:
1 Use dynamic programming approach. Build dp table where dp[a][b][c] is the number of ways you can start building the array starting from index a where the search_cost = c and the maximum used integer was b.
2 Recursively, solve the small sub-problems first. Optimize your answer by stopping the search if you exceeded k changes.

analysis:
TC: O(n⋅m^2⋅k)
"""
from functools import cache


class BuildArrayWhereYouCanFindTheMaximumExactlyKComparisons:
    def numOfArrays(self, n: int, m: int, k: int) -> int:
        MOD = 10 ** 9 + 7

        @cache
        def dfs(pos, max_val, cost):
            if cost > k:
                return 0
            if pos == n:
                if cost == k:
                    return 1
                return 0
            res = 0
            for num in range(1, m + 1):
                if num <= max_val:
                    res = (res + dfs(pos + 1, max_val, cost)) % MOD
                else:
                    res = (res + dfs(pos + 1, num, cost + 1)) % MOD
            return res

        return dfs(0, 0, 0)
