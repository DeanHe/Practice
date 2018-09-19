package Backtracking;

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
}
