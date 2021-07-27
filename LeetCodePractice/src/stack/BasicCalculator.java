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
*/
public class BasicCalculator {
	public int calculate(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		Stack<Integer> numStack = new Stack<>();
		Stack<Integer> signStack = new Stack<>();
		int len = s.length();
		int res = 0;
		int cur = 0;
		int sign = 1;
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				cur = cur * 10 + c - '0';
			} else {
				res += sign * cur;
				cur = 0;
				if (c == '+') {
					sign = 1;
				} else if (c == '-') {
					sign = -1;
				} else if (c == '(') {
					// we push the result first, then sign;
					numStack.push(res);
					signStack.push(sign);
					// reset the sign and result for the value in the parenthesis
					sign = 1;
					res = 0;
				} else if (c == ')') {
					cur = res; // is the result calculated in the parenthesis
					res = numStack.pop(); // is the result calculated before the open parenthesis
					sign = signStack.pop(); // is the sign before the open parenthesis
				}
			}
		}
		if (cur != 0) {
			res += sign * cur;
		}
		return res;
	}

}
