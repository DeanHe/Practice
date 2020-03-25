package DP;
/*
        You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
        Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

        Example 1:
        Input: [2,3,2]
        Output: 3
        Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
        because they are adjacent houses.

        Example 2:
        Input: [1,2,3,1]
        Output: 4
        Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
        Total amount you can rob = 1 + 3 = 4.
*/
public class HouseRobberII {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }
        int[] startFromFirst = new int[len + 1];
        int[] startFromSecond = new int[len + 1];
        startFromFirst[0] = 0;
        startFromFirst[1] = nums[0];
        startFromSecond[0] = 0;
        startFromSecond[1] = 0;
        for(int i = 2; i <= len; i++){
            startFromFirst[i] = Math.max(startFromFirst[i - 1], startFromFirst[i - 2] + nums[i - 1]);
            startFromSecond[i] = Math.max(startFromSecond[i - 1], startFromSecond[i - 2] + nums[i - 1]);
        }
        return Math.max(startFromFirst[len - 1], startFromSecond[len]);
    }

    public int rob2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }
        return Math.max(helper(nums, 0, len - 2), helper(nums, 1, len - 1));
    }

    private int helper(int[] nums, int start ,int end){
        int len = nums.length, include = 0, exclude = 0;
        for(int i = start; i <= end; i++){
            int in = include, ex =exclude;
            include = ex + nums[i];
            exclude = Math.max(ex, in);
        }
        return Math.max(include, exclude);
    }

    private int helper2(int[] nums, int start ,int end){
        int[][] dp = new int[end + 1][2]; // dp[i][j] means max value of pick i slices with last slice taken 0, not taken 1
        dp[start][0] = nums[start];
        for(int i = start + 1; i <= end; i++){
            dp[i][0] = dp[i - 1][1] + nums[i];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        return Math.max(dp[end][0], dp[end][1]);
    }
}
