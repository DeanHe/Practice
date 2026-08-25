"""
You are given a 2D integer array occupiedIntervals, where occupiedIntervals[i] = [starti, endi] represents a time interval during which you are occupied. Each interval starts at starti and ends at endi, inclusive. These intervals may overlap.

You are also given two integers freeStart and freeEnd, which define a free time interval from freeStart to freeEnd, inclusive.

Your task is to merge all occupied intervals that overlap or touch, then remove all integer points in the free interval from the merged occupied intervals.

Two intervals touch if the second interval starts immediately after the first one ends. For example, [1, 1] and [2, 2] touch and should be merged into [1, 2].

Return the remaining occupied intervals in sorted order. The returned intervals must be non-overlapping and must contain the minimum number of intervals possible. If there are no remaining occupied points, return an empty list.

Example 1:
Input: occupiedIntervals = [[2,6],[4,8],[10,10],[10,12],[14,16]], freeStart = 7, freeEnd = 11
Output: [[2,6],[12,12],[14,16]]

Explanation:
After merging, the occupied intervals are [2, 8], [10, 12], and [14, 16].
Excluding the free interval [7, 11] results in [2, 6], [12, 12], and [14, 16].

Example 2:
Input: occupiedIntervals = [[1,5],[2,3]], freeStart = 3, freeEnd = 8
Output: [[1,2]]

Explanation:
After merging, the occupied interval is [1, 5].
Excluding the free interval [3, 8] results in [1, 2].

Constraints:
1 <= occupiedIntervals.length <= 5 * 10^4
occupiedIntervals[i].length == 2
1 <= starti <= endi <= 10^9
1 <= freeStart <= freeEnd <= 10^9

hints:
1 Sort the occupied intervals by start time.
2 While merging, two intervals should be combined if the next interval starts at most one point after the current interval ends.
3 After merging, remove [freeStart, freeEnd] from each merged interval independently.
4 An interval may disappear completely, shrink on one side, remain unchanged, or split into two intervals.
"""
from typing import List


class FilterOccupiedIntervals:
    def filterOccupiedIntervals(self, occupiedIntervals: List[List[int]], freeStart: int, freeEnd: int) -> List[List[int]]:
        res = []
        merged = []
        occupiedIntervals.sort(key=lambda x: x[0])
        pre = occupiedIntervals[0]
        for i in range(1, len(occupiedIntervals)):
            cur = occupiedIntervals[i]
            if pre[1] + 1 < cur[0]:
                merged.append(pre)
                pre = cur
            else:
                pre[1] = max(pre[1], cur[1])
        merged.append(pre)
        for cur in merged:
            if cur[1] < freeStart or freeEnd < cur[0]:
                res.append(cur)
            else:
                if cur[0] < freeStart:
                    res.append([cur[0], freeStart - 1])
                if freeEnd < cur[1]:
                    res.append([freeEnd + 1, cur[1]])
        return res




