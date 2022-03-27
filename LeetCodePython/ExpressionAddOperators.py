"""
Given a string num that contains only digits and an integer target, return all possibilities to add the binary operators '+', '-', or '*' between the digits of num so that the resultant expression evaluates to the target value.



Example 1:

Input: num = "123", target = 6
Output: ["1*2*3","1+2+3"]
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2","2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0*0","0+0","0-0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []

Constraints:
1 <= num.length <= 10
num consists of only digits.
-2^31 <= target <= 2^31 - 1

hint:
Note that a number can contain multiple digits.

Since the question asks us to find all of the valid expressions, we need a way to iterate over all of them. (Hint: Recursion!)

We can keep track of the expression string and evaluate it at the very end. But that would take a lot of time.
Can we keep track of the expression's value as well so as to avoid the evaluation at the very end of recursion?

Think carefully about the multiply operator. It has a higher precedence than the addition and subtraction operators.
1 + 2 = 3
1 + 2 - 4 --> 3 - 4 --> -1
1 + 2 - 4 * 12 --> -1 * 12 --> -12 (WRONG!)
1 + 2 - 4 * 12 --> -1 - (-4) + (-4 * 12) --> 3 + (-48) --> -45 (CORRECT!)

We simply need to keep track of the last operand in our expression and reverse it's effect on the expression's value while considering the multiply operator.

Constraints:
1 <= num.length <= 10
num consists of only digits.
-2^31 <= target <= 2^31 - 1

hint:
1 Note that a number can contain multiple digits.
2 Since the question asks us to find all of the valid expressions, we need a way to iterate over all of them. (Hint: Recursion!)
3 We can keep track of the expression string and evaluate it at the very end. But that would take a lot of time.
Can we keep track of the expression's value as well so as to avoid the evaluation at the very end of recursion?
4 Think carefully about the multiply operator. It has a higher precedence than the addition and subtraction operators.
1 + 2 = 3
1 + 2 - 4 --> 3 - 4 --> -1
1 + 2 - 4 * 12 --> -1 * 12 --> -12 (WRONG!)
1 + 2 - 4 * 12 --> -1 - (-4) + (-4 * 12) --> 3 + (-48) --> -45 (CORRECT!)
5 We simply need to keep track of the last operand in our expression and reverse it's effect on the expression's value while considering the multiply operator.


analysis:
caveat:
0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
for the MULTIPLY, need to reduce the previous operation added to sum, as the previous ops is merged into multiply
TC O(N * 4 ^ N)
"""
from typing import List


class ExpressionAddOperators:
    def addOperators(self, num: str, target: int) -> List[str]:
        res = []

        def dfs(pos, exp, total, op):
            if pos == len(num):
                if total == target:
                    res.append(exp)
                return
            for i in range(pos, len(num)):
                if i != pos and num[pos] == '0':
                    break
                cur = int(num[pos:i + 1])
                if pos == 0:
                    dfs(i + 1, exp + str(cur), total + cur, cur)
                else:
                    dfs(i + 1, exp + '+' + str(cur), total + cur, cur)
                    dfs(i + 1, exp + '-' + str(cur), total - cur, -cur)
                    dfs(i + 1, exp + '*' + str(cur), total - op + op * cur, op * cur)

        dfs(0, '', 0, 0)
        return res
