package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
/*
#332
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].

All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].But it is larger in lexical order.

analysis:
This is an application of Hierholzer’s algorithm to find a Eulerian path, need to visit all directed edges in the graph.
Just Eulerian path. greedy DFS, building the route backwards when retreating.
*/
public class ReconstructItinerary {
	Map<String, PriorityQueue<String>> graph = new HashMap<>();

	public List<String> findItinerary(List<List<String>> tickets) {
		List<String> path = new ArrayList<>();
		for (List<String> ticket : tickets) {
			graph.computeIfAbsent(ticket.get(0), x -> new PriorityQueue<>()).add(ticket.get(1));
		}
		dfs("JFK", path);
		return path;

	}

	private void dfs(String departure, List<String> path) {
		if (graph.containsKey(departure)) {
			PriorityQueue<String> neighbors = graph.get(departure);
			while (!neighbors.isEmpty()) {
				// remove the edge from the graph after visit
				String next = neighbors.poll();
				dfs(next, path);
			}
		}
		path.add(0, departure);
	}
}
