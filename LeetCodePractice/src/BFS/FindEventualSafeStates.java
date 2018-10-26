package BFS;

import java.util.*;

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
