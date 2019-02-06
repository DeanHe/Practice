package Stack;

import java.util.Stack;

/*Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23*/
public class BasicCalculator {
	public int calculate(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		Stack<Integer> stack = new Stack<>();
		int len = s.length(), sign = 1, cur = 0, res = 0;
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				cur = cur * 10 + c - '0';
			} else if (c == '+') {
				res += sign * cur;
				sign = 1;
			} else if (c == '-') {
				res += sign * cur;
				sign = -1;
			} else if (sign == '(') {
				stack.push(res);
				stack.push(sign);
				res = 0;
				sign = 1; 
			} else if (c == ')') {
				res += stack.pop();
			}
			sign = s.charAt(i);
			cur = 0;
		}
		return sign;
	}

}
