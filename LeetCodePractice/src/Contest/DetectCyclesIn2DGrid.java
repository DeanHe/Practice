package Contest;
/*
Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.



Example 1:



Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2:



Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:



Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false


Constraints:

m == grid.length
n == grid[i].length
1 <= m <= 500
1 <= n <= 500
grid consists only of lowercase English letters.
 */
public class DetectCyclesIn2DGrid {
    int[] dirs = {0, -1, 0, 1, 0};
    int rows, cols;
    boolean[][] visited;
    public boolean containsCycle(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(!visited[r][c]){
                    if(dfs(r, c, r, c, grid, visited)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(int r, int c, int pre_r, int pre_c, char[][] grid, boolean[][] visited) {
        if(visited[r][c]){
            return false;
        }
        visited[r][c] = true;
        for(int i = 0; i < dirs.length - 1; i++){
            int nb_r = r + dirs[i];
            int nb_c = c + dirs[i + 1];
            if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && grid[r][c] == grid[nb_r][nb_c]){
                if(nb_r != pre_r || nb_c != pre_c){
                    if(dfs(nb_r, nb_c, r, c, grid, visited)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
