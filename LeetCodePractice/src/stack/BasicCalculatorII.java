package stack;

import java.util.Stack;

/*
implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Example
Example 1:

Input:
"3+2*2"
Output:
7
Example 2:

Input:
" 3/2 "
Output:
1
Notice
Do not use the eval built-in library function.
*/
public class BasicCalculatorII {
	/**
	 * @param s:
	 *            the given expression
	 * @return: the result of expression
	 */
	public int calculate(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int len = s.length();
		int res = 0, cur = 0;
		char sign = '+';
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				cur = cur * 10 + c - '0';
			}
			if ((!Character.isDigit(c) && c != ' ') || i == len - 1) { // attn
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
		while(!stack.isEmpty()){
			res += stack.pop();
		}
		return res;
	}
}
