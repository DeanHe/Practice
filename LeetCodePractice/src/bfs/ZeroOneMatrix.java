package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.



Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 10^4
1 <= m * n <= 10^4
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.
 */
public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] mat) {
        int[] dirs = {0, 1, 0, -1, 0};
        int rows = mat.length, cols = mat[0].length;
        Queue<int[]> q = new LinkedList<>();
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(mat[r][c] == 0){
                    q.offer(new int[]{r, c});
                } else {
                    mat[r][c] = Integer.MAX_VALUE;
                }
            }
        }
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i = 0; i < sz; i++){
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                for(int j = 0; j + 1 < dirs.length; j++){
                    int nb_r = r + dirs[j];
                    int nb_c = c + dirs[j + 1];
                    if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && mat[r][c] + 1 < mat[nb_r][nb_c]){
                        mat[nb_r][nb_c] = mat[r][c] + 1;
                        q.offer(new int[]{nb_r, nb_c});
                    }
                }
            }
        }
        return mat;
    }
}

