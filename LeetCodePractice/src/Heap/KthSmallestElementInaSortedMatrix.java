package Heap;

import java.util.*;

/*Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.*/
public class KthSmallestElementInaSortedMatrix {
	public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int low = matrix[0][0], high = matrix[rows - 1][cols - 1];
        while(low <= high){
        	int mid = low + (high - low) / 2; // fake a mid number, and find count of numbers less than it in matrix
        	int count = countLessOrEqualTo(matrix, mid);
        	if(count < k){
        		low = mid + 1;
        	} else {
        		high = mid - 1;
        	}
        }
        return low;
    }
	private int countLessOrEqualTo(int[][] matrix, int target){
		int count = 0;
		int rows = matrix.length;
        int cols = matrix[0].length;
        int c = cols - 1; // iterate trough the last column from top row to bottom
        for(int r = 0; r < rows; r++){
        	while(c>= 0 && matrix[r][c] < target){
        		c--;
        	}
        	count += c + 1;
        }
        return count;
	}
}
