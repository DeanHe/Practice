"""
Given a digit string excluded '0' and '1', return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.

1
2ABC
3DEF
4GHI
5JKL
6MNO
7PQRS
8TUV
9WXYZ
Example
Example 1:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
Explanation:
'2' could be 'a', 'b' or 'c'
'3' could be 'd', 'e' or 'f'
Example 2:

Input: "5"
Output: ["j", "k", "l"]

Constraints:
0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
"""
from typing import List


class LetterCombinationsofaPhoneNumber:
    def letterCombinations(self, digits: str) -> List[str]:
        matches = ["abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"]
        res = []
        if len(digits) == 0:
            return res

        def dfs(cur):
            if len(cur) == len(digits):
                res.append(cur)
                return
            i = len(cur)
            letters = matches[int(digits[i]) - 2]
            for _, c in enumerate(letters):
                dfs(cur + c)

        dfs("")
        return res
