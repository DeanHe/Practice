package unionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].

The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:

The rank is an integer starting from 1.
If two elements p and q are in the same row or column, then:
If p < q then rank(p) < rank(q)
If p == q then rank(p) == rank(q)
If p > q then rank(p) > rank(q)
The rank should be as small as possible.
It is guaranteed that answer is unique under the given rules.



Example 1:


Input: matrix = [[1,2],[3,4]]
Output: [[1,2],[2,3]]
Explanation:
The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
Example 2:


Input: matrix = [[7,7],[7,7]]
Output: [[1,1],[1,1]]
Example 3:


Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
Example 4:


Input: matrix = [[7,3,6],[1,4,5],[9,8,2]]
Output: [[5,1,4],[1,2,3],[6,3,1]]


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 500
-109 <= matrix[row][col] <= 10^9

Time O(NNlog(MN))
Space O(MN)
 */
public class RankTransformOfaMatrix {
    public int[][] matrixRankTransform(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] res = new int[rows][cols];
        int[] rank = new int[rows + cols];
        TreeMap<Integer, List<int[]>> valMap = new TreeMap<>();
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                valMap.computeIfAbsent(matrix[r][c], x-> new ArrayList<>()).add(new int[]{r, c});
            }
        }
        for(int key : valMap.keySet()){
            List<int[]> ls = valMap.get(key);
            UF uf = new UF(rows + cols);
            int[] rankCopy = rank.clone();
            for(int[] cur : ls){
                int r = cur[0];
                int c = cur[1];
                int[] root = uf.union(r, rows + c);
                rankCopy[root[1]] = Math.max(rankCopy[root[0]], rankCopy[root[1]]);
            }
            for(int[] cur : ls){
                int r = cur[0];
                int c = cur[1];
                int root = uf.findRoot(r);
                res[r][c] = rankCopy[root] + 1;
                rank[r] = rankCopy[root] + 1;
                rank[rows + c] = rankCopy[root] + 1;
            }
        }
        return res;
    }

    private class UF {
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

        public int[] union(int a, int b){
            int root_a = findRoot(a);
            int root_b = findRoot(b);
            if(root_a != root_b){
                parent[root_a] = root_b;
            }
            return new int[]{root_a, root_b};
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
}
