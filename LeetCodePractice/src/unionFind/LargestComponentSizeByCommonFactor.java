package unionFind;

import java.util.HashMap;
import java.util.Map;

/*
Given a non-empty array of unique positive integers A, consider the following graph:

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

Constraints:

1 <= nums.length <= 2 * 10^4
1 <= nums[i] <= 10^5
All the values of nums are unique.
*/
public class LargestComponentSizeByCommonFactor {
    int[] parent, size;
    int maxGroupSize;

    public int largestComponentSize(int[] nums) {
        int len = nums.length;
        parent = new int[len];
        size = new int[len];
        maxGroupSize = 1;
        for (int i = 0; i < len; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        Map<Integer, Integer> map = new HashMap<>(); // key is the factor, val is the node index
        for (int i = 0; i < len; i++) {
            int n = nums[i];
            for (int f = 2; f * f <= n; f++) {
                if (n % f == 0) {
                    if (!map.containsKey(f)) {
                        //this means that no index has claimed the factor yet
                        map.put(f, i);
                    } else {
                        union(i, map.get(f));
                    }
                    if (!map.containsKey(n / f)) {
                        map.put(n / f, i);
                    } else {
                        union(i, map.get(n / f));
                    }
                }
            }
            //num could be factor too. Don't miss this
            if (!map.containsKey(n)) {
                map.put(n, i);
            } else {
                union(i, map.get(n));
            }
        }
        return maxGroupSize;
    }

    private void union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            size[root_a] += size[root_b];
            parent[root_b] = root_a;
            size[root_b] = 0;
            maxGroupSize = Math.max(maxGroupSize, size[root_a]);
        }
    }

    private int find(int a) {
        int root = a;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (parent[a] != root) {
            int father = parent[a];
            parent[a] = root;
            a = father;
        }
        return root;
    }
}
