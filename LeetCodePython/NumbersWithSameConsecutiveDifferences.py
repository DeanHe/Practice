"""
Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.

Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.

You may return the answer in any order.

Example 1:
Input: n = 3, k = 7
Output: [181,292,707,818,929]
Explanation: Note that 070 is not a valid number, because it has leading zeroes.

Example 2:
Input: n = 2, k = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]

Constraints:
2 <= n <= 9
0 <= k <= 9
"""
from typing import List


class NumbersWithSameConsecutiveDifferences:
    def numsSameConsecDiff(self, n: int, k: int) -> List[int]:
        res = []
        if n == 1:
            res.append(1)

        def dfs(num, i):
            if i == 0:
                res.append(num)
                return
            for d in range(0, 10):
                if num == 0:
                    if d != 0:
                        dfs(d, i - 1)
                else:
                    last_digit = num % 10
                    if abs(last_digit - d) == k:
                        dfs(num * 10 + d, i - 1)

        dfs(0, n)
        return res
