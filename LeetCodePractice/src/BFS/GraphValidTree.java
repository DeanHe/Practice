package BFS;

import java.util.*;
/*Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Notice
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/
public class GraphValidTree {
	/**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
	//BFS
	public boolean validTree(int n, int[][] edges) {
	    if(edges == null || edges.length != n - 1){
	        return false;
	    }
	    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
	    for(int i = 0; i < n; i++){
	        ArrayList<Integer> list = new ArrayList<>();
	        map.put(i, list);
	    }
	    for(int i = 0; i < edges.length; i++){
	        map.get(edges[i][0]).add(edges[i][1]);
	        map.get(edges[i][1]).add(edges[i][0]);
	    }
	    boolean[] visited = new boolean[n];
	    Queue<Integer> queue = new LinkedList<>();
	    queue.offer(0);
	    while(!queue.isEmpty()){
	        int cur = queue.poll();
	        if(visited[cur]){
	            return false;
	        }
	        visited[cur] = true;
            ArrayList<Integer> neighbors = map.get(cur);
            for(int nb : neighbors){
                if(!visited[nb]){
                    queue.offer(nb);
                }
            }
	    }
	    for(int i = 0; i < n; i++){
	        if(!visited[i]){
	            return false;
	        }
	    }
	    return true;
	}
    //DFS 
    public boolean validTreeDFS(int n, int[][] edges) {
    	HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            ArrayList<Integer> list = new ArrayList<>();
            map.put(i, list);
        }
        for(int i = 0; i < edges.length; i++){
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }
        boolean[] visited = new boolean[n];
       if(!dfs(0, -1, map, visited)){
           return false;
       }
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                return false;
            }
        }
        return true;
    }
    private boolean dfs(int cur, int parent, HashMap<Integer, ArrayList<Integer>> map, boolean[] visited){
        if(visited[cur]){
            return false;
        }
        visited[cur] = true;
        ArrayList<Integer> neighbors = map.get(cur);
        for(int nb : neighbors){
            if(nb != parent && !dfs(nb, cur, map, visited)){
                return false;
            }
        }
        return true;
    }
}
