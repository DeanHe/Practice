package BFS;

import java.util.*;

public class MinimumHeightTrees {
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
		int[] degree = new int[n];
		List<Integer> res = new ArrayList<>();
		if(n == 1){
			res.add(0);
			return res;
		}
		for(int[] edge : edges){
			int node1 = edge[0];
			int node2 = edge[1];
			if(!graph.containsKey(node1)){
				graph.put(node1, new ArrayList<>());
			}
			graph.get(node1).add(node2);
			if(!graph.containsKey(node2)){
				graph.put(node2, new ArrayList<>());
			}
			graph.get(node2).add(node1);
			degree[node1]++;
			degree[node2]++;
		}
		Queue<Integer> queue = new ArrayDeque<>();
		//put leaf to queue
		for(int i = 0; i < n; i++){
			if(degree[i] == 1){
				queue.offer(i);
			}
		}
		while(!queue.isEmpty()){
			res.clear();
			int size = queue.size();
			for(int i = 0; i < size; i++){
				int leaf = queue.poll();
				res.add(leaf);
				degree[leaf]--;
				for(int nb : graph.get(leaf)){
					if(degree[nb] == 0){
						continue;
					}
					degree[nb]--;
					if(degree[nb] == 1){
						queue.offer(nb);
					}
				}
			}
		}
		return res;
	}
}
