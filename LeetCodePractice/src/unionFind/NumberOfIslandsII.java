package unionFind;

import java.util.*;
/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation 1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation 2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation 3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation 4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/
public class NumberOfIslandsII {
	
	class UnionFind {
		private int count;
		private int[] parent; // id[i] is parent of i

		public UnionFind(int total) {
			parent = new int[total];
			Arrays.fill(parent, -1);
		}

		public int getRoot(int x) {
			int root = x;
	        // find root first
	        while(parent[root] != root){
	            root = parent[root];
	        }
	        // compress path
	        while(parent[x] != root){
	            int father = parent[x];
	            parent[x] = root;
	            x = father;
	        }
	        return root;
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
     * @param positions: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        // write your code here
    	List<Integer> res = new ArrayList<>();
    	if(positions == null || positions.length == 0){
    		return res;
    	}
    	UnionFind uf = new UnionFind(n * m);
    	int[] dirs = {0, -1, 0, 1, 0};
    	for(int[] point : positions){
    		int r = point[0];
    		int c = point[1];
    		int idx = r * n + c;
    		if(uf.parent[idx] != -1){
    		    res.add(uf.count);
    			continue;
    		}
			uf.parent[idx] = idx;
    		uf.count++;
    		for(int i = 0; i + 1 < dirs.length; i++){
    			int nb_r = r + dirs[i];
    			int nb_c = c + dirs[i + 1];
				int nb_idx = nb_r * n + nb_c;
    			if(0 <= nb_r && nb_r < m && 0 <= nb_c && nb_c < n && uf.parent[nb_idx] != -1) {
    				uf.union(idx, nb_idx);
    			}
    		}
    		res.add(uf.count);
    	}
    	return res;
    }
}
