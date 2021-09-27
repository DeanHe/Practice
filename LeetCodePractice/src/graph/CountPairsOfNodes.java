package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
#1782

You are given an undirected graph represented by an integer n, which is the number of nodes, and edges, where edges[i] = [ui, vi] which indicates that there is an undirected edge between ui and vi. You are also given an integer array queries.

The answer to the jth query is the number of pairs of nodes (a, b) that satisfy the following conditions:

a < b
cnt is strictly greater than queries[j], where cnt is the number of edges incident to a or b.
Return an array answers such that answers.length == queries.length and answers[j] is the answer of the jth query.

Note that there can be repeated edges.



Example 1:


Input: n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
Output: [6,5]
Explanation: The number of edges incident to at least one of each pair is shown above.
Example 2:

Input: n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]], queries = [1,2,3,4,5]
Output: [10,10,9,8,6]


Constraints:

2 <= n <= 2 * 10^4
1 <= edges.length <= 10^5
1 <= ui, vi <= n
ui != vi
1 <= queries.length <= 20
0 <= queries[j] < edges.length

hint:
1 We want to count pairs (x,y) such that degree[x] + degree[y] - occurrences(x,y) > k
2 Think about iterating on x, and counting the number of valid y to pair with x.
3 You can consider at first that the (- occurrences(x,y)) isn't there, or it is 0 at first for all y. Count the valid y this way.
4 Then you can iterate on the neighbors of x, let that neighbor be y, and update occurrences(x,y).
5 When you update occurrences(x,y), the left-hand side decreases. Once it reaches k, then y is not valid for x anymore, so you should decrease the answer by 1.
 */
public class CountPairsOfNodes {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] deg = new int[n + 1];
        Map<Integer, Integer> common = new HashMap<>();
        for (int[] e : edges) {
            deg[e[0]]++;
            deg[e[1]]++;
            if (e[0] > e[1]) {
                int temp = e[0];
                e[0] = e[1];
                e[1] = temp;
            }
            int key = e[0] * 20001 + e[1];
            common.put(key, common.getOrDefault(key, 0) + 1);
        }
        int[] sortedDeg = Arrays.copyOf(deg, deg.length);
        Arrays.sort(sortedDeg);
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            for (int l = 1, r = n; l < r; ) {
                if (sortedDeg[l] + sortedDeg[r] > query) {
                    res[i] += r - l;
                    r--;
                } else {
                    l++;
                }
            }
            for (int pair : common.keySet()) {
                int a = pair / 20001;
                int b = pair % 20001;
                if (deg[a] + deg[b] > query && deg[a] + deg[b] - common.get(pair) <= query) {
                    res[i]--;
                }
            }
        }
        return res;
    }
}
