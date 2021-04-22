package binarySearch;
/*
In a binary matrix (all elements are 0 and 1), every row is sorted in ascending order (0 to the left of 1).
Find the leftmost column index with a 1 in it.

Example 1:

Input:
[[0, 0, 0, 1],
 [0, 0, 1, 1],
 [0, 1, 1, 1],
 [0, 0, 0, 0]]
Output: 1
Example 2:

Input:
[[0, 0, 0, 0],
 [0, 0, 0, 0],
 [0, 0, 0, 0],
 [0, 0, 0, 0]]
Output: -1
Expected solution better than O(r * c).

similar to search2DMatrixII
time complexity: O(r + c)
 */
public class LeftMostColumnWithAtLeastaOne {
    public int findLeftmostIndexOfOne(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length, res = -1;
        int r = 0, c = cols - 1;
        while(r < rows && c >= 0){
            if(matrix[r][c] == 1){
                res = c;
                c--;
            } else {
                r++;
            }
        }
        return res;
    }

}
