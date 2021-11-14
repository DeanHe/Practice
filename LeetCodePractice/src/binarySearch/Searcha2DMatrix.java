package binarySearch;
/*
Write an efficient algorithm that searches for a value in an m x n matrix.

This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Example
Example 1:
	Input:  [[5]],2
	Output: false
	
	Explanation: 
	false if not included.
	
Example 2:
	Input:  [
    [1, 3, 5, 7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
],3
	Output: true
	
	Explanation: 
	return true if included.
*/
// https://www.lintcode.com/problem/search-a-2d-matrix/description
public class Searcha2DMatrix {
	/**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if(matrix == null || matrix.length == 0){
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int start = 0;
        int end = rows * cols - 1;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            int mid_r = mid / cols;
            int mid_c = mid % cols;
            if(matrix[mid_r][mid_c] == target){
                return true;
            } else if(matrix[mid_r][mid_c] < target){
                start = mid;
            } else {
                end = mid;
            }
        } 
        if(matrix[start / cols][start % cols] == target){
            return true;
        }
        if(matrix[end / cols][end % cols] == target){
            return true;
        }
        return false;
    }
}
