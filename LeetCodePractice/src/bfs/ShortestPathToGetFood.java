package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

Example 1:

Image text

Input: grid = [[“X”,”X”,”X”,”X”,”X”,”X”],[“X”,”*”,”O”,”O”,”O”,”X”],[“X”,”O”,”O”,”#”,”O”,”X”],[“X”,”X”,”X”,”X”,”X”,”X”]]

Output: 3

Explanation: It takes 3 steps to reach the food.

Example 2:

Image text

Input: grid = [[“X”,”X”,”X”,”X”,”X”],[“X”,”*”,”X”,”O”,”X”],[“X”,”O”,”X”,”#”,”X”],[“X”,”X”,”X”,”X”,”X”]]

Output: -1

Explanation: It is not possible to reach the food.

Example 3:

Image text

Input: grid = [[“X”,”X”,”X”,”X”,”X”,”X”,”X”,”X”],[“X”,”*”,”O”,”X”,”O”,”#”,”O”,”X”],[“X”,”O”,”O”,”X”,”O”,”O”,”X”,”X”],[“X”,”O”,”O”,”O”,”O”,”#”,”O”,”X”],[“X”,”X”,”X”,”X”,”X”,”X”,”X”,”X”]]

Output: 6

Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.

Example 4:

Input: grid = [[“O”,”*”],[”#”,”O”]]

Output: 2

Example 5:

Input: grid = [[“X”,”*”],[”#”,”X”]]

Output: -1

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[row][col] is '*', 'X', 'O', or '#'.
The grid contains exactly one '*'.

TC:(MN)
 */
public class ShortestPathToGetFood {
    public int getFood(char[][] grid) {
        int[] dirs = {0, 1, 0, -1, 0};
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> q = new LinkedList<>();
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == '*'){
                    q.offer(new int[]{r, c});
                    visited[r][c] = true;
                    break;
                }
            }
        }
        int step = 0;
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i = 0; i < sz; i++){
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                if(grid[r][c] == '#'){
                    return step;
                }
                for(int j = 0; j + 1 < dirs.length; j++){
                    int nb_r = r + dirs[j];
                    int nb_c = c + dirs[j + 1];
                    if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && grid[nb_r][nb_c] != 'X' && !visited[nb_r][nb_c]){
                        visited[nb_r][nb_c] = true;
                        q.offer(new int[]{nb_r, nb_c});
                    }
                }
            }
            step++;
        }
        return step;
    }
}

