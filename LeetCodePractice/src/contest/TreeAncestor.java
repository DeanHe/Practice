package contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given a tree with n nodes numbered from 0 to n-1 in the form of a parent array where parent[i] is the parent of node i. The root of the tree is node 0.

Implement the function getKthAncestor(int node, int k) to return the k-th ancestor of the given node. If there is no such ancestor, return -1.

The k-th ancestor of a tree node is the k-th node in the path from that node to the root.


Example:



Input:
["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
[[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]

Output:
[null,1,0,-1]

Explanation:
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);

treeAncestor.getKthAncestor(3, 1);  // returns 1 which is the parent of 3
treeAncestor.getKthAncestor(5, 2);  // returns 0 which is the grandparent of 5
treeAncestor.getKthAncestor(6, 3);  // returns -1 because there is no such ancestor


Constraints:

1 <= k <= n <= 5*10^4
parent[0] == -1 indicating that 0 is the root node.
0 <= parent[i] < n for all 0 < i < n
0 <= node < n
There will be at most 5*10^4 queries.
 */
public class TreeAncestor {
    // dp[i][j] stores (2^j)th ancestor of node i
    int[][] dp = new int[50001][30];
    List<List<Integer>> tree = new ArrayList<>();
    public TreeAncestor(int n, int[] parent) {
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }
        for(int i = 0; i < parent.length; i++){
            tree.add(new ArrayList<>());
        }
        // Step 1: Reconstruct tree in correct format, root to leaf
        for(int i = 0; i < parent.length; i++){
            if(parent[i] >= 0){
                tree.get(parent[i]).add(i);
            }
        }
        // Step 2: Prefill dp[i][j]
        dfs(0, new ArrayList<>());
    }

    private void dfs(int cur, ArrayList<Integer> parentList) {
        int i = 0, len = parentList.size();
        while((1 << i) <= len){
            dp[cur][i] = parentList.get(len - (1 << i));
            i++;
        }
        parentList.add(cur);
        for(int child : tree.get(cur)){
            dfs(child, parentList);
        }
        parentList.remove(parentList.size() - 1);
    }

    public int getKthAncestor(int node, int k) {
        int i = 0;
        while(k > 0){
            if(k % 2 == 1){
                node = dp[node][i];
                if(node == -1){
                    return -1;
                }
            }
            k /= 2;
            i++;
        }
        return node;
    }

}
/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
