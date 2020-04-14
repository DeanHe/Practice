package SweepLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
        We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1, y1, x2, y2],
        where (x1, y1) are the coordinates of the bottom-left corner,
        and (x2, y2) are the coordinates of the top-right corner of the ith rectangle.
        Find the total area covered by all rectangles in the plane.
        Since the answer may be too large, return it modulo 10^9 + 7.

        Example 1:
        Input: [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
        Output: 6
        Explanation: As illustrated in the picture.

        Example 2:
        Input: [[0,0,1000000000,1000000000]]
        Output: 49
        Explanation: The answer is 10^18 modulo (10^9 + 7), which is (10^9)^2 = (-7)^2 = 49.
        Note:

        1 <= rectangles.length <= 200
        rectanges[i].length = 4
        0 <= rectangles[i][j] <= 10^9
        The total area covered by all rectangles will never exceed 2^63 - 1 and thus will fit in a 64-bit signed integer.
*/
public class RectangleAreaII {
    public int rectangleArea(int[][] rectangles) {
        int MOD = (int) (1e9 + 7);
        int res = 0;
        List<Point> points = new ArrayList<>();
        for (int[] rect : rectangles) {
            int x1 = rect[0];
            int y1 = rect[1];
            int x2 = rect[2];
            int y2 = rect[3];
            points.add(new Point(x1, y1, 1));
            points.add(new Point(x2, y1, -1));
            points.add(new Point(x1, y2, -1)); // tricky
            points.add(new Point(x2, y2, 1)); // tricky
        }
        Collections.sort(points, (a, b) -> {
            if (a.y == b.y) {
                return a.x - b.x;
            }
            return a.y - b.y;
        });
        TreeMap<Integer, Integer> xSpanMap = new TreeMap<>(); // x : isStart of x
        int preXspan = -1, preY = -1;
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            xSpanMap.put(p.x, xSpanMap.getOrDefault(p.x, 0) + p.isStart);
            if (i == points.size() - 1 || points.get(i + 1).y > p.y) {
                if (preXspan > 0 && preY >= 0) {
                    res += ((long) preXspan * (p.y - preY)) % MOD;
                    res %= MOD;
                }
                preY = p.y;
                preXspan = getXspan(xSpanMap);
            }
        }
        return res;
    }

    private int getXspan(TreeMap<Integer, Integer> xSpanMap) {
        int res = 0, preX = -1, sign = 0; // sign : whether to include by sum isStart
        for (Map.Entry<Integer, Integer> e : xSpanMap.entrySet()) {
            if (preX >= 0 && sign > 0) {
                res += e.getKey() - preX;
            }
            sign += e.getValue();
            preX = e.getKey();
        }
        return res;
    }

    class Point {
        int x, y, isStart;

        Point(int x, int y, int isStart) {
            this.x = x;
            this.y = y;
            this.isStart = isStart;
        }
    }
}
