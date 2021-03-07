package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
#Apple

Given a matrix made of 1 and 0, and start point and end point.
find a valid path from start point to end point;
 */
public class FindPathApple {
    int[] dirs = {0, 1, 0, -1, 0};
    int rows, cols;
    List<int[]> findPath(int[][] matrix, int[] start, int[] end){
        rows = matrix.length;
        cols = matrix[0].length;
        List<int[]> path = new ArrayList<>();
        path.add(start);
        matrix[start[0]][start[1]] = 2;
        if(dfs(matrix, end, path)){
            return path;
        }
        return new ArrayList<>();
    }

    private boolean dfs(int[][] matrix, int[] end, List<int[]> path) {
        /*
        for(int[] row : matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        */
        int[] cur = path.get(path.size() - 1);
        if(cur[0] == end[0] && cur[1] == end[1]){
            return true;
        }
        for(int i = 0; i < dirs.length - 1; i++){
            int nb_r = cur[0] + dirs[i];
            int nb_c = cur[1] + dirs[i + 1];
            if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && matrix[nb_r][nb_c] == 1){
                matrix[nb_r][nb_c] = 2;
                path.add(new int[]{nb_r, nb_c});
                if(dfs(matrix, end, path)){
                    return true;
                }
                matrix[nb_r][nb_c] = 1;
                path.remove(path.size() - 1);
            }
        }
        return false;
    }
}
