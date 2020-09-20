package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?

Example 1:

Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.
Notes:

1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1
 */
public class ImageOverlap {
    public int largestOverlap(int[][] A, int[][] B) {
        int rows = A.length, cols = A[0].length, res = 0;
        Map<String, Integer> map = new HashMap<>();
        List<int[]> A_points = new ArrayList<>();
        List<int[]> B_points = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (A[r][c] == 1) {
                    A_points.add(new int[]{r, c});
                }
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (B[r][c] == 1) {
                    B_points.add(new int[]{r, c});
                }
            }
        }
        for (int[] A_point : A_points) {
            for (int[] B_point : B_points) {
                String key = (A_point[0] - B_point[0]) + "_" + (A_point[1] - B_point[1]);
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        for (int val : map.values()) {
            res = Math.max(res, val);
        }
        return res;
    }
}
