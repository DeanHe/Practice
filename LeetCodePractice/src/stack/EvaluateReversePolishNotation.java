package stack;

import java.util.*;

/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.
http://en.wikipedia.org/wiki/Reverse_Polish_notation
Example
["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

analysis:
first pop element is b
*/
public class EvaluateReversePolishNotation {
	/**
	 * @param tokens The Reverse Polish Notation
	 * @return the value
	 */
	public int evalRPN(String[] tokens) {
		String operators = "+-*/";
		Stack<Integer> stack = new Stack<>();
		for (String s : tokens) {
			if (!operators.contains(s)) {
				stack.push(Integer.valueOf(s));
			} else {
				int b = stack.pop();
				int a = stack.pop();
				if (s.equals("+")) {
					stack.push(a + b);
				} else if (s.equals("-")) {
					stack.push(a - b);
				} else if (s.equals("*")) {
					stack.push(a * b);
				} else if (s.equals("/")) {
					stack.push(a / b);
				}
			}
		}
		return stack.pop();
	}
}
