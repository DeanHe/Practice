package BFS;

import java.util.*;

public class IsGraphBipartite {
	public boolean isBipartite(int[][] graph) {
        int N = graph.length;
        Map<Integer, Integer> visited = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < N; i++){
            if(visited.containsKey(i)){
                continue;
            }
            visited.put(i, 1);
            queue.offer(i);
            while(!queue.isEmpty()){
                int cur = queue.poll();
                if(!visited.containsKey(cur)){
                    continue;
                }
                int flag = visited.get(cur);
                for(int nb : graph[cur]){
                    if(!visited.containsKey(nb)){
                        visited.put(nb, -flag);
                        queue.offer(nb);
                    } else {
                        if(visited.get(nb) == flag){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
