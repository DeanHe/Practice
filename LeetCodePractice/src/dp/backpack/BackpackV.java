package dp.backpack;
/*
Given n items with size nums[i] which an integer array and all positive numbers.
An integer target denotes the size of a backpack. Find the number of possible fill the backpack.
Each item may only be used once

Example
Given candidate items [1,2,3,3,7] and target 7,

A solution set is: 
[7]
[1, 3, 3]
return 2
*/
public class BackpackV {
	/**
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public int backPackV2D(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[][] dp = new int[len + 1][target + 1];
        dp[0][0] = 1; // 
        for (int j = 1; j <= target; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= len; i++) {
            for  (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j]; // means how many ways to pick from A[0:i] to sum to j
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];    
                }
            }
        }
        return dp[len][target];
    }
    
    public int backPackV(int[] nums, int target) {
        // Write your code here
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; ++i)
            for (int  j = target; j >= nums[i]; j--)
                dp[j] += dp[j - nums[i]];

        return dp[target];
    }
}
