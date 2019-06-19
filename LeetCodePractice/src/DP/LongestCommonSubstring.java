package DP;

public class LongestCommonSubstring {
	/**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m+1][n+1];
        int max = 0;
        for(int i = 1; i <= m; i++){
            char A_cur = A.charAt(i-1);
            for(int j = 1; j <= n; j++){
                char B_cur = B.charAt(j-1);
                if(A_cur == B_cur){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }
}
