package DP;

public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
		int length = s.length();
		int begin = 0;
		int maxlen = 1;
		int[][] table = new int[length][length];

		for (int i = 0; i < length; i++) {
			table[i][i] = 1;
		}

		for (int i = 0; i < length - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				table[i][i + 1] = 1;
				maxlen = 2;
				begin = i;
			}
		}

		for (int len = 3; len <= length; len++) {
			for (int i = 0; i + len - 1 < length; i++) {
				int j = i + len - 1;
				if (s.charAt(i) == s.charAt(j) && table[i + 1][j - 1] == 1) {
					table[i][j] = 1;
					if (len > maxlen) {
						maxlen = len;
						begin = i;
					}
				}
			}
		}

		return s.substring(begin, begin + maxlen);
	}
}
