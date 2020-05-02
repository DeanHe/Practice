package Dijkstra;

import java.util.PriorityQueue;

/*
Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
The score of a path is the minimum value in that path. For example, the value of the path 8 �� 4 �� 5 �� 9 is 4.
A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).

Example 1:
Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation:
The path with the maximum score is highlighted in yellow.

Example 2:
Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2

Example 3:
Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3

Note:
1. 1 <= R, C <= 100
2. 0 <= A[i][j] <= 10^9
*/

public class PathWithMaximumMinimumValue {
    public int maximumMinimumPath(int[][] A) {
        int[] dirs = {0, 1, 0, -1, 0};
        int rows = A.length;
        int cols = A[0].length;
        int res = A[0][0];
        boolean[][] visited = new boolean[rows][cols];
        // r : c : val
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        maxHeap.offer(new int[]{0, 0, A[0][0]});
        visited[0][0] = true;
        while (!maxHeap.isEmpty()) {
            int[] cur = maxHeap.poll();
            res = Math.min(res, cur[2]);
            if (cur[0] == rows - 1 && cur[1] == cols - 1) {
                return res;
            }
            for (int i = 0; i < dirs.length - 1; i++) {
                int nb_r = cur[0] + dirs[i];
                int nb_c = cur[1] + dirs[i + 1];
                if (nb_r < rows && nb_r >= 0 && nb_c < cols && nb_c >= 0 && !visited[nb_r][nb_c]) {
                    maxHeap.offer(new int[]{nb_r, nb_c, A[nb_r][nb_c]});
                    visited[nb_r][nb_c] = true;
                }
            }
        }
        return -1;
    }
}
