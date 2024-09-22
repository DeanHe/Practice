"""
Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.

Example 1:
Input: timePoints = ["23:59","00:00"]
Output: 1

Example 2:
Input: timePoints = ["00:00","23:59","00:00"]
Output: 0

Constraints:
2 <= timePoints.length <= 2 * 10^4
timePoints[i] is in the format "HH:MM".

analysis:
Bucket Sort
TC: O(N)
"""
import math
from typing import List

class MinimumTimeDifference:
    def findMinDifference(self, timePoints: List[str]) -> int:
        minutes = [False] * (24 * 60)
        for time in timePoints:
            h, m = map(int, time.split(":"))
            minute = h * 60 + m
            if minutes[minute]:
                return 0
            else:
                minutes[minute] = True
        pre = -1
        first = -1
        last = -1
        res = math.inf
        for i in range(24 * 60):
            if minutes[i]:
                if pre != -1:
                    res = min(res, i - pre)
                if first == -1:
                    first = i
                last = max(last, i)
                pre = i
        res = min(res, 24 * 60 + first - last)
        return res

