package Math;
/*
Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

        Example 1:

        Input: 2
        Output: 1
        Explanation: 2 = 1 + 1, 1 × 1 = 1.
        Example 2:

        Input: 10
        Output: 36
        Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
        Note: You may assume that n is not less than 2 and not larger than 58.
*/
public class IntegerBreak {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j < i; j++){
                int tmp = Math.max((i - j), dp[i - j]) * Math.max(j, dp[j]);
                dp[i] = Math.max(dp[i], tmp);
            }
        }
        return dp[n];
    }
}
