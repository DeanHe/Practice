package DP;
/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example
Given an example [2,1,2,0,1], return 2*/
public class BestTimeToBuyAndSellStockII {
	/**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        // greedy strategy
        if(prices == null || prices.length == 0){
            return 0;
        }
        int profit = 0;
        int len = prices.length;
        for(int i = 0; i < len - 1; i++){
            if(prices[i + 1] > prices[i]){
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }
}
