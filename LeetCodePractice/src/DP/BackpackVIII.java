package DP;
/*Give some coins of different value and their quantity. Find how many values which are in range 1 ~ n can these coins be combined

Example
Given:
n = 10
value = [1,2,4]
amount = [2,1,1]

Return: 8
They can combine all the values in 1 ~ 8*/
public class BackpackVIII {
	/**
     * @param n: the value from 1 - n
     * @param value: the value of coins
     * @param amount: the number of coins
     * @return: how many different value
     */
    public int backPackVIII(int n, int[] value, int[] amount) {
        // write your code here
    	int len = value.length;
    	boolean[][] dp = new boolean[len + 1][n + 1];
    	for(int i = 1; i <= len; i ++){
    		for(int j = 1; j <= n; j++){
    			for(int a = 0; a <= amount[i]; a++){
    				if(dp[i - 1][j]){
    					dp[i][j] = true;
    				}
    				if(j >= a * value[i - 1] && dp[i - 1][j - a * value[i - 1]]){
    					dp[i][j] = true;
    				}
    			}
    		}
    	}
    	int count = 0;
    	for(int i = 1; i <= n; i++){
    		if(dp[len][i]){
    			count++;
    		}
    	}
    	return count;
    }
}
