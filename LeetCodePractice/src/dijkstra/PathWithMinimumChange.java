package dijkstra;

import java.util.PriorityQueue;

/*
Google interview

Given a 2D array of integers, on each cell you can move to a four directed neighbor cell with lower or equal value.
If you are stuck at a cell, you are allowed to increase the cell value to reach a neighbor obeying the rule.
what is the minimum cost to go from top left to bottom right, with a path cell value in non increasing order.

similar to PathWithMinimumEffort
 */
public class PathWithMinimumChange {

    public int minCostToMakePath(int[][] matrix) {
        int[] dirs = {0, 1, 0, -1, 0};
        int rows = matrix.length, cols = matrix[0].length;
        Integer[][] dist = new Integer[rows][cols];
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> a[2] - b[2]);
        // r, c, cost, maxVal
        pq.offer(new int[]{0, 0, 0, matrix[rows - 1][cols - 1]});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int cost = cur[2];
            int maxVal = cur[3];
            if(dist[r][c] == null){
                dist[r][c] = cost;
                if (r == rows - 1 && c == cols - 1) {
                    return dist[r][c];
                }
                for (int i = 0; i + 1 < dirs.length; i++) {
                    int nb_r = r + dirs[i];
                    int nb_c = c + dirs[i + 1];
                    if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols) {
                        int change = 0;
                        if (maxVal < matrix[nb_r][nb_c]) {
                            change = matrix[nb_r][nb_c] - maxVal;
                        }
                        if(dist[nb_r][nb_c] == null){
                            pq.offer(new int[]{nb_r, nb_c, cost + change, Math.max(maxVal, matrix[nb_r][nb_c])});
                        }
                    }
                }
            }
        }
        return -1;
    }

    public int minCostToMakePath2(int[][] matrix) {
        int[] dirs = {0, 1, 0, -1, 0};
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] v = new boolean[rows][cols];
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> a[2] - b[2]);
        pq.offer(new int[]{0, 0, 0, matrix[rows - 1][cols - 1]});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int cost = cur[2];
            int maxVal = cur[3];
            if(!v[r][c]){
                v[r][c] = true;
                if (r == rows - 1 && c == cols - 1) {
                    return cost;
                }
                for (int i = 0; i + 1 < dirs.length; i++) {
                    int nb_r = r + dirs[i];
                    int nb_c = c + dirs[i + 1];
                    if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && !v[nb_r][nb_c]) {
                        int change = 0;
                        if (maxVal < matrix[nb_r][nb_c]) {
                            change = matrix[nb_r][nb_c] - maxVal;
                        }
                        pq.offer(new int[]{nb_r, nb_c, cost + change, Math.max(maxVal, matrix[nb_r][nb_c])});
                    }
                }
            }

        }
        return -1;
    }
}
