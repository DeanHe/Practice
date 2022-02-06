package dp.memorization;

import java.util.HashSet;
import java.util.Set;

/*
Given a 2D matrix, and a bunch of intermediate Points, and a start point and an endpoint point.
return total number of paths from start to end, and cover all the intermediate points
you can only move in 3 directions: right, top-right, bottom-right each time
 */
public class NumberOfPathsIncludingAllIntermediatePointGoogle {
    int rows, cols;
    int[] end;
    int[][][] mem;
    Set<Integer> interSet;
    int[][] dirs = {{0, 1}, {1, 1}, {-1, 1}};
    public int totalPaths(int rows, int cols, int[] start, int[] end, int[][] intermediates){
        this.rows = rows;
        this.cols = cols;
        this.end = end;
        mem = new int[rows][cols][intermediates.length + 1];
        interSet = new HashSet<>();
        for(int[] p : intermediates){
            int r = p[0];
            int c = p[1];
            interSet.add(r * cols + c);
        }
        return dfs(start[0], start[1], interSet.size());
    }

    private int dfs(int r, int c, int toVisit) {
        if(r > end[0] || r < 0 || r >= rows || c > end[1] || c < 0 || c >= cols){
            return 0;
        }
        if(mem[r][c][toVisit] != 0){
            return mem[r][c][toVisit];
        }
        int nextTo = toVisit;
        if(interSet.contains(r * cols + c)){
            nextTo--;
        }
        if(r == end[0] && c == end[1] && nextTo == 0){
            return mem[r][c][toVisit] = 1;
        }
        for(int[] d : dirs){
            int nb_r = r + d[0];
            int nb_c = c + d[1];
            mem[r][c][toVisit] += dfs(nb_r, nb_c, nextTo);
        }
        return mem[r][c][toVisit];
    }
}
