"""
We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.

Example 1:
Input: n = 1, k = 1
Output: 0
Explanation: row 1: 0

Example 2:
Input: n = 2, k = 1
Output: 0
Explanation:
row 1: 0
row 2: 01

Example 3:
Input: n = 2, k = 2
Output: 1
Explanation:
row 1: 0
row 2: 01

Constraints:
1 <= n <= 30
1 <= k <= 2^(n - 1)

hints:
1 Try to represent the current (N, K) in terms of some (N-1, prevK). What is prevK ?
"""

class KthSymbolInGrammar:
    def kthGrammar(self, n: int, k: int) -> int:
        def dfs(row, i):
            if row == 1:
                return 0

            length = 2 ** (row - 1)
            half_len = length // 2
            if half_len < i:
                return 1 - dfs(row, i - half_len)
            else:
                return dfs(row - 1, i)

        return dfs(n, k)