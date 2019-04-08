package DFS;
/*Follow up for N-Queens problem.
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
0 0 1 0*/
public class NQueensII {
	/**
     * Calculate the total number of distinct N-Queen solutions.
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
     public static int count = 0;
    public int totalNQueens(int n) {
        //write your code here
        if(n < 1){
            return 0;
        }
        int[] board = new int[n];
        dfs(board, n, 0);
        return count;
        
    }
    private void dfs(int[] board, int n, int row){
        if(n == row){
            count++;
            return;
        }
        for(int c = 0; c < n; c++){
            if(isValid(board, row, c)){
                board[row] = c;
                dfs(board, n, row + 1);
                board[row] = 0;
            }
        }
    }
    boolean isValid(int[] board, int row, int col){
        for(int i = 0; i < row; i++){
            if(board[i] == col){
        		return false;
        	}
        	if(Math.abs(i - row) == Math.abs(board[i] - col)){
        		return false;
        	}
        }
        return true;
    }
}
