package BFS;
/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.
Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".
The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example
Example 1:

Input:
[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
[4,3]
[0,1]

Output:
"lul"
Example 2:

Input:
[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
[0,0]
[1,1]
[2,2]
[3,3]
Output:
"impossible"
Notice
1.There is only one ball and one hole in the maze.
2.Both the ball and hole exist on an empty space, and they will not be at the same position initially.
3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
4.The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.
*/

import java.util.*;

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
