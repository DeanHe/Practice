package dfs;

import java.util.LinkedList;
import java.util.Queue;

/*
In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

Example 1:
Input: [[0,1],[1,0]]
Output: 1

Example 2:
Input: [[0,1,0],[0,0,0],[0,0,1]]
Output: 2

Example 3:
Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1
 
Note:
1 <= A.length = A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1

analysis:
trick: use step > 0 condition to filter first island nodes
*/
public class ShortestBridge {
    int rows, cols;
    boolean[][] visited;
    int[] dirs = {0, 1, 0, -1, 0};
    Queue<int[]> queue = new LinkedList<>();

    public int shortestBridge(int[][] A) {
        rows = A.length;
        cols = A[0].length;
        visited = new boolean[rows][cols];
        findFirstIsland(A);
        return bfs(A);

    }

    private void findFirstIsland(int[][] A) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (A[r][c] == 1) {
                    dfs(A, r, c);
                    return;
                }
            }
        }
    }

    private void dfs(int[][] A, int r, int c) {
        if (r >= 0 && r < rows && c >= 0 && c < cols && !visited[r][c] && A[r][c] == 1) {
            queue.offer(new int[]{r, c});
            visited[r][c] = true;
            for (int i = 0; i + 1 < dirs.length; i++) {
                dfs(A, r + dirs[i], c + dirs[i + 1]);
            }
        }
    }

    private int bfs(int[][] A) {
        int step = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                if (A[r][c] == 1 && step > 0) {
                    return step - 1;
                }
                for (int j = 0; j + 1 < dirs.length; j++) {
                    int nb_r = r + dirs[i];
                    int nb_c = c + dirs[i + 1];
                    if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && !visited[nb_r][nb_c]) {
                        queue.offer(new int[]{nb_r, nb_c});
                        visited[nb_r][nb_c] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
