package Contest;

/*
Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

        Return the number of closed islands.

        Example 1:

        Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
        Output: 2
        Explanation:
        Islands in gray are closed because they are completely surrounded by water (group of 1s).
        Example 2:



        Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
        Output: 1
        Example 3:

        Input: grid = [[1,1,1,1,1,1,1],
        [1,0,0,0,0,0,1],
        [1,0,1,1,1,0,1],
        [1,0,1,0,1,0,1],
        [1,0,1,1,1,0,1],
        [1,0,0,0,0,0,1],
        [1,1,1,1,1,1,1]]
        Output: 2

        Constraints:

        1 <= grid.length, grid[0].length <= 100
        0 <= grid[i][j] <=1
*/
public class NumberOfClosedIslands {
    int[] dirs = {0, 1, 0, -1, 0};
    int rows, cols;
    int[][] matrix;

    public int closedIsland(int[][] grid) {
        int res = 0;
        rows = grid.length;
        cols = grid[0].length;
        this.matrix = grid;
        for (int r = 0; r < rows; r++) {
            if (matrix[r][0] == 0) {
                dfs(r, 0, 2);
            }
            if (matrix[r][cols - 1] == 0) {
                dfs(r, cols - 1, 2);
            }
        }
        for (int c = 0; c < cols; c++) {
            if (matrix[0][c] == 0) {
                dfs(0, c, 2);
            }
            if (matrix[rows - 1][c] == 0) {
                dfs(rows - 1, c, 2);
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    res++;
                    dfs(r, c, 1);
                }
            }
        }
        return res;
    }

    private void dfs(int r, int c, int val) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return;
        }
        if (matrix[r][c] == 0) {
            matrix[r][c] = val;
            for (int i = 0; i < dirs.length - 1; i++) {
                dfs(r + dirs[i], c + dirs[i + 1], val);
            }
        }
    }
}
