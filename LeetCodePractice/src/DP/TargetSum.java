package DP;
//Let P denotes a set of nums have + sign before it
//Let N denotes a set of nums have - sign before it
//P U N = nums 
//sum(P) - sum(N) = target
//sum(P) - sum(N) + sum(P) + sum(N) = target + sum(P) + sum(N)
//2 * sum(P) = target + sum(nums);
//sum(P) = (target + sum(nums)) / 2; ~ 0:1 backpack problem         https://www.youtube.com/watch?v=zks6mN06xdQ
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
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i = 0; i < len; i++){
            for(int j = target; j >= 0; j--){
                if(j >= nums[i]){
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[target];     
    }
}
