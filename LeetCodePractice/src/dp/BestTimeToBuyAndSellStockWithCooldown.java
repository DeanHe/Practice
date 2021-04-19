package dp;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]
*/
public class BestTimeToBuyAndSellStockWithCooldown {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int n = prices.length;
		int[] hold = new int[n + 1];
		int[] sold = new int[n + 1];
		int[] rest = new int[n + 1];
		hold[0] = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			// buy[i + 1] means max profit when day i is in buy state,
			//ex: buy, buy, buy, sell, rest, buy, buy
			hold[i + 1] = Math.max(hold[i], rest[i] - prices[i]);
			sold[i + 1] = Math.max(sold[i], hold[i] + prices[i]);
			rest[i + 1] = Math.max(Math.max(rest[i], hold[i]), sold[i]);
		}
		int res = Math.max(sold[n], rest[n]);
		return res;
	}
}
