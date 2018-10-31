package BFS;

import java.util.*;

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
        Point destinationPoint = new Point(destination[0], destination[1], Integer.MAX_VALUE);
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
        		Point nb = goDirect(cur, maze, new int[]{dx[i],dy[i]});
        		if(nb.dist < cost[nb.x][nb.y]){
        			queue.offer(nb);
        			cost[nb.x][nb.y] = nb.dist;
        		}
        	}
        }
        return cost[destinationPoint.x][destinationPoint.y] == Integer.MAX_VALUE ? -1 : cost[destinationPoint.x][destinationPoint.y];
    }
    private Point goDirect(Point cur, int[][] maze, int[] direct){
    	Point temp = new Point(cur.x + direct[0], cur.y + direct[1], cur.dist + 1);
    	while(temp.x >= 0 && temp.x < rows && temp.y >= 0 && temp.y < cols && maze[temp.x][temp.y] == 0){
    		cur = temp;
    		temp = new Point(cur.x + direct[0], cur.y + direct[1], cur.dist + 1);
    	}
    	return cur;
    }
}
