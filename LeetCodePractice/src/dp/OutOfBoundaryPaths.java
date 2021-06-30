package dp;
/*
There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.

Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.



Example 1:


Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
Output: 6
Example 2:


Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
Output: 12


Constraints:

1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n

Is traversing every path feasible? There are many possible paths for a small matrix. Try to optimize it.

Can we use some space to store the number of paths and update them after every move?

One obvious thing: the ball will go out of the boundary only by crossing it.
Also, there is only one possible way the ball can go out of the boundary from the boundary cell except for corner cells.
From the corner cell, the ball can go out in two different ways. Can you use this thing to solve the problem?
 */
public class OutOfBoundaryPaths {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int MOD = (int)(1e9 + 7);
        int[] dirs = {0, 1, 0, -1, 0};
        int res = 0;
        int[][] count = new int[m][n];
        count[startRow][startColumn] = 1;
        for(int step = 0; step < maxMove; step++){
            int[][] temp = new int[m][n];
            for(int r = 0; r < m; r++){
                for(int c = 0; c < n; c++){
                    for(int i = 0; i + 1 < dirs.length; i++){
                        int nb_r = r + dirs[i];
                        int nb_c = c + dirs[i + 1];
                        if(nb_r < 0 || nb_r >= m || nb_c < 0 || nb_c >= n){
                            res = (res + count[r][c]) % MOD;
                        } else {
                            temp[nb_r][nb_c] = (temp[nb_r][nb_c] +  count[r][c]) % MOD;
                        }
                    }
                }
            }
            count = temp;
        }
        return res;
    }
}

