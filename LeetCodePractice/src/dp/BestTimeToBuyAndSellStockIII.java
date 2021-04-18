package dp;

import java.util.Arrays;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/
public class BestTimeToBuyAndSellStockIII {
	public int maxProfit(int[] prices) {
        int len = prices.length;
        //left[i] means max profit gained from day [0 i]
        int[] left = new int[len];
        int leftMin = prices[0];
        for(int i = 1; i < len; i++){
        	left[i] = Math.max(left[i - 1], prices[i] - leftMin);
        	leftMin = Math.min(leftMin, prices[i]);
        }
      //right[i] means max profit gained from day [i len)
        int[] right = new int[len];
        int rightMax = prices[len - 1];
        for(int i = len - 2; i >= 0; i--){
        	right[i] = Math.max(right[i + 1], rightMax - prices[i]);
        	rightMax = Math.max(rightMax, prices[i]);
        }
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < len; i++){
        	res = Math.max(res, left[i] + right[i]);
        }
        return res;
    }

    public int maxProfitDP(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][4];
        /*
        dp[i][0] means max profit gained by day i with hold first stock
        dp[i][1] means max profit gained by day i with sold first stock
        dp[i][2] means max profit gained by day i with hold second stock
        dp[i][3] means max profit gained by day i with sold second stock
         */
        dp[0][0] = -prices[0];
        dp[0][2] = Integer.MIN_VALUE;
        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = dp[i - 1][1];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }
        /*
        for(int[] arr : dp){
            System.out.println(Arrays.toString(arr));
        }
        */
        return Math.max(dp[len - 1][1], dp[len - 1][3]);
    }
}
