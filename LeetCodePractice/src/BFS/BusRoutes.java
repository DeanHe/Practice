package BFS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

Example
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Notice
1.1 <= routes.length <= 500.
2.1 <= routes[i].length <= 500.
3.0 <= routes[i][j] < 10 ^ 6.*/
public class BusRoutes {
	/**
     * @param routes:  a list of bus routes
     * @param S: start
     * @param T: destination
     * @return: the least number of buses we must take to reach destination
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        // Write your code here
    	Map<Integer, Set<Integer>> stopToRoutes = new HashMap<>();
    	for(int i = 0; i < routes.length; i++){
    		int[] stops = routes[i];
    		for(int stop : stops){
    			if(!stopToRoutes.containsKey(stop)){
    				stopToRoutes.put(stop, new HashSet<>());
    			}
    			stopToRoutes.get(stop).add(i);
    		}
    	}
    	int count = 0;
    	Queue<Integer> queue = new LinkedList<>();
    	Set<Integer> visitedRoutes = new HashSet<>();
    	queue.offer(S);
    	while(!queue.isEmpty()){
    		int size = queue.size();
    		for(int i = 0; i < size; i++){
    			int curStop = queue.poll();
        		if(curStop == T){
        			return count;
        		}
        		Set<Integer> routesOnStop = stopToRoutes.get(curStop);
        		for(int route : routesOnStop){
        			if(!visitedRoutes.contains(route)){
        				visitedRoutes.add(route);
        				for(int stop : routes[route]){
        					queue.offer(stop);
        				}
        			}
         		}
    		}
    		count++;
    	}
    	return -1;
    }
}
