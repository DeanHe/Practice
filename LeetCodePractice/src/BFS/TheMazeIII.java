package BFS;

import java.util.*;

import BFS.TheMazeII.Point;

public class TheMazeIII {
	class Point {
		int x, y, dist;
		String path;
		public Point(int x, int y, int dist, String path) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.path = path;
		}
		public boolean lessThan(Point p){
			if(this.dist < p.dist){
				return true;
			} else if (this.dist == p.dist) {
				if(path.compareTo(p.path) < 0){
					return true;
				}
			}
			return false;
		}
	}
	int rows, cols;
	/**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */
    public String findShortestWay(int[][] maze, int[] start, int[] destination) {
    	int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        String[] directStr = {"r","d","l","u"};
        rows = maze.length;
        cols = maze[0].length;
        Queue<Point> queue = new LinkedList<>();
        Point[][] cost = new Point[rows][cols];
        for(int r = 0; r < rows; r++){
        	for(int c =0; c < cols; c++){
        		cost[r][c] = new Point(r, c, Integer.MAX_VALUE, "");
        	}
        }
        cost[start[0]][start[1]].dist = 0;
        queue.offer(cost[start[0]][start[1]]);
        boolean updateQueue = false;
        while(!queue.isEmpty()){
        	Point cur = queue.poll();
        	for(int i = 0; i < 4; i++){
        		Point nb = new Point(cur.x, cur.y, cur.dist, cur.path + directStr[i]);
				while (nb.x + dx[i] >= 0 && nb.x + dx[i] < rows && nb.y + dy[i] >= 0 && nb.y + dy[i] < cols
						&& maze[nb.x + dx[i]][nb.y + dy[i]] == 0) {
					nb.x += dx[i];
					nb.y += dy[i];
					nb.dist++;
					if(nb.lessThan(cost[nb.x][nb.y])){
	        			cost[nb.x][nb.y].dist = nb.dist;
	        			cost[nb.x][nb.y].path = nb.path;
	        			updateQueue = true;
	        		}
				}
        		if(updateQueue){
        			queue.offer(nb);
        			updateQueue = false;
        		}
        	}
        }
        return cost[destination[0]][destination[1]].dist == Integer.MAX_VALUE ? "impossible" : cost[destination[0]][destination[1]].path;
    }
}
