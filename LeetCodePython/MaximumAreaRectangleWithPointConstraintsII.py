"""
There are n points on an infinite plane. You are given two integer arrays xCoord and yCoord where (xCoord[i], yCoord[i]) represents the coordinates of the ith point.

Your task is to find the maximum area of a rectangle that:

Can be formed using four of these points as its corners.
Does not contain any other point inside or on its border.
Has its edges parallel to the axes.
Return the maximum area that you can obtain or -1 if no such rectangle is possible.

Example 1:
Input: xCoord = [1,1,3,3], yCoord = [1,3,1,3]
Output: 4
Explanation:
Example 1 diagram
We can make a rectangle with these 4 points as corners and there is no other point that lies inside or on the border. Hence, the maximum possible area would be 4.

Example 2:
Input: xCoord = [1,1,3,3,2], yCoord = [1,3,1,3,2]
Output: -1
Explanation:
Example 2 diagram
There is only one rectangle possible is with points [1,1], [1,3], [3,1] and [3,3] but [2,2] will always lie inside it. Hence, returning -1.

Example 3:
Input: xCoord = [1,1,3,3,1,3], yCoord = [1,3,1,3,2,2]
Output: 2
Explanation:
Example 3 diagram
The maximum area rectangle is formed by the points [1,3], [1,2], [3,2], [3,3], which has an area of 2. Additionally, the points [1,1], [1,2], [3,1], [3,2] also form a valid rectangle with the same area.


Constraints:
1 <= xCoord.length == yCoord.length <= 2 * 10^5
0 <= xCoord[i], yCoord[i] <= 8 * 10^7
All the given points are unique.

hints:
1 Process the points by sorting them based on their x-coordinates.
2 For each x-coordinate, sort the corresponding points by y and select two consecutive points y1 and y2 (y1 < y2).
3 Identify the closest x-coordinate (greater than the current x) where some y-coordinates lie in [y1, y2].
4 Use a segment tree to efficiently locate the nearest x-coordinate.
5 Check if the points form a valid rectangle. How?

Analysis:
To identify an axis-aligned rectangle with no other points on the border:
pre_x == x and max_x_for_y_val[pre_y] == max_x_for_y_val[y].
Top and bottom borders: y and pre_y;
Left and right borders: max_x_for_y_val[y] and x.

Binary Index Tree:
TC: O(NlogN)
"""
import math
from typing import List

class SegTree:
    def __init__(self, n: int):
        self.n = 1 << n.bit_length()
        self.tree = [-math.inf] * (self.n << 1)

    def update(self, idx: int, val: int):
        idx += self.n
        while idx:
            self.tree[idx] = val
            idx >>= 1

    def query(self, l: int, r: int) -> int:
        res = -math.inf
        l += self.n
        r += self.n
        while r-l > 1:
            if not l & 1:
                res = max(res, self.tree[l + 1])
            if r&1:
                res = max(res, self.tree[r - 1])
            l >>= 1
            r >>= 1
        return res

class MaximumAreaRectangleWithPointConstraintsII:
    def maxRectangleArea(self, xCoord: List[int], yCoord: List[int]) -> int:

        points = list(zip(xCoord, yCoord))
        points.sort()
        y_axis = sorted(set(yCoord))
        sg_y = SegTree(len(y_axis))
        y_pos = {y: i for i, y in y_axis}
        last_x_for_y_val = {y: -math.inf for y in y_axis}
        pre_x, pre_y = points[0]
        res = -1

        for x, y in points[1:]:
            if pre_x == x and last_x_for_y_val[pre_y] == last_x_for_y_val[y] \
                    and sg_y.query(y_pos[pre_y], y_pos[y]) < last_x_for_y_val[y]:
                res = max(res, (x - last_x_for_y_val[y]) * (y - pre_y))
            last_x_for_y_val[pre_y] = pre_x
            sg_y.update(y_pos[pre_y], pre_x)
            pre_x, pre_y = x, y
        return res
