"""
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
"""
import collections
from typing import List


class MaxPointsOnaLine:
    def maxPoints(self, points: List[List[int]]) -> int:
        res = 0
        slopes = collections.defaultdict(dict)

        def gcd(a, b):
            if a == 0:
                return b
            return gcd(b % a, a)

        for i in range(len(points)):
            slopes.clear()
            overlap, most = 0, 0
            for j in range(i):
                x_diff = points[i][0] - points[j][0]
                y_diff = points[i][1] - points[j][1]
                if x_diff == 0 and y_diff == 0:
                    overlap += 1
                    continue
                divisor = gcd(x_diff, y_diff)
                if divisor != 0:
                    x_diff /= divisor
                    y_diff /= divisor
                if x_diff in slopes:
                    if y_diff in slopes[x_diff]:
                        slopes[x_diff][y_diff] += 1
                    else:
                        slopes[x_diff][y_diff] = 1
                else:
                    slopes[x_diff] = {y_diff: 1}
                most = max(most, slopes[x_diff][y_diff])
            res = max(res, most + overlap + 1)
        return res
