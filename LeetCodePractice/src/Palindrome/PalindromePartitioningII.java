package Palindrome;

/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

cut[i] is the minimum of cut[j - 1] + 1 (j <= i), if [j, i] is palindrome.
If [j, i] is palindrome, [j + 1, i - 1] is palindrome, and c[j] == c[i].

analysis:
TC O(N^ 2) SC O(N)
https://leetcode.com/problems/palindrome-partitioning-ii/discuss/42198/My-solution-does-not-need-a-table-for-palindrome-is-it-right-It-uses-only-O(n)-space.
*/
public class PalindromePartitioningII {
	public int minCut(String s) {
		if (s == null || s.length() <= 1) {
			return 0;
		}
		int len = s.length();
		int[] dp = new int[len]; // dp[i] means minimum cut to make s[0:i] all palindrome partitions
		for (int i = 0; i < len; i++) {
			dp[i] = i;
		}
		for(int i = 0; i  < len; i++){
			// CASE 1. odd len: center is at index mid, expand on both sides
			for(int l = 0; i - l >= 0 && i + l < len && s.charAt(i - l) == s.charAt(i + l); l++){
				int temp = 0;
				if(i - l > 0){
					temp = dp[i - l - 1] + 1;
				}
				dp[i + l] = Math.min(dp[i + l], temp);
			}
			// CASE 2: even len: center is between [mid-1,mid], expand on both sides
			for(int l = 0; i - l - 1 >= 0 && i + l < len && s.charAt(i - l - 1) == s.charAt(i + l); l++){
				int temp = 0;
				if(i - l - 1 > 0){
					temp = dp[i - l - 2] + 1;
				}
				dp[i + l] = Math.min(dp[i + l], temp);
			}
		}
		return dp[len - 1];
	}
}
