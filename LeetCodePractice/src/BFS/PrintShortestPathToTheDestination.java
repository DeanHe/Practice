package BFS;

import java.util.*;

public class PrintShortestPathToTheDestination {
	public List<Integer> getShortestPath(Map<Integer, List<Integer>> graph, int start, int end){
		List<Integer> path = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();
		Map<Integer, Integer> parent = new HashMap<>();
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited.add(start);
		while(!queue.isEmpty()){
			int cur = queue.poll();
			if(cur == end){
				break;
			}
			for(int nb : graph.get(cur)){
				if(!visited.contains(nb)){
					visited.add(nb);
					queue.offer(nb);
					parent.put(nb, cur);
				}
			}
		}
		int cur = end;
		while(parent.containsKey(cur)){
			path.add(0, cur);
			cur = parent.get(cur);
		}
		path.add(0, start);
		return path;		
	}
}
