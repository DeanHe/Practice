package stack;

import java.util.Stack;

/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23

analysis:
push previous res to stack when meet open parenthesis
pop previous res to stack when meet close parenthesis
*/
public class BasicCalculator {
	public int calculate(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int res = 0, cur = 0, sign = 1;
		Stack<Integer> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				cur = cur * 10 + c - '0';
			} else {
				if (c == '+') {
					res += sign * cur;
					cur = 0;
					sign = 1;
				} else if (c == '-') {
					res += sign * cur;
					cur = 0;
					sign = -1;
				} else if (c == '(') {
					// we push the result first, then sign;
					stack.push(res);
					stack.push(sign);
					// reset the sign and result for the value in the parenthesis
					sign = 1;
					res = 0;
				} else if (c == ')') {
					res += sign * cur;
					cur = 0;
					res *= stack.pop(); // stack.pop() is the sign before the parenthesis
					res += stack.pop(); // stack.pop() now is the result calculated before the parenthesis
				}
			}
		}
		if (cur != 0) {
			res += sign * cur;
		}
		return res;
	}

}
