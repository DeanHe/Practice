package unionFind;
/*
On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
        Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
        What is the largest possible number of moves we can make?

        Example 1:

        Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
        Output: 5
        Example 2:

        Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
        Output: 3
        Example 3:

        Input: stones = [[0,0]]
        Output: 0


        Note:

        1 <= stones.length <= 1000
        0 <= stones[i][j] < 10000

        analysis:
        union and find functions have worst case O(N), amortize O(1)
        The whole union-find solution with path compression,
        has O(N) Time, O(N) Space
*/
public class MostStonesRemovedWithSameRowOrColumn {
    int[] parent; // use idx of stones to represent
    public int removeStones(int[][] stones) {
        int islands = 0;
        int len = stones.length;
        parent = new int[len];
        for(int i = 0; i < len; i++){
            parent[i] = i;
        }
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
                    union(i, j);
                }
            }
        }
        for(int i = 0; i < len; i++){
           if(parent[i] == i){
               islands++;
           }
        }
        return len - islands;
    }

    private void union(int a, int b){
        int a_root = findRoot(a);
        int b_root = findRoot(b);
        if(a_root != b_root){
            parent[a_root] = b_root;
        }
        return;
    }

    private int findRoot(int x) {
        int root = x;
        while(parent[root] != root){
            root = parent[root];
        }
        while(parent[x] != root){
            int father = parent[x];
            parent[x] = root;
            x = father;
        }
        return root;
    }
}
