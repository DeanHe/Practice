package sweepLine;

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

analysis:
1. sort the points by y order then x order
2. For the points in the same y, calculate the current x interval sum (like the meeting room problem).
3. In the next y, calculate the area by x_interval_sum * (y - pre_y)

The complexity in the worst case is O(N ^ 2) (all the rectangles have the same x)
*/
public class RectangleAreaII {
    public int rectangleArea(int[][] rectangles) {
        int MOD = (int) (1e9 + 7);
        long res = 0;
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
        TreeMap<Integer, Integer> x_start_cnt = new TreeMap<>(); // x : number of isStart of x
        int x_interval = 0, pre_y = points.get(0).y;
        for (Point p : points) {
            x_start_cnt.put(p.x, x_start_cnt.getOrDefault(p.x, 0) + p.isStart);
            res += (long) x_interval * (p.y - pre_y);
            pre_y = p.y;
            x_interval = cal_x_interval(x_start_cnt);
        }
        return (int) (res % MOD);
    }

    private int cal_x_interval(TreeMap<Integer, Integer> xSpanMap) {
        int res = 0, pre_x = -1, sign = 0; // sign : whether to include by sum isStart
        for (Map.Entry<Integer, Integer> e : xSpanMap.entrySet()) {
            if (pre_x >= 0 && sign > 0) {
                res += e.getKey() - pre_x;
            }
            sign += e.getValue();
            pre_x = e.getKey();
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

    public int rectangleAreaDFS(int[][] rectangles) {
        int MOD = (int) (1e9 + 7);
        long res = 0;
        List<int[]> rectLists = new ArrayList<>();
        for (int[] r : rectangles) {
            addRectangle(rectLists, r, 0);
        }
        for (int[] r : rectLists) {
            int x1 = r[0], y1 = r[1], x2 = r[2], y2 = r[3];
            res += (long) (x2 - x1) * (y2 - y1);
        }
        return (int) (res % MOD);
    }

    private void addRectangle(List<int[]> rectLists, int[] r, int idx) {
        if (idx == rectLists.size()) {
            rectLists.add(r);
            return;
        }
        int[] exist_r = rectLists.get(idx);
        int x1 = r[0], y1 = r[1], x2 = r[2], y2 = r[3];
        int e_x1 = exist_r[0], e_y1 = exist_r[1], e_x2 = exist_r[2], e_y2 = exist_r[3];
        // no overlap
        if (x1 >= e_x2 || x2 <= e_x1 || y1 >= e_y2 || y2 <= e_y1) {
            addRectangle(rectLists, r, idx + 1);
            return;
        }

        if (x1 < e_x1) {
            addRectangle(rectLists, new int[]{x1, y1, e_x1, y2}, idx + 1);
        }
        if (x2 > e_x2) {
            addRectangle(rectLists, new int[]{e_x2, y1, x2, y2}, idx + 1);
        }
        if (y1 < e_y1) {
            addRectangle(rectLists, new int[]{Math.max(x1, e_x1), y1, Math.min(x2, e_x2), e_y1}, idx + 1);
        }
        if (y2 > e_y2) {
            addRectangle(rectLists, new int[]{Math.max(x1, e_x1), e_y2, Math.min(x2, e_x2), y2}, idx + 1);
        }
    }
}
