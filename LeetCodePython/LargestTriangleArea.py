"""
Given an array of points on the X-Y plane points where points[i] = [xi, yi], return the area of the largest triangle that can be formed by any three different points. Answers within 10-5 of the actual answer will be accepted.

Example 1:
Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
Output: 2.00000
Explanation: The five points are shown in the above figure. The red triangle is the largest.

Example 2:
Input: points = [[1,0],[0,0],[0,1]]
Output: 0.50000

Constraints:
3 <= points.length <= 50
-50 <= xi, yi <= 50
All the given points are unique.

Analysis:
Math Shoelace formula
TC: O(N^3)
"""
import itertools
from typing import List


class LargestTriangleArea:
    def largestTriangleArea(self, points: List[List[int]]) -> float:
        def area(a, b, c):
            return 0.5 * abs(a[0] * b[1] + b[0] * c[1] + c[0] * a[1] - a[1] * b[0] - b[1] * c[0] - c[1] * a[0])

        res = 0
        for a, b, c in itertools.combinations(points, 3):
            res = max(res, area(a, b, c))
        return res
