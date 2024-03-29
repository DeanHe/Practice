package sweepLine.Intervals;

import java.util.*;

/*
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Example

Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

Example 2:
Input: [ [1,2], [1,2], [1,2] ]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.

Example 3:
Input: [ [1,2], [2,3] ]
Output: 0

Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
Notice
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

analysis:
The heuristic is: always pick the interval with the earliest end time.
Actually, the problem is the same as "Given a collection of intervals, 
find the maximum number of intervals that are non-overlapping." (the classic greedy problem: Interval Scheduling).
With the solution to that problem, guess how do we get the minimum number of intervals to remove? : )

*/
public class NonOverlappingIntervals {

    /**
     * @param intervals: a collection of intervals
     * @return: the minimum number of intervals you need to remove
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int overlap = 0;
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int preEnd = Integer.MIN_VALUE;
        for (int[] cur : intervals) {
            if (cur[0] < preEnd) {
                overlap++;
            } else {
                preEnd = cur[1];
            }
        }
        return overlap;
    }

    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        int nonOverlapCount = 1;
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        int minEnd = intervals[0][1];
        for (int[] cur : intervals) {
            if (minEnd <= cur[0]) {
                nonOverlapCount++;
                minEnd = cur[1];
            } else {
                minEnd = Math.min(minEnd, cur[1]);
            }
        }
        return intervals.length - nonOverlapCount;
    }
}
