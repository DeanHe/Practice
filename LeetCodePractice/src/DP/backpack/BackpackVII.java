package DP.backpack;

/*Assume that you have n yuan. There are many kinds of rice in the supermarket. Each kind of rice is bagged and must be purchased in the whole bag. 
Given the weight, price and quantity of each type of rice, find the maximum weight of rice that you can purchase.

Example
Given:
n = 8
prices = [2,4]
weight = [100,100]
amounts = [4,2]

Return:400*/
public class BackpackVII {
	/**
	 * @param n:
	 *            the money of you
	 * @param prices:
	 *            the price of rice[i]
	 * @param weight:
	 *            the weight of rice[i]
	 * @param amounts:
	 *            the amount of rice[i]
	 * @return: the maximum weight
	 */
	public int backPackVII(int n, int[] prices, int[] weight, int[] amounts) {
		int len = prices.length;
		int[][] dp = new int[len][n + 1];
		for (int i = 0; i < len; i++) {
			for (int j = 1; j <= n; j++) {
				for (int amount = 0; amount <= amounts[i]; amount++) {
					if (i == 0) {
						if (j >= amount * prices[0]) {
							dp[0][j] = amount * weight[0];
						}
					} else {
						// not take
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);                 
						if (j >= amount * prices[i]) {
						    // take
							dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - amount * prices[i]] + amount * weight[i]);
						}
					}
				}
			}
		}
		return dp[len - 1][n];
	}
	
	public int backPackVII2(int n, int[] prices, int[] weight, int[] amounts) {
		int len = prices.length;
		int[][] dp = new int[len + 1][n + 1];
		for (int i = 1; i <= len; i++) {
			for (int j = 1; j <= n; j++) {
				for (int amount = 0; amount <= amounts[i]; amount++) {
					// not take
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);                 
					if (j >= amount * prices[i - 1]) {
					    // take
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - amount * prices[i - 1]] + amount * weight[i - 1]);
					}
				}
			}
		}
		return dp[len][n];
	}
}
