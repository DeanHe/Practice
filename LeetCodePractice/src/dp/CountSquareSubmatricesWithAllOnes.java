package dp;
/*Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

        Example 1:

        Input: matrix =
        [
        [0,1,1,1],
        [1,1,1,1],
        [0,1,1,1]
        ]
        Output: 15
        Explanation:
        There are 10 squares of side 1.
        There are 4 squares of side 2.
        There is  1 square of side 3.
        Total number of squares = 10 + 4 + 1 = 15.
        Example 2:

        Input: matrix =
        [
        [1,0,1],
        [1,1,0],
        [1,1,0]
        ]
        Output: 7
        Explanation:
        There are 6 squares of side 1.
        There is 1 square of side 2.
        Total number of squares = 6 + 1 = 7.


        Constraints:

        1 <= arr.length <= 300
        1 <= arr[0].length <= 300
        0 <= arr[i][j] <= 1

analysis:
similar to MaximalSquare
CountSubarraysWIthAllOnes

analysis:
dp[i][j] means the max side of square with bottom right at matrix[i][j];
TC: O(M * N)
SC: O(M * N)
*/
public class CountSquareSubmatricesWithAllOnes {
    public int countSquares(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int res = 0;
        int[][] dp = new int[rows][cols];
        for(int r = 0; r < rows; r++){
            dp[r][0] = matrix[r][0] == 1 ? 1 : 0;
            res += dp[r][0];
        }
        for(int c = 1; c < cols; c++){
            dp[0][c] = matrix[0][c] == 1 ? 1 : 0;
            res += dp[0][c];
        }
        for(int r = 1; r < rows; r++){
            for(int c = 1; c  < cols; c++){
                if(matrix[r][c] == 1){
                    int temp = Math.min(dp[r - 1][c - 1], dp[r][c - 1]);
                    temp = Math.min(temp, dp[r - 1][c]);
                    dp[r][c] = temp + 1;
                }
                res += dp[r][c];
            }
        }
        return res;
    }
}
