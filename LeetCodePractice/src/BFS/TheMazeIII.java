package BFS;
/*There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example
Given:
a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

start coordinate (rowStart, colStart) = (0, 4)
destination coordinate (rowDest, colDest) = (4, 4)

Return:true
Notice
1.There is only one ball and one destination in the maze.
2.Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
5.The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/import java.util.*;

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
