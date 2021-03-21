package array;

/*
A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

        Now given an M x N matrix, return True if and only if the matrix is Toeplitz.


        Example 1:

        Input:
        matrix = [
        [1,2,3,4],
        [5,1,2,3],
        [9,5,1,2]
        ]
        Output: True
        Explanation:
        In the above grid, the diagonals are:
        "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
        In each diagonal all elements are the same, so the answer is True.
        Example 2:

        Input:
        matrix = [
        [1,2],
        [2,2]
        ]
        Output: False
        Explanation:
        The diagonal "[1, 2]" has different elements.

        Note:

        matrix will be a 2D array of integers.
        matrix will have a number of rows and columns in range [1, 20].
        matrix[i][j] will be integers in range [0, 99].

        Follow up:

        1, What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
        2, What if the matrix is so large that you can only load up a partial row into the memory at once?
*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ToeplitzMatrix {
    public boolean isToeplitzMatrix(int[][] matrix) {
        Map<Integer, Integer>  group = new HashMap<>();
        for(int r = 0; r < matrix.length; r++){
            for(int c = 0; c < matrix[0].length; c++){
                int key = r - c;
                if(group.containsKey(key)){
                   if(group.get(key) != matrix[r][c]){
                       return false;
                   }
                }
                group.put(key, matrix[r][c]);
            }
        }
        return true;
    }

    public boolean isToeplitzMatrixFollowUpI(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        LinkedList<Integer> list = new LinkedList<>();
        for(int c = 0; c < cols; c++){
            list.offerLast(matrix[0][c]);
        }
        for(int r = 1; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(c == 0){
                    list.offerFirst(matrix[r][c]);
                } else {
                    if(matrix[r][c] != list.get(c)){
                        return false;
                    }
                }
            }
            list.removeLast();
        }
        return true;
    }

}
