package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.

You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.

A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.

Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.



Example 1:



Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
Output: 3
Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
Example 2:



Input: colors = "a", edges = [[0,0]]
Output: -1
Explanation: There is a cycle from 0 to 0.


Constraints:

n == colors.length
m == edges.length
1 <= n <= 10^5
0 <= m <= 10^5
colors consists of lowercase English letters.
0 <= aj, bj < n

hint:
Use topological sort. similar to course schedule
let dp[u][c] := the maximum count of vertices with color c of any path ended at vertex u.
 */
public class LargestColorValueInaDirectedGraph {
    public int largestPathValue(String colors, int[][] edges) {
        int res = 0, len = colors.length();
        int[][] dp = new int[len][26];
        Map<Integer, Integer> inDeg = new HashMap<>();
        Map<Integer, List<Integer>> g = new HashMap<>();
        int visited = 0;
        for(int i = 0; i < len; i++){
            inDeg.put(i, 0);
            g.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            int s = edge[0];
            int e = edge[1];
            g.get(s).add(e);
            inDeg.put(e, inDeg.get(e) + 1);
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < len; i++){
            if(inDeg.get(i) == 0){
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            int color = colors.charAt(i) - 'a';
            dp[i][color]++;
            res = Math.max(res, dp[i][color]);
            visited++;
            for(int nb : g.get(i)){
                for(int j = 0; j < 26; j++){
                    dp[nb][j] = Math.max(dp[nb][j], dp[i][j]);
                }
                inDeg.put(nb, inDeg.get(nb) - 1);
                if(inDeg.get(nb) == 0){
                    q.offer(nb);
                }
            }
        }
        return visited == len ? res : -1;
    }
}

