package Backtracking;

import java.util.*;
/*Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].But it is larger in lexical order.*/
//This is an application of Hierholzer’s algorithm to find a Eulerian path.
public class ReconstructItinerary {
	Map<String, PriorityQueue<String>> graph = new HashMap<>();
	List<String> path = new ArrayList<String>();
    public List<String> findItinerary(String[][] tickets) {
    	for(String[] itinerary : tickets){
    		if(graph.containsKey(itinerary[0])){
    			graph.put(itinerary[0], new PriorityQueue<String>());
    		}
    		graph.get(itinerary[0]).add(itinerary[1]);
    	}
        dfs("JFK");
        return path;
        
    }
    private void dfs(String departure){
    	PriorityQueue<String> neighbors = graph.get(departure);
    	while(neighbors != null && !neighbors.isEmpty()){
    		String next = neighbors.poll();
    		dfs(next);
    	}
    	path.add(0, departure);
    }
}
