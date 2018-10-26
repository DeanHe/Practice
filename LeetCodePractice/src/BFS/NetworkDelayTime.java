package BFS;

import java.util.*;

public class NetworkDelayTime {
	class Node {
        int index, dist;
        public Node(int index, int dist){
            this.index = index;
            this.dist = dist;
        }
    }	
	/**
     * @param times: a 2D array
     * @param N: an integer, # of network nodes, labelled 1 to N
     * @param K: an integer, we send a signal from a certain node K
     * @return: how long will it take for all nodes to receive the signal
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        // Write your code here
    	Map<Integer, List<Node>> graph = new HashMap<>();
        for(int[] entry : times){
            if(!graph.containsKey(entry[0])){
                graph.put(entry[0], new ArrayList<Node>());
            }
            List<Node> neighbors = graph.get(entry[0]);
            neighbors.add(new Node(entry[1], entry[2]));
            graph.put(entry[0], neighbors);
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>(N, new Comparator<Node>(){
            public int compare(Node a, Node b){
                return a.dist - b.dist;
            }
        });
        Node start = new Node(K, 0);
        pq.offer(start);
        
        Map<Integer, Integer> results = new HashMap<>();
        // Dijkstra's Algorithm
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(results.containsKey(cur.index)){
                continue;
            }
            results.put(cur.index, cur.dist);
            if(graph.containsKey(cur.index)){
                List<Node> neighbors = graph.get(cur.index);
                for(Node nb : neighbors){
                    if(!results.containsKey(nb.index)){
                        nb.dist = cur.dist + nb.dist;
                        pq.offer(nb);
                    }
                }
            }                     
        }
        
        if(results.size() != N){
            return -1;
        }
        int ans = 0;
        for(int d : results.values()){
            ans = Math.max(ans, d);
        }
        return ans;
    }
}
