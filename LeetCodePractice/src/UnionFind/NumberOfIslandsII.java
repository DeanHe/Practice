package UnionFind;

import java.util.*;
//Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). 
//Originally, the 2D matrix is all 0 which means there is only sea in the matrix. 
//The list pair has k operator and each operator has two integer A[i].x, A[i].y 
//means that you can change the grid matrix[A[i].x][A[i].y] from sea to island. 
//Return how many island are there in the matrix after each operator.
public class NumberOfIslandsII {
	class Point {
		int x;
		int y;
		Point() {
			x = 0;
			y = 0;
		}
		Point(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	class UnionFind {
		public int count;
		int[] parent; // id[i] is parent of i

		public UnionFind(int n) {
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public int getRoot(int x) {
			int root = x;
	        // find root first
	        while(parent[root] != root){
	            root = parent[root];
	        }
	        // compress path
	        while(x != root){
	            int x_father = parent[x];
	            parent[x] = root;
	            x = x_father;
	        }
	        return x;
		}

		public void union(int a, int b) {
			int root_a = getRoot(a);
			int root_b = getRoot(b);
			if (root_a != root_b) {
				parent[root_a] = root_b;
				count--;
			}
		}
	}
	/**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // write your code here
    	List<Integer> ans = new ArrayList<Integer>();
    	if(operators == null){
    		return ans;
    	}
    	UnionFind unionFind = new UnionFind(n * m);
    	int[] dx = {0, -1, 0, 1};
    	int[] dy = {1, 0, -1, 0};
    	boolean[][] isIsland = new boolean[n][m];
    	for(Point point : operators){
    		int x = point.x;
    		int y = point.y;
    		if(isIsland[x][y]){
    		    ans.add(unionFind.count);
    			continue;
    		}
    		isIsland[x][y] = true;
    		unionFind.count++;
    		int index = x * m + y;
    		for(int j = 0; j < 4; j++){
    			int nb_x = x + dx[j];
    			int nb_y = y + dy[j];
    			if(0 <= nb_x && nb_x < n && 0 <= nb_y && nb_y < m && isIsland[nb_x][nb_y]){
    				int nb_index = nb_x * m + nb_y;
    				unionFind.union(index, nb_index);
    			}
    		}
    		ans.add(unionFind.count);
    	}
    	return ans;
    }
}
