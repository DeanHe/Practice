package array;
/*
You are given an n x n binary grid board. In each move, you can swap any two rows with each other, or any two columns with each other.

Return the minimum number of moves to transform the board into a chessboard board. If the task is impossible, return -1.

A chessboard board is a board where no 0's and no 1's are 4-directionally adjacent.



Example 1:


Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
Output: 2
Explanation: One potential sequence of moves is shown.
The first move swaps the first and second column.
The second move swaps the second and third row.
Example 2:


Input: board = [[0,1],[1,0]]
Output: 0
Explanation: Also note that the board with 0 in the top left corner, is also a valid chessboard.
Example 3:


Input: board = [[1,0],[1,0]]
Output: -1
Explanation: No matter what sequence of moves you make, you cannot end with a valid chessboard.


Constraints:

n == board.length
n == board[i].length
2 <= n <= 30
board[i][j] is either 0 or 1.

 */
public class TransformToChessboard {
    public int movesToChessboard(int[][] board) {
        int n = board.length, rowsToMove = 0, colsToMove = 0, rowOneCnt = 0, colOneCnt = 0;
        // two types of rows if it can transform to a chessboard
        // e.g., if there's a row 01010011
        // then any other row must be either 01010011 or 10101100 (inverse)
        //
        // corollary: the 4 corners of any rectangle inside the board must be one of the following:
        // - 4 zeros
        // - 2 zeros 2 ones
        // - 4 ones
        //
        // checks the top left corner rectangle
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                if(((board[0][0] ^ board[r][0]) ^ (board[r][c] ^ board[0][c])) == 1){
                    return -1;
                }
            }
        }
        // first row and column, count of one's
        for(int i = 0; i < n; i++){
            rowOneCnt += board[0][i];
            colOneCnt += board[i][0];
            // if the final pattern is "1010..."
            if(board[i][0] == i % 2){
                rowsToMove++;
            }
            if(board[0][i] == i % 2){
                colsToMove++;
            }
        }
        // - if n == 2 * k, then count(0) == count(1) == k
        // - if n == 2 * k + 1, then count(0) == k, count(1) == k + 1
        //   or count(0) == k + 1, count(1) == k
        //
        // checking the first row and column is sufficient,
        // because the top left corner rectangle is verified
        if (rowOneCnt != (n / 2) && rowOneCnt != (n + 1) / 2) {
            return -1;
        }
        if (colOneCnt != (n / 2) || colOneCnt != (n + 1) / 2) {
            return -1;
        }

        if (n % 2 == 1) {
            // when n is odd
            // only one final pattern is possible
            //
            // if misplaced is even
            // then the final pattern is "1010..."
            //
            // if misplaced is odd,
            // then the final pattern is the inverse of "1010..."
            // i.e. "0101..."
            // and the actual count of misplaced elements is (n - misplace)
            //
            // e.g. "001", misplaced == 1
            // the final pattern should be "010",
            // and the actual count of misplaced elements is 2 == n - misplaced
            //
            // in either case, the actual count of misplaced elements is even
            if (colsToMove % 2 == 1) {
                colsToMove = n - colsToMove;
            }
            if (rowsToMove % 2 == 1) {
                rowsToMove = n - rowsToMove;
            }
        } else {
            // when n is even
            // the final pattern can be either "1010..." or "0101..."
            //
            // if the final pattern is "0101..." (inverse)
            // the misplaced counts for row and col will be
            // (n - rowMisplaced) and (n - colMisplaced) respectively
            //
            // misplaced and (n - misplaced) are both even
            // picks the minimum of them
            colsToMove = Math.min(colsToMove, n - colsToMove);
            rowsToMove = Math.min(rowsToMove, n - rowsToMove);
        }
        return (rowsToMove + colsToMove) / 2;
    }
}

