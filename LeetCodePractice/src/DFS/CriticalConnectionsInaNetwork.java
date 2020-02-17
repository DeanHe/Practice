package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
        A critical connection is a connection that, if removed, will make some server unable to reach some other server.
        Return all critical connections in the network in any order.

        Example 1:
        Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
        Output: [[1,3]]
        Explanation: [[3,1]] is also accepted.

        Constraints:
        1 <= n <= 10^5
        n-1 <= connections.length <= 10^5
        connections[i][0] != connections[i][1]
        There are no repeated connections.

Basically, Tarjan Algorithm uses dfs to travel through the graph to find if current vertex u, can travel back to u or previous vertex
low[u] records the lowest vertex u can reach
disc[u] records the time when u was discovered
*/
public class CriticalConnectionsInaNetwork {
    private int time = 0; // time when discover each vertex

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> res = new ArrayList<>();
        int[] disc = new int[n];
        Arrays.fill(disc, -1);
        int[] low = new int[n];
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> connection : connections) {
            int start = connection.get(0);
            int end = connection.get(1);
            graph[start].add(end);
            graph[end].add(start);
        }
        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(graph, disc, low, res, i, i);
            }
        }
        return res;
    }

    private void dfs(List<Integer>[] graph, int[] disc, int[] low, List<List<Integer>> res, int cur, int pre) {
        time++;
        disc[cur] = time;
        low[cur] = time;
        for (int nb : graph[cur]) {
            if (nb == pre) {
                continue;
            }
            if (disc[nb] == -1) { // not visited
                dfs(graph, disc, low, res, nb, cur);
                low[cur] = Math.min(low[cur], low[nb]);
                if (low[nb] > disc[cur]) {  // cur - nb is critical, there is no path for nb to reach back to cur or previous vertices of cur
                    res.add(Arrays.asList(cur, nb));
                }
            } else { // if nb discovered and is not parent of cur, update low[cur], cannot use low[nb] because cur is not subtree of nb
                low[cur] = Math.min(low[cur], disc[nb]);
            }
        }
    }
}
