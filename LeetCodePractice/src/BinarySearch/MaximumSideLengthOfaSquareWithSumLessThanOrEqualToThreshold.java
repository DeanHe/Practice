package BinarySearch;

/*Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.

        Example 1:


        Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
        Output: 2
        Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
        Example 2:

        Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
        Output: 0
        Example 3:

        Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
        Output: 3
        Example 4:

        Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
        Output: 2


        Constraints:

        1 <= m, n <= 300
        m == mat.length
        n == mat[i].length
        0 <= mat[i][j] <= 10000
        0 <= threshold <= 10^5
*/
public class MaximumSideLengthOfaSquareWithSumLessThanOrEqualToThreshold {
    int rows, cols;

    public int maxSideLength(int[][] mat, int threshold) {
        rows = mat.length;
        cols = mat[0].length;
        int[][] preSum = new int[rows + 1][cols + 1]; // preSum[r + 1][c + 1] means the sum of rectangle(0,0) -> (r,c)
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                preSum[r][c] = mat[r - 1][c - 1] + preSum[r - 1][c] + preSum[r][c - 1] - preSum[r - 1][c - 1];
            }
        }
        int start = 0, end = Math.min(rows, cols);
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isSquareExist(preSum, threshold, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if(isSquareExist(preSum, threshold, end)){
            return end;
        }
        return start;
    }

    private boolean isSquareExist(int[][] sum, int thres, int side) {
        for (int r = 0; r + side <= rows; r++) {
            for (int c = 0; c + side <= cols; c++) {
                int cur = sum[r + side][c + side] - sum[r + side][c] - sum[r][c + side] + sum[r][c];
                if (cur <= thres) {
                    return true;
                }
            }
        }
        return false;
    }
}
