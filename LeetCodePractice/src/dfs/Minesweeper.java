package dfs;

import java.util.LinkedList;
import java.util.Queue;

/*
Let's play the minesweeper game (Wikipedia, online game)!

You are given an m x n char matrix board representing the game board where:

'M' represents an unrevealed mine,
'E' represents an unrevealed empty square,
'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
'X' represents a revealed mine.
You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').

Return the board after revealing this position according to the following rules:

If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.


Example 1:


Input: board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], click = [3,0]
Output: [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
Example 2:


Input: board = [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]], click = [1,2]
Output: [["B","1","E","1","B"],["B","1","X","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 50
board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
click.length == 2
0 <= clickr < m
0 <= clickc < n
board[clickr][clickc] is either 'M' or 'E'.

analysis:
BFS
 */
public class Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        int rows = board.length, cols = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(click);
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            if(board[r][c] == 'M'){
                board[r][c] = 'X';
            } else {
                // count 8 neighbors mines
                int cnt = 0;
                for(int i = -1; i <= 1; i++){
                    for(int j = -1; j <= 1; j++){
                        if(i == 0 && j == 0){
                            continue;
                        }
                        int nb_r = r + i;
                        int nb_c = c + j;
                        if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols){
                            if(board[nb_r][nb_c] == 'M' || board[nb_r][nb_c] == 'X'){
                                cnt++;
                            }
                        }
                    }
                }
                if(cnt > 0){
                    board[r][c] = (char)('0' + cnt);
                } else {
                    // continue bfs search
                    board[r][c] = 'B';
                    for(int i = -1; i <= 1; i++){
                        for(int j = -1; j <= 1; j++){
                            if(i == 0 && j == 0){
                                continue;
                            }
                            int nb_r = r + i;
                            int nb_c = c + j;
                            if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols){
                                if(board[nb_r][nb_c] == 'E'){
                                    q.offer(new int[]{nb_r, nb_c});
                                    board[nb_r][nb_c] = 'B';
                                }
                            }
                        }
                    }
                }
            }
        }
        return board;
    }
}
