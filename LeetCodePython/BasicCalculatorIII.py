"""
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647]

Example
"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
"""


class BasicCalculatorIII:
    def calculate(self, s: str) -> int:
        order = {'+': 0, '-':0, '*': 1, '/':1}
        stack = []
        num = 0

        for c in s:
            if c.isdigit():
                num = num * 10 + int(c)

            if c in '+-*/':
                while stack and stack[-1] != '(' and order[stack[-1]] >= order[c]:
                    op = stack.pop()
                    pre = stack.pop()
                    num = self.calc(pre, num, op)
                stack.append(num)
                stack.append(c)
                num = 0

            if c is '(':
                stack.append(c)

            if c is ')':
                while stack[-1] != '(':
                    op = stack.pop()
                    pre = stack.pop()
                    num = self.calc(pre, num, op)
                stack.pop()

        while stack:
            op = stack.pop()
            pre = stack.pop()
            num = self.calc(pre, num, op)

        return num


    def calc(self, a, b, op):
        if op == '+':
            return a + b
        if op == '-':
            return a - b
        if op == '*':
            return a * b
        if op == '/':
            return a // b



