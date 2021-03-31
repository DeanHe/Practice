package dfs;
/*
A robot is located at the top-left corner of a m x n grid.

The robot can move any direction at any point in time, but every grid can only be up to once. The robot is trying to reach the bottom-right corner of the grid.

How many possible unique paths are there?

Example
Given m = 2 and n = 3, return 4.
Given m = 3 and n = 3, return 12.
*/
public class FourWaysUniquePaths {
	/**
     * @param m: the row
     * @param n: the column
     * @return: the possible unique paths
     */
	int[] direct = {0, 1, 0, -1, 0};
	int rows, cols;
	boolean[][] visited;
	int count = 0;
    public int uniquePaths(int m, int n) {
        // Write your code here
    	rows = m;
    	cols = n;
    	visited = new boolean[m][n];
    	dfs(0, 0);
    	return count;
    }
    private void dfs(int r, int c) {
    	if(r == rows - 1 && c == cols - 1){
    		count++;
    		return;
    	}
    	visited[r][c] = true;
    	for (int i = 0; i < direct.length - 1; i++) {
    		int nb_r = r + direct[i];
    		int nb_c = c + direct[i + 1];
    		if (isValid(nb_r, nb_c)) {
    			dfs(nb_r, nb_c);
    		}
    	}
    	visited[r][c] = false;
    }
    private boolean isValid(int r, int c) {
    	if (r >= 0 && r < rows && c >= 0 && c < cols && !visited[r][c]) {
    		return true;
    	}
    	return false;
    }
}
