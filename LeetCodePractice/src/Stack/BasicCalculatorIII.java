package Stack;

import java.util.Stack;

/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647]

Example
"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

solution:
to use the interpreter
*/
public class BasicCalculatorIII {
	/**
	 * @param s: the expression string
	 * @return: the answer
	 */
	public int calculate(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		Stack<Integer> stack = new Stack<>();
		int len = s.length();
		int cur = 0;
		char sign = '+';
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				cur = cur * 10 + c - '0';
			} else if (c == '(') {
				int parenthese_count = 0, parenthese_start_pos = i;
				for (; i < len; i++) {
					if (s.charAt(i) == '(') {
						parenthese_count++;
					}
					if (s.charAt(i) == ')') {
						parenthese_count--;
					}
					if (parenthese_count == 0) {
						break;
					}
				}
				cur = calculate(s.substring(parenthese_start_pos + 1, i));
			}
			if ((!Character.isDigit(c) && c != ' ') || i == len - 1) {
				if (sign == '+') {
					stack.push(cur);
				} else if (sign == '-') {
					stack.push(-cur);
				} else if (sign == '*') {
					stack.push(stack.pop() * cur);
				} else if (sign == '/') {
					stack.push(stack.pop() / cur);
				}
				sign = c;
				cur = 0;
			}
		}
		int res = 0;
		for (int i : stack) {
			res += i;
		}
		return res;
	}
}
