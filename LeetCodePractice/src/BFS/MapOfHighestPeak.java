package BFS;

import java.util.LinkedList;
import java.util.Queue;

/*
#1765

You are given an integer matrix isWater of size m x n that represents a map of land and water cells.

If isWater[i][j] == 0, cell (i, j) is a land cell.
If isWater[i][j] == 1, cell (i, j) is a water cell.
You must assign each cell a height in a way that follows these rules:

The height of each cell must be non-negative.
If the cell is a water cell, its height must be 0.
Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
Find an assignment of heights such that the maximum height in the matrix is maximized.

Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them.



Example 1:



Input: isWater = [[0,1],[0,0]]
Output: [[1,0],[2,1]]
Explanation: The image shows the assigned heights of each cell.
The blue cell is the water cell, and the green cells are the land cells.
Example 2:



Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
Output: [[1,1,0],[0,1,1],[1,2,2]]
Explanation: A height of 2 is the maximum possible height of any assignment.
Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.


Constraints:

m == isWater.length
n == isWater[i].length
1 <= m, n <= 1000
isWater[i][j] is 0 or 1.
There is at least one water cell.

analysis:
how to not use visited matrix to track, save Space
 */
public class MapOfHighestPeak {
    public int[][] highestPeak(int[][] isWater) {
        int[] dirs = {0, 1, 0, -1, 0};
        int rows = isWater.length, cols = isWater[0].length;
        int[][] res = new int[rows][cols];
        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (isWater[r][c] == 1) {
                    res[r][c] = 0;
                    q.offer(new int[]{r, c});
                } else {
                    res[r][c] = -1;
                }
            }
        }
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                for (int j = 0; j < dirs.length - 1; j++) {
                    int nb_r = r + dirs[j];
                    int nb_c = c + dirs[j + 1];
                    if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && res[nb_r][nb_c] == -1) {
                        res[nb_r][nb_c] = res[r][c] + 1;
                        q.offer(new int[]{nb_r, nb_c});
                    }
                }
            }
        }
        return res;
    }
}
