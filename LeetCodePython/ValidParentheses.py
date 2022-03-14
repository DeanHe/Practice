"""
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false


Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
"""
import collections


class ValidParentheses:
    def isValid(self, s: str) -> bool:
        pair = {')': '(', '}': '{', ']': '['}
        sl = list(s)
        deq = collections.deque()
        for c in sl:
            if c in pair:
                if len(deq) > 0 and deq[-1] == pair[c]:
                    deq.pop()
                else:
                    return False
            else:
                deq.append(c)
        return len(deq) == 0
