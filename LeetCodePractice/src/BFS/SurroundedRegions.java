package BFS;

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
   private void bfs(char[][] board, int i, int j){
       int rows = board.length;
       int cols = board[0].length;
       int index = i * cols + j;
       Queue<Integer> queue = new LinkedList<>();
       queue.offer(index);
       board[i][j] = '#';
       while(!queue.isEmpty()){
           int cur = queue.poll();
           int r = cur / cols;
           int c = cur % cols;
           if(r > 0  && board[r - 1][c] == 'O'){
               board[r - 1][c] = '#';
               int new_index = (r - 1) * cols + c;
               queue.offer(new_index);
           }
           if(r < rows - 1 && board[r + 1][c] == 'O'){
               board[r + 1][c] = '#';
               int new_index = (r + 1) * cols + c;
               queue.offer(new_index);
           }
           if(c > 0 && board[r][c - 1] == 'O'){
               board[r][c - 1] = '#';
               int new_index = r * cols + c - 1;
               queue.offer(new_index);
           }
           if(c < cols - 1 && board[r][c + 1] == 'O'){
               board[r][c + 1] = '#';
               int new_index = r * cols + c + 1;
               queue.offer(new_index);
           }
       }
   }
}
