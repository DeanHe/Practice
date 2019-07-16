package Backtracking;
/*Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).


The function prototype should be:

bool isMatch(string s, string p)

Example
isMatch("aa","a") is false
isMatch("aa","aa") is true
isMatch("aaa","aa") is false
isMatch("aa", "a*") is true
isMatch("aa", ".*") is true
isMatch("ab", ".*") is true
isMatch("aab", "c*a*b") is true*/
public class RegularExpressionMatching {
	public boolean isMatch(String s, String p) {
		if (p.length() == 0) {
			return s.length() == 0;
		}
		// p.length == 1 is a special case
		// Second character of p is not '*'
		if (p.length() == 1 || p.charAt(1) != '*') {
			// if first character of p is not '.' and p.charAt(0) != s.charAt(0)
			// return false
			// else keep matching
			if (s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0))) {
				return false;
			}
			return isMatch(s.substring(1), p.substring(1));
		} // Second character of p is '*'
		else {
			int i = -1;
			// first character of p equals first character of s keep matching
			while (i < s.length() && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
				if (isMatch(s.substring(i + 1), p.substring(2))) {
					return true;
				}
				i++;
			}
			return false;
		}
	}
	
	public boolean isMatchDP(String s, String p) {
		if(s == null || p == null){
			return false;
		}
		int sLen = s.length();
		int pLen = p.length();
		boolean[][] dp = new boolean[sLen + 1][pLen + 1];
		dp[0][0] = true;
		for(int i = 0; i < pLen; i++){
			if(p.charAt(i) == '*' && dp[0][i]){
				dp[0][i + 1] = true;
			}
		}
		for(int i = 0; i < sLen; i++){
			for(int j = 0; j < pLen; j++){
				if(p.charAt(j) == s.charAt(i)){
					dp[i + 1][j + 1] = dp[i][j];
				}
				if(p.charAt(j) == '.'){
					dp[i + 1][j + 1] = dp[i][j];
				}
				if(p.charAt(j) == '*'){
					if(p.charAt(j-1) != '.'){
						if(p.charAt(j-1) == s.charAt(i)){
							
						} else {
							
						}
					} else {
						
					}
					if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
						//compare p[j - 2] equals s[i]
						dp[i + 1][j + 1] = dp[i + 1][j - 1];
					} else {
						dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j] || dp[i + 1][j - 1];
					}
				}
			}
		}
		return dp[sLen][pLen];
	}
}
