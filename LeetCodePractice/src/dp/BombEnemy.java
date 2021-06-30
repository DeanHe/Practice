package dp;
/*
        Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
        The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
        Note that you can only put the bomb at an empty cell.
        Example:
        For the given grid
        0 E 0 0
        E 0 W E
        0 E 0 0
        return 3. (Placing a bomb at (1,1) kills 3 enemies)
*/
public class BombEnemy {
	 /**
     * @param grid: Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return: an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies(char[][] grid) {
        // write your code here
        int ans = 0;
        if(grid == null || grid.length == 0){
            return ans;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int rowCount = 0;
        int[] colCount = new int[cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                // start from first row, count the enemies in the current row between two walls
                // store it to avoid recompute
                if(c == 0 || grid[r][c - 1] == 'W'){
                    rowCount = 0;
                    for(int i = c; i < cols && grid[r][i] != 'W'; i++){
                        if(grid[r][i] == 'E'){
                            rowCount++;
                        }
                    }
                }
                // start from solumn, count the enemies in the current col between two walls
                if(r == 0 || grid[r - 1][c] == 'W'){
                    colCount[c] = 0;
                    for(int i = r; i < rows && grid[i][c] != 'W'; i++){
                        if(grid[i][c] == 'E'){
                            colCount[c]++;
                        }
                    }
                }
                 // if this is a position to place the bomb, get the current max
                 if(grid[r][c] == '0'){
                     ans = Math.max(ans, rowCount + colCount[c]);
                 }
            }
        }
        return ans;
    }
}
