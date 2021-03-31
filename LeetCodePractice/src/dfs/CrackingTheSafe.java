package dfs;

import java.util.HashSet;
import java.util.Set;

/*
There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.
You can keep inputting the password, the password will automatically be matched against the last n digits entered.
For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.
Please return any string of minimum length that is guaranteed to open the box after the entire string is inputed.

Example 1:
Input: n = 1, k = 2
Output: "01"
Note: "10" will be accepted too.
Example 2:
Input: n = 2, k = 2
Output: "00110"
Note: "01100", "10011", "11001" will be accepted too.
Note:
n will be in the range [1, 4].
k will be in the range [1, 10].
k^n will be at most 4096.

analysis:
To make the input password as short as possible, we'd better make each possible length-n combination on digits [0..k-1] occurs exactly once as a substring of the password.
The existence of such a password is proved by De Bruijn sequence:
A de Bruijn sequence of order n on a size-k alphabet A is a cyclic sequence in which every possible length-n string on A occurs exactly once as a substring.
It has length k^n, which is also the number of distinct substrings of length n on a size-k alphabet; de Bruijn sequences are therefore optimally short.
*/
public class CrackingTheSafe {
	public String crackSafe(int n, int k) {
		StringBuilder pwd = new StringBuilder();
		for (int i = 0; i < n; i++) {
			pwd.append('0');
		}
		int totalComb = (int) Math.pow(k, n);
		Set<String> visited = new HashSet<>();
		visited.add(pwd.toString());
		dfs(pwd, visited, totalComb, n, k);
		return pwd.toString();
	}

	private boolean dfs(StringBuilder pwd, Set<String> visited, int totalComb, int n, int k) {
		// Base case: all n-length combinations among digits 0..k-1 are visited. 
		if (visited.size() == totalComb) {
			return true;
		}
		String lastDigits = pwd.substring(pwd.length() - n + 1); // Last n-1 digits of pwd.
		for (int i = 0; i < k; i++) {
			char c = (char) ('0' + i);
			String temp = lastDigits + c;
			if (!visited.contains(temp)) {
				visited.add(temp);
				pwd.append(c);
				if(dfs(pwd, visited, totalComb, n, k)){
					return true;
				}
				pwd.deleteCharAt(pwd.length() - 1);
				visited.remove(temp);
			}
		}
		return false;
	}
}
