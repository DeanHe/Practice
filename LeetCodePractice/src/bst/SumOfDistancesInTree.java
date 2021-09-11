package bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.



Example 1:


Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: The tree is shown above.
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.
Hence, answer[0] = 8, and so on.
Example 2:


Input: n = 1, edges = []
Output: [0]
Example 3:


Input: n = 2, edges = [[1,0]]
Output: [1,1]


Constraints:

1 <= n <= 3 * 104
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
The given input represents a valid tree.

analysis:
https://leetcode.com/problems/sum-of-distances-in-tree/discuss/130583/C%2B%2BJavaPython-Pre-order-and-Post-order-DFS-O(N)
first post-order to get the sum of distance from 0 to all other nodes
on pre-order when moving from parent to child, consider how many distances are gained and loss.
TC O(N)
SC O(N)

 */
public class SumOfDistancesInTree {
    int[] res, subCnt;
    int n;
    List<Set<Integer>> graph;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        this.n = n;
        res = new int[n];
        subCnt = new int[n];
        Arrays.fill(subCnt, 1);
        graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new HashSet<>());
        }
        for(int[] e : edges){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        postorder(0, -1);
        preorder(0, -1);
        return res;
    }

    private void postorder(int cur, int parent) {
        for(int child : graph.get(cur)){
            if(child != parent){
                postorder(child, cur);
                subCnt[cur] += subCnt[child];
                res[cur] += res[child] + subCnt[child];
            }
        }
    }

    private void preorder(int cur, int parent) {
        for(int child : graph.get(cur)){
            if(child != parent){
                res[child] = res[cur] - subCnt[child] + n - subCnt[child];
                preorder(child, cur);
            }
        }
    }
}

