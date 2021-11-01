package bfs;
/*
Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example 1:

Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
Two cells are connected if they are adjacent cells connected horizontally or vertically.


Example 2:

Input: board = [["X"]]
Output: [["X"]]

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
*/
import java.util.*;

public class SurroundedRegions {
	/**
     * @param board a 2D board containing 'X' and 'O'
     * @return void
     */
   public void solve(char[][] board) {
       if(board == null || board.length == 0 || board[0].length == 0){
           return;
       }
       int rows = board.length;
       int cols = board[0].length;
       int[] dirs = {0, 1, 0, -1, 0};
       Queue<int[]> queue = new LinkedList<>();
       for(int r = 0; r < rows; r++){
           if(board[r][0] == 'O'){
               queue.offer(new int[]{r, 0});
               board[r][0] = '#';
           }
           if(board[r][cols - 1] == 'O'){
               queue.offer(new int[]{r, cols - 1});
               board[r][cols - 1] = '#';
           }
       }
       for(int c = 0; c < cols; c++){
           if(board[0][c] == 'O'){
               queue.offer(new int[]{0, c});
               board[0][c] = '#';
           }
           if(board[rows - 1][c] == 'O'){
               queue.offer(new int[]{rows - 1, c});
               board[rows - 1][c] = '#';
           }
       }
       //bfs
       while(!queue.isEmpty()){
           int[] cur = queue.poll();
           int cur_r = cur[0];
           int cur_c = cur[1];
           for(int i = 0; i < dirs.length - 1; i++){
               int nb_r = cur_r + dirs[i];
               int nb_c = cur_c + dirs[i + 1];
               if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && board[nb_r][nb_c] == 'O'){
                   System.out.println(nb_r + ":" + nb_c);
                   board[nb_r][nb_c] = '#';
                   queue.offer(new int[]{nb_r, nb_c});
               }
           }
       }
       for(int r = 0; r < rows; r++){
           for(int c = 0; c < cols; c++){
               if(board[r][c] == '#'){
                   board[r][c] = 'O';
               } else if(board[r][c] == 'O'){
                   board[r][c] = 'X';
               }
           }
       }
   }
}
