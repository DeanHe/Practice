"""
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "(*)"
Output: true

Example 3:
Input: s = "(*))"
Output: true

Constraints:
1 <= s.length <= 100
s[i] is '(', ')' or '*'.
"""
class ValidParenthesisString:
    def checkValidString(self, s: str) -> bool:
        open_min, open_max = 0, 0
        for i in range(0, len(s)):
            if s[i] == '(':
                open_min += 1
                open_max += 1
            elif s[i] == ')':
                if open_min > 0:
                    open_min -= 1
                open_max -= 1
            else: # for '*'
                if open_min > 0:
                    open_min -= 1
                open_max += 1
            if open_max < 0:
                return False
        return open_min == 0