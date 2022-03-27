"""
Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.


Example 1:

Input: "()"
Output: 1

Example 2:
Input: "(())"
Output: 2

Example 3:
Input: "()()"
Output: 2

Example 4:
Input: "(()(()))"
Output: 6


Note:

S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50

analysis:
Time Complexity: O(N)
space Complexity: O(1)
"""
class ScoreOfParentheses:
    def scoreOfParentheses(self, s: str) -> int:
        res = left = 0
        for i, c in enumerate(s):
            if c == '(':
                left += 1
            else:
                left -= 1
                if s[i - 1] == '(':
                    res += 1 << left
        return res