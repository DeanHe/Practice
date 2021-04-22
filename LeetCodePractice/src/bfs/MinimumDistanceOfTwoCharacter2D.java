package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
find the minimum manhattan distance between two characters in the matrix
 */
public class MinimumDistanceOfTwoCharacter2D {
    public int calculateMinimumDistance2D(char[][] matrix, char a, char b) throws Exception {
        int rows = matrix.length, cols = matrix[0].length;
        int[] dirs = {0, 1, 0, -1, 0};
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> q = new LinkedList<>();
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(matrix[r][c] == a){
                    q.offer(new int[]{r, c, 0});
                    visited[r][c] = true;
                }
            }
        }
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i = 0; i < sz; i++){
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                int dist = cur[2];
                if(matrix[r][c] == b){
                    return dist;
                }
                for(int j = 0; j < dirs.length - 1; j++){
                    int nb_r = r + dirs[j];
                    int nb_c = c + dirs[j + 1];
                    if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && !visited[nb_r][nb_c]){
                        q.offer(new int[]{nb_r, nb_c, dist + 1});
                        visited[nb_r][nb_c] = true;
                    }
                }
            }
        }
        throw new Exception("not found");
    }
}

