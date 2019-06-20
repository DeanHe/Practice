package Palindrome;
/*Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".*/
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
    	int len = s.length();
    	int[][] dp = new int[len][len]; // dp[i][j] means longest palindrome length in s[i:j]
    	for(int i = 0; i < len; i++){
    		dp[i][i] = 1;
    	}
    	for(int i = 0; i + 1 < len; i++){
    		if(s.charAt(i) == s.charAt(i + 1)){
    			dp[i][i + 1] = 2;
    		} else {
    			dp[i][i + 1] = 1;
    		}
    	}
    	for(int l = 3; l <= len; l++){
    		for(int i = 0; i + 1 < len; i++){
    			int j = i + l - 1;
        		if(s.charAt(i + 1) == s.charAt(j - 1)){
        			dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
        		} else {
        			int temp = Math.max(dp[i + 1][j], dp[i][j - 1]);
        			dp[i][j] = Math.max(dp[i][j], temp);
        		}
        	}
    	}
    	return dp[0][len - 1];
    }
}
