package hashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/*
Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.



Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
 */
public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        int area = Integer.MAX_VALUE;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] point : points){
            int x = point[0];
            int y = point[1];
            map.computeIfAbsent(x, z -> new HashSet<>()).add(y);
        }
        for(int i = 0; i < points.length; i++){
            for(int j = i + 1; j < points.length; j++){
                int[] p1 = points[i];
                int[] p2 = points[j];
                if(p1[0] == p2[0] || p1[1] == p2[1]){
                    continue;
                }
                if(map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])){
                    area = Math.min(area, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                }
            }
        }
        return area == Integer.MAX_VALUE ? 0 : area;
    }
}
