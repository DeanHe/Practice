"""
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return all the possible results. You may return the answer in any order.

Example 1:
Input: s = "()())()"
Output: ["(())()","()()()"]

Example 2:
Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]

Example 3:
Input: s = ")("
Output: [""]


Constraints:
1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.

hint:
1 Since we don't know which of the brackets can possibly be removed, we try out all the options!
2 We can use recursion to try out all possibilities for the given expression. For each of the brackets, we have 2 options:
We keep the bracket and add it to the expression that we are building on the fly during recursion.
OR, we can discard the bracket and move on.
3 The one thing all these valid expressions have in common is that they will all be of the same length i.e. as compared to the original expression, all of these expressions will have the same number of characters removed. Can we somehow find the number of misplaced parentheses and use it in our solution?
4 For every left parenthesis, we should have a corresponding right parenthesis. We can make use of two counters which keep track of misplaced left and right parenthesis and in one iteration we can find out these two values.
0 1 2 3 4 5 6 7
( ) ) ) ( ( ( )
i = 0, left = 1, right = 0
i = 1, left = 0, right = 0
i = 2, left = 0, right = 1
i = 3, left = 0, right = 2
i = 4, left = 1, right = 2
i = 5, left = 2, right = 2
i = 6, left = 3, right = 2
i = 7, left = 2, right = 2
We have 2 misplaced left and 2 misplaced right parentheses.
5 We found out that the exact number of left and right parenthesis that has to be removed to get a valid expression. So, e.g. in a 1000 parentheses string, if there are 2 misplaced left and 2 misplaced right parentheses, after we are done discarding 2 left and 2 right parentheses, we will have only one option per remaining character in the expression i.e. to consider them. We can't discard them.
"""
from typing import List


class RemoveInvalidParentheses:
    def removeInvalidParentheses(self, s: str) -> List[str]:
        res = []
        extra_left, extra_right = 0, 0
        for c in s:
            if c == '(':
                extra_left += 1
            elif c == ')':
                if extra_left > 0:
                    extra_left -= 1
                else:
                    extra_right += 1

        def dfs(cur, left_to_remove, right_to_remove, pos, open):
            if left_to_remove < 0 or right_to_remove < 0 or open < 0:
                return
            if pos == len(s):
                if left_to_remove == 0 and right_to_remove == 0 and open == 0:
                    if cur not in res:
                        res.append(cur)
                    return
            c = s[pos]
            if c == '(':
                dfs(cur, left_to_remove - 1, right_to_remove, pos + 1, open)
                dfs(cur + str('('), left_to_remove, right_to_remove, pos + 1, open + 1)
            elif c == ')':
                dfs(cur, left_to_remove, extra_right - 1, pos + 1, open)
                dfs(cur + str(')'), left_to_remove, right_to_remove, pos + 1, open - 1)
            else:
                dfs(cur + str('('), left_to_remove, right_to_remove, pos + 1, open)

        dfs("", extra_left, extra_right, 0, 0)
        return res