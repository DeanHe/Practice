package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
There is a ball in a maze with empty spaces and walls.
The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest number of turns for the ball to stop at the destination.
If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space.
You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

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

Return:12
Notice
1.There is only one ball and one destination in the maze.
2.Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
4.The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/
public class TheMazeGoogle {
    /**
     * @param maze:        the maze
     * @param start:       the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    public int shortestTurns(int[][] maze, int[] start, int[] destination) {
        int[] dirs = {0, 1, 0, -1, 0};
        int rows = maze.length;
        int cols = maze[0].length;
        Integer[][] cost = new Integer[rows][cols];
        int[] startPoint = new int[]{start[0], start[1]};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(startPoint);
        cost[startPoint[0]][startPoint[1]] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            for (int i = 0; i + 1 < dirs.length; i++) {
                int nb_r = r;
                int nb_c = c;
                while (nb_r + dirs[i] >= 0 && nb_r + dirs[i] < rows && nb_c + dirs[i + 1] >= 0 && nb_c + dirs[i + 1] < cols
                        && maze[nb_r + dirs[i]][nb_c + dirs[i + 1]] == 0) {
                    nb_r += dirs[i];
                    nb_c += dirs[i + 1];
                }
                if (cost[nb_r][nb_c] == null || cost[r][c] + 1 < cost[nb_r][nb_c]) {
                    queue.offer(new int[]{nb_r, nb_c});
                    cost[nb_r][nb_c] = cost[r][c] + 1;
                    System.out.println(nb_r + ":" + nb_c + ":" + cost[nb_r][nb_c]);
                }
            }
        }
        return cost[destination[0]][destination[1]] != null ? cost[destination[0]][destination[1]] - 1 : -1;
    }
}
