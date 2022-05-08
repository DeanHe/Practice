package MinimumSpanningTree;
/*
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

Example 1:
Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation:

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.

Example 2:
Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18


Constraints:
1 <= points.length <= 1000
-10^6 <= xi, yi <= 10^6
All pairs (xi, yi) are distinct.

hint:
1 Connect each pair of points with a weighted edge, the weight being the manhattan distance between those points.
2 The problem is now the cost of minimum spanning tree in graph with above edges.

analysis:
Kruskal's algorithm using Union-Find
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinCostToConnectAllPoints {
    int[] parent;
    public int minCostConnectPoints(int[][] points) {
        int res = 0;
        int len = points.length;
        parent = new int[len];
        for(int i = 0; i < len; i++){
            parent[i] = i;
        }
        List<int[]> ls = new ArrayList<>();
        for(int i = 0; i < len; i++){
            int[] a = points[i];
            for(int j = i + 1; j < len; j++){
                int[] b = points[j];
                int dist = manhattanDist(a, b);
                int[] edge = new int[]{i, j, dist};
                ls.add(edge);
            }
        }
        Collections.sort(ls, (a, b) -> a[2] - b[2]);
        for(int[] edge : ls){
            if(union(edge[0], edge[1])){
                res += edge[2];
            }
        }
        return res;
    }

    private boolean union(int a, int b) {
        int root_a = getRoot(a);
        int root_b = getRoot(b);
        if(root_a != root_b){
            parent[root_a] = parent[root_b];
            return true;
        } else {
            return false;
        }
    }

    private int getRoot(int x) {
        int root = x;
        while(parent[root] != root){
            root = parent[root];
        }
        while(parent[x] != root){
            int fa = parent[x];
            parent[x] = root;
            x = fa;
        }
        return root;
    }

    private int manhattanDist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
