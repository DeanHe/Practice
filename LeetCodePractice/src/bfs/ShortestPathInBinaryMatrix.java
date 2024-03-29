package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
#1091
In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.

Example 1:

Input: [[0,1],[1,0]]
Output: 2
Example 2:

Input: [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Note:
1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1
*/

public class ShortestPathInBinaryMatrix {
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int rows = grid.length;
        int cols = grid[0].length;
        if (grid[0][0] == 1 || grid[rows - 1][cols - 1] == 1) {
            return -1;
        }
        int step = 1;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = queue.poll();
                if (cur[0] == rows - 1 && cur[1] == cols - 1) {
                    return step;
                }
                for (int[] dir : dirs) {
                    int nb_x = cur[0] + dir[0];
                    int nb_y = cur[1] + dir[1];
                    if (nb_x >= 0 && nb_x < rows && nb_y >= 0 && nb_y < cols) {
                        if (grid[nb_x][nb_y] == 0 && !visited[nb_x][nb_y]) {
                            queue.offer(new int[]{nb_x, nb_y});
                            visited[nb_x][nb_y] = true;
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
