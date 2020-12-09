package Array;
/*
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.



Example 1:


Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]


Constraints:

1 <= n <= 20
 */
public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        for(int r1 = 0, r2 = n - 1, c1 = 0, c2 = n - 1, num = 1;
            r1 <= r2 && c1 <= c2;
            r1++, r2--, c1++, c2--){
            if(r1 == r2){
                matrix[r1][c1] = num++;
                return matrix;
            }
            for(int c = c1; c < c2; c++){
                matrix[r1][c] = num++;
            }
            for(int r = r1; r < r2; r++){
                matrix[r][c2] = num++;
            }
            for(int c = c2; c > c1; c--){
                matrix[r2][c] = num++;
            }
            for(int r = r2; r > r1; r--){
                matrix[r][c1] = num++;
            }
        }
        return matrix;
    }
}
