package UnionFind;
import java.util.*;

/*There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.
Given a parameter m, and find the last day of m group flowering at the same time(each group has at least k plots)
If there isn't such day, output -1.
Example
input:
flowerbed = [1,3,2]
k = 1
m = 2
output:
2
*/
public class FlowerProblem {
	
	class UnionFind {
		public int count, k;
		int[] parent; // id[i] is parent of i
		// group id : group size
        HashMap<Integer, Integer> map = new HashMap<>();

		public UnionFind(int n) {
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = -1;
			}
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
	        return x;
		}

		public void union(int a, int b) {
			int root_a = getRoot(a);
			int root_b = getRoot(b);
			if (root_a != root_b) {
				parent[root_a] = root_b;
				if(map.get(root_a) >= k && map.get(root_b) >= k){
					count--;
				} else if(map.get(root_a) < k && map.get(root_b) < k){
					if(map.get(root_a) + map.get(root_b) >= k){
						count++;
					}
				}
				map.put(root_b, map.get(root_a) + map.get(root_b));
			}
		}
	}
	/**
     * @param flowers: an array
     * @param k: an integer
     * @param m: an integer
     * @return: the last day
     */
    public int flowerProblem(int[] flowers, int k, int m) {
    	int lastDay = -1;
        int len = flowers.length;
        UnionFind unionFind = new UnionFind(len);
        unionFind.k = k;
        for(int i = 0; i < len; i++){
        	int spot = flowers[i] - 1;
        	unionFind.parent[spot] = spot;
        	unionFind.map.put(spot, 1);
        	if(k <= 1){
        		unionFind.count++;
        	}
        	if(spot >= 1 && unionFind.parent[spot - 1] != -1){
        		unionFind.union(spot - 1, spot);
        	}
        	if(spot < len - 1 && unionFind.parent[spot + 1] != -1){
        		unionFind.union(spot + 1, spot);
        	}
        	if(unionFind.count >= m){
        		lastDay = i + 1;
        	}
        }
        return lastDay;
    }
}
