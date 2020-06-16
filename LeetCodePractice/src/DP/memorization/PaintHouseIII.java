package DP.memorization;

/*
There is a row of m houses in a small city, each house must be painted with one of the n colors (labeled from 1 to n), some houses that has been painted last summer should not be painted again.

        A neighborhood is a maximal group of continuous houses that are painted with the same color. (For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods  [{1}, {2,2}, {3,3}, {2}, {1,1}]).

        Given an array houses, an m * n matrix cost and an integer target where:

        houses[i]: is the color of the house i, 0 if the house is not painted yet.
        cost[i][j]: is the cost of paint the house i with the color j+1.
        Return the minimum cost of painting all the remaining houses in such a way that there are exactly target neighborhoods, if not possible return -1.



        Example 1:

        Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
        Output: 9
        Explanation: Paint houses of this way [1,2,2,1,1]
        This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
        Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
        Example 2:

        Input: houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
        Output: 11
        Explanation: Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
        This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}].
        Cost of paint the first and last house (10 + 1) = 11.
        Example 3:

        Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target = 5
        Output: 5
        Example 4:

        Input: houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
        Output: -1
        Explanation: Houses are already painted with a total of 4 neighborhoods [{3},{1},{2},{3}] different of target = 3.


        Constraints:

        m == houses.length == cost.length
        n == cost[i].length
        1 <= m <= 100
        1 <= n <= 20
        1 <= target <= m
        0 <= houses[i] <= n
        1 <= cost[i][j] <= 10^4

        analysis:
        time complexity: O(m * n * n * target)
*/

public class PaintHouseIII {
    int[] houses;
    int[][] cost;
    int m, n, target;
    Integer[][][] dp;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        this.houses = houses;
        this.cost = cost;
        this.m = m;
        this.n = n;
        this.target = target;
        dp = new Integer[m + 1][target + 1][n + 1];
        int res = dfs(0, 0, 0);
        return res != Integer.MAX_VALUE ? res : -1;
    }

    private int dfs(int i, int group, int preColor) {
        //System.out.println(i + " " + group + " " + Arrays.toString(houses));
        if (group > target) {
            return Integer.MAX_VALUE;
        }
        if (i == m) {
            if (group == target) {
                return dp[i][group][preColor] = 0;
            } else {
                return dp[i][group][preColor] = Integer.MAX_VALUE;
            }
        }
        if (dp[i][group][preColor] != null) {
            return dp[i][group][preColor];
        }
        int res = Integer.MAX_VALUE;
        if (houses[i] != 0) {
            if (i == 0 || preColor != houses[i]) {
                res = dfs(i + 1, group + 1, houses[i]);
            } else {
                res = dfs(i + 1, group, houses[i]);
            }
        } else {
            for (int color = 1; color <= n; color++) {
                int temp;
                if (i == 0 || preColor != color) {
                    temp = dfs(i + 1, group + 1, color);
                } else {
                    temp = dfs(i + 1, group, color);
                }
                if (temp != Integer.MAX_VALUE) {
                    temp += cost[i][color - 1];
                }
                res = Math.min(res, temp);
            }
        }
        return dp[i][group][preColor] = res;
    }
}
