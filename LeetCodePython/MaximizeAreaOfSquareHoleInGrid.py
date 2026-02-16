"""
You are given the two integers, n and m and two integer arrays, hBars and vBars. The grid has n + 2 horizontal and m + 2 vertical bars, creating 1 x 1 unit cells. The bars are indexed starting from 1.

You can remove some of the bars in hBars from horizontal bars and some of the bars in vBars from vertical bars. Note that other bars are fixed and cannot be removed.

Return an integer denoting the maximum area of a square-shaped hole in the grid, after removing some bars (possibly none).

Example 1:
Input: n = 2, m = 1, hBars = [2,3], vBars = [2]
Output: 4

Explanation:
The left image shows the initial grid formed by the bars. The horizontal bars are [1,2,3,4], and the vertical bars are [1,2,3].

One way to get the maximum square-shaped hole is by removing horizontal bar 2 and vertical bar 2.

Example 2:
Input: n = 1, m = 1, hBars = [2], vBars = [2]
Output: 4

Explanation:
To get the maximum square-shaped hole, we remove horizontal bar 2 and vertical bar 2.

Example 3:
Input: n = 2, m = 3, hBars = [2,3], vBars = [2,4]
Output: 4

Explanation:
One way to get the maximum square-shaped hole is by removing horizontal bar 3, and vertical bar 4.


Constraints:
1 <= n <= 10^9
1 <= m <= 10^9
1 <= hBars.length <= 100
2 <= hBars[i] <= n + 1
1 <= vBars.length <= 100
2 <= vBars[i] <= m + 1
All values in hBars are distinct.
All values in vBars are distinct.

hints:
1 Sort hBars and vBars and consider them separately.
2 Compute the longest sequence of consecutive integer values in each array, denoted as [hx, hy] and [vx, vy], respectively.
3 The maximum square length we can get is min(hy - hx + 2, vy - vx + 2).
4 Square the maximum square length to get the area.

analysis:
TC:O(HlogH + VlogV)
"""
from typing import List


class MaximizeAreaOfSquareHoleInGrid:
    def maximizeSquareHoleArea(self, n: int, m: int, hBars: List[int], vBars: List[int]) -> int:
        hBars.sort()
        vBars.sort()
        h_max, v_max = 1, 1
        h_cur, v_cur = 1, 1
        for i in range(1, len(hBars)):
            if hBars[i - 1] + 1 == hBars[i]:
                h_cur += 1
            else:
                h_cur = 1
            h_max = max(h_max, h_cur)
        for i in range(1, len(vBars)):
            if vBars[i - 1] + 1 == vBars[i]:
                v_cur += 1
            else:
                v_cur = 1
            v_max = max(v_max, v_cur)
        side = min(h_max, v_max) + 1
        return side * side
