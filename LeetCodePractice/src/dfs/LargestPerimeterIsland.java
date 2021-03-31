package dfs;
/*
Find the largest perimeter of an island given a 2 dimensional array of 1's and 0's representing land and water respectively.

Land = 1
Water = 0
Island = contiguous 1's that are adjacent to other 1's to the left, right, up, or down

Edge of an island = any 1 that is adjacent to a 0 OR grid boundary to the left, right, up, or down

Perimeter = total # of 1's on the edge of an island

Example 1:

Input:
[[1, 0, 1, 1, 1],
 [1, 0, 1, 1, 1],
 [0, 1, 0, 1, 1]]

Output: 7 (Island on the right)
Example 2:

Input:
[[0, 0, 0, 0, 0, 0, 0],
 [0, 1, 0, 1, 1, 1, 0],
 [0, 1, 0, 1, 1, 1, 0],
 [0, 0, 1, 1, 1, 1, 0],
 [0, 0, 0, 0, 0, 0, 0]]

Output: 9 (Island on the right)
 */
public class LargestPerimeterIsland {
    int rows, cols;
    int[] dirs = {0, 1, 0, -1, 0};
    boolean[][] visited;
    public int largestPerimeter(int[][] grid) {
        int res = 0;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 1){
                    int[] count = dfs(grid, r, c);
                    res = Math.max(res, count[0] - count[1]);
                }
            }
        }
        return res;
    }
    // {totalCnt, insideCnt}
    private int[] dfs(int[][] grid, int r, int c) {
        int[] res = new int[2];
        visited[r][c] = true;
        res[0] = 1;
        if(r > 0 && grid[r - 1][c] == 1
            && r + 1 < rows && grid[r + 1][c] == 1
            && c > 0 && grid[r][c - 1] == 1
            && c + 1 < cols && grid[r][c + 1] == 1){
            res[1] = 1;
        }
        for(int i = 0; i < dirs.length - 1; i++){
            int nb_r = r + dirs[i];
            int nb_c = c + dirs[i + 1];
            if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols){
                if(grid[nb_r][nb_c] == 1 && !visited[nb_r][nb_c]){
                    int[] temp = dfs(grid, nb_r, nb_c);
                    res[0] += temp[0];
                    res[1] += temp[1];
                }
            }
        }
        return res;
    }
}
