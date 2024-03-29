package sort;

import java.util.Arrays;

/*
You are given a binary matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.
Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.

Example 1:
Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
Output: 4
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 4.

Example 2:
Input: matrix = [[1,0,1,0,1]]
Output: 3
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 3.

Example 3:
Input: matrix = [[1,1,0],[1,0,1]]
Output: 2
Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.

Example 4:
Input: matrix = [[0,0],[0,0]]
Output: 0
Explanation: As there are no 1s, no submatrix of 1s can be formed and the area is 0.


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m * n <= 10^5
matrix[i][j] is 0 or 1.

hint:
1 For each column, find the number of consecutive ones ending at each position.
2 For each row, sort the cumulative ones in non-increasing order and "fit" the largest submatrix.

analysis:
Convert matrix[r][c] to represent continuous sum of 1s on matrix[:r][c]
Then for row, sort the preSum in increasing order
the rectangle area with bottom left point on matrix[r][c], is calculated by (cols - c) * matrix[r][c])

time complexity: M * NlogN * N
 */
public class LargestSubmatrixWithRearrangements {
    public int largestSubmatrix(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        for(int c = 0; c < cols; c++){
            for(int r = 1; r < rows; r++){
                if(matrix[r][c] == 1){
                    matrix[r][c] += matrix[r - 1][c];
                } else {
                    matrix[r][c] = 0;
                }
            }
        }
        int res = 0;
        for(int r = 0; r < rows; r++){
            Arrays.sort(matrix[r]);
            for(int c = 0; c < cols; c++){
                res = Math.max(res, (cols - c) * matrix[r][c]);
            }
        }
        return res;
    }
}
