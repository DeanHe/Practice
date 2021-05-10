package MinimumSpanningTree;

import java.util.Arrays;

/*
There are N cities numbered from 1 to N.

You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.
(A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.
The cost is the sum of the connection costs used. If the task is impossible, return -1.



Example 1:



Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation:
Choosing any 2 edges will connect all cities so we choose the minimum 2.
Example 2:



Input: N = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation:
There is no way to connect all cities even if all edges are used.
Note:

1 <= N <= 10000
1 <= connections.length <= 10000
1 <= connections[i][0], connections[i][1] <= N
0 <= connections[i][2] <= 10^5
connections[i][0] != connections[i][1]

Analysis：
2 methods Prim and Kruskal unionFind
Try to connect cities with minimum cost, then find small cost edge first, if two cities connected by the edge do no have same ancestor, then union them.

When number of unions equal to 1, all cities are connected.

Time Complexity: O(mlogm + mlogN). sort takes O(mlogm). find takes O(logN). With path compression and union by weight, amortized O(1).

Space: O(N).
 */
public class ConnectingCitiesWithMinimumCost {
    int[] parent;
    public int minimumCost(int N, int[][] connections) {
        int res = 0;
        parent = new int[N + 1];
        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        for(int[] con : connections){
            int a = con[0];
            int b = con[1];
            int cost = con[2];
            int root_a = findRoot(a);
            int root_b = findRoot(b);
            if(root_a != root_b){
                res += cost;
                parent[root_a] = root_b;
                N--;
            }
        }
        return N == 1 ? res : -1;
    }

    private int findRoot(int x){
        int root = x;
        while(root != parent[root]){
            root = parent[root];
        }
        while(root == parent[x]){
            int fa = parent[x];
            parent[x] = root;
            x = fa;
        }
        return root;
    }

}
