package array;

import java.util.*;

/*
#1329
A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.



Example 1:


Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100

method 1: hashmap + pq
Time complexity: O(N×M×log(min(N,M))), where NN is a number of rows and MM is a number of columns.
We perform N×M operations in two nested loops. At each iteration, we push an element into a heap that contains the current diagonal.
The longest diagonal contains not more than min(N,M) elements.

Space complexity: O(N×M) to keep the hashmap with all input elements, where N is a number of rows and M is a number of columns.

method 2: sort all diagonal iteratively
 */
public class SortTheMatrixDiagonally {
    public int[][] diagonalSort(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int[][] res = new int[rows][cols];
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                map.computeIfAbsent(r - c, x -> new PriorityQueue<>()).offer(mat[r][c]);
            }
        }
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                res[r][c] = map.get(r - c).poll();
            }
        }
        return res;
    }

    public int[][] diagonalSortII(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        for(int c = 0; c < cols; c++){
            sort(mat, 0, c);
        }
        for(int r = 1; r < rows; r++){
            sort(mat, r, 0);
        }
        return mat;
    }

    private void sort(int[][] mat, int row ,int col){
        int rows = mat.length, cols = mat[0].length;
        List<Integer> ls = new ArrayList<>();
        for(int r = row, c = col; r < rows && c < cols; r++, c++){
            ls.add(mat[r][c]);
        }
        Collections.sort(ls);
        for(int n : ls){
            mat[row++][col++] = n;
        }
    }
}
