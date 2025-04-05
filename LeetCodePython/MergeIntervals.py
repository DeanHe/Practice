"""
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Constraints:
1 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^4

Analysis:
TC: O(NlogN)
"""
import math
from collections import defaultdict
from typing import List


class MergeIntervals:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        if len(intervals) < 2:
            return intervals
        res = []
        intervals.sort()
        pre = intervals[0]
        for i in range(1, len(intervals)):
            cur = intervals[i]
            if pre[1] < cur[0]:
                res.append(pre)
                pre = cur
            else:
                pre[1] = max(pre[1], cur[1])
        res.append(pre)
        return res

    def mergeSweepline(self, intervals: List[List[int]]) -> List[List[int]]:
        if len(intervals) < 2:
            return intervals
        res = []
        cur = 0
        axis = defaultdict(int)
        for s, e in intervals:
            axis[s] += 1
            axis[e] -= 1
        s = math.inf
        e = -math.inf
        for i in sorted(axis.keys()):
            cur += axis[i]
            s = min(s, i)
            e = max(e, i)
            if cur == 0:
                res.append([s, e])
                s = math.inf
                e = -math.inf
        return res

