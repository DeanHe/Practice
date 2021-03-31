package dijkstra;

import java.util.PriorityQueue;

/*
Google interview

Given a 2D array of integers, on each cell you can move to a four directed neighbor cell with lower or equal value.
If you are stuck at a cell, you are allowed to increase the cell value to reach a neighbor obeying the rule.
what is the minimum cost to go from top left to bottom right, with a path cell value in non increasing order.
 */
public class PathWithMinimumChange {

    public int minCostToMakePath(int[][] matrix) {
        int[] dirs = {0, 1, 0, -1, 0};
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] v = new boolean[rows][cols];
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> a[2] - b[2]);
        pq.offer(new int[]{rows - 1, cols - 1, 0, matrix[rows - 1][cols - 1]});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int cost = cur[2];
            int maxVal = cur[3];
            if(!v[r][c]){
                v[r][c] = true;
                if (r == 0 && c == 0) {
                    return cost;
                }
                for (int i = 0; i < dirs.length - 1; i++) {
                    int nb_r = r + dirs[i];
                    int nb_c = c + dirs[i + 1];
                    if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && !v[nb_r][nb_c]) {
                        int change = 0;
                        if (matrix[nb_r][nb_c] < maxVal) {
                            change = maxVal - matrix[nb_r][nb_c];
                        }
                        pq.offer(new int[]{nb_r, nb_c, cost + change, Math.max(maxVal, matrix[nb_r][nb_c])});
                    }
                }
            }

        }
        return -1;
    }
}
