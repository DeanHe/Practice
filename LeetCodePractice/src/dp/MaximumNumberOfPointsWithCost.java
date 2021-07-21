package dp;

import java.util.Arrays;

/*
You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.

To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.

However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.

Return the maximum number of points you can achieve.

abs(x) is defined as:

x for x >= 0.
-x for x < 0.


Example 1:


Input: points = [[1,2,3],[1,5,1],[3,1,1]]
Output: 9
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
You add 3 + 5 + 3 = 11 to your score.
However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
Your final score is 11 - 2 = 9.
Example 2:


Input: points = [[1,5],[2,3],[4,2]]
Output: 11
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
You add 5 + 3 + 4 = 12 to your score.
However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
Your final score is 12 - 1 = 11.


Constraints:

m == points.length
n == points[r].length
1 <= m, n <= 10^5
1 <= m * n <= 10^5
0 <= points[r][c] <= 10^5

hint:
Try using dynamic programming.
dp[i][j] is the maximum number of points you can have if points[i][j] is the most recent cell you picked.

analysis:
TC (O(M * N))
pre compute the preSum gain on previous row left[], right[]

Tag: Google Interview
 */
public class MaximumNumberOfPointsWithCost {
    int rows, cols;
    long res = Long.MIN_VALUE;
    Long[][] mem;

    public long maxPoints(int[][] points) {
        rows = points.length;
        cols = points[0].length;
        mem = new Long[rows][cols];
        for(int c = 0; c < cols; c++){
            res = Math.max(res, points[0][c] + dfs(1, c, points));
        }
        return res;
    }

    private long dfs(int r, int pre_c, int[][] points) {
        if(r == rows){
            return 0;
        }
        if(mem[r][pre_c] != null){
            return mem[r][pre_c];
        }
        long res = Long.MIN_VALUE;
        for(int c = 0; c < cols; c++){
            long tmp = points[r][c] - Math.abs(c - pre_c) + dfs(r + 1, c, points);
            res = Math.max(res, tmp);
        }
        return mem[r][pre_c] = res;
    }

    public long maxPointsDP(int[][] points){
        rows = points.length;
        cols = points[0].length;
        long[][] dp = new long[rows][cols];
        long[] left = new long[cols];
        long[] right = new long[cols];
        // row 0, init
        for(int c = 0; c < cols; c++){
            dp[0][c] = points[0][c];
            System.out.println(dp[0][c]);
        }

        for(int r = 1; r < rows; r++){
            Arrays.fill(left, Long.MIN_VALUE);
            Arrays.fill(right, Long.MIN_VALUE);
            left[0] = dp[r - 1][0];
            for(int c = 1; c < cols; c++){
                left[c] = Math.max(left[c - 1], dp[r - 1][c] + c);
            }
            right[cols - 1] = dp[r - 1][cols - 1] - (cols - 1);
            for(int c = cols - 2; c >= 0; c--){
                right[c] = Math.max(right[c + 1], dp[r - 1][c] - c);
            }
            for(int c = 0; c < cols; c++){
                dp[r][c] = Math.max(dp[r][c], points[r][c] - c + left[c]);
                dp[r][c] = Math.max(dp[r][c], points[r][c] + c + right[c]);
                System.out.println(dp[r][c]);
            }

        }
        for(int c = 0; c < cols; c++){
            res = Math.max(res, dp[rows - 1][c]);
        }
        return res;
    }
}

