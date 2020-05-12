package DP.backpack;
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
		int len = value.length;
        int count = 0;
    	boolean[] dp = new boolean[n + 1];
    	dp[0] = true;
    	for(int i = 0; i < len; i ++){
    		int[] cnt = new int[n + 1];
    		for(int j = value[i]; j <= n; j++){
    			if(!dp[j] && dp[j - value[i]] && cnt[j - value[i]] < amount[i]){
    				cnt[j] = cnt[j - value[i]] + 1;
    				dp[j] = true;
    				count++;
    			}
    		}
    	}
    	return count;
    }
	
	public int backPackVIII2D(int n, int[] value, int[] amount) {
        int len = value.length;
        int count = 0;
    	boolean[][] dp = new boolean[len + 1][n + 1];
    	for(int i = 1; i <= len; i ++){
    	    dp[i - 1][0] = true;
    		for(int j = 1; j <= n; j++){
    			for(int a = 0; a <= amount[i - 1]; a++){
    				if(!dp[i][j] && j >= a * value[i - 1] && dp[i - 1][j - a * value[i - 1]]){
    					dp[i][j] = true;
    				}
    			}
    		}
    	}
    	for(int i = 1; i <= n; i++){
    		if(dp[len][i]){
    			count++;
    		}
    	}
    	return count;
    }
}
