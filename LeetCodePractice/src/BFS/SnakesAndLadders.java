package BFS;
/*On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting from the bottom left of the board, 
and alternating direction each row.  For example, for a 6 x 6 board, the numbers are written as follows:
You start on square 1 of the board (which is always in the last row and first column).  Each move, starting from square x, consists of the following:

You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, provided this number is <= N*N.
(This choice simulates the result of a standard 6-sided die roll: ie., there are always at most 6 destinations.)
If S has a snake or ladder, you move to the destination of that snake or ladder.  Otherwise, you move to S.
A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.  The destination of that snake or ladder is board[r][c].
Note that you only take a snake or ladder at most once per move: if the destination to a snake or ladder is the start of another snake or ladder, you do not continue moving.  
(For example, if the board is `[[4,-1],[-1,3]]`, and on the first move your destination square is `2`, then you finish your first move at `3`, because you do not continue moving to `4`.)

Return the least number of moves required to reach square N*N.  If it is not possible, return -1.*/
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
