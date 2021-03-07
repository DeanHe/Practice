package DP;

import java.util.Arrays;

/*
#Google

Given a matrix, you can pick one element from each row,
the score is the element value minus column idx difference between its index and previous row pick column index

 */
public class MaxScoreOfMatrixGoogle {
    int rows, cols, res = Integer.MIN_VALUE;
    Integer[][] dp;

    public void test(){
        int[][] matrix = new int[4][4];
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random()*10);
            }
        }
        for(int[] row : matrix)
            System.out.println(Arrays.toString(row));
        System.out.println(maxScoreOfMatrixGoogle(matrix));
    }
    public int maxScoreOfMatrixGoogle(int[][] mat){
        rows = mat.length;
        cols = mat[0].length;
        dp = new Integer[rows][cols];
        for(int c = 0; c < cols; c++){
            res = Math.max(res, mat[0][c] + dfs(1, c, mat));
        }
        return res;
    }

    private int dfs(int r, int cp, int[][] mat) {
        if(r == rows){
            return 0;
        }
        if(dp[r][cp] != null){
            System.out.println("dp" + r + ":" + cp);
            return dp[r][cp];
        }
        int res = Integer.MIN_VALUE;
        for(int c = 0; c < cols; c++){
            int tmp = mat[r][c] - Math.abs(c - cp) + dfs(r + 1, c, mat);
            res = Math.max(res, tmp);
        }
        return dp[r][cp] = res;
    }
}
