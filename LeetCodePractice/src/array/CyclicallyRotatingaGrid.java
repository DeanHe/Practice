package array;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given an m x n integer matrix grid​​​, where m and n are both even integers, and an integer k.

The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:



A cyclic rotation of the matrix is done by cyclically rotating each layer in the matrix. To cyclically rotate a layer once, each element in the layer will take the place of the adjacent element in the counter-clockwise direction. An example rotation is shown below:


Return the matrix after applying k cyclic rotations to it.



Example 1:


Input: grid = [[40,10],[30,20]], k = 1
Output: [[10,20],[40,30]]
Explanation: The figures above represent the grid at every state.
Example 2:


Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
Output: [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
Explanation: The figures above represent the grid at every state.


Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 50
Both m and n are even integers.
1 <= grid[i][j] <= 5000
1 <= k <= 10^9

hint
First, you need to consider each layer separately as an array.
Just cycle this array and then re-assign it.

TC: O(rows * cols)
 */
public class CyclicallyRotatingaGrid {
    int rows, cols;

    public int[][] rotateGrid(int[][] grid, int k) {
        rows = grid.length;
        cols = grid[0].length;
        int layers = Math.min(rows, cols) / 2;
        for (int l = 0; l < layers; l++) {
            Queue<Integer> q = new LinkedList<>();
            //top row
            for (int c = l; c < cols - l - 1; c++) {
                q.offer(grid[l][c]);
            }
            //right col
            for (int r = l; r < rows - l - 1; r++) {
                q.offer(grid[r][cols - l - 1]);
            }
            //bottom row
            for (int c = cols - l - 1; c > l; c--) {
                q.offer(grid[rows - l - 1][c]);
            }
            //left col
            for (int r = rows - l - 1; r > l; r--) {
                q.offer(grid[r][l]);
            }
            //rotate
            int kk = k % q.size();
            for (int i = 0; i < kk; i++) {
                q.offer(q.poll());
            }
            assign(grid, q, l);
        }
        return grid;
    }

    private void assign(int[][] grid, Queue<Integer> q, int l) {
        //top row
        for (int c = l; c < cols - l - 1; c++) {
            grid[l][c] = q.poll();
        }
        //right col
        for (int r = l; r < rows - l - 1; r++) {
            grid[r][cols - l - 1] = q.poll();
        }
        //bottom row
        for (int c = cols - l - 1; c > l; c--) {
            grid[rows - l - 1][c] = q.poll();
        }
        //left col
        for (int r = rows - l - 1; r > l; r--) {
            grid[r][l] = q.poll();
        }
    }
}

