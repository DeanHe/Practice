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

similar to SubarraySumEqualsK
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
        for(int c = 0; c < cols; c++){
        	for(int i = c; i < cols; i++){
        		Map<Integer, Integer> counter = new HashMap<>();
        		counter.put(0, 1);
        		int cur = 0;
        		for(int r = 0; r < rows; r++){
        			if(c == 0){
            			cur += preSum[r][i];
            		} else {
            			cur += preSum[r][i] - preSum[r][c - 1];
            		}
        			res += counter.getOrDefault(cur - target, 0);
        			counter.put(cur, counter.getOrDefault(cur, 0) + 1);
        		}
        	}
        }
        return res;                         
    }
}
