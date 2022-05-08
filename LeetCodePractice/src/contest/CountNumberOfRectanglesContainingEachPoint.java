package contest;

import sweepLine.Intervals.Interval;

import java.util.*;

/*
You are given a 2D integer array rectangles where rectangles[i] = [li, hi] indicates that ith rectangle has a length of li and a height of hi. You are also given a 2D integer array points where points[j] = [xj, yj] is a point with coordinates (xj, yj).

The ith rectangle has its bottom-left corner point at the coordinates (0, 0) and its top-right corner point at (li, hi).

Return an integer array count of length points.length where count[j] is the number of rectangles that contain the jth point.

The ith rectangle contains the jth point if 0 <= xj <= li and 0 <= yj <= hi. Note that points that lie on the edges of a rectangle are also considered to be contained by that rectangle.



Example 1:


Input: rectangles = [[1,2],[2,3],[2,5]], points = [[2,1],[1,4]]
Output: [2,1]
Explanation:
The first rectangle contains no points.
The second rectangle contains only the point (2, 1).
The third rectangle contains the points (2, 1) and (1, 4).
The number of rectangles that contain the point (2, 1) is 2.
The number of rectangles that contain the point (1, 4) is 1.
Therefore, we return [2, 1].
Example 2:


Input: rectangles = [[1,1],[2,2],[3,3]], points = [[1,3],[1,1]]
Output: [1,3]
Explanation:
The first rectangle contains only the point (1, 1).
The second rectangle contains only the point (1, 1).
The third rectangle contains the points (1, 3) and (1, 1).
The number of rectangles that contain the point (1, 3) is 1.
The number of rectangles that contain the point (1, 1) is 3.
Therefore, we return [1, 3].


Constraints:

1 <= rectangles.length, points.length <= 5 * 10^4
rectangles[i].length == points[j].length == 2
1 <= li, xj <= 10^9
1 <= hi, yj <= 100
All the rectangles are unique.
All the points are unique.

hint:
1 The heights of the rectangles and the y-coordinates of the points are only at most 100, so for each point,
we can iterate over the possible heights of the rectangles that contain a given point.
2 For a given point and height, can we efficiently count how many rectangles with that height contain our point?
3 Sort the rectangles at each height and use binary search.

TC O(M logN)
 */
public class CountNumberOfRectanglesContainingEachPoint {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int[] res = new int[points.length];
        TreeMap<Integer, List<Integer>> yAxis = new TreeMap<>();
        Arrays.sort(rectangles, (a, b) -> a[0] - b[0]);
        for (int[] rect : rectangles) {
            int width = rect[0];
            int height = rect[1];
            yAxis.computeIfAbsent(height, x -> new ArrayList<>()).add(width);
        }
        for (int i = 0; i < points.length; i++) {
            int px = points[i][0];
            int py = points[i][1];
            int cnt = 0;
            for (List<Integer> xList : yAxis.subMap(py, true, Integer.MAX_VALUE, true).values()) {
                int found = Collections.binarySearch(xList, px);
                if (found >= 0) {
                    cnt += xList.size() - found;
                } else {
                    cnt += xList.size() + found + 1;
                }
            }
            res[i] = cnt;
        }
        return res;
    }
}
