package UnionFind;

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
-109 <= matrix[row][col] <= 109

Time O(NNlog(MN))
Space O(MN)
 */
public class RankTransformOfaMatrix {
    public int[][] matrixRankTransform(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] res = new int[rows][cols];
        Map<Integer, Integer> nextRankForRows = new HashMap<>(); // For row x, nextRankForRows[x] is the next rank
        Map<Integer, Integer> nextRankForCols = new HashMap<>(); // For col x, nextRankForCols[x] is the next rank
        for(int r = 0; r < rows; r++)
            nextRankForRows.put(r, 1);
        for(int c = 0; c < cols; c++)
            nextRankForCols.put(c, 1);
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                map.putIfAbsent(matrix[r][c], new ArrayList<>());
                map.get(matrix[r][c]).add(new int[]{r, c});
            }
        }
        for(int key : map.keySet()){
            List<int[]> ls = map.get(key);
            UF uf = new UF(ls.size());
            for(int i = 0; i < ls.size(); i++){
                for(int j = i + 1; j  < ls.size(); j++){
                    int r1 = ls.get(i)[0];
                    int c1 = ls.get(i)[1];
                    int r2 = ls.get(j)[0];
                    int c2 = ls.get(j)[1];
                    if(r1 == r2 || c1 == c2){
                        uf.union(i, j);
                    }
                }
            }
            Map<Integer, List<int[]>> all = new HashMap<>();
            for(int i = 0; i < ls.size(); i++){
                int root = uf.findRoot(i);
                all.putIfAbsent(root, new ArrayList<>());
                all.get(root).add(ls.get(i));
            }
            for(int root : all.keySet()){
                int m = 0; // m will be the rank we assign to this all unionList
                List<int[]> sameRowOrCol = all.get(root);
                for(int[] idx : sameRowOrCol){
                    int r = idx[0];
                    int c = idx[1];
                    m = Math.max(m, Math.max(nextRankForRows.get(r), nextRankForCols.get(c)));
                }
                for(int[] idx : sameRowOrCol){
                    int r = idx[0];
                    int c = idx[1];
                    res[r][c] = m;
                    nextRankForRows.put(r, m + 1);
                    nextRankForCols.put(c, m + 1);
                }

            }
        }
        return res;
    }
}
