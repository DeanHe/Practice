package DP;
/*You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
 * Now you have 2 symbols + and -. 
 * For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.
Example
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.*/
//Let P denotes a set of nums have + sign before it
//Let N denotes a set of nums have - sign before it
//P U N = nums 
//sum(P) - sum(N) = S            
//sum(P) - sum(N) + sum(P) + sum(N) = target + sum(P) + sum(N)
//2 * sum(P) = S + sum(nums);
//sum(P) = (S + sum(nums)) / 2; ~ 0:1 backpack problem         https://www.youtube.com/watch?v=zks6mN06xdQ
//new target is (S + sum(nums)) / 2
public class TargetSum {
	/**
     * @param nums: the given array
     * @param s: the given target
     * @return: the number of ways to assign symbols to make sum of integers equal to target S
     */
	public int findTargetSumWays(int[] nums, int S) {
        int totalSum = 0;
        int len = nums.length;
        for(int i = 0; i < len; i++){
            totalSum += nums[i];
        }
        if(totalSum > 1000 || S > 1000){
            return 0;
        }
        if(len > 20){
            return 0;
        }
        if((S + totalSum) % 2 != 0){
            return 0;
        }
        int target = (S + totalSum) / 2;
        //mem[i][j] means number of subset from nums[0: j - 1] has sum equals to i
        int[][] dp = new int[target + 1][len + 1];
        dp[0][0] = 1;
        for(int i = 0; i <= target; i++){
            for(int j = 1; j <= len; j++){
            	dp[i][j] = dp[i][j - 1];
                if(i >= nums[j - 1]){
                    dp[i][j] += dp[i - nums[j - 1]][j - 1];
                }
            }
        }
        return dp[target][len];     
    }
}
