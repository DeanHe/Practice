package dfs;

import java.util.HashSet;
import java.util.Set;

/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island has the same shape as another island (and not rotated or reflected).

Notice that:

11
1
and

 1
11
are considered different island shapes, because we do not consider reflection / rotation.

Example
Given grid = 
[
[1,1,0,0,0],
[1,1,0,0,0],
[0,0,0,1,1],
[0,0,0,1,1]
]
return 1
Given grid = 
[
[1,1,0,1,1],
[1,0,0,0,0],
[0,0,0,0,1],
[1,1,0,1,1]
]
return 3
Notice
The length of each dimension in the given grid does not exceed 50.
*/
public class NumberOfDistinctIslands {
    int[] dirs = {0, -1, 0, 1, 0};
    Set<String> set = new HashSet<>();
    int rows, cols, start_r, start_c;
	/**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
    public int numberofDistinctIslands(int[][] grid) {
        // write your code here
        rows = grid.length;
        cols = grid[0].length;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 1){
                    StringBuilder shape  = new StringBuilder();
                    start_r = r;
                    start_c = c;
                    dfs(grid, r, c, shape);
                    set.add(shape.toString());
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int r, int c, StringBuilder sb) {
        sb.append((r - start_r) + "" + (c - start_c));
        grid[r][c] = 0;
        for(int i = 0; i < dirs.length - 1; i++){
            int nb_r = r + dirs[i];
            int nb_c = c + dirs[i + 1];
            if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && grid[nb_r][nb_c] == 1){
                dfs(grid, nb_r, nb_c, sb);
            }
        }
    }
}
