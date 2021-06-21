package dijkstra;

import java.util.PriorityQueue;

/*
On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

Example 1:

Input: [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.

You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:

Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

The final route is marked in bold.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
Note:

2 <= N <= 50.
grid[i][j] is a permutation of [0, ..., N*N - 1].

hint:
Use either Dijkstra's, or binary search for the best time T for which you can reach the end if you only step on squares at most T.

approach 1: Binary Search range [0, n*n-1] to find the minimum feasible water level. For each water level, verification using DFS or BFS is O(n^2). DFS is slightly faster in practice. O(n^2 * logn)
approach 2: Dijkstra using Priority Queue, O(n^2 * logn)
 */
public class SwimInRisingWater {
    public int swimInWater(int[][] grid) {
        int[] dirs = {0, 1, 0, -1, 0};
        int rows = grid.length, cols = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        Integer[][] dist = new Integer[rows][cols];
        pq.offer(new int[]{0, 0, grid[0][0]});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            if(dist[r][c] == null){
                dist[r][c] = cur[2];
                if(r == rows - 1 && c == cols - 1){
                    return dist[r][c];
                }
                for(int i = 0; i + 1 < dirs.length; i++){
                    int nb_r = r + dirs[i];
                    int nb_c = c + dirs[i + 1];
                    if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && dist[nb_r][nb_c] == null){
                        int d = Math.max(dist[r][c], grid[nb_r][nb_c]);
                        pq.offer(new int[]{nb_r, nb_c, d});
                    }
                }
            }
        }
        return -1;
    }
}

