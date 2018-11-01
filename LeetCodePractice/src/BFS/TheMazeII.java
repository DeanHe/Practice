package BFS;

import java.util.*;

import BFS.TheMaze.Point;

public class TheMazeII {
	class Point {
		int x, y, dist;
		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	int rows, cols;
	/**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    	int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        rows = maze.length;
        cols = maze[0].length;
        Point startPoint = new Point(start[0], start[1], 0);
        Queue<Point> queue = new LinkedList<>();
        int[][] cost = new int[rows][cols];
        for(int[] row : cost){
        	Arrays.fill(row, Integer.MAX_VALUE);
        }
        queue.offer(startPoint);
        cost[startPoint.x][startPoint.y] = 0;
        while(!queue.isEmpty()){
        	Point cur = queue.poll();
        	for(int i = 0; i < 4; i++){
        		Point nb = new Point(cur.x, cur.y, cur.dist);
				while (nb.x + dx[i] >= 0 && nb.x + dx[i] < rows && nb.y + dy[i] >= 0 && nb.y + dy[i] < cols
						&& maze[nb.x + dx[i]][nb.y + dy[i]] == 0) {
					nb.x += dx[i];
					nb.y += dy[i];
					nb.dist++;
				}
        		if(nb.dist < cost[nb.x][nb.y]){
        			queue.offer(nb);
        			cost[nb.x][nb.y] = nb.dist;
        		}
        	}
        }
        return cost[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : cost[destination[0]][destination[1]];
    }
}
