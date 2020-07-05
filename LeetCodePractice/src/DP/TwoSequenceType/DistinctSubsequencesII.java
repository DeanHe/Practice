package DP.TwoSequenceType;

import java.util.Arrays;

/*
Given a string S, count the number of distinct, non-empty subsequences of S .

Since the result may be large, return the answer modulo 10^9 + 7.

Example 1:

Input: "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
Example 2:

Input: "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".
Example 3:

Input: "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".

Note:

S contains only lowercase letters.
1 <= S.length <= 2000
https://leetcode.com/problems/distinct-subsequences-ii/solution/
*/
public class DistinctSubsequencesII {
	public int distinctSubseqII(String S) {
		int MOD = 1_000_000_007;
		int len = S.length();
        int[] dp =  new int[len + 1];
        dp[0] = 1;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for(int i = 1; i <= len; i++){
        	dp[i] = 2 * dp[i - 1] % MOD;
        	int k = S.charAt(i - 1) - 'a';
        	if(last[k] >  0){
        		dp[i] -= dp[last[k] - 1];
        	}
        	dp[i] %= MOD;
        	last[k] = i;
        }
        dp[len]--;
        if(dp[len] < 0){
        	dp[len] += MOD;
        }
        return dp[len];
    }
}
