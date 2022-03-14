package binarySearch;

/*
Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.
You must find a solution with a memory complexity better than O(n^2).


Example 1:
Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

Example 2:
Input: matrix = [[-5]], k = 1
Output: -5

Constraints:
n == matrix.length == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 10^9
All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
1 <= k <= n2

Follow up:
Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.

analysis:
TC: O(n * n * log(max_element - min_element)) where n is the number of rows
*/
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
        	while(c>= 0 && matrix[r][c] > target){
        		c--;
        	}
        	count += c + 1;
        }
        return count;
	}
}
