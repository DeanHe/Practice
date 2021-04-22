package binaryIndexedTree;

/*
        Given a 2D matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2). And the elements of the matrix could be changed.
        You have to implement three functions:

        NumMatrix(matrix) The constructor.
        sumRegion(row1, col1, row2, col2) Return the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
        update(row, col, val) Update the element at (row, col) to val.
        The matrix is only modifiable by update.
        You may assume the number of calls to update and sumRegion function is distributed evenly.
        You may assume that row1 ≤ row2 and col1 ≤ col2.

        Example

        Example 1:
        Input:
        NumMatrix(
        [[3,0,1,4,2],
        [5,6,3,2,1],
        [1,2,0,1,5],
        [4,1,0,1,7],
        [1,0,3,0,5]]
        )
        sumRegion(2,1,4,3)
        update(3,2,2)
        sumRegion(2,1,4,3)
        Output:
        8
        10

        Example 2:
        Input:
        NumMatrix([[1]])
        sumRegion(0, 0, 0, 0)
        update(0, 0, -1)
        sumRegion(0, 0, 0, 0)
        Output:
        1
        -1
*/
public class RangeSumQuery2DMutable {
    class NumMatrix {
        int[][] arr, bit;
        int rows, cols;

        /**
         * @return: nothing
         */
        public NumMatrix(int[][] matrix) {
            rows = matrix.length;
            cols = matrix[0].length;
            arr = new int[rows][cols];
            bit = new int[rows + 1][cols + 1];

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    update(r, c, matrix[r][c]);
                }
            }
        }

        public void update(int row, int col, int val) {
            int delta = val - arr[row][col];
            arr[row][col] = val;

            for (int r = row + 1; r <= rows; r = r + lowbit(r)) {
                for (int c = col + 1; c <= cols; c = c + lowbit(c)) {
                    bit[r][c] += delta;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return prefixSum(row2, col2) - prefixSum(row2, col1 - 1) - prefixSum(row1 - 1, col2) + prefixSum(row1 - 1, col1 - 1);
        }

        private int prefixSum(int row, int col) {
            int sum = 0;
            for (int r = row + 1; r > 0; r = r - lowbit(r)) {
                for (int c = col + 1; c > 0; c = c - lowbit(c)) {
                    sum += bit[r][c];
                }
            }
            return sum;
        }

        private int lowbit(int x) {
            return x & (-x);
        }
    }
}
