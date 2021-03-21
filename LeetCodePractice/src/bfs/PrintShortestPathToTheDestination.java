package bfs;

import java.util.*;
/*
test:
PrintShortestPathToTheDestination pst = new PrintShortestPathToTheDestination();
Map<Integer, List<Integer>> graph = new HashMap<>();
graph.put(1, Arrays.asList(2, 3));
graph.put(2, Arrays.asList(1, 6));
graph.put(3, Arrays.asList(1, 4, 9));
graph.put(4, Arrays.asList(3, 5, 7));
graph.put(5, Arrays.asList(4, 6));
graph.put(6, Arrays.asList(2, 5, 7));
graph.put(7, Arrays.asList(4, 6));
graph.put(9, Arrays.asList(3));
List<Integer> path = pst.getShortestPath(graph, 1, 6);
System.out.println(Arrays.toString(path.toArray()));
*/
public class PrintShortestPathToTheDestination {
	public List<Integer> getShortestPath(Map<Integer, List<Integer>> graph, int start, int end){
		List<Integer> path = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();
		Map<Integer, Integer> parent = new HashMap<>();  //to:from
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
