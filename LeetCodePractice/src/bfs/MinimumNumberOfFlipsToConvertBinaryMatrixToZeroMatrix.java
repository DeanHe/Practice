package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.
Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
Binary matrix is a matrix with all cells equal to 0 or 1 only.
Zero matrix is a matrix with all cells equal to 0.

Example 1:
Input: mat = [[0,0],[0,1]]
Output: 3
Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
Example 2:

Input: mat = [[0]]
Output: 0
Explanation: Given matrix is a zero matrix. We don't need to change it.

Example 3:
Input: mat = [[1,1,1],[1,0,1],[0,0,0]]
Output: 6

Example 4:
Input: mat = [[1,0,0],[1,0,0]]
Output: -1
Explanation: Given matrix can't be a zero matrix


Constraints:
m == mat.length
n == mat[0].length
1 <= m <= 3
1 <= n <= 3
mat[i][j] is 0 or 1.

hint:
1 Flipping same index two times is like not flipping it at all. Each index can be flipped one time. Try all possible combinations. O(2^(n*m)).

analysis:
bitmask bfs
TC: O(m * n * 2 ^ (m * n)), SC: O(2 ^ (m * n)).
*/
public class MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix {
    public int minFlips(int[][] mat) {
        int[] dirs = {0, 0, 1, 0, -1, 0};
        int rows = mat.length;
        int cols = mat[0].length;
        int start = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // convert the matrix to an int.
                start |= mat[r][c] << (r * cols + c);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        Set<Integer> visited = new HashSet<>();
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (cur == 0) {
                    return step;
                }
                // traverse all rows * cols bits of cur.
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < cols; c++) {
                        int next = cur;
                        for (int j = 0; j < dirs.length - 1; j++) {
                            int nb_r = r + dirs[j];
                            int nb_c = c + dirs[j + 1];
                            // flip the cell (r, c) and its neighbors.
                            if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols) {
                                next = next ^ (1 << (nb_r * cols + nb_c));
                            }
                        }
                        if (!visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
