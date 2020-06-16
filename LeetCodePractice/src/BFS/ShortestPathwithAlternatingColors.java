package BFS;

import java.util.*;

/*
Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.

Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.

Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).


Example 1:

Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
Output: [0,1,-1]
Example 2:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
Output: [0,1,-1]
Example 3:

Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
Output: [0,-1,-1]
Example 4:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
Output: [0,1,2]
Example 5:

Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
Output: [0,1,1]
 

Constraints:

1 <= n <= 100
red_edges.length <= 400
blue_edges.length <= 400
red_edges[i].length == blue_edges[i].length == 2
0 <= red_edges[i][j], blue_edges[i][j] < n
*/
public class ShortestPathwithAlternatingColors {
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Map<Integer, List<Integer>> redGraph = new HashMap<>();
        Map<Integer, List<Integer>> blueGraph = new HashMap<>();
        for(int i = 0; i < n; i++){
        	redGraph.put(i, new ArrayList<>());
        	blueGraph.put(i, new ArrayList<>());
        }
        for(int[] edge : red_edges){
        	redGraph.get(edge[0]).add(edge[1]);
        }
        for(int[] edge : blue_edges){
        	blueGraph.get(edge[0]).add(edge[1]);
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();   //node = idx : (previous edge color 1 red, 2 blue)
        queue.offer(new int[]{0, 0});
        int dist = 0;
        while(!queue.isEmpty()){
        	int size = queue.size();
        	for(int i = 0; i < size; i++){
        		int[] cur = queue.poll();
        		if(res[cur[0]] == -1){
        			res[cur[0]] = dist;
        		}
            	if(cur[1] == 0 || cur[1] == 1){
            		List<Integer> blueNbs = blueGraph.get(cur[0]);
            		for(int nb : blueNbs){
            			String hash = nb + "+" + 2;
            			if(!visited.contains(hash)){
            				queue.offer(new int[] {nb, 2});
            			}
            		}
            	}
            	if(cur[1] == 0 || cur[1] == 2){
            		List<Integer> redNbs = redGraph.get(cur[0]);
            		for(int nb : redNbs){
            			String hash = nb + "+" + 1;
            			if(!visited.contains(hash)){
            				queue.offer(new int[] {nb, 1});
            			}
            		}
            	}
        	}
        	dist++;
        }
        return res;
    }
}
