package heap;

import java.util.*;

//https://www.jiuzhang.com/solution/trapping-rain-water-ii/
//Given n x m non-negative integers representing an elevation map 2d where the area of each cell is 1 x 1, 
//compute how much water it is able to trap after raining.
public class TrappingRainWaterII {
	class Cell {
	    public int x, y, height;
	    Cell() {}
	    Cell(int x, int y, int height) {
	        this.x = x;
	        this.y = y;
	        this.height = height;
	    }
	}

	class CellComparator implements Comparator<Cell> {
	    @Override
	    public int compare(Cell left, Cell right) {
	        return left.height - right.height;
	    }
	}
	
	int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
    
        PriorityQueue<Cell> minheap =  new PriorityQueue<>(new CellComparator());
        
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            minheap.offer(new Cell(i, 0, heights[i][0]));
            minheap.offer(new Cell(i, m - 1, heights[i][m-1]));
            visited[i][0] = true;
            visited[i][m - 1] = true;
        }
        
        for (int i = 0; i < m; i++) {
            minheap.offer(new Cell(0, i, heights[0][i]));
            minheap.offer(new Cell(n-1, i, heights[n-1][i]));
            visited[0][i] = true;
            visited[n - 1][i] = true;
        }

        int water = 0;
        while (!minheap.isEmpty()) {
            Cell cell = minheap.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = cell.x + dx[i];
                int ny = cell.y + dy[i];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                
                visited[nx][ny] = true;
                minheap.offer(new Cell(nx, ny, Math.max(cell.height, heights[nx][ny])));
                water = water + Math.max(0, cell.height - heights[nx][ny]);
            }
        }
        
        return water;
    } 
    
}
