package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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

analysis:
bfs
TC O(rows * cols * k)
*/
public class ShortestPathInaGridWithObstaclesElimination {
    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dirs = {0, 1, 0, -1, 0};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[rows][cols][k + 1];
        visited[0][0][0] = true;
        queue.offer(new int[]{0, 0, 0});
        int step = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for(int i = 0; i < sz; i++){
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                int obs = cur[2];
                if (cur[0] == rows - 1 && cur[1] == cols - 1) {
                    return step;
                }
                for (int j = 0; j < dirs.length - 1; j++) {
                    int nb_r = r + dirs[j];
                    int nb_c = c + dirs[j + 1];
                    int nb_obs = obs;
                    if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols) {
                        if (grid[nb_r][nb_c] == 1) {
                            nb_obs++;
                        }
                        if (nb_obs <= k && !visited[nb_r][nb_c][nb_obs]) {
                            visited[nb_r][nb_c][nb_obs] = true;
                            queue.offer(new int[]{nb_r, nb_c, nb_obs});
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }


    int rows, cols;
    public List<int[]> shortestPathII(int[][] grid, int k) {
        rows = grid.length;
        cols = grid[0].length;
        int[] dirs = {0, 1, 0, -1, 0};
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> res = new ArrayList<>();
        boolean[][][] visited = new boolean[rows][cols][k + 1];
        int[][][] pre = new int[rows][cols][rows * cols];
        visited[0][0][0] = true;
        queue.offer(new int[]{0, 0, 0});
        int step = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for(int i = 0; i < sz; i++){
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                int obs = cur[2];
                if (cur[0] == rows - 1 && cur[1] == cols - 1) {
                     print(res, pre, r, c, step);
                     return res;
                }
                for (int j = 0; j < dirs.length - 1; j++) {
                    int nb_r = r + dirs[j];
                    int nb_c = c + dirs[j + 1];
                    int nb_obs = obs;
                    if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols) {
                        if (grid[nb_r][nb_c] == 1) {
                            nb_obs++;
                        }
                        if (nb_obs <= k && !visited[nb_r][nb_c][nb_obs]) {
                            visited[nb_r][nb_c][nb_obs] = true;
                            if(pre[nb_r][nb_c][step + 1] == 0){
                                pre[nb_r][nb_c][step + 1] = r * cols + c;
                            }
                            queue.offer(new int[]{nb_r, nb_c, nb_obs});
                        }
                    }
                }
            }
            step++;
        }
        return null;
    }

    private void print(List<int[]> ls, int[][][] pre, int r, int c, int step) {
        ls.add(0, new int[]{r, c});
        if(r == 0 && c == 0){
            return;
        }
        int next = pre[r][c][step];
        int nb_r = next / cols;
        int nb_c = next % cols;
        print(ls, pre, nb_r, nb_c, step - 1);
    }
}
