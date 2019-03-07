package DP;

/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]*/
public class BestTimeToBuyAndSellStockWithCooldown {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int n = prices.length;
		int[] buy = new int[n + 1];
		int[] sell = new int[n + 1];
		int[] rest = new int[n + 1];
		buy[0] = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			// buy[i] means max profit when day i is in buy state, 
			//ex: buy, buy, buy, sell, rest, buy, buy
			buy[i] = Math.max(buy[i - 1], rest[i - 1] - prices[i - 1]);
			sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1]);
			rest[i] = Math.max(rest[i - 1], buy[i - 1]);
			rest[i] = Math.max(rest[i], sell[i - 1]);
		}
		int res = Math.max(sell[n], rest[n]);
		return res;

	}
}
