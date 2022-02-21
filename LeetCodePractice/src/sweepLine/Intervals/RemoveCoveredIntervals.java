package sweepLine.Intervals;

import java.util.Arrays;

/*
Given a list of intervals, remove all intervals that are covered by another interval in the list.
Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
After doing so, return the number of remaining intervals.

Example 1:
Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.

Constraints:
1 <= intervals.length <= 1000
0 <= intervals[i][0] < intervals[i][1] <= 10^5
intervals[i] != intervals[j] for all i != j

hint:
1 How to check if an interval is covered by another?
2 Compare each interval to all others and check if it is covered by any interval.

analysis:
we sort on start first. When start are same, we sort end in descending order.
Complexity: time O(NlogN), space O(N)
*/
public class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        int res = 0, endMost = 0;
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        for (int[] interval : intervals) {
            if (endMost < interval[1]) {
                endMost = interval[1];
                res++;
            }
        }
        return res;
    }
}
