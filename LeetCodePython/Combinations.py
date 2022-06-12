"""
Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].

You may return the answer in any order.

Example 1:
Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

Example 2:
Input: n = 1, k = 1
Output: [[1]]

Constraints:
1 <= n <= 20
1 <= k <= n
"""
from typing import List

class Combinations:
    def combine(self, n: int, k: int) -> List[List[int]]:
        res = []

        def dfs(ls, cur):
            if len(ls) == k:
                res.append(ls.copy())
                return
            for i in range(cur, n + 1):
                ls.append(i)
                dfs(ls, i + 1)
                ls.pop()

        dfs([], 1)
        return res