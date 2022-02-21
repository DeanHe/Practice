package math;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

Example 1:
Input: points = [[1,1],[2,2],[3,3]]
Output: 3

Example 2:
Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4

Constraints:

1 <= points.length <= 300
points[i].length == 2
-10^4 <= xi, yi <= 10^4
All the points are unique.

analysis:
map stores x_diff:<y_diff, cnt> for a given point
 */
public class MaxPointsOnaLine {
    public int maxPoints(int[][] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        int res = 0;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int overlap = 0, most = 0;
            for (int j = 0; j < i; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                int divisor = gcd(x, y);
                if (divisor != 0) {
                    x /= divisor;
                    y /= divisor;
                }
                if (map.containsKey(x)) {
                    if (map.get(x).containsKey(y)) {
                        map.get(x).put(y, map.get(x).get(y) + 1);
                    } else {
                        map.get(x).put(y, 1);
                    }
                } else {
                    Map<Integer, Integer> t = new HashMap<>();
                    t.put(y, 1);
                    map.put(x, t);
                }
                // x-y is the slope
                most = Math.max(most, map.get(x).get(y));
            }
            res = Math.max(res, most + overlap + 1);
        }
        return res;
    }

    private int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
