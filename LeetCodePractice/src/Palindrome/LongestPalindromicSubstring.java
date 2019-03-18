package Palindrome;
/*Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

Example
Example 1:

Input:"abcdzdcab"
Output:"cdzdc"
Example 2:

Input:"aba"
Output:"aba"
Challenge
O(n2) time is acceptable. Can you do it in O(n) time.*/
public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
		int length = s.length();
		int begin = 0;
		int maxlen = 1;
		boolean[][] table = new boolean[length][length];

		for (int i = 0; i < length; i++) {
			table[i][i] = true;
		}

		for (int i = 0; i < length - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				table[i][i + 1] = true;
				maxlen = 2;
				begin = i;
			}
		}

		for (int len = 3; len <= length; len++) {
			for (int i = 0; i + len - 1 < length; i++) {
				int j = i + len - 1;
				if (s.charAt(i) == s.charAt(j) && table[i + 1][j - 1]) {
					table[i][j] = true;
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
