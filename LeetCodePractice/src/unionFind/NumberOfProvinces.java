package unionFind;

/*
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

Example 1:
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

Example 2:
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3


Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces {
    int cnt;
    int[] parent;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        cnt = n;
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        for(int i = 0; i + 1 < n; i++){
            for(int j = i + 1; j < n; j++){
                if(isConnected[i][j] == 1){
                    union(i, j);
                }
            }
        }
        return cnt;
    }

    private int findRoot(int x){
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

    private void union(int a, int b){
        int root_a = findRoot(a);
        int root_b = findRoot(b);
        if(root_a == root_b){
            return;
        }
        parent[root_a] = root_b;
        cnt--;
    }
}
