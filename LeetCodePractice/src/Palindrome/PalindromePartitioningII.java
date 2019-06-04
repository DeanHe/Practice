package Palindrome;

/*Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

cut[i] is the minimum of cut[j - 1] + 1 (j <= i), if [j, i] is palindrome.
If [j, i] is palindrome, [j + 1, i - 1] is palindrome, and c[j] == c[i].
*/
public class PalindromePartitioningII {
	public int minCut(String s) {
		if (s == null || s.length() <= 1) {
			return 0;
		}
		int len = s.length();
		boolean[][] isPalindrome = new boolean[len][len];
		int[] dp = new int[len]; // dp[i] means minimum cut to make s[0:i] all palindrome partitions
		for (int i = 0; i < len; i++) {
			dp[i] = i;
			for (int j = 0; j <= i; j++) {
				if (s.charAt(j) == s.charAt(i) && (j + 1 > i - 1 || isPalindrome[j + 1][i - 1])) {
					isPalindrome[j][i] = true;
					if (j == 0) {
						dp[i] = 0;
					} else {
						dp[i] = Math.min(dp[i], dp[j - 1] + 1);
					}
				}
			}
		}
		return dp[len - 1];
	}
}
