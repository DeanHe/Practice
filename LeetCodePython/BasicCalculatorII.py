"""
Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2^31, 2^31 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:
Input: s = "3+2*2"
Output: 7

Example 2:
Input: s = " 3/2 "
Output: 1

Example 3:
Input: s = " 3+5 / 2 "
Output: 5


Constraints:
1 <= s.length <= 3 * 10^5
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.

Analysis:
TC: O(N)
SC: there is an O(1) solution
"""

class BasicCalculatorII:
    def calculate(self, s: str) -> int:
        res, last, cur, sign = 0, 0, 0, '+'
        for i, c in enumerate(s):
            if c.isdigit():
                cur = cur * 10 + ord(c) - ord('0')
            if (not c.isdigit() and c != ' ') or i == len(s) - 1:
                if sign == '+':
                    res += last
                    last = cur
                elif sign == '-':
                    res += last
                    last = -cur
                elif sign == '*':
                    last *= cur
                elif sign == '/':
                    if last // cur < 0 and last % cur != 0:
                        last = last // cur + 1
                    else:
                        last = last // cur
                sign = c
                cur = 0
        res += last
        return res

    def calculate(self, s: str) -> int:
        stack = []
        res, cur, sign = 0, 0, '+'
        for i, c in enumerate(s):
            if c.isdigit():
                cur = cur * 10 + ord(c) - ord('0')
            if (not c.isdigit() and c != ' ') or i == len(s) - 1:
                if sign == '+':
                    stack.append(cur)
                elif sign == '-':
                    stack.append(-cur)
                elif sign == '*':
                    stack.append(stack.pop() * cur)
                elif sign == '/':
                    tmp = stack.pop()
                    if tmp // cur < 0 and tmp % cur != 0:
                        stack.append(tmp // cur + 1)
                    else:
                        stack.append(tmp // cur)
                sign = c
                cur = 0
        while stack:
            res += stack.pop()
        return res
