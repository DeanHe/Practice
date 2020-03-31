package Array;

import java.util.ArrayList;
import java.util.List;

/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*/
public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0){
        	return res;
        }      
        int rows = matrix.length;
        int cols = matrix[0].length;
        for(int r1 = 0, r2 = rows - 1, c1 = 0, c2 = cols - 1; r1 <= r2 && c1 <= c2; r1++, c1++, r2--, c2--){
        	//if one row/column left, no circle can be formed
        	if(r1 == r2){
        		for(int c = c1; c <= c2; c++){
        			res.add(matrix[r1][c]);
        		}
        		break;
        	} else if(c1 == c2){
        		for(int r = r1; r <= r2; r++){
        			res.add(matrix[r][c1]);
        		}
        		break;
        	} else {
        		// process a circle
        		// on top - moving right
        		for(int c = c1; c < c2; c++){
        			res.add(matrix[r1][c]);
        		}
        		// on right - moving bottom
        		for(int r = r1; r < r2; r++){
        			res.add(matrix[r][c2]);
        		}
        		// on bottom - moving left
        		for(int c = c2; c > c1; c--){
        			res.add(matrix[r2][c]);
        		}
        		// on right - moving top
        		for(int r = r2; r > r1; r--){
        			res.add(matrix[r][c1]);
        		}
        	}
        }
        return res;
    }
}
