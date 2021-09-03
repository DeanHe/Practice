package array;
/*
You are given an n x n integer matrix. You can do the following operation any number of times:

Choose any two adjacent elements of matrix and multiply each of them by -1.
Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.



Example 1:


Input: matrix = [[1,-1],[-1,1]]
Output: 4
Explanation: We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.
Example 2:


Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
Output: 16
Explanation: We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.


Constraints:

n == matrix.length == matrix[i].length
2 <= n <= 250
-10^5 <= matrix[i][j] <= 10^5

hint:
Try to use the operation so that each row has only one negative number.
If you have only one negative element you cannot convert it to positive.

analysis:
if the negative number count is even, then you can eliminate all negative sign
if the negative number count is odd, then the result is sum of all absolute value - 2 * min
 */
public class MaximumMatrixSum {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int rows = matrix.length, cols = matrix[0].length, least = Integer.MAX_VALUE, negCnt = 0;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(matrix[r][c] >= 0){
                    sum += matrix[r][c];
                } else {
                    sum += -matrix[r][c];
                    negCnt++;
                }
                least = Math.min(least, Math.abs(matrix[r][c]));
            }
        }
        if(negCnt % 2 == 0){
            return sum;
        } else {
            return sum - 2 * least;
        }
    }
}

