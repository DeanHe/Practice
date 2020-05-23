package HashMap;

/*
Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from these points, with sides not necessarily parallel to the x and y axes.

        If there isn't any rectangle, return 0.

        Example 1:


        Input: [[1,2],[2,1],[1,0],[0,1]]
        Output: 2.00000
        Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.
        Example 2:



        Input: [[0,1],[2,1],[1,1],[1,0],[2,0]]
        Output: 1.00000
        Explanation: The minimum area rectangle occurs at [1,0],[1,1],[2,1],[2,0], with an area of 1.
        Example 3:



        Input: [[0,3],[1,2],[3,1],[1,3],[2,1]]
        Output: 0
        Explanation: There is no possible rectangle to form from these points.
        Example 4:



        Input: [[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]
        Output: 2.00000
        Explanation: The minimum area rectangle occurs at [2,1],[2,3],[3,3],[3,1], with an area of 2.


        Note:

        1 <= points.length <= 50
        0 <= points[i][0] <= 40000
        0 <= points[i][1] <= 40000
        All points are distinct.
        Answers within 10^-5 of the actual value will be accepted as correct.
*/

import java.util.*;

public class MinimumAreaRectangleII {
    public double minAreaFreeRect(int[][] points) {
        if (points.length < 4) {
            return 0;
        }
        double res = Double.MAX_VALUE;
        Map<String, List<int[]>> diagMap = new HashMap<>(); // diag : points pair index
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                double center_x = (p1[0] + p2[0]) / 2.0;
                double center_y = (p1[1] + p2[1]) / 2.0;
                double diagLen = (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
                String key = diagLen + "_" + center_x + "_" + center_y;
                diagMap.putIfAbsent(key, new ArrayList<>());
                diagMap.get(key).add(new int[]{i, j});
            }
        }
        for (String key : diagMap.keySet()) {
            List<int[]> ls = diagMap.get(key);
            if (ls.size() > 1) { // there are two valid points pairs to form rectangle
                for (int i = 0; i < ls.size(); i++) {
                    for (int j = i + 1; j < ls.size(); j++) {
                        int p1_idx = ls.get(i)[0];
                        int p2_idx = ls.get(i)[1];
                        int p3_idx = ls.get(j)[0];
                        int[] p1 = points[p1_idx];
                        int[] p2 = points[p2_idx];
                        int[] p3 = points[p3_idx];
                        double len1 = Math.sqrt((p1[0] - p3[0]) * (p1[0] - p3[0]) + (p1[1] - p3[1]) * (p1[1] - p3[1]));
                        double len2 = Math.sqrt((p2[0] - p3[0]) * (p2[0] - p3[0]) + (p2[1] - p3[1]) * (p2[1] - p3[1]));
                        res = Math.min(res, len1 * len2);
                    }
                }
            }
        }
        return res != Double.MAX_VALUE ? res : 0;
    }
}
