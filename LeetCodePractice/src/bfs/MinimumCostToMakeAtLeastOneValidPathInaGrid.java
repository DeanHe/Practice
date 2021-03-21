package bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/*Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
        1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
        2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
        3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
        4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
        Notice that there could be some invalid signs on the cells of the grid which points outside the grid.
        You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path doesn't have to be the shortest.
        You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
        Return the minimum cost to make the grid have at least one valid path.


        Example 1:


        Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
        Output: 3
        Explanation: You will start at point (0, 0).
        The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
        The total cost = 3.
        Example 2:


        Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
        Output: 0
        Explanation: You can follow the path from (0, 0) to (2, 2).
        Example 3:


        Input: grid = [[1,2],[4,3]]
        Output: 1
        Example 4:

        Input: grid = [[2,2,2],[2,2,2]]
        Output: 3
        Example 5:

        Input: grid = [[4]]
        Output: 0


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 100
*/
public class MinimumCostToMakeAtLeastOneValidPathInaGrid {
    public int minCost(int[][] grid) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int rows = grid.length, cols = grid[0].length;
        int[][] cost = new int[rows][cols];
        for(int[] row : cost){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        cost[0][0] = 0;
        Deque<int[]> deque = new ArrayDeque<>(); // {r, c}
        deque.offerFirst(new int[]{0, 0});
        while(!deque.isEmpty()){
            int[] cur = deque.pollFirst();
            int r = cur[0];
            int c = cur[1];
            for(int i = 0; i < dirs.length; i++){
                int[] dir = dirs[i];
                int nb_r = r + dir[0];
                int nb_c = c + dir[1];
                if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols){
                    int edge = 1;
                    if(i == grid[r][c] - 1){
                        edge = 0;
                    }
                    if(cost[nb_r][nb_c] > cost[r][c] + edge){
                        cost[nb_r][nb_c] = cost[r][c] + edge;
                        if(edge == 0){
                            deque.offerFirst(new int[]{nb_r, nb_c});
                        } else {
                            deque.offerLast(new int[]{nb_r, nb_c});
                        }
                    }
                }
            }
        }
        return cost[rows - 1][cols - 1];
    }
    // Dijkstra's in O(ElogV) ~ O(mn * log(mn))
    public int minCostDijk(int[][] grid) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int rows = grid.length, cols = grid[0].length;
        int[][] cost = new int[rows][cols];
        for(int[] row : cost){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.offer(new int[]{0, 0, 0});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];
            if(cost[r][c] != Integer.MAX_VALUE){
                continue;
            }
            cost[r][c] = dist;
            for(int i = 0; i < dirs.length; i++){
                int[] dir = dirs[i];
                int nb_r = r + dir[0];
                int nb_c = c + dir[1];
                if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols){
                    int edge = 1;
                    if(i == grid[r][c] - 1){
                        edge = 0;
                    }
                    if(cost[nb_r][nb_c] == Integer.MAX_VALUE){
                        pq.offer(new int[]{nb_r, nb_c, cost[r][c] + edge});
                    }
                }
            }
        }
        return cost[rows - 1][cols - 1];
    }


}
