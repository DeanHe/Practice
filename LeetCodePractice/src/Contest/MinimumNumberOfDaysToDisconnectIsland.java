package Contest;
/*
Given a 2D grid consisting of 1s (land) and 0s (water).  An island is a maximal 4-directionally (horizontal or vertical) connected group of 1s.

The grid is said to be connected if we have exactly one island, otherwise is said disconnected.

In one day, we are allowed to change any single land cell (1) into a water cell (0).

Return the minimum number of days to disconnect the grid.



Example 1:



Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
Output: 2
Explanation: We need at least 2 days to get a disconnected grid.
Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
Example 2:

Input: grid = [[1,1]]
Output: 2
Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
Example 3:

Input: grid = [[1,0,1,0]]
Output: 0
Example 4:

Input: grid = [[1,1,0,1,1],
               [1,1,1,1,1],
               [1,1,0,1,1],
               [1,1,0,1,1]]
Output: 1
Example 5:

Input: grid = [[1,1,0,1,1],
               [1,1,1,1,1],
               [1,1,0,1,1],
               [1,1,1,1,1]]
Output: 2


Constraints:

1 <= grid.length, grid[i].length <= 30
grid[i][j] is 0 or 1.
 */
public class MinimumNumberOfDaysToDisconnectIsland {
    int[] dirs = {0, 1, 0, -1, 0};
    int rows, cols;
    boolean[][] visited;
    public int minDays(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        int islands = totalIslands(grid, visited);
        if(islands == 0 || islands >= 2){
            return 0;
        }
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 1){
                    grid[r][c] = 0;
                    visited = new boolean[rows][cols];
                    int islands2 = totalIslands(grid, visited);
                    if(islands2 >= 2){
                        return 1;
                    }
                    grid[r][c] = 1;
                }
            }
        }
        return 2;
    }

    private int totalIslands(int[][] grid, boolean[][] visited){
        int res = 0;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 1 && !visited[r][c]){
                    res++;
                    dfs(grid, visited, r, c);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, boolean[][] visited, int r, int c) {
        visited[r][c] =  true;
        for(int i = 0; i < dirs.length - 1; i++){
            int nb_r = r + dirs[i];
            int nb_c = c + dirs[i + 1];
            if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && grid[nb_r][nb_c] == 1 && !visited[nb_r][nb_c]){
                dfs(grid, visited, nb_r, nb_c);
            }
        }
    }
}
