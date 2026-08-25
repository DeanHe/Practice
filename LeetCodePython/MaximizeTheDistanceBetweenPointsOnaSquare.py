"""
You are given an integer side, representing the edge length of a square with corners at (0, 0), (0, side), (side, 0), and (side, side) on a Cartesian plane.

You are also given a positive integer k and a 2D integer array points, where points[i] = [xi, yi] represents the coordinate of a point lying on the boundary of the square.

You need to select k elements among points such that the minimum Manhattan distance between any two points is maximized.

Return the maximum possible minimum Manhattan distance between the selected k points.

The Manhattan Distance between two cells (xi, yi) and (xj, yj) is |xi - xj| + |yi - yj|.

Example 1:
Input: side = 2, points = [[0,2],[2,0],[2,2],[0,0]], k = 4
Output: 2

Explanation:
Select all four points.

Example 2:
Input: side = 2, points = [[0,0],[1,2],[2,0],[2,2],[2,1]], k = 4
Output: 1

Explanation:
Select the points (0, 0), (2, 0), (2, 2), and (2, 1).

Example 3:
Input: side = 2, points = [[0,0],[0,1],[0,2],[1,2],[2,0],[2,2],[2,1]], k = 5
Output: 1

Explanation:
Select the points (0, 0), (0, 1), (0, 2), (1, 2), and (2, 2).

Constraints:
1 <= side <= 10^9
4 <= points.length <= min(4 * side, 15 * 10^3)
points[i] == [xi, yi]
The input is generated such that:
points[i] lies on the boundary of the square.
All points[i] are unique.
4 <= k <= min(25, points.length)

hints:
1 Can we use binary search for this problem?
2 Think of the coordinates on a straight line in clockwise order.
3 Binary search on the minimum Manhattan distance x.
4 During the binary search, for each coordinate, find the immediate next coordinate with distance >= x.

analysis:
Can we select k numbers from a one-dimensional array arr such that any two adjacent elements differ by at least x,
and the difference between the last and first numbers is at most side⋅4−x;

TC: O(N*K*logN*log(side)).
Sorting takes O(NlogN). Each feasibility check takes O(N*K*logN), and binary search runs for at most log(side) iterations.
"""
import bisect
from typing import List


class MaximizeTheDistanceBetweenPointsOnaSquare:
    def maxDistance(self, side: int, points: List[List[int]], k: int) -> int:
        ls = []
        for x, y in points:
            if x == 0:
                ls.append(y)
            elif y == side:
                ls.append(side + x)
            elif x == side:
                ls.append(3 * side - y)
            else:
                ls.append(4 * side - x)
        ls.sort()

        def validate(limit):
            for start in ls:
                end = start + 4 * side - limit
                cur = start
                for _ in range(k - 1):
                    i = bisect.bisect_left(ls, cur + limit)
                    if i == len(ls) or ls[i] > end:
                        cur = -1
                        break
                    cur = ls[i]
                if cur >= 0:
                    return True
            return False

        s, e = 1, side
        while s + 1 < e:
            mid = (s + e) // 2
            if validate(mid):
                s = mid
            else:
                e = mid
        if validate(e):
            return e
        if validate(s):
            return s
        return 0
