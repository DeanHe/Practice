package dp;
/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

Example
For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
*/
public class MaximalSquare {
	/**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        // write your code here
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
    		return 0;
    	}
    	int max = 0, rows = matrix.length, cols = matrix[0].length;
    	int[][] dp = new int[rows][cols];
    	//init
    	for(int r = 0; r < rows; r++){
    		if(matrix[r][0] == 1){
    			max = 1;
    		}
    		dp[r][0] = matrix[r][0];
    	}
    	
    	for(int c = 0; c < cols; c++){
    		if(matrix[0][c] == 1){
    			max = 1;
    		}
    		dp[0][c] = matrix[0][c];
    	}
    	//transfer
    	for(int r = 1; r < rows; r++){
    		for(int c = 1; c < cols; c++){
    			if(matrix[r][c] == 1){
    				// mem[r][c] means the largest square width by bottom-right corner at r, c
                    // mem[r][c] = min(mem[r-1][c-1],mem[r][c-1],mem[r-1][c]) + 1
    				int temp = Math.min(dp[r - 1][c - 1], dp[r - 1][c]);
    				dp[r][c] = Math.min(temp, dp[r][c - 1]) + 1;
    				if(dp[r][c] > max){
    					max = dp[r][c];
    				}
    			}
    		}
    	}
    	return max * max;
    	
    }
}
