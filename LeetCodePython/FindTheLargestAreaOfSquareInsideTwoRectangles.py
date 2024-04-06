"""
There exist n rectangles in a 2D plane. You are given two 0-indexed 2D integer arrays bottomLeft and topRight, both of size n x 2, where bottomLeft[i] and topRight[i] represent the bottom-left and top-right coordinates of the ith rectangle respectively.

You can select a region formed from the intersection of two of the given rectangles. You need to find the largest area of a square that can fit inside this region if you select the region optimally.

Return the largest possible area of a square, or 0 if there do not exist any intersecting regions between the rectangles.

Example 1:
Input: bottomLeft = [[1,1],[2,2],[3,1]], topRight = [[3,3],[4,4],[6,6]]
Output: 1
Explanation: A square with side length 1 can fit inside either the intersecting region of rectangle 0 and rectangle 1, or the intersecting region of rectangle 1 and rectangle 2. Hence the largest area is side * side which is 1 * 1 == 1.
It can be shown that a square with a greater side length can not fit inside any intersecting region.

Example 2:
Input: bottomLeft = [[1,1],[2,2],[1,2]], topRight = [[3,3],[4,4],[3,4]]
Output: 1
Explanation: A square with side length 1 can fit inside either the intersecting region of rectangle 0 and rectangle 1, the intersecting region of rectangle 1 and rectangle 2, or the intersection region of all 3 rectangles. Hence the largest area is side * side which is 1 * 1 == 1.
It can be shown that a square with a greater side length can not fit inside any intersecting region.
Note that the region can be formed by the intersection of more than 2 rectangles.

Example 3:
Input: bottomLeft = [[1,1],[3,3],[3,1]], topRight = [[2,2],[4,4],[4,2]]
Output: 0
Explanation: No pair of rectangles intersect, hence, we return 0.

Constraints:
n == bottomLeft.length == topRight.length
2 <= n <= 10^3
bottomLeft[i].length == topRight[i].length == 2
1 <= bottomLeft[i][0], bottomLeft[i][1] <= 10^7
1 <= topRight[i][0], topRight[i][1] <= 10^7
bottomLeft[i][0] < topRight[i][0]
bottomLeft[i][1] < topRight[i][1]
"""
from typing import List


class FindTheLargestAreaOfSquareInsideTwoRectangles:
    def largestSquareArea(self, bottomLeft: List[List[int]], topRight: List[List[int]]) -> int:
        res = 0
        n = len(bottomLeft)
        for i in range(n):
            a_bottom_left_x, a_bottom_left_y = bottomLeft[i]
            a_top_right_x, a_top_right_y = topRight[i]
            for j in range(i + 1, n):
                b_bottom_left_x, b_bottom_left_y = bottomLeft[j]
                b_top_right_x, b_top_right_y = topRight[j]
                if b_top_right_x < a_bottom_left_x or a_top_right_x < b_bottom_left_x or b_top_right_y < a_bottom_left_y or a_top_right_y < b_bottom_left_y:
                    continue
                x_edge = min(a_top_right_x, b_top_right_x) - max(a_bottom_left_x, b_bottom_left_x)
                y_edge = min(a_top_right_y, b_top_right_y) - max(a_bottom_left_y, b_bottom_left_y)
                edge = min(x_edge, y_edge)
                res = max(res, edge * edge)
        return res
