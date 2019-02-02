package Palindrome;

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
