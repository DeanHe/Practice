package UnionFind;
/*
Basic Union Find Class
 */
public class UF {
    private int[] parent;

    public UF(int n){
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
    }

    public boolean connected(int a, int b){
        return findRoot(a) == findRoot(b);
    }

    public void union(int a, int b){
        int root_a = findRoot(a);
        int root_b = findRoot(b);
        if(root_a != root_b){
            parent[root_a] = root_b;
        }
    }

    public int findRoot(int x){
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
}
