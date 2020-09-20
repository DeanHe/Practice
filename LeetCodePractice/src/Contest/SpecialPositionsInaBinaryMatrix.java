package Contest;
/*
Given a rows x cols matrix mat, where mat[i][j] is either 0 or 1, return the number of special positions in mat.

A position (i,j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and columns are 0-indexed).



Example 1:

Input: mat = [[1,0,0],
              [0,0,1],
              [1,0,0]]
Output: 1
Explanation: (1,2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.
Example 2:

Input: mat = [[1,0,0],
              [0,1,0],
              [0,0,1]]
Output: 3
Explanation: (0,0), (1,1) and (2,2) are special positions.
Example 3:

Input: mat = [[0,0,0,1],
              [1,0,0,0],
              [0,1,1,0],
              [0,0,0,0]]
Output: 2
Example 4:

Input: mat = [[0,0,0,0,0],
              [1,0,0,0,0],
              [0,1,0,0,0],
              [0,0,1,0,0],
              [0,0,0,1,1]]
Output: 3


Constraints:

rows == mat.length
cols == mat[i].length
1 <= rows, cols <= 100
mat[i][j] is 0 or 1.

analysis:
two pass
 */
public class SpecialPositionsInaBinaryMatrix {
    public int numSpecial(int[][] mat) {
        int rows = mat.length, cols = mat[0].length, res = 0;
        int[] rowCnt = new int[rows];
        int[] colCnt = new int[cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 1) {
                    rowCnt[r]++;
                    colCnt[c]++;
                }
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 1 && rowCnt[r] == 1 && colCnt[c] == 1) {
                    res++;
                }
            }
        }
        return res;
    }
}
