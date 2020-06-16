package DP;

/*
You are given coins of different denominations and a total amount of money.
Write a function to compute the number of combinations that make up that amount.
You may assume that you have infinite number of each kind of coin.
        Example 1:

        Input: amount = 5, coins = [1, 2, 5]
        Output: 4
        Explanation: there are four ways to make up the amount:
        5=5
        5=2+2+1
        5=2+1+1+1
        5=1+1+1+1+1
        Example 2:

        Input: amount = 3, coins = [2]
        Output: 0
        Explanation: the amount of 3 cannot be made up just with coins of 2.
        Example 3:

        Input: amount = 10, coins = [10]
        Output: 1


        Note:

        You can assume that

        0 <= amount <= 5000
        1 <= coin <= 5000
        the number of coins is less than 500
        the answer is guaranteed to fit into signed 32-bit integer

        tag:
        dfs + memorization
*/

public class CoinChangeII {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= coins[i - 1]) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[coins.length][amount];
    }

    public int changeRecursive(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        Integer[][] dp = new Integer[amount + 1][coins.length + 1];
        return dfs(amount, coins, dp, 0, amount);
    }

    int dfs(int amount, int[] coins, Integer[][] dp, int i, int cur) {
        if (i == coins.length) {
            return 0;
        }
        if (cur < 0) {
            return 0;
        }
        if (cur == 0) {
            return dp[cur][i] = 1;
        }
        if (dp[cur][i] != null) {
            return dp[cur][i];
        }
        int res = 0;
        res += dfs(amount, coins, dp, i, cur - coins[i]);
        res += dfs(amount, coins, dp, i + 1, cur);
        return dp[cur][i] = res;
    }
}
