package contest;
/*
You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.



Example 1:


Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
Example 2:


Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.


Constraints:

m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1.

hint:
Let's use floodfill to iterate over the islands of the second grid
Let's note that if all the cells in an island in the second grid
if they are represented by land in the first grid then they are connected hence making that island a sub-island
 */
public class CountSubIslands {
    int[] dirs = {0, 1, 0, -1, 0};
    int rows, cols;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        rows = grid1.length;
        cols = grid1[0].length;
        int res = 0;
        for(int r = 0; r < rows; r++){
            for(int c = 0 ; c < cols; c++){
                if(grid2[r][c] == 1){
                    grid2[r][c] = 0;
                    if(dfs(grid1, grid2, r, c)){
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int r, int c) {
        boolean res = true;
        for(int i = 0; i + 1 < dirs.length; i++){
            int nb_r = r + dirs[i];
            int nb_c = c + dirs[i + 1];
            if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && grid2[nb_r][nb_c] == 1){
                grid2[nb_r][nb_c] = 0;
                res &= dfs(grid1, grid2, nb_r, nb_c);
            }
        }
        if(grid1[r][c] == 1){
            return res;
        }
        return false;
    }
}

