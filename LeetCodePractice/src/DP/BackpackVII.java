package DP;

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
		// write your code here
		int len = prices.length;
		int[][] dp = new int[len][n + 1];
		for (int i = 0; i < len; i++) {
			for (int amount = amounts[i]; amount > 0; amount--) {
				for (int j = 1; j <= n; j++) {
					if (i == 0) {
						if (j >= amount * prices[0]) {
							dp[0][j] = Math.max(dp[0][j], dp[0][j - amount * prices[0]] + weight[i]);
						}
					} else {
						dp[i][j] = dp[i][j - 1]; // not take
						if (j >= amount * prices[i]) {
							dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - amount * prices[i]] + weight[i]);
						}
					}
				}
			}
		}
		return dp[len - 1][n];
	}
}
