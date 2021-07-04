package PrefixSum;

import java.util.TreeSet;

/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2 
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
Note:

The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

analysis:
similar to question: max subarray sum no more than k.
TC  min(m,n)^2 * max(m,n) * log(max(m,n))
*/
public class MaxSumOfRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        boolean isRowLarger = false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (rows > cols) {
            isRowLarger = true;
        }
        int m = Math.max(rows, cols);
        int n = Math.min(rows, cols);
        for (int c = 0; c < n; c++) {
            int[] arr = new int[m];
            for (int ct = c; ct < n; ct++) {
                for (int r = 0; r < m; r++) {
                    if (isRowLarger) {
                        arr[r] += matrix[r][ct];
                    } else {
                        arr[r] += matrix[ct][r];
                    }
                }
                int candidateMatrixSum = maximumSumOfSubarrayCloseToK(arr, k);
                res = Math.max(res, candidateMatrixSum);
            }
        }
        return res;
    }

    private int maximumSumOfSubarrayCloseToK(int[] arr, int K) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int prefixSum = 0;
        int res = Integer.MIN_VALUE;
        for (int n : arr) {
            prefixSum += n;
            Integer ceiling = set.ceiling(prefixSum - K);
            if (ceiling != null) {
                res = Math.max(res, prefixSum - ceiling);
            }
            set.add(prefixSum);
        }
        return res;
    }
}
