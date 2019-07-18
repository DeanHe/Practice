package Array;
/*According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.*/
public class GameOfLife {
	/**
     * @param board: the given board
     * @return: nothing
     */
	int[][] board;
	int rows, cols;
    public void gameOfLife(int[][] board) {
    	this.board = board;
        rows = board.length;
        cols = board[0].length;
        //define state:
        //00 - previous dead next state dead
        //11 - previous live next state live
        //10 - previous dead next state live
        //01 - previous live next state dead
        for(int r = 0; r < rows; r++){
        	for(int c = 0; c < cols; c++){
        		int lives = calculateLiveNeighbors(r, c);
        		if(board[r][c] == 1 && (lives == 2 || lives == 3)){
        			board[r][c] = 3;
        		}  else if(board[r][c] == 0 && lives == 3){
        			board[r][c] = 2;
        		}
        	}
        }
        for(int r = 0; r < rows; r++){
        	for(int c = 0; c < cols; c++){
        		board[r][c] = board[r][c] >> 1;
        	}
        }
        
    }
    private int calculateLiveNeighbors(int r, int c){
    	int lives = 0;
    	for(int x = Math.max(0, r - 1); x <= Math.min(rows - 1, r + 1); x++){
    		for(int y = Math.max(0, c - 1); y <= Math.min(cols - 1, c + 1); y++){
    			if(x == r && y == c){
    				continue;
    			}
    			lives += board[x][y] & 1;
    		}
    	}
    	return lives;
    }
}