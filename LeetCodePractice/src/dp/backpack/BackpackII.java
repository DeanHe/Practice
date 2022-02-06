package dp.backpack;

/*
Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.
*/
public class BackpackII {
	/**
	 * @param m:
	 *            An integer m denotes the size of a backpack
	 * @param A
	 *            & V: Given n items with size A[i] and value V[i]
	 * @return: The maximum value
	 */
	public int backPackII(int m, int[] A, int[] V) {
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

	// print selection
	private String showSelection(int size, int[][] dp, int[] A) {
		String str = "";
		int temp = size;
		for (int i = dp.length - 1; i >= 1; i--) {
			if (dp[i][temp]  > dp[i - 1][temp]) { // means item (i - 1) is picked
				str += i - 1 + ",";
				temp -= A[i - 1];
			}
		}
		return str;
	}
}
