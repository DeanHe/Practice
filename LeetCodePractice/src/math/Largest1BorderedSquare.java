package math;
/*Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.	 

Example 1:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
Output: 9
Example 2:

Input: grid = [[1,1,0,0]]
Output: 1
 

Constraints:

1 <= grid.length <= 100
1 <= grid[0].length <= 100
grid[i][j] is 0 or 1*/
public class Largest1BorderedSquare {
	public int largest1BorderedSquare(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] hor = new int[rows][cols];
        int[][] ver = new int[rows][cols];
        int maxLen = 0;
        for(int r = 0; r < rows; r++){
        	for(int c = 0; c < cols; c++){
        		if(grid[r][c] == 1){
        			hor[r][c] = (c == 0) ? 1 : 1 + hor[r][c - 1]; // auxillary horizontal array
        			ver[r][c] = (r == 0) ? 1 : 1 + ver[r - 1][c]; // auxillary vertical array
        		}
        	}
        }
        for(int r = rows - 1; r >= 0; r--){
        	for(int c = cols - 1; c >= 0; c--){
        		int candLen = Math.min(hor[r][c], ver[r][c]);
        		while(candLen > maxLen){
        			// make sure square top right is valid and bottom left is valid
        			if(hor[r - candLen + 1][c] >= candLen && ver[r][c - candLen + 1] >= candLen){
        				maxLen = candLen;
        			}
        			candLen--;
        		}
        	}
        }
        return maxLen * maxLen;
    }
}
