package BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.

        Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

        Example 1:

        Input:
        grid =
        [[0,0,0],
        [1,1,0],
        [0,0,0],
        [0,1,1],
        [0,0,0]],
        k = 1
        Output: 6
        Explanation:
        The shortest path without eliminating any obstacle is 10.
        The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).


        Example 2:

        Input:
        grid =
        [[0,1,1],
        [1,1,1],
        [1,0,0]],
        k = 1
        Output: -1
        Explanation:
        We need to eliminate at least two obstacles to find such a walk.

        Constraints:

        grid.length == m
        grid[0].length == n
        1 <= m, n <= 40
        1 <= k <= m*n
        grid[i][j] == 0 or 1
        grid[0][0] == grid[m-1][n-1] == 0
*/
public class ShortestPathInaGridWithObstaclesElimination {
    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dirs = {0, 1, 0, -1, 0};
        Queue<int[]> queue = new LinkedList<>();
        int[][][] dist = new int[rows][cols][k + 1];
        for (int[][] matrix : dist) {
            for (int[] arr : matrix) {
                Arrays.fill(arr, Integer.MAX_VALUE);
            }
        }
        Arrays.fill(dist[0][0], 0);
        queue.offer(new int[]{0, 0, k});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int leftObs = cur[2];
            int cur_dist = dist[r][c][leftObs];
            if (cur[0] == rows - 1 && cur[1] == cols - 1) {
                return cur_dist;
            }
            for (int i = 0; i < dirs.length - 1; i++) {
                int nb_r = r + dirs[i];
                int nb_c = c + dirs[i + 1];
                int nb_leftObs = leftObs;
                if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols) {
                    if (grid[nb_r][nb_c] == 1) {
                        nb_leftObs--;
                    }
                    if (nb_leftObs >= 0 && cur_dist + 1 < dist[nb_r][nb_c][nb_leftObs]) {
                        dist[nb_r][nb_c][nb_leftObs] = cur_dist + 1;
                        queue.offer(new int[]{nb_r, nb_c, nb_leftObs});
                    }
                }
            }
        }
        return -1;
    }
}
