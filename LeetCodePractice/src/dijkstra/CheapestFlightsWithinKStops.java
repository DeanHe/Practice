package dijkstra;

import java.util.*;
/*
There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and fights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200

Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500

Notice
The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.

analysis:
not typical dijkstra case
TC O(n^k * k * log(n^k))
*/

public class CheapestFlightsWithinKStops {
	/**
     * @param n: # of cities
     * @param flights: a 2D array
     * @param src: a integer
     * @param dst: a integer
     * @param k: a integer represents up to k stops
     * @return: return a integer
     */
	public int findCheapestPriceDijk(int n, int[][] flights, int src, int dst, int k) {
        int[][] graph = new int[n][n];
        for(int[] f : flights){
            graph[f[0]][f[1]] = f[2];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // city : cost : stop
        pq.offer(new int[]{src, 0, -1});
        while(!pq.isEmpty()){
            int[] temp = pq.poll();
        	int city = temp[0];
        	int price = temp[1];
            int stop = temp[2];
            if(stop <= k){
            	if(city == dst){
            		return price;
            	}
            	for(int nb = 0; nb < graph[city].length; nb++){
            		if(graph[city][nb] > 0){
            			// is valid nb
            			pq.offer(new int[]{nb, graph[city][nb] + price, stop + 1});
            		}
            	}
            }
        }
    	return -1;
    }
	
	// using bfs
	public int findCheapestPriceBFS(int n, int[][] flights, int src, int dst, int K) {
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;
        int[][] graph = new int[n][n];
        for(int[] f : flights){
            graph[f[0]][f[1]] = f[2];
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        int stops = 0;
        while(!queue.isEmpty()){
            if(stops > K){
                break;
            }
            int[] costSnapShot = Arrays.copyOf(costs, costs.length);
            int sz = queue.size();
            for(int i = 0; i < sz; i++){
                int city = queue.poll();      
        	    for(int nb = 0; nb < graph[city].length; nb++){
        		    if(graph[city][nb] > 0){
        			    // is valid nb
                        if(costs[nb] > graph[city][nb] + costSnapShot[city]){
                            costs[nb] = graph[city][nb] + costSnapShot[city];
                            queue.offer(nb);
                        }
        		    }
        	    }
            }
            stops++;
        }
    	return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }
	// using dp
	public int findCheapestPriceDP(int n, int[][] flights, int src, int dst, int K) {
		int[][] dp = new int[K + 2][n];
        for(int[] row : dp){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][src] = 0;
        for(int i = 1; i <= K + 1; i++){
        	dp[i][src] = 0;
        	for(int[] flight : flights){
        		int start = flight[0];
        		int end = flight[1];
        		int cost = flight[2];
                if(dp[i - 1][start] < Integer.MAX_VALUE){
                    dp[i][end] = Math.min(dp[i][end], dp[i - 1][start] + cost);
                }
        	}
        }
        return dp[K + 1][dst] == Integer.MAX_VALUE ? -1 : dp[K + 1][dst];
    }
}
