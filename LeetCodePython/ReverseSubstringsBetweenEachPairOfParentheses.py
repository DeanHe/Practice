"""
You are given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.

Example 1:
Input: s = "(abcd)"
Output: "dcba"
Example 2:

Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.

Example 3:
Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.

Constraints:
1 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It is guaranteed that all parentheses are balanced.

hints:
1 Find all brackets in the string.
2 Does the order of the reverse matter ?
3 The order does not matter.

analysis:
stack
TC: O(N)
"""


class ReverseSubstringsBetweenEachPairOfParentheses:
    def reverseParentheses(self, s: str) -> str:
        res = []
        sz = len(s)
        pair = [-1] * sz
        stack = []
        for i, c in enumerate(s):
            if c == '(':
                stack.append(i)
            elif c == ')':
                pre = stack.pop()
                pair[pre] = i
                pair[i] = pre

        direction = 1
        i = 0
        while i < sz:
            if s[i] == '(' or s[i] == ')':
                i = pair[i]
                direction = -direction
            else:
                res.append(s[i])
            i += direction
        return ''.join(res)
