package bfs;

import java.util.*;

/*In a directed graph, we start at some node and every turn, walk along a directed edge of the graph. If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node. More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe? Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph. The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Notice
graph will have length at most 10000.
The number of edges in the graph will not exceed 32000.
Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].*/

public class FindEventualSafeStates {
	/**
     * @param graph: a 2D integers array
     * @return: return a list of integers
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        if(graph == null || graph.length == 0){
            return res;
        }
        ArrayList<HashSet<Integer>> inbound_neighbors = new ArrayList<>();
        int[] outDegree = new int[graph.length];
        for(int i = 0; i < graph.length; i++){
            outDegree[i] = graph[i].length;
            inbound_neighbors.add(new HashSet<Integer>());
        }
        for(int i = 0; i < graph.length; i++){
            for(int j : graph[i]){
                inbound_neighbors.get(j).add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < graph.length; i++){
            if(outDegree[i] == 0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int nb : inbound_neighbors.get(cur)){
                outDegree[nb]--;
                if(outDegree[nb] == 0){
                    queue.offer(nb);
                }
            }
        }
        for(int i = 0; i < graph.length; i++){
            if(outDegree[i] == 0){
                res.add(i);
            }
        }
        return res;
    }
}
