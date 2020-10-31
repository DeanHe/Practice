package DP;
/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Example
Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.

Challenge
O(nk) time.

Notice
You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
*/
public class BestTimeToBuyAndSellStockIV {
	/**
     * @param K: An integer
     * @param prices: An integer array
     * @return: Maximum profit
     */
    public int maxProfit(int K, int[] prices) {
        // write your code here
    	if(prices == null || prices.length == 0){
            return 0;
        }
        int len = prices.length;
        if(K >= len / 2){
        	return sellStockII(prices);
        }
        int[][] dp = new int[K + 1][len];
        //DP: mem(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j< len).
        for(int i = 1; i <= K; i++){
        	int balance = -prices[0];
        	for(int j = 1; j < len; j++){
        		dp[i][j] = Math.max(dp[i][j - 1], balance + prices[j]);
        		balance = Math.max(balance, dp[i - 1][j - 1] - prices[j]);
        		//accumulate means the maximum profit of just doing at most i-1 transactions, using at most first j-1 prices, and buying the stock at price[j]
        	}
        }
        return dp[K][len - 1];
    }
    private int sellStockII(int[] prices){
    	int res = 0;
    	int len = prices.length;
    	for(int i = 1; i < len; i++){
    	    if(prices[i] > prices[i - 1]){
    	        res += prices[i] - prices[i - 1];
    	    }
    	}
    	return res;
    }
}
