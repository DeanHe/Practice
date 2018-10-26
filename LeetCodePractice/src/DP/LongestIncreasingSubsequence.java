package DP;

public class LongestIncreasingSubsequence {
	/**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        // dp[i] means the LIS ends in nums[i]
        int[] dp = new int[len];
        /* initialization
        for(int i = 0; i < len; i++){
            dp[i] = 1;
        }
        */
        int res = 1;
        for(int i = 0; i < len; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
