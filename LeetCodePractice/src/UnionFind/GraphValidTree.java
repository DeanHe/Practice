package UnionFind;

import java.util.*;

public class GraphValidTree {
	class UnionFind {
	    // map of node and its parent
	    HashMap<Integer, Integer> parent_map = new HashMap<>();
	    UnionFind(int n){
	        for(int i = 0; i < n; i++){
	            parent_map.put(i, i);
	        }
	    }
	    // find the root of x and compress the path
	    int compressed_find(int x){
	        int parent =  parent_map.get(x);
	        while(parent !=  parent_map.get(parent)){
	            parent =  parent_map.get(parent);
	        }
	        // here parent equals root of x
	        int temp = -1;
	        int fa =  parent_map.get(x);
	        while(fa != parent){
	            temp =  parent_map.get(fa);
	            parent_map.put(fa, parent);
	            fa = temp;
	        }
	        return parent;
	    }
	    void union(int a, int b){
	        int root_a = compressed_find(a);
	        int root_b = compressed_find(b);
	        if(root_a != root_b){
	            parent_map.put(root_a, root_b);
	        }
	    }
	}
	
	public boolean validTree(int n, int[][] edges) {
	    // Write your code here
	    if(n - 1 != edges.length){
	        return false;
	    }
	     
	    UnionFind uf = new UnionFind(n);
	    for(int i = 0; i < edges.length; i++){
	        //check if there is cycle
	        if(uf.compressed_find(edges[i][0]) == uf.compressed_find(edges[i][1])){
	            return false;
	        }
	        uf.union(edges[i][0], edges[i][1]);
	    }
	    return true;
	}
}
