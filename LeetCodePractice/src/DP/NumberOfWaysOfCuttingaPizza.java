package DP;

/*
Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.

        For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.

        Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.



        Example 1:



        Input: pizza = ["A..","AAA","..."], k = 3
        Output: 3
        Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
        Example 2:

        Input: pizza = ["A..","AA.","..."], k = 3
        Output: 1
        Example 3:

        Input: pizza = ["A..","A..","..."], k = 1
        Output: 1


        Constraints:

        1 <= rows, cols <= 50
        rows == pizza.length
        cols == pizza[i].length
        1 <= k <= 10
        pizza consists of characters 'A' and '.' only.
*/

public class NumberOfWaysOfCuttingaPizza {
    Integer[][][] dp;
    int[][] prefixSum;
    int rows, cols;
    int MOD = (int) (1e9 + 7);

    public int ways(String[] pizza, int k) {
        rows = pizza.length;
        cols = pizza[0].length();
        dp = new Integer[rows][cols][k]; // dp[i][j][m] means # of ways to cut pizza[i:][j:] into m pieces
        prefixSum = new int[rows + 1][cols + 1]; // preSum[i][j] means pizza[i:][j:] has how many apples
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                prefixSum[r][c] = (pizza[r].charAt(c) == 'A' ? 1 : 0) + prefixSum[r + 1][c] + prefixSum[r][c + 1] - prefixSum[r + 1][c + 1];
            }
        }
        return dfs(0, 0, k - 1);
    }

    private int dfs(int r, int c, int k) {
        if (prefixSum[r][c] == 0) { // if the remain piece has no apple -> invalid
            return 0;
        }
        if (k == 0) { // k means number of cut
            return 1;
        }
        if (dp[r][c][k] != null) {
            return dp[r][c][k];
        }
        int res = 0;
        //horizontal cut
        for (int next_r = r + 1; next_r < rows; next_r++) {
            if (prefixSum[r][c] - prefixSum[next_r][c] > 0) { // cut if the upper piece contains at least one apple
                res = (res + dfs(next_r, c, k - 1)) % MOD;
            }
        }
        //vertical cut
        for (int next_c = c + 1; next_c < cols; next_c++) {
            if (prefixSum[r][c] - prefixSum[r][next_c] > 0) { // cut if the left piece contains at least one apple
                res = (res + dfs(r, next_c, k - 1)) % MOD;
            }
        }
        return dp[r][c][k] = res;
    }

}
