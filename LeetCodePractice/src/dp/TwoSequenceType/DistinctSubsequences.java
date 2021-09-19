package dp.TwoSequenceType;
/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.

A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).

It is guaranteed the answer fits on a 32-bit signed integer.



Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
babgbag
babgbag
babgbag
babgbag
babgbag


Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.

analysis:
How do you define "distinct" subsequence? Clearly, the 'distinct' here mean different operation combination, not the final string of subsequence. Otherwise, the result is always 0 or 1.
When you see string problem that is about subsequence or matching, dynamic programming method should come to mind naturally. The key is to find the initial and changing condition.
mem[i][j]，represent S[:i] matches T[:j] count
Let W(i, j) stand for the number of subsequences of S(0, i) equals to T(0, j).
If S.charAt(i) == T.charAt), W(i, j) = W(i-1, j-1) + W(i-1,j); Otherwise, W(i, j) = W(i-1,j).
*/
public class DistinctSubsequences {
	/**
     * @param s, T: Two string.
     * @return: Count the number of distinct subsequences
     */
public int numDistinct(String s, String t) {
	if(s == null || t == null){
        return 0;
    }
    int sLen = s.length();
    int tLen = t.length();
    int[][] dp = new int[sLen + 1][tLen + 1];
    //init
    dp[0][0] = 1;
    for(int i = 1; i <= sLen; i++){
        dp[i][0] = 1;
    }
    for(int j = 1; j <= tLen; j++){
        dp[0][j] = 0;
    }
    //function 
    for(int i = 1; i <= sLen; i++){
        for(int j = 1; j <= tLen; j++){
	        dp[i][j] = dp[i - 1][j];
	        if(s.charAt(i - 1) == t.charAt(j - 1)){
		        dp[i][j] += dp[i - 1][j - 1];
	        }
        }
    }
    return dp[sLen][tLen];
    }
}
