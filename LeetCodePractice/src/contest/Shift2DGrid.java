package contest;

import java.util.*;

/*
Given a 2D grid of size n * m and an integer k. You need to shift the grid k times.

In one shift operation:

Element at grid[i][j] becomes at grid[i][j + 1].
Element at grid[i][m - 1] becomes at grid[i + 1][0].
Element at grid[n - 1][m - 1] becomes at grid[0][0].
Return the 2D grid after applying shift operation k times.

Example 1:
Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[9,1,2],[3,4,5],[6,7,8]]

Example 2:
Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]

Example 3:
Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
Output: [[1,2,3],[4,5,6],[7,8,9]]

Constraints:
1 <= grid.length <= 50
1 <= grid[i].length <= 50
-1000 <= grid[i][j] <= 1000
0 <= k <= 100
*/
public class Shift2DGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] temp = new int[rows][cols];
        for(int r  = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                int c2 = (c + k) % cols;
                int r2 = (r + (c + k) / cols) % rows;
                temp[r2][c2] = grid[r][c];
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int r  = 0; r < rows; r++){
            res.add(new ArrayList<>());
            int last = res.size() - 1;
            for(int c = 0; c < cols; c++){
                res.get(last).add(temp[r][c]);
            }
        }
        return res;
    }
}
