package unionFind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.

Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .

Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.



Example 1:
Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
Output: [false,true]
Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.

Example 2:
Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
Output: [true,false]
Explanation: The above figure shows the given graph.


Constraints:
2 <= n <= 10^5
1 <= edgeList.length, queries.length <= 10^5
edgeList[i].length == 3
queries[j].length == 3
0 <= ui, vi, pj, qj <= n - 1
ui != vi
pj != qj
1 <= dis_i, limit_j <= 10^9
There may be multiple edges between two nodes.

analysis:
sort by query and edge, move forward at the sametime
 */
public class CheckingExistenceOfEdgeLengthLimitedPaths {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        UF uf = new UF(n);
        boolean[] res = new boolean[queries.length];
        Map<String, Integer> queryToIdx = new HashMap<>();
        for(int i = 0; i < queries.length; i++){
            queryToIdx.put(Arrays.toString(queries[i]), i);
        }
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        Arrays.sort(queries, (a, b) -> a[2] - b[2]);
        int i = 0;
        for(int[] query : queries){
            int p = query[0], q = query[1], limit = query[2];
            while(i < edgeList.length && edgeList[i][2] < limit){
                int u = edgeList[i][0];
                int v = edgeList[i][1];
                i++;
                uf.union(u, v);
            }
            res[queryToIdx.get(Arrays.toString(query))] = uf.connected(p, q);
        }
        return res;
    }
}
