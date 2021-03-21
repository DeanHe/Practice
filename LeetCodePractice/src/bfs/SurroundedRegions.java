package bfs;
/*Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O''s into 'X''s in that surrounded region.

Example
X X X X
X O O X
X X O X
X O X X
After capture all regions surrounded by 'X', the board should be:

X X X X
X X X X
X X X X
X O X X*/

import java.util.*;

public class SurroundedRegions {
	/**
     * @param board a 2D board containing 'X' and 'O'
     * @return void
     */
   public void surroundedRegions(char[][] board) {
       // Write your code here
       if(board == null || board.length == 0 || board[0].length == 0){
           return;
       }
       int rows = board.length;
       int cols = board[0].length;
       for(int r = 0; r < rows; r++){
           if(board[r][0] == 'O'){
               bfs(board, r, 0);
           }
           if(board[r][cols - 1] == 'O'){
               bfs(board, r, cols - 1);
           }
       }
       for(int c = 0; c < cols; c++){
           if(board[0][c] == 'O'){
               bfs(board, 0, c);
           }
           if(board[rows - 1][c] == 'O'){
               bfs(board, rows - 1, c);
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
   private void bfs(char[][] board, int r, int c){
       int rows = board.length;
       int cols = board[0].length;
       int[] dirs = {0, 1, 0, -1, 0};
       Queue<int[]> queue = new LinkedList<>();
       queue.offer(new int[]{r, c});
       board[r][c] = '#';
       while(!queue.isEmpty()){
           int[] cur = queue.poll();
           int cur_r = cur[0];
           int cur_c = cur[1];
           for(int i = 0; i < dirs.length - 1; i++){
        	   int nb_r = cur_r + dirs[i];
        	   int nb_c = cur_c + dirs[i + 1];
        	   if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && board[nb_r][nb_c] == '0'){
        		   board[nb_r][nb_c] = '#';
        		   queue.offer(new int[]{nb_r, nb_c});
        	   }
           }
       }
   }
}
