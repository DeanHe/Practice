package DP;

//Given n items with size Ai and value Vi, and a backpack with size m. 
//What's the maximum value can you put into the backpack?
public class BackpackII {
	/**
	 * @param m:
	 *            An integer m denotes the size of a backpack
	 * @param A
	 *            & V: Given n items with size A[i] and value V[i]
	 * @return: The maximum value
	 */
	public int backPackII(int m, int[] A, int V[]) {
		// write your code here
		int len = A.length;
		int[][] dp = new int[len + 1][m + 1];
		// initializaiton

		// function
		for (int i = 1; i <= len; i++) {
			for (int j = 0; j <= m; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j >= A[i - 1]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
				}
			}
		}
		return dp[len][m];
	}
}
