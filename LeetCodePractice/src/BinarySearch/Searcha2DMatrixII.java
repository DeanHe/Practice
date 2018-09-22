package BinarySearch;

// https://www.lintcode.com/problem/search-a-2d-matrix-ii/description
public class Searcha2DMatrixII {
	/**
     * @param matrix: A list of lists of integers
     * @param: A number you want to search in the matrix
     * @return: An integer indicate the occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int count = 0;
        int x = row - 1;
        int y = 0;
        while(x >= 0 && y < col){
            if(matrix[x][y] == target){
                count++;
                x--;
                y++;
            } else if(matrix[x][y] < target){
                y++;
            } else {
                x--;
            }
        }
        return count;
    }
}
