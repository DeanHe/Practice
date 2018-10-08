package DP;
// Combination Sum IV
//Given an integer array nums with all positive numbers and no duplicates, 
//find the number of possible combinations that add up to a positive integer target.
// each number can be used unlimited times
public class BackpackVI {
	/**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackVI(int[] nums, int target) {
        int len = nums.length;
        int[] dp = new int[target+1];
       dp[0] = 1;
       for(int i = 1; i <= target; i++){
           for(int j = 0; j < len; j++){
               if(i - nums[j] >= 0){
                   dp[i] += dp[i - nums[j]];
               }
           }
       }
        return dp[target];
    }
}
