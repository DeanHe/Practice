package dp;

/*A child is running up a staircase with n steps, and can hop either 1 step, 2 steps, or 3 steps at a time. Implement a method to count how many possible ways the child can run up the stairs.*/
public class ClimbingStairsII {
	/**
     * @param n an integer
     * @return an integer
     */
    public int climbStairs2(int n) {
        // Write your code here
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 0; i <= n; i++){
            for(int j = 1; j <= 3; j++){
                if(i - j >= 0){
                    dp[i] += dp[i - j];
                }
            }
        }
        return dp[n];
    }
}
