package backtracking;

/*
On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.



Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation:
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.


Note:

1 <= grid.length * grid[0].length <= 20

analysis:
Time Complexity: O(3^N)
Space Complexity: O(N)
N
 )
 */
public class UniquePathsIII {
    int rows, cols, empty, res;
    int[] dirs = {0, 1, 0, -1, 0};

    public int uniquePathsIII(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int[] start = new int[2];
        int[] end = new int[2];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    start[0] = r;
                    start[1] = c;
                    empty++;
                } else if (grid[r][c] == 0) {
                    empty++;
                }
            }
        }
        dfs(grid, start);
        return res;
    }

    private void dfs(int[][] grid, int[] start) {
        if (grid[start[0]][start[1]] == 2) {
            if (empty == 0) {
                res++;
            }
            return;
        }
        empty--;
        grid[start[0]][start[1]] = -2;
        for (int i = 0; i < dirs.length - 1; i++) {
            int[] nb = new int[2];
            nb[0] = start[0] + dirs[i];
            nb[1] = start[1] + dirs[i + 1];
            if (nb[0] >= 0 && nb[0] < rows && nb[1] >= 0 && nb[1] < cols
                    && (grid[nb[0]][nb[1]] == 0 || grid[nb[0]][nb[1]] == 2)) {
                dfs(grid, nb);
            }
        }
        grid[start[0]][start[1]] = 0;
        empty++;
    }

}
