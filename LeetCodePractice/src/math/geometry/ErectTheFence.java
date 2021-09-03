package math.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.

You are asked to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed.

Return the coordinates of trees that are exactly located on the fence perimeter.



Example 1:


Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]
Example 2:


Input: points = [[1,2],[2,2],[4,2]]
Output: [[4,2],[2,2],[1,2]]


Constraints:

1 <= points.length <= 3000
points[i].length == 2
0 <= xi, yi <= 100
All the given points are unique.

analysis:
https://algorithmist.com/wiki/Monotone_chain_convex_hull
TC O(N log N)
SC O(N)
 */
public class ErectTheFence {
    public int[][] outerTrees(int[][] points) {
        // sort the point of P by x-coor (case tie, sort by y-coor)
        int len = points.length;
        Arrays.sort(points, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        List<int[]> hull = new ArrayList<>();
        // Build Lower layer of hulls
        for (int i = 0; i < len; i++) {
            int[] point = points[i];
            // sequence of last two points of Lower hulls
            // and the point point[i] does not make a counter-clockwise turn
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), point) > 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(point);
        }
        // Remove last point of list (it's same as first point of  other list).
        hull.remove(hull.size() - 1);
        // Build Upper layer of hulls
        for (int i = len - 1; i >= 0; i--) {
            int[] point = points[i];
            // sequence of last two points of Lower hulls
            // and the point point[i] does not make a counter-clockwise turn
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), point) > 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(point);
        }
        // remove redundant elements from the hull
        return hull.stream().distinct().collect(Collectors.toList()).toArray(new int[0][]);
    }

    // orientation is positive means direction is clockwise
    //            q()
    //          /     \
    //        /         \
    //     p()            \
    //                     r()
    //
    // (q[1] - p[1]) / (q[0] - p[0])   vs  (r[1] - q[1]) / (r[0] - q[0])
    private int orientation(int[] pre, int[] cur, int[] next) {
        return (cur[1] - pre[1]) * (next[0] - cur[0]) - (next[1] - cur[1]) * (cur[0] - pre[0]);
    }
}

