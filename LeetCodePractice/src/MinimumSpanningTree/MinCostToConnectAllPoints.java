package MinimumSpanningTree;

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
