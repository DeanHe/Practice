package Dijkstra;

import java.util.PriorityQueue;

/*
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.



Example 1:



Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
Example 2:



Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
Example 3:


Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.


Constraints:

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106

Complexity:

Time: O(ElogV) = O(M*N log M*N)
Space: O(M*N)
 */
public class PathWithMinimumEffort {
    public int minimumEffortPath(int[][] heights) {
        int[] dirs = {0, 1, 0, -1, 0};
        int rows = heights.length, cols = heights[0].length;
        int[][] dist = new int[rows][cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                dist[r][c] = -1;
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{0, 0, 0});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int d = cur[2];
            if(dist[r][c] != -1){
                continue;
            }
            dist[r][c] = d;
            for(int i = 0; i < dirs.length - 1; i++){
                int nb_r = r + dirs[i];
                int nb_c = c + dirs[i + 1];
                if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && dist[nb_r][nb_c] == -1){
                    int[] nb = new int[]{nb_r, nb_c, Math.max(d, Math.abs(heights[r][c] - heights[nb_r][nb_c]))};
                    pq.offer(nb);
                }
            }
        }
        return dist[rows - 1][cols - 1];
    }

}
