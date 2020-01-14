package UnionFind;

import java.util.HashMap;
import java.util.Map;

/*Given a non-empty array of unique positive integers A, consider the following graph:

There are A.length nodes, labelled A[0] to A[A.length - 1];
There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
Return the size of the largest connected component in the graph.

 

Example 1:

Input: [4,6,15,35]
Output: 4

Example 2:

Input: [20,50,9,63]
Output: 2

Example 3:

Input: [2,3,6,7,4,12,21,39]
Output: 8

Note:

1 <= A.length <= 20000
1 <= A[i] <= 100000*/
public class LargestComponentSizeByCommonFactor {
	private int[] id, size;
	private int maxGroupSize;
	public int largestComponentSize(int[] A) {
        int len = A.length;
        id = new int[len];
        size = new int[len];
        maxGroupSize = 1;
        for(int i = 0; i < len; i++){
        	id[i] = i;
        	size[i] = 1;
        }
        Map<Integer, Integer> map = new HashMap<>(); // key is the factor, val is the node index
        for(int i = 0; i < len; i++){
        	int num = A[i];
        	for(int f = 2; f * f <= num; f++){
        		if(num % f == 0){
        			if(!map.containsKey(f)){
        				//this means that no index has claimed the factor yet
        				map.put(f, i);
        			} else {
        				union(i, map.get(f));
        			}
        			if(!map.containsKey(num / f)){
        				map.put(num / f, i);
        			} else {
        				union(i, map.get(num / f));
        			}
        		}
        	}
        	//num could be factor too. Don't miss this
        	if(!map.containsKey(num)){
				map.put(num, i);
			} else {
				union(i, map.get(num));
			}
        }
        return maxGroupSize;
    }
	private void union(int a, int b){
		int root_a = find(a);
		int root_b = find(b);
		if(root_a != root_b){
			size[root_a] += size[root_b];
			id[root_b] = root_a;
			size[root_b] = 0;
			maxGroupSize = Math.max(maxGroupSize, size[root_a]);
		}
	}
	private int find(int a){
		int root = a;
		while(id[root] != root){
			root = id[root];
		}
		while(id[a] != root){
			int father = id[a];
			id[a] = root;
			a = father;
		}
		return root;
	}
}
