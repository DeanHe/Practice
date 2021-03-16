package PrefixSum;
/*
https://www.geeksforgeeks.org/maximize-binary-matrix-filpping-submatrix/

Given a binary matrix of R rows and C columns. We are allowed flip to any size of sub matrix. Flipping means changing 1 to 0 and 0 to 1. The task is maximize the number of 1s in the matrix. Output the maximum number of 1s.

Examples:

Input : R = 3, C =3
mat[][] = { 0, 0, 1,
            0, 0, 1,
            1, 0, 1 }

Output : 8
Flip
0 0 1
0 0 1
1 0 1

to get

1 1 1
1 1 1
0 1 1

Input : R = 2, C = 3
mat[][] = { 0, 0, 0,
            0, 0, 0 }
Output : 6

analysis:
TC O(R * C * R * C)
 */
public class MaximizeTheBinaryMatrixByFilppingSubmatrixOnce {
    int rows, cols;
    int[][] ones;

    int solve(int[][] mat) {
        int res = 0;
        rows = mat.length;
        cols = mat[0].length;
        ones = new int[rows + 1][cols + 1];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(mat[r][c] == 1){
                    ones[r + 1][c + 1] = 1;
                }
                ones[r + 1][c + 1] += ones[r + 1][c] + ones[r][c + 1] - ones[r][c];
            }
        }
        for(int h = 1; h <= rows; h++){
            for(int w = 1; w <= cols; w++){
                for(int r = 0; r + h - 1 < rows; r++){
                    for(int c = 0; c + w - 1 < cols; c++){
                        res = Math.max(res, ones[rows][cols] + h * w - 2 * countOnes(r, c, r + h - 1, c + w - 1));
                    }
                }
            }
        }
        return res;
    }

    private int countOnes(int r1, int c1, int r2, int c2) {
        return ones[r2 + 1][c2 + 1] - ones[r1 + 1][c2] - ones[r2][c1 + 1] + ones[r1][c1];
    }
}
