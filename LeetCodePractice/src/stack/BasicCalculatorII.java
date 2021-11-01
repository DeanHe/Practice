package stack;

import java.util.Stack;

/*
Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

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
All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
The answer is guaranteed to fit in a 32-bit integer.

analysis:
push to stack the previous operation value when meet a sign or end of string
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
