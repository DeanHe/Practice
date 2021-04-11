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
O(n2) time is acceptable. Can you do it in O(n) time.

TC(N^2)
*/
public class LongestPalindromicSubstring {
	int maxLen = 0, start = 0;
	public String longestPalindrome(String s) {
		if(s == null || s.length() < 2){
			return s;
		}
		int len = s.length();
		for(int i = 0; i < len - 1; i++){
			expand(s, i, i);
			expand(s, i, i + 1);
		}
		return s.substring(start, start + maxLen);
	}

	private void expand(String str, int l, int r){
		int len = str.length();
		while(l >= 0 && r < len && str.charAt(l) == str.charAt(r)){
			l--;
			r++;
		}
		if(r - l - 1 > maxLen){
			maxLen = r - l - 1;
			start = l + 1;
		}
	}
}
