package DP;
/*
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.
(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a substring of "cabac" because we can delete the first "c".
str2 = "cab" is a substring of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
 
Note:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
*/
public class ShortestCommonSupersequence {
	public String shortestCommonSupersequence(String A, String B) {
		// find Longest common subsequence
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m+1][n+1];
        //dp[i][j] means the LCS length formed by A[:i] and B[:j]
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(A.charAt(i - 1) == B.charAt(j - 1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = m - 1, j = n - 1;
        while(i >= 0 && j >= 0){
        	if(A.charAt(i) == B.charAt(j)){
        		sb.insert(0, A.charAt(i));
        		i--;
        		j--;
        	} else if(dp[i + 1][j] < dp[i][j + 1]){
        		//add the char not in LCS, dp value is less
        		sb.insert(0, A.charAt(i));
        		i--;
        	} else {
        		sb.insert(0, B.charAt(j));
        		j--;
        	}
        }
        while(i >= 0){
        	sb.insert(0, A.charAt(i));
    		i--;
        }
        while(j >= 0){
        	sb.insert(0, B.charAt(j));
    		j--;
        }
        return sb.toString();
    }
}
