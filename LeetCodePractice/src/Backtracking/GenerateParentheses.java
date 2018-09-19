package Backtracking;

import java.util.*;

public class GenerateParentheses {
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<String>();
		if (n < 1) {
			return res;
		} else {
			String temp = new String();
			helper(n, n, res, temp);
			return res;
		}
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
