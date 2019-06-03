package Palindrome;
/*Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Note:

The input string length won't exceed 1000.
*/
public class PalindromicSubstrings {
	public int countSubstrings(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}
		int ans = 0;
		for(int i = 0; i < s.length(); i++){
			ans += countPalindrome(s, i, i);
			ans += countPalindrome(s, i, i + 1);
		}
		return ans;
	}
	private int countPalindrome(String s, int start, int end){
		int count = 0;
		while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
			count++;
			start--;
			end++;
		}
		return count;
	}
}
