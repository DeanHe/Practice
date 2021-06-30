package bfs;

import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*
Suppose you drive a car in a undirected graph, the time to travel between two connected nodes is 1, for some node
there is a gas station, you may spend extra 1 to fill the gas, the car has a gas tank of n volume,
calculate the minimum time to travel from start to end.
 */
public class ShortestPathWithGasFillGoogle {

    public int shortestPath(int start, int end, Map<Integer, Set<Integer>> g, int n, int len, Set<Integer> gasStations){
        boolean[][] visited = new boolean[len][n + 1];
        visited[start][n] = true;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0, n});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int id = cur[0];
            int step = cur[1];
            int gas = cur[2];
            if(id == end){
                return step;
            }
            for(int nb : g.get(id)){
                if(gasStations.contains(nb)){
                    if(!visited[nb][n - 1]){
                        visited[nb][n - 1] = true;
                        pq.offer(new int[]{nb, step + 2, n - 1});
                    }
                }
                if(gas > 0){
                    if(!visited[nb][gas - 1]){
                        visited[nb][gas - 1] = true;
                        pq.offer(new int[]{nb, step + 1, gas - 1});
                    }
                }
            }
        }
        return -1;
    }
}

