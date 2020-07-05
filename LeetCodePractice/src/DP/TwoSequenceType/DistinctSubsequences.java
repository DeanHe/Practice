package DP.TwoSequenceType;
/*Given a string S and a string T, count the number of distinct subsequences of S which equals T.
A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
Example 1:

Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:

As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
Example 2:

Input: S = "babgbag", T = "bag"
Output: 5
Explanation:

As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^*/
public class DistinctSubsequences {
	/**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
     //How do you define "distinct" subsequence? Clearly, the 'distinct' here mean different operation combination, not the final string of subsequence. Otherwise, the result is always 0 or 1. 
    //When you see string problem that is about subsequence or matching, dynamic programming method should come to mind naturally. The key is to find the initial and changing condition. 
    // mem[i][j]，represent S[:i] matches T[:j] count
	// Let W(i, j) stand for the number of subsequences of S(0, i) equals to T(0, j). 
	//If S.charAt(i) == T.charAt), W(i, j) = W(i-1, j-1) + W(i-1,j); Otherwise, W(i, j) = W(i-1,j).
public int numDistinct(String S, String T) {
	if(S == null || T == null){
        return 0;
    }
    int sLen = S.length();
    int tLen = T.length();
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
	        if(S.charAt(i - 1) == T.charAt(j - 1)){
		        dp[i][j] += dp[i - 1][j - 1];
	        }
        }
    }
    return dp[sLen][tLen];
    }
}
