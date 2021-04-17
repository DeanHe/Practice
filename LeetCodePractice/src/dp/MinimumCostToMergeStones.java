package dp;

/*There are N piles of stones arranged in a row. The i-th pile has stones[i] stones.

A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.

Find the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.

Example
Example 1:

Input: stones = [3,2,4,1], K = 2
Output: 20
Explanation: 
We start with [3, 2, 4, 1].
We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
We merge [4, 1] for a cost of 5, and we are left with [5, 5].
We merge [5, 5] for a cost of 10, and we are left with [10].
The total cost was 20, and this is the minimum possible.
Example 1:

Input: stones = [3,2,4,1], K = 3
Output: -1
Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
Notice
1 <= stones.length <= 30
2 <= K <= 30
1 <= stones[i] <= 100*/
public class MinimumCostToMergeStones {
	/**
     * @param stones: 
     * @param K: 
     * @return: return a integer 
     */
    public int mergeStones(int[] stones, int K) {
        // write your code here
    	int len = stones.length;
    	if((len - 1) % (K - 1) != 0){
    		return -1;
    	}
    	int[] preSum = new int[len + 1];
    	for(int i = 1; i <= len; i++){
    		preSum[i] = preSum[i - 1] + stones[i - 1];
    	}
    	int[][][] dp = new int[len][len][K + 1];
    	//mem[i][j][k] means minimum cost to divide A[i:j] to k consecutive piles
    	for(int i = 0; i < len; i++){
    		for(int j = 0; j < len; j++){
    			for(int k = 0; k <= K; k++){
    				dp[i][j][k] = Integer.MAX_VALUE;
    			}
    		}
    	}
    	for(int i = 0; i < len; i++){
    		dp[i][i][1] = 0;
    	}
    	for(int d = 1; d < len; d++){
    		for(int i = 0; i + d < len; i++){
    			int j = i + d;
    			for(int k = 2; k <= K; k++){
    				for(int m = i; m < j; m += K - 1){
    					dp[i][j][k] = Math.min(dp[i][j][k], dp[i][m][1] + dp[m + 1][j][k - 1]);
    				}
    			}
    			dp[i][j][1] = dp[i][j][K] + (preSum[j + 1] - preSum[i]);
    		}
    	}
    	return dp[0][len - 1][1];
    }
}
