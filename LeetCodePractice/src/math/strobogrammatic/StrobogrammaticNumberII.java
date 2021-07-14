package math.strobogrammatic;

import java.util.*;

/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.

Example
Example 1:

Input: n = 2, 
Output: ["11","69","88","96"]
Example 2:

Input: n = 1, 
Output: ["0","1","8"]
*/
public class StrobogrammaticNumberII {
	/**
	 * @param n: the length of strobogrammatic number
	 * @return: All strobogrammatic numbers
	 */
	int N;

	public List<String> findStrobogrammatic(int n) {
		// write your code here
		N = n;
		return dfs(n);
	}

	private List<String> dfs(int n) {
		if (n == 0) {
			return new ArrayList<>(Arrays.asList(""));
		}
		if (n == 1) {
			return new ArrayList<>(Arrays.asList("0", "1", "8"));
		}
		List<String> list = dfs(n - 2);
		List<String> res = new ArrayList<>();
		for (String str : list) {
			if (n != N) {
				res.add("0" + str + "0");
			}
			res.add("1" + str + "1");
			res.add("8" + str + "8");
			res.add("6" + str + "9");
			res.add("9" + str + "6");
		}
		return res;
	}
}
