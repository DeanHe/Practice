package DFS;
/*
We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.

We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.

Return an array representing the number of bricks that will drop after each erasure in sequence.

Example 1:
Input: 
grid = [[1,0,0,0],[1,1,1,0]]
hits = [[1,0]]
Output: [2]
Explanation: 
If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
Example 2:
Input: 
grid = [[1,0,0,0],[1,1,0,0]]
hits = [[1,1],[1,0]]
Output: [0,0]
Explanation: 
When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.
 

Note:

The number of rows and columns in the grid will be in the range [1, 200].
The number of erasures will not exceed the area of the grid.
It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
An erasure may refer to a location with no brick - if it does, no bricks drop.
*/

import java.util.HashSet;
import java.util.Set;

public class BricksFallingWhenHit {
    int rows, cols;
    int[] dirs = {0, 1, 0, -1, 0};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        rows = grid.length;
        cols = grid[0].length;
        int len = hits.length;
        // remove the brick at the each hit
        for (int i = 0; i < len; i++) {
            int[] hit = hits[i];
            int hit_r = hit[0];
            int hit_c = hit[1];
            if (grid[hit_r][hit_c] == 1) {
                grid[hit_r][hit_c] = 3; // means valid hit
            }
        }
        // mark remaining bricks connected to the top
        for (int c = 0; c < cols; c++) {
            dfs(0, c, grid);
        }
        // reversely add back the hit brick and count its not-connected-to-top neighbors
        int[] res = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            int[] hit = hits[i];
            int hit_r = hit[0];
            int hit_c = hit[1];
            if (grid[hit_r][hit_c] == 3) {
                grid[hit_r][hit_c] = 1;
                if (connectedToTop(hit_r, hit_c, grid)) {
                    res[i] = dfs(hit_r, hit_c, grid) - 1;
                }
            }
        }
        return res;
    }

    private int dfs(int r, int c, int[][] grid) {
        int validBrick = 0;
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return 0;
        }
        if (grid[r][c] == 1) {
            // 2 means effective brick still connected to top
            grid[r][c] = 2;
            validBrick++;
            for (int i = 0; i < dirs.length - 1; i++) {
                int nb_r = r + dirs[i];
                int nb_c = c + dirs[i + 1];
                validBrick += dfs(nb_r, nb_c, grid);
            }
        }
        return validBrick;
    }

    private boolean connectedToTop(int r, int c, int[][] grid) {
        if (r == 0) {
            return true;
        }
        if (r + 1 >= 0 && r + 1 < rows && grid[r + 1][c] == 2) {
            return true;
        }
        if (r - 1 >= 0 && r - 1 < rows && grid[r - 1][c] == 2) {
            return true;
        }
        if (c + 1 >= 0 && c + 1 < cols && grid[r][c + 1] == 2) {
            return true;
        }
        if (c - 1 >= 0 && c - 1 < cols && grid[r][c - 1] == 2) {
            return true;
        }
        return false;
    }
}
