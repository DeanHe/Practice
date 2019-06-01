package DP;
/*Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. Determine the maximum value obtainable by cutting up the rod and selling the pieces.

Example
Example1

Input:
[1, 5, 8, 9, 10, 17, 17, 20]
8
Output: 22
Explanation:
length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 1   5   8   9  10  17  17  20
by cutting in two pieces of lengths 2 and 6
Example2

Input:
[3, 5, 8, 9, 10, 17, 17, 20]
8
Output: 24
Explanation:
length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 3   5   8   9  10  17  17  20
by cutting in eight pieces of length 1.*/
public class CuttingaRod {
	/**
     * @param prices: the prices
     * @param n: the length of rod
     * @return: the max value
     */
    public int cutting(int[] prices, int n) {
    	// dp[i] means maximum value by using rod of length i 
        int[] dp = new int[n + 1];   
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                dp[i] = Math.max(dp[i], dp[i - j] + prices[j - 1]);
            }
        }
        return dp[n];
    }
}
