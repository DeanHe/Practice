package DP;
/*You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example
Example1

Input: 
[1, 2, 5]
11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example2

Input: 
[2]
3
Output: -1
Notice
You may assume that you have an infinite number of each kind of coin.*/
public class CoinChange {
	/**
     * @param coins: a list of integer
     * @param amount: a total amount of money amount
     * @return: the fewest number of coins that you need to make up
     */
    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        int[] dp = new int[amount + 1];
        //initialization
        dp[0] = 0;
        // state transfer function
        for(int i = 1; i <= amount; i++){
        	dp[i] = Integer.MAX_VALUE;
        	for(int j = 0; j < len; j++){
        		if(i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE){
        			dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        		}
        	}
        }
        if(dp[amount] == Integer.MAX_VALUE){
        	return -1;
        } else {
        	return dp[amount];
        }
    }

}
