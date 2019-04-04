package BFS;
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
       Queue<int[]> queue = new LinkedList<>();
       queue.offer(new int[]{r, c});
       board[r][c] = '#';
       while(!queue.isEmpty()){
           int[] cur = queue.poll();
           int cur_r = cur[0];
           int cur_c = cur[1];
           if(cur_r > 0  && board[cur_r - 1][cur_c] == 'O'){
               board[cur_r - 1][cur_c] = '#';
               queue.offer(new int[]{cur_r - 1, cur_c});
           }
           if(cur_r < rows - 1 && board[cur_r + 1][cur_c] == 'O'){
               board[cur_r + 1][cur_c] = '#';
               queue.offer(new int[]{cur_r + 1, cur_c});
           }
           if(cur_c > 0 && board[cur_r][cur_c - 1] == 'O'){
               board[cur_r][cur_c - 1] = '#';;
               queue.offer(new int[]{cur_r, cur_c - 1});
           }
           if(cur_c < cols - 1 && board[cur_r][cur_c + 1] == 'O'){
               board[cur_r][cur_c + 1] = '#';
               queue.offer(new int[]{cur_r, cur_c + 1});
           }
       }
   }
}
