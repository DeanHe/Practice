package DFS.Bipartite;
/*
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists. Each node is an integer between 0 and graph.length - 1. There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example
Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
Notice
graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
*/

import java.util.*;

public class IsGraphBipartite {
    int N;
    int[][] graph;
    Map<Integer, Integer> group;

    public boolean isBipartiteDFS(int[][] graph) {
        this.graph = graph;
        N = graph.length;
        group = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (!group.containsKey(i)) {
                if (!dfs(i, 1)) {
                    return false;
                }
            }
        }
        if (group.size() != N) {
            return false;
        }
        return true;
    }

    private boolean dfs(int idx, int color) {
        group.put(idx, color);
        for (int nb : graph[idx]) {
            if (group.containsKey(nb)) {
                if (group.get(nb) == color) {
                    return false;
                }
            } else { // not assign group yet
                if (!dfs(nb, -color)) {
                    return false;
                }
            }
        }
        return true;
    }
    
	public boolean isBipartiteBFS(int[][] graph) {
        int N = graph.length;
        Map<Integer, Integer> group = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < N; i++){
            if(group.containsKey(i)){
                continue;
            }
            group.put(i, 1);
            queue.offer(i);
            while(!queue.isEmpty()){
                int cur = queue.poll();
                int color = group.get(cur);
                for(int nb : graph[cur]){
                    if(!group.containsKey(nb)){
                        group.put(nb, -color);
                        queue.offer(nb);
                    } else {
                        if(group.get(nb) == color){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
