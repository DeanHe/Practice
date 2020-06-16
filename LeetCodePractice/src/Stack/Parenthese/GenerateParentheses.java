package Stack.Parenthese;

import java.util.*;
/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example
Given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/
public class GenerateParentheses {
	/**
     * @param n: n pairs
     * @return: All combinations of well-formed parentheses
     */
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		if (n < 1) {
			return res;
		}
		String temp = new String();
		helper(n, n, res, temp);
		return res;
	}

	public void helper(int left, int right, List<String> res, String temp) {
		// deal with ")("
		if (left > right) {
			return;
		}
		if (left == 0 && right == 0) {
			res.add(temp);
			return;
		}
		if (left > 0) {
			helper(left - 1, right, res, temp + "(");
		}
		if (right > 0) {
			helper(left, right - 1, res, temp + ")");
		}
	}
}
