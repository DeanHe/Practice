package BFS;

import java.util.PriorityQueue;

/*Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:

Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

 

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.
After the rain, water is trapped between the blocks. The total volume of water trapped is 4.*/

public class TrappingRainWaterII {

	public class Cell {
		int row, col, height;
		public Cell(int row, int col, int height){
			this.row = row;
			this.col = col;
			this.height = height;
		}
	}
	/**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    public int trapRainWater(int[][] heights) {
    	if(heights == null || heights.length == 0 || heights[0].length == 0){
    		return 0;
    	}
    	int rows = heights.length;
    	int cols = heights[0].length;
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>((a, b) -> {
        	return a.height - b.height;
        });
        // Initially, add all the Cells which are on borders to the queue.
        for(int r = 0; r < rows; r++){
        	minHeap.offer(new Cell(r, 0, heights[r][0]));
        	minHeap.offer(new Cell(r, cols - 1, heights[r][cols - 1]));
        	visited[r][0] = true;
        	visited[r][cols - 1] = true;
        }
        for(int c = 0; c < cols; c++){
        	minHeap.offer(new Cell(0, c, heights[0][c]));
        	minHeap.offer(new Cell(rows - 1, c, heights[rows - 1][c]));
        	visited[0][c] = true;
        	visited[rows - 1][c] = true;
        }
        // from the borders, pick the shortest cell visited and check its neighbors:
        // if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
        // add all its neighbors to the queue.
        int res = 0;
        int[] dirs = {0, 1, 0, -1, 0};
        while(!minHeap.isEmpty()){
        	Cell cur = minHeap.poll();
        	for(int i = 0; i < dirs.length - 1; i++){
        		int nb_row = cur.row + dirs[i];
        		int nb_col = cur.col + dirs[i + 1];
        		if(nb_row >= 0 && nb_row < rows && nb_col >= 0 && nb_col < cols && !visited[nb_row][nb_col]){
        			if(heights[nb_row][nb_col] < cur.height){
        				res += cur.height - heights[nb_row][nb_col];
        			}
        			minHeap.offer(new Cell(nb_row, nb_col, Math.max(cur.height, heights[nb_row][nb_col])));
        			visited[nb_row][nb_col] = true;
        		}
        	}
        }
        return res;
    }
}
