package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi. Each city has a name consisting of exactly 3 upper-case English letters given in the string array names. Starting at any city x,
you can reach any city y where y != x (i.e. the cities and the roads are forming an undirected connected graph).
You will be given a string array targetPath. You should find a path in the graph of the same length and with the minimum edit distance to targetPath.
You need to return the order of the nodes in the path with the minimum edit distance, The path should be of the same length of targetPath and should be valid (i.e. there should be a direct road between ans[i] and ans[i + 1]).
If there are multiple answers return any one of them.

The edit distance is defined as follows:


Follow-up: If each node can be visited only once in the path, What should you change in your solution?

Example 1:
Input: n = 5,
roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]],
names = ["ATL","PEK","LAX","DXB","HND"],
targetPath = ["ATL","DXB","HND","LAX"]
Output: [0,2,4,2]
Explanation: [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
[0,2,4,2] is equivalent to ["ATL","LAX","HND","LAX"] which has edit distance = 1 with targetPath.
[0,3,0,2] is equivalent to ["ATL","DXB","ATL","LAX"] which has edit distance = 1 with targetPath.
[0,3,1,2] is equivalent to ["ATL","DXB","PEK","LAX"] which has edit distance = 1 with targetPath.

Example 2:
Input: n = 4,
roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]],
names = ["ATL","PEK","LAX","DXB"],
targetPath = ["ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"]
Output: [0,1,0,1,0,1,0,1]
Explanation: Any path in this graph has edit distance = 8 with targetPath.

Example 3:
Input: n = 6,
roads = [[0,1],[1,2],[2,3],[3,4],[4,5]],
names = ["ATL","PEK","LAX","ATL","DXB","HND"],
targetPath = ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
Output: [3,4,5,4,3,2,1]
Explanation: [3,4,5,4,3,2,1] is the only path with edit distance = 0 with targetPath.
It's equivalent to ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]

Constraints:
2 <= n <= 100
m == roads.length
n - 1 <= m <= (n * (n - 1) / 2)
0 <= ai, bi <= n - 1
ai != bi
The graph is guaranteed to be connected and each pair of nodes may have at most one direct road.
names.length == n
names[i].length == 3
names[i] consists of upper-case English letters.
1 <= targetPath.length <= 100
targetPath[i].length == 3
targetPath[i] consists of upper-case English letters.

analysis:
dp[i][j] means the minEditDistance to match to targetPath[i] with original end word in names[j]
pre[i][j] means for the minEditDistance to match to targetPath[i] with original end word in names[j], what is the previous step index
 */
public class TheMostSimilarPathInaGraph {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        List<Integer> res = new ArrayList<>();
        int len = targetPath.length;
        Integer[][] dp = new Integer[len][n];
        int[][] pre = new int[len][n];
        Map<Integer, List<Integer>> g = new HashMap<>();
        for(int[] r : roads){
            g.computeIfAbsent(r[0], x -> new ArrayList<>()).add(r[1]);
            g.computeIfAbsent(r[1], x -> new ArrayList<>()).add(r[0]);
        }
        //init
        Arrays.fill(pre[0], -1);
        for(int i = 0; i < n; i++){
            dp[0][i] = targetPath[0].equals(names[i]) ? 0 : 1;
        }
        //transfer func
        for(int i = 1; i < len; i++){
            for(int j = 0; j < n; j++){
                int editDist = targetPath[i].equals(names[j]) ? 0 : 1;
                List<Integer> nbs = g.getOrDefault(j, new ArrayList<>());
                int temp = Integer.MAX_VALUE, last = -1;
                for(int nb : nbs){
                    if(temp > dp[i - 1][nb] + editDist){
                        temp = dp[i - 1][nb] + editDist;
                        last = nb;
                    }
                }
                dp[i][j] = temp;
                pre[i][j] = last;
            }
        }
        //aggregate
        int minDist = Integer.MAX_VALUE, last = -1;
        for(int i = 0; i < n; i++){
            if(minDist > dp[len - 1][i]){
                minDist = dp[len - 1][i];
                last = i;
            }
        }
        for(int i = len - 1; i >= 0; i--){
            res.add(last);
            last = pre[i][last];
        }
        Collections.reverse(res);
        return res;
    }
}

