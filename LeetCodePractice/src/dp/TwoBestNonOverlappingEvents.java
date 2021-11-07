package dp;

import java.util.Arrays;
import java.util.TreeMap;

/*
You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.

Return this maximum sum.

Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.



Example 1:


Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
Example 2:

Example 1 Diagram
Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.
Example 3:


Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.


Constraints:

2 <= events.length <= 10^5
events[i].length == 3
1 <= startTime_i <= endTime_i <= 10^9
1 <= value_i <= 10^6

hint 1: How can sorting the events on the basis of their start times help? How about end times?
hint 2: How can we quickly get the maximum score of an interval not intersecting with the interval we chose?

analysis:
similar to MaximumProfitJobScheduling
sort by event end time, dp[i] means the max value can get by single event end time i
TC O(N log N)
 */
public class TwoBestNonOverlappingEvents {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        int res = 0;
        for(int[] event : events){
            int s = event[0];
            int e = event[1];
            int val = event[2];
            res = Math.max(res, dp.lowerEntry(s).getValue() + val);
            if(val > dp.lastEntry().getValue()){
                dp.put(e, val);
            }
        }
        return res;
    }
}
