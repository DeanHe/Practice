package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a matrix with characters, L represents Land, W represent Lake, O represent Ocean which also connect from
the boundary of matrix. find the minimum steps to connect all Lakes to the Ocean by digging through the Land
 */
public class MinimumStepsToReachOceanGoogle {
    int[] dirs = {0, 1, 0, -1, 0};
    int rows, cols;
    public int minStepsToDig(char[][] matrix){
        rows = matrix.length;
        cols = matrix[0].length;
        int res = 0;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(matrix[r][c] == 'W'){
                    res += helper(matrix, r, c);
                }
            }
        }
        return res;
    }

    private int helper(char[][] matrix, int ir, int ic) {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> qq = new LinkedList<>();
        q.offer(new int[]{ir, ic});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            if(nextTo(matrix, r, c, 'L')){
                qq.offer(new int[]{r, c});
            }
            matrix[r][c] = 'X';
            for(int i = 0; i < dirs.length - 1; i++){
                int nb_r = r + dirs[i];
                int nb_c = c + dirs[i + 1];
                if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && matrix[nb_r][nb_c] == 'W'){
                    q.offer(new int[]{nb_r, nb_c});
                }
            }
        }
        int step = 0;
        while(!qq.isEmpty()){
            int sz = qq.size();
            for(int i = 0; i < sz; i++){
                int[] cur = qq.poll();
                int r = cur[0];
                int c = cur[1];
                if(nextTo(matrix, r, c, 'O')){
                    return step;
                }
                for(int j = 0; j < dirs.length - 1; j++){
                    int nb_r = r + dirs[j];
                    int nb_c = c + dirs[j + 1];
                    if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && matrix[nb_r][nb_c] == 'L'){
                        qq.offer(new int[]{nb_r, nb_c});
                        matrix[r][c] = 'Y';
                    }
                }
            }
            step++;
        }
        return step;
    }

    private boolean nextTo(char[][] matrix, int r, int c, char target) {
        for(int i = 0; i < dirs.length - 1; i++){
            int nb_r = r + dirs[i];
            int nb_c = c + dirs[i + 1];
            if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && matrix[nb_r][nb_c] == target){
                return true;
            }
        }
        return false;
    }
}

