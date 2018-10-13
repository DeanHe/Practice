package UnionFind;

public class SimilarStringGroups {
	int[] parent;
    int count;
    public int numSimilarGroupsUN(String[] A) {
        int len = A.length;
        parent = new int[len];
        for(int i = 0; i < len; i++){
            parent[i] = i;
        }
        count = len;
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                if(getRoot(i) != getRoot(j) && areSimilar(A[i], A[j])){
                    union(i, j);
                }
            }
        }
        return count;
    }
    
    private int getRoot(int x){
        int root = x;
        // find root first
        while(parent[root] != root){
            root = parent[root];
        }
        // compress path
        while(x != root){
            int x_father = parent[x];
            parent[x] = root;
            x = x_father;
        }
        return x;
    }
    private void union(int x, int y){
        int root_x = getRoot(x);
        int root_y = getRoot(y);
        if(root_x == root_y){
            return;
        }
        parent[root_x] = root_y;
        count--;
    }
    
    private boolean areSimilar(String s1, String s2){
        int diff = 0;
        if(s1.length() != s2.length()){
            return false;
        }
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                diff++;
                if(diff > 2){
                    return false;
                }
            }
        }
        return true;
    }
}
