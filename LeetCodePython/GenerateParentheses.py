"""
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]


Constraints:
1 <= n <= 8
"""
from typing import List


class GenerateParentheses:
    def generateParenthesis(self, n: int) -> List[str]:
        res = []

        def dfs(left, right, cur):
            if left > right:
                return

            if left == 0 and right == 0:
                res.append(cur)
                return

            if left > 0:
                dfs(left - 1, right, cur + '(')

            if right > 0:
                dfs(left, right - 1, cur + ')')

        dfs(n, n, "")
        return res
