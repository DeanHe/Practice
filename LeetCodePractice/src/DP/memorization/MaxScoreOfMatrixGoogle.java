package DP.memorization;

import java.util.Arrays;

/*
#Google

Given a matrix, you can pick one element from each row,
the score is the element value minus column idx difference between its index and previous row pick column index

analysis:
TC (O(M*N))
pre compute the preSum gain on previous row left[], right[]
 */
public class MaxScoreOfMatrixGoogle {
    int rows, cols, res = Integer.MIN_VALUE;
    Integer[][] mem;

    public int getMaxScoreOfMatrix(int[][] mat){
        rows = mat.length;
        cols = mat[0].length;
        mem = new Integer[rows][cols];
        for(int c = 0; c < cols; c++){
            res = Math.max(res, mat[0][c] + dfs(1, c, mat));
        }
        return res;
    }

    private int dfs(int r, int pre_c, int[][] mat) {
        if(r == rows){
            return 0;
        }
        if(mem[r][pre_c] != null){
            return mem[r][pre_c];
        }
        int res = Integer.MIN_VALUE;
        for(int c = 0; c < cols; c++){
            int tmp = mat[r][c] - Math.abs(c - pre_c) + dfs(r + 1, c, mat);
            res = Math.max(res, tmp);
        }
        return mem[r][pre_c] = res;
    }

    public int getMaxScoreOfMatrixDP(int[][] mat){
        rows = mat.length;
        cols = mat[0].length;
        int[][] dp = new int[rows][cols];
        int[] left = new int[cols];
        int[] right = new int[cols];
        // row 0
        for(int c = 0; c < cols; c++){
            dp[0][c] = mat[0][c];
        }

        for(int r = 1; r < rows; r++){
            Arrays.fill(left, Integer.MIN_VALUE);
            Arrays.fill(right, Integer.MIN_VALUE);
            left[0] = mat[r][0];
            for(int c = 1; c < cols; c++){
                left[c] = Math.max(left[c - 1], mat[r - 1][c] - c);
            }
            right[cols - 1] = mat[r][cols - 1] + cols - 1;
            for(int c = cols - 2; c >= 0; c--){
                right[c] = Math.max(right[c + 1], mat[r - 1][c] + c);
            }
            for(int c = 0; c < cols; c++){
                dp[r][c] = Math.max(dp[r][c], mat[r][c] + c + left[c]);
                dp[r][c] = Math.max(dp[r][c], mat[r][c] - c + right[c]);
            }
        }
        for(int c = 0; c < cols; c++){
            res = Math.max(res, dp[rows - 1][c]);
        }
        return res;
    }
}
