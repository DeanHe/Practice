package unionFind;
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

import java.util.Arrays;

public class BricksFallingWhenHit {
    int rows, cols;
    int[] dirs = {0, 1, 0, -1, 0};
    int[][] grid;
    int[] parent, size;

    public int[] hitBricks(int[][] grid, int[][] hits) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        parent = new int[rows * cols + 1];
        size = new int[rows * cols + 1];
        int[] res = new int[hits.length];
        Arrays.fill(size, 1);
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        // Mark cells to hit as 2.
        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) {
                grid[hit[0]][hit[1]] = 2;
            }
        }
        // Union around not erased bricks
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    unionAround(r, c);
                }
            }
        }
        int numBricksLeft = size[findRoot(0)]; // number of bricks connect to top after last erase
        for (int i = hits.length - 1; i >= 0; i--) {
            int hit_r = hits[i][0];
            int hit_c = hits[i][1];
            if (grid[hit_r][hit_c] == 2) {
                grid[hit_r][hit_c] = 1; // Restore to last erasure.
                unionAround(hit_r, hit_c);
                int numBricksBeforeHit = size[findRoot(0)];
                res[i] = Math.max(0, numBricksBeforeHit - numBricksLeft - 1);
                numBricksLeft = numBricksBeforeHit;
            }
        }
        return res;
    }

    private void unionAround(int r, int c) {
        int idx = r * cols + c + 1;
        for (int i = 0; i < dirs.length - 1; i++) {
            int nb_r = r + dirs[i];
            int nb_c = c + dirs[i + 1];
            if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && grid[nb_r][nb_c] == 1) {
                int nb_idx = nb_r * cols + nb_c + 1;
                union(nb_idx, idx);
            }
        }
        if (r == 0) {
            union(idx, 0); // Connect to the top of the grid. use 0 as the top roof
        }
    }

    private void union(int a, int b) {
        int root_a = findRoot(a);
        int root_b = findRoot(b);
        if (root_a != root_b) {
            parent[root_a] = root_b;
            size[root_b] += size[root_a];
            size[root_a] = 0;
        }
    }

    private int findRoot(int x) {
        int root = x;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (parent[x] != root) {
            int father = parent[x];
            parent[x] = root;
            x = father;
        }
        return root;
    }
}
