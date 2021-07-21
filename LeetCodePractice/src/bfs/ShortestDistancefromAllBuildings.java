package bfs;

/*
https://www.lintcode.com/problem/shortest-distance-from-all-buildings/description
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

�Each **0** marks an empty land which you can pass by freely.
�Each **1** marks a building which you cannot pass through.
�Each **2** marks an obstacle which you cannot pass through.
Example
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
*/

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistancefromAllBuildings {
    /**
     * @param grid: the 2D grid
     * @return: the shortest distance
     */
    int[][] distances;
    int[][] buildingsReached;
    int[] direct = {0, 1, 0, -1, 0};
    int rows, cols;

    public int shortestDistance(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        distances = new int[rows][cols];
        buildingsReached = new int[rows][cols];
        int buildingCount = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    boolean[][] visited = new boolean[rows][cols];
                    bfs(r, c, grid, visited);
                    buildingCount++;
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0 && buildingsReached[r][c] == buildingCount) {
                    minDistance = Math.min(minDistance, distances[r][c]);
                }
            }
        }
        return minDistance;
    }

    private void bfs(int start_r, int start_c, int[][] grid, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start_r, start_c});
        visited[start_r][start_c] = true;
        int dist = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                buildingsReached[r][c]++;
                distances[r][c] += dist;
                for (int j = 0; j < direct.length - 1; j++) {
                    int nb_r = r + direct[j];
                    int nb_c = c + direct[j + 1];
                    if (inbound(nb_r, nb_c, grid) && !visited[nb_r][nb_c]) {
                        queue.offer(new int[]{nb_r, nb_c});
                        visited[nb_r][nb_c] = true;
                    }
                }
            }
            dist++;
        }
    }

    private boolean inbound(int x, int y, int[][] grid) {
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 0;
    }
}
