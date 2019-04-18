package DP;
/*Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.

A subsequence of a string S is obtained by deleting 0 or more characters from S.

A sequence is palindromic if it is equal to the sequence reversed.

Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.

Example 1:
Input: 
S = 'bccb'
Output: 6
Explanation: 
The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
Note that 'bcb' is counted only once, even though it occurs twice.
Example 2:
Input: 
S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
Output: 104860361
Explanation: 
There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
Note:

The length of S will be in the range [1, 1000].
Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.

DP[i][j] to record in substring from i to j(included), the number of palindrome without duplicate
when s.charAt(i) != s.charAt(j):
dp[i][j] = dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];

When s.charAt(i) == s.charAt(j):
*/
public class CountDifferentPalindromicSubsequences {
	public int countPalindromicSubsequences(String s) {
		int len = s.length();
        Integer[][] dp = new Integer[len][len];
        char[] arr = s.toCharArray();
        for(int i = 0; i < len; i++){
        	dp[i][i] = 1; // Consider the test case "a", "b" "c"...
        }
        for(int dist = 1; dist < len; dist++){
        	for(int i = 0; i + dist < len; i++){
        		int j = i + dist;
        		if(arr[i] == arr[j]){
        			int start =
        		}
        	}
        }
    }
}
