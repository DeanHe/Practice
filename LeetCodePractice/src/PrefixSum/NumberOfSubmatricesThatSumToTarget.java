package PrefixSum;

import java.util.HashMap;
import java.util.Map;

/*
Given a matrix, and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.

 

Example 1:

Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.
Example 2:

Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
 

Note:

1 <= matrix.length <= 300
1 <= matrix[0].length <= 300
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8

hint:
1 Using a 2D prefix sum, we can query the sum of any submatrix in O(1) time. Now for each (r1, r2),
we can find the largest sum of a submatrix that uses every row in [r1, r2] in linear time using a sliding window.

analysis:
for each row calculate prefix sum,
For each pair of columns,
calculate the accumulated sum of rows.
to get sum from matrix[0][0] to matrix[r][c], use sum(preSum[:r][c])
to get all submatrix sum, use two pointer on start_col and end_col for the submatrix, sum(preSum[:][start_col:end_col])
similar to SubarraySumEqualsK

TC(Rows * Cols ^ 2)
*/
public class NumberOfSubmatricesThatSumToTarget {
	public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] preSum = new int[rows][cols];
        for(int r = 0; r < rows; r++){
        	for(int c = 0; c < cols; c++){
        		if(c == 0){
        			preSum[r][c] = matrix[r][c];
        		} else {
        			preSum[r][c] = preSum[r][c - 1] + matrix[r][c];
        		}
        	}
        }
        int res = 0;
        for(int sc = 0; sc < cols; sc++){
        	for(int ec = sc; ec < cols; ec++){
        		Map<Integer, Integer> rowSumCnt = new HashMap<>();
        		rowSumCnt.put(0, 1);
        		int sum = 0;
        		for(int r = 0; r < rows; r++){
        			if(sc == 0){
            			sum += preSum[r][ec];
            		} else {
            			sum += preSum[r][ec] - preSum[r][sc - 1];
            		}
        			res += rowSumCnt.getOrDefault(sum - target, 0);
        			rowSumCnt.put(sum, rowSumCnt.getOrDefault(sum, 0) + 1);
        		}
        	}
        }
        return res;                         
    }
}
