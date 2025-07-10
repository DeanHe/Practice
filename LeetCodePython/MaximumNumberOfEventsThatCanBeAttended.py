"""
You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend one event at any time d.
Return the maximum number of events you can attend.

Example 1:
Input: events = [[1,2],[2,3],[3,4]]
Output: 3
Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.

Example 2:
Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4

Constraints:
1 <= events.length <= 10^5
events[i].length == 2
1 <= startDayi <= endDayi <= 10^5

hints:
1 Sort the events by the start time and in case of tie by the end time in ascending order.
2 Loop over the sorted events. Attend as much as you can and keep the last day occupied. When you try to attend new event keep in mind the first day you can attend a new event in.

Analysis:
Greedy, priority queue track ongoing events end days
TC: O(NlogN)
"""
import heapq
from typing import List


class MaximumNumberOfEventsThatCanBeAttended:
    def maxEvents(self, events: List[List[int]]) -> int:
        max_day = max(e[1] for e in events)
        events.sort()
        pq = []
        res = 0
        i = 0
        for d in range(1, max_day + 1):
            while i < len(events) and events[i][0] <= d:
                heapq.heappush(pq, events[i][1])
                i += 1
            while pq and pq[0] < d:
                heapq.heappop(pq)
            if pq:
                heapq.heappop(pq)
                res += 1
        return res