package BST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

There is a tree (i.e., a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. Each node has a value associated with it, and the root of the tree is node 0.

To represent this tree, you are given an integer array nums and a 2D array edges. Each nums[i] represents the ith node's value, and each edges[j] = [uj, vj] represents an edge between nodes uj and vj in the tree.

Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the greatest common divisor of x and y.

An ancestor of a node i is any other node on the shortest path from node i to the root. A node is not considered an ancestor of itself.

Return an array ans of size n, where ans[i] is the closest ancestor to node i such that nums[i] and nums[ans[i]] are coprime, or -1 if there is no such ancestor.



Example 1:
Input: nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
Output: [-1,0,0,1]
Explanation: In the above figure, each node's value is in parentheses.
- Node 0 has no coprime ancestors.
- Node 1 has only one ancestor, node 0. Their values are coprime (gcd(2,3) == 1).
- Node 2 has two ancestors, nodes 1 and 0. Node 1's value is not coprime (gcd(3,3) == 3), but node 0's
  value is (gcd(2,3) == 1), so node 0 is the closest valid ancestor.
- Node 3 has two ancestors, nodes 1 and 0. It is coprime with node 1 (gcd(3,2) == 1), so node 1 is its
  closest valid ancestor.


Example 2:
Input: nums = [5,6,10,2,3,6,15], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
Output: [-1,0,-1,0,0,0,-1]


Constraints:

nums.length == n
1 <= nums[i] <= 50
1 <= n <= 10^5
edges.length == n - 1
edges[j].length == 2
0 <= uj, vj < n
uj != vj
 */
public class TreeOfCoprimes {
    public int[] getCoprimes(int[] nums, int[][] edges) {
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -2);
        boolean[][] primePairs = preComputePrimePairs();
        // mem save the node for value equals key
        Map<Integer, List<int[]>> mem = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], x -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], x -> new ArrayList<>()).add(e[0]);
        }
        dfs(res, 0, 0, mem, nums, graph, primePairs);
        return res;
    }

    private void dfs(int[] res, int idx, int depth, Map<Integer, List<int[]>> mem, int[] nums
            , Map<Integer, List<Integer>> graph, boolean[][] primePairs) {
        int val = nums[idx];
        int dist = Integer.MAX_VALUE;
        int closest = -1;
        for (int n = 1; n <= 50; n++) {
            if (primePairs[val][n] && mem.containsKey(n)) {
                List<int[]> ancestors = mem.get(n);
                int[] closestAncestor = ancestors.get(ancestors.size() - 1);
                if (depth - closestAncestor[0] < dist) {
                    dist = depth - closestAncestor[0];
                    closest = closestAncestor[1];
                }
            }
        }
        res[idx] = closest;
        if(graph.containsKey(idx)){
            mem.computeIfAbsent(val, x -> new ArrayList<>()).add(new int[]{depth, idx});
            for (int nb : graph.get(idx)) {
                if(res[nb] == -2){
                    dfs(res, nb, depth + 1, mem, nums, graph, primePairs);
                }
            }
            List<int[]> ancestors = mem.get(val);
            ancestors.remove(ancestors.size() - 1);
            if (ancestors.isEmpty()) {
                mem.remove(val);
            }
        }
    }

    private boolean[][] preComputePrimePairs() {
        boolean[][] primePairs = new boolean[51][51];
        for (int i = 1; i <= 50; i++) {
            for (int j = i; j <= 50; j++) {
                if (gcd(i, j) == 1) {
                    primePairs[i][j] = true;
                    primePairs[j][i] = true;
                }
            }
        }
        return primePairs;
    }

    private int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
