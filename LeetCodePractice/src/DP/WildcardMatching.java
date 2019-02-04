package DP;

import java.util.ArrayList;
import java.util.List;

/*Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false*/
public class WildcardMatching {
	/**
	 * @param s:
	 *            A string
	 * @param p:
	 *            A string includes "?" and "*"
	 * @return: A boolean
	 */
	public boolean isMatch(String s, String p) {
		// write your code here
		int sLen = s.length();
		int pLen = p.length();
		// where dp[i][j] means the first i characters in string s matches the
		// first characters of string p.
		boolean[][] dp = new boolean[sLen + 1][pLen + 1];
		// init
		dp[0][0] = true;
		for (int j = 1; j <= pLen; j++) {
			if (p.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j - 1];
			}
		}
		/*
		 * Transit function: -- If p.charAt(j - 1) != '*', then dp[i][j] = dp[i
		 * - 1][j - 1] IFF s.charAt(i) == p.charAt(j) || p.charAt(j) == '?' --
		 * If p.charAt(j - 1) == '*', then -- dp[i][j] = dp[i - 1][j - 1] || //
		 * Match 1 character = dp[i][j - 1] || // Match 0 character = dp[i -
		 * 1][j] // Match any sequence of characters
		 */
		for (int i = 1; i <= sLen; i++) {
			for (int j = 1; j <= pLen; j++) {
				if (p.charAt(j - 1) != '*') {
					if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1];
					}
				} else {
					dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
				}
			}
		}
		return dp[sLen][pLen];
	}

	// with O(1) space, and save star matched substrings
	public boolean comparison(String str, String pattern) {
		int s = 0, p = 0, match = 0, starIdx = -1;
		List<String> starMatch = new ArrayList<>();
		StringBuilder temp = new StringBuilder();
		while (s < str.length()) {
			// advancing both pointers
			if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
				if (temp.length() != 0) {
					starMatch.add(temp.toString());
					temp.setLength(0);
				}
				s++;
				p++;
			}
			// * found, only advancing pattern pointer
			else if (p < pattern.length() && pattern.charAt(p) == '*') {
				starIdx = p;
				match = s;
				temp.append(str.charAt(s));
				p++;
			}
			// last pattern pointer was *, advancing string pointer
			else if (starIdx != -1) {
				p = starIdx + 1;
				match++;
				s = match;
				if (s < str.length() && str.charAt(s) != pattern.charAt(p)) {
					temp.append(str.charAt(s));
				}
			}
			// current pattern pointer is not star, last patter pointer was not
			// *
			// characters do not match
			else
				return false;
		}
		if (temp.length() != 0) {
			starMatch.add(temp.toString());
			temp.setLength(0);
		}
		// check for remaining characters in pattern
		while (p < pattern.length() && pattern.charAt(p) == '*')
			p++;

		return p == pattern.length();
	}
}
