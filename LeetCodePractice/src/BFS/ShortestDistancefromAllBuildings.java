package BFS;
// https://www.lintcode.com/problem/shortest-distance-from-all-buildings/description
import java.util.*;

public class ShortestDistancefromAllBuildings {
	/**
     * @param grid: the 2D grid
     * @return: the shortest distance
     */
    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    int[][] distances;
    int[][] numReach;
    int rows, cols;
    public int shortestDistance(int[][] grid) {
        // write your code here
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        distances = new int[rows][cols];
        numReach = new int[rows][cols];
        int buildingCount = 0;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 1){
                    boolean[][] visited = new boolean[rows][cols];
                    bfs(r, c, grid, visited);
                    buildingCount++;
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 0 && numReach[r][c] == buildingCount){
                    minDistance = Math.min(minDistance, distances[r][c]);
                }
            }
        }
        return minDistance;
    }
    private void bfs(int r, int c, int[][] grid, boolean[][] visited){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(r, c));
        visited[r][c] = true;
        int dist = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Point cur = queue.poll();
                numReach[cur.x][cur.y]++;
                distances[cur.x][cur.y] += dist;
                if(inbound(cur.x + 1, cur.y, grid) && !visited[cur.x + 1][cur.y]){
                    queue.offer(new Point(cur.x + 1, cur.y));
                    visited[cur.x + 1][cur.y] = true;
                }
                if(inbound(cur.x - 1, cur.y, grid) && !visited[cur.x - 1][cur.y]){
                    queue.offer(new Point(cur.x - 1, cur.y));
                    visited[cur.x - 1][cur.y] = true;
                }
                if(inbound(cur.x, cur.y + 1, grid) && !visited[cur.x][cur.y + 1]){
                    queue.offer(new Point(cur.x, cur.y + 1));
                    visited[cur.x][cur.y + 1] = true;
                }
                if(inbound(cur.x, cur.y - 1, grid) && !visited[cur.x][cur.y - 1]){
                    queue.offer(new Point(cur.x, cur.y - 1));
                    visited[cur.x][cur.y - 1] = true;
                }
            }
            dist++;
        }
    }
    private boolean inbound(int x, int y, int[][] grid){
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 0;
    }
}
