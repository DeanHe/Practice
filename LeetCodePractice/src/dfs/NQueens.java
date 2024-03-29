package dfs;

import java.util.*;

/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.
Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example 1:
Input:1
Output:
   [["Q"]]

Example 2:
Input:4
Output:
[
  // Solution 1
  [".Q..",
   "...Q",
   "Q...",
   "..Q."
  ],
  // Solution 2
  ["..Q.",
   "Q...",
   "...Q",
   ".Q.."
  ]
]

Challenge
Can you do it without recursion?

analysis:
TC: O(N!) SC O(N^2)
*/
public class NQueens {
	/**
     * Get all distinct N-Queen solutions
     * @param n: The number of queens
     * @return: All distinct solutions
     * For example, A string '...Q' shows a queen on forth position
     */
    ArrayList<ArrayList<String>> solveNQueens(int n) {
        // write your code here
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        if(n <= 0){
            return res;
        }
        int[] board = new int[n];
        dfs(res, board, n, 0);
        return res;
    }
    
    void dfs(ArrayList<ArrayList<String>> res, int[] board, int n, int row){
        if(row == n){
            res.add(translateString(board));
            return;
        }
        for(int c = 0; c < n; c++){
            if(isValid(board, row, c)){
                board[row] = c;
                dfs(res, board, n, row + 1);
                board[row] = 0;
            }
        }
    }

    boolean isValid(int[] board, int row, int col){
        for(int r = 0; r < row; r++){
            if(board[r] == col){
                return false;
            }
            if( Math.abs(col - board[r]) == Math.abs(row - r)){
                return false;
            }
        }
        return true;
    }
    
    ArrayList<String> translateString(int[] board) {
        ArrayList<String> res = new ArrayList<>();
        for(int r = 0; r < board.length; r++){
            StringBuilder sb = new StringBuilder();
            for(int c = 0; c < board.length; c++){
                if(board[r] == c){
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            res.add(sb.toString());
        }
        return res;
    }
}
