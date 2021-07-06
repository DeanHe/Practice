package dp;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. For each day, you can choose to buy stock or sell all stock or hold.

Example 1:
Given an example [2,1,2,0,1], return 2

Example 2:
Input: [3,3,5,0,0,3,1,4], return 16

Example 3:
Input: [1,2,3,4,5]
Output: 10

Example 4:
Input: [5,4,3,2,1]
Output: 10

Example 5
Given prices = [4,4,6,1,1,5,2,1],
Output: 12

Notice
You may not engage in multiple transactions at the same time.
*/
public class BestTimeToBuyAndSellStockFacebook {
	/**
     * @param prices: An integer array
     * @return: Maximum profit
     */
	public int sellStock(int[] prices){
    	int res = 0;
    	int len = prices.length;
    	int[] rightMost = new int[len];
    	rightMost[len - 1] = prices[len - 1];
    	for(int i = len - 2; i >= 0; i--){
			rightMost[i] = Math.max(rightMost[i + 1], prices[i]);
    	}
    	for(int i = 0; i < len; i++){
    		if(prices[i] < rightMost[i]){
    			res += rightMost[i] - prices[i];
			}
		}
    	return res;
    }
}
