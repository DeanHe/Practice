package BFS;
/*There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Example
Example1

Input:
times = [[2,1,1],[2,3,1],[3,4,1]]
N = 4
K = 2
Output:
2
Notice
1.N will be in the range [1, 100].
2.K will be in the range [1, N].
3.The length of times will be in the range [1, 6000].
4.All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.*/

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
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>(N, new Comparator<Node>(){
            public int compare(Node a, Node b){
                return a.dist - b.dist;
            }
        });
        Node start = new Node(K, 0);
        pq.offer(start);
        
        Map<Integer, Integer> visited = new HashMap<>();
        // Dijkstra's Algorithm
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visited.containsKey(cur.index)){
                continue;
            }
            visited.put(cur.index, cur.dist);
            if(graph.containsKey(cur.index)){
                List<Node> neighbors = graph.get(cur.index);
                for(Node nb : neighbors){
                    if(!visited.containsKey(nb.index)){
                        nb.dist = cur.dist + nb.dist;
                        pq.offer(nb);
                    }
                }
            }                     
        }
        
        if(visited.size() != N){
            return -1;
        }
        int ans = 0;
        for(int d : visited.values()){
            ans = Math.max(ans, d);
        }
        return ans;
    }
}
