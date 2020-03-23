package HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        int area = Integer.MAX_VALUE;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] point : points){
            map.putIfAbsent(point[0], new HashSet<>());
            map.get(point[0]).add(point[1]);
        }
        for(int[] p1 : points){
            for(int[] p2 : points){
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
