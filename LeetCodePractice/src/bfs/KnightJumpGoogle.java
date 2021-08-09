package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class KnightJumpGoogle {
    public int minStep(int src, int dest) {
        //Your code here
        int rows = 8, cols = 8;
        boolean[][] board = new boolean[rows][cols];
        int sr = src / cols, sc = src % cols, dr = dest / cols, dc = dest % cols;
        int[] dirs = {1, -2, -1, -2, 1, 2, -1, 2, 1};
        board[sr][sc] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc});
        int step = 0;
        while(!q.isEmpty()){
            int sz = q.size();
            for(int j = 0; j < sz; j++){
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                if(r == dr && c == dc){
                    return step;
                }
                for(int i = 0; i + 1 < dirs.length; i++){
                    int nb_r = r + dirs[i];
                    int nb_c = c + dirs[i + 1];
                    if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && !board[nb_r][nb_c]){
                        board[nb_r][nb_c] = true;
                        q.offer(new int[]{nb_r, nb_c});
                    }
                }
            }
            step++;
        }
        return -1;
    }
}

