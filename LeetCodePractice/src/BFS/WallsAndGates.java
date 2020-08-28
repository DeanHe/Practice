package BFS;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a ROOM, that room should remain filled with INF

Example
Example1

Input:
[[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output:
[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]

Explanation:
the 2D grid is:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
the answer is:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
Example2

Input:
[[0,-1],[2147483647,2147483647]]
Output:
[[0,-1],[1,2]]
*/
public class WallsAndGates {
	/**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    public void wallsAndGates(int[][] rooms) {
        // write your code here
    	int rows = rooms.length;
    	int cols = rooms[0].length;
    	Queue<int[]> queue = new LinkedList<>();
    	for(int r = 0; r < rows; r++){
    		for(int c = 0; c < cols; c++){
    			if(rooms[r][c] == 0){
    				queue.offer(new int[]{r, c});
    			}
    		}
    	}
    	int[] direct = new int[]{0, -1, 0, 1, 0};
    	while(!queue.isEmpty()){
    		int qLen = queue.size();
    		for(int i = 0; i < qLen; i++){
    			int[] cur = queue.poll();
    			int cur_r = cur[0];
    			int cur_c = cur[1];
    			for(int j = 0; j < direct.length - 1; j++){
    				int nb_r = cur_r + direct[j];
    				int nb_c = cur_c + direct[j + 1];
    				if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && rooms[nb_r][nb_c] == Integer.MAX_VALUE){
    					rooms[nb_r][nb_c] = rooms[cur_r][cur_c] + 1;
    					queue.offer(new int[]{nb_r, nb_c});
    				}
    			}
    		}
    	}
    }
}
