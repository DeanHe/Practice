package DP.backpack;
/*Given n kind of items with size Ai and value Vi( each item has an infinite number available) 
and a backpack with size m. What's the maximum value can you put into the backpack?
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 15.*/
public class BackpackIII {
	/**
     * @param A an integer array
     * @param V an integer array
     * @param m an integer
     * @return an array
     */
    public int backPackIII2D(int[] A, int[] V, int m) {
        // Write your code here
        int n = A.length;
        int[][] dp = new int[n + 1][m + 1];        
        for (int i = 1; i <= n; ++i)
            for (int j = 0; j <= m; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (j >= A[i - 1])
                    dp[i][j] = Math.max(dp[i][j - A[i - 1]] + V[i - 1], dp[i][j]);
            }
        return dp[n][m];
    }
    
    public int backPackIII(int[] A, int[] V, int m) {
        // Write your code here
        int n = A.length;
        int[] dp = new int[m+1];        
        for (int i = 0; i < n; ++i)
            for (int j = A[i]; j <= m; j++)
                if (dp[j - A[i]] + V[i] > dp[j])
                    dp[j] = dp[j - A[i]] + V[i];
        return dp[m];
    }
}
