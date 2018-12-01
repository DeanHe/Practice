package DFS;

import java.util.*;


/*In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

 

Example 1:

Input: [[0,1],[1,0]]
Output: 1
Example 2:

Input: [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1
 

Note:

1 <= A.length = A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1*/
public class ShortestBridge {
	int rows, cols;
	boolean[][] visited;
	int[] direct = {0, 1, 0, -1, 0};
	Queue<int[]> queue = new LinkedList<>();
	
    public int shortestBridge(int[][] A) {
        rows = A.length;
        cols = A[0].length;
        visited = new boolean[rows][cols];
        findFirstIsland(A);
        return bfs(A);
        
    }
    private void findFirstIsland(int[][] A) {
    	for(int r = 0; r < rows; r++) {
        	for(int c = 0; c < cols; c++) {
        		if(A[r][c] == 1) {
        			dfs(A, r, c);
        			return;
        		}
        	}
        }
    }
    private void dfs(int[][] A, int r, int c) {
    	if(r >= 0 && r  < rows && c >= 0 && c < cols && !visited[r][c] && A[r][c] == 1) {
    		queue.offer(new int[] {r, c});
			visited[r][c] = true;
			for(int i = 0; i < direct.length - 1; i++) {
	    		int dx = direct[i];
	    		int dy = direct[i + 1];
	    		dfs(A, r + dy, c + dx);
	    	}
	    }
    }
    private int bfs(int[][] A) {
    	int step = 0;
    	while(!queue.isEmpty()) {
    		int size = queue.size();
    		for(int i = 0; i < size; i++) {
    			int[] cur = queue.poll();
    			if(A[cur[0]][cur[1]] == 1 && step > 0) {
					return step;
				}
				for(int j = 0; j < direct.length - 1; j++) {
					int dx = direct[j];
		    		int dy = direct[j + 1];
					int[] nb = new int[] {cur[0] + dy, cur[1] + dx};
					if(nb[0] >= 0 && nb[0] < rows && nb[1] >= 0 && nb[1] < cols && !visited[nb[0]][nb[1]]) {
						queue.offer(nb);
						visited[nb[0]][nb[1]] = true;
					}
				}
    			
    		}
    		step++;
    	}
    	return step;
    }
}
