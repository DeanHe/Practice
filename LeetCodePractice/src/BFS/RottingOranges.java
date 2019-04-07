package BFS;

import java.util.LinkedList;
import java.util.Queue;

/*n a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

Example 1:
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:
Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Note:
1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.*/
public class RottingOranges {
	public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int r = 0; r < rows; r++){
        	for(int c = 0; c < cols; c++){
        		if(grid[r][c] == 1){
        			fresh++;
        		} else if(grid[r][c] == 2){
        			queue.offer(new int[] {r, c});
        		}
        	}
        }
        int[] direct = new int[]{0, 1, 0, -1, 0};
        int day = 0;
        while(!queue.isEmpty()){
        	int len = queue.size();
        	for(int i = 0; i < len; i++){
        		int[] cur = queue.poll();
        		for(int j = 0; j < direct.length - 1; j++){
        			int nb_r = cur[0] + direct[j];
        			int nb_c = cur[1] + direct[j + 1];
        			if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && grid[nb_r][nb_c] == 1){
        				fresh--;
        				grid[nb_r][nb_c] = 2;
        				queue.offer(new int[]{nb_r, nb_c});
        			}
        		}
        	}
        	day++;
        }
        if(fresh > 0){
        	return -1;
        }
        if(day > 0){
        	return day - 1;
        }
        return day;
    }
}
