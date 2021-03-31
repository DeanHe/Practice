package dfs;
/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

Follow up for N-Queens problem.
Now, instead outputting board configurations, return the total number of distinct solutions.

Example 1:
Input: n=1
Output: 1
Explanation:
1:
1

Example 2:
Input: n=4
Output: 2
Explanation:
1:
0 0 1 0
1 0 0 0
0 0 0 1
0 1 0 0
2:
0 1 0 0 
0 0 0 1
1 0 0 0
0 0 1 0
*/
public class NQueensII {
	/**
     * Calculate the total number of distinct N-Queen solutions.
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
	int count = 0, n = 0;
    public int totalNQueens(int n) {
        //write your code here
        this.n = n;
        if(n < 1){
            return 0;
        }
        int[] board = new int[n];
        dfs(board,0);
        return count;
        
    }
    private void dfs(int[] board, int row){
        if(n == row){
            count++;
            return;
        }
        for(int c = 0; c < n; c++){
            if(isValid(board, row, c)){
                board[row] = c;
                dfs(board, row + 1);
                board[row] = 0;
            }
        }
    }
    boolean isValid(int[] board, int row, int col){
        for(int r = 0; r < row; r++){
            if(board[r] == col){
        		return false;
        	}
        	if(Math.abs(r - row) == Math.abs(board[r] - col)){
        		return false;
        	}
        }
        return true;
    }
}
