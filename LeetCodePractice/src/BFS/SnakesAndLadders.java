package BFS;

import java.util.*;

public class SnakesAndLadders {
	
	int N;
    public int snakesAndLadders(int[][] board) {
        N = board.length;
        Map<Integer, Integer> dist = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        dist.put(1, 0);
        while(!queue.isEmpty()){
            int cur = queue.poll();
            if(cur == N * N){
                return dist.get(cur);
            }
            for(int nb = cur + 1; nb <= Math.min(N * N, cur + 6); nb++){
                int nb_final = nb;
                int[] coordinate = getCoordinate(nb);
                int nb_r = coordinate[0];
                int nb_c = coordinate[1];
                if(board[nb_r][nb_c] != -1){
                    nb_final = board[nb_r][nb_c];
                } 
                if(!dist.containsKey(nb_final)){
                    queue.offer(nb_final);
                    dist.put(nb_final, dist.get(cur) + 1);
                }
            }
        }
        return -1;
    }
    
    private int[] getCoordinate(int s){
        int row = N - 1 - (s - 1) / N;
        int col;
        if(row % 2 == N % 2){
            col = N - 1 - (s - 1) % N;
        } else {
            col = (s - 1) % N;
        }
        return new int[] {
           row,
           col 
        };
    }
}
