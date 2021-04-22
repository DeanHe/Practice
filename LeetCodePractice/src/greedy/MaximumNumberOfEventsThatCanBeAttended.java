package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
        Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
        You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can only attend one event at any time d.
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

        Example 3:
        Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
        Output: 4

        Example 4:
        Input: events = [[1,100000]]
        Output: 1

        Example 5:
        Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
        Output: 7

        Constraints:
        1 <= events.length <= 10^5
        events[i].length == 2
        1 <= events[i][0] <= events[i][1] <= 10^5
*/
public class MaximumNumberOfEventsThatCanBeAttended {
    public int maxEvents(int[][] events) {
        int res = 0, len = events.length, cur = 0;
        Arrays.sort(events, (int[] a, int[] b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int day = 1; day <= 100_000; day++){
            while(!pq.isEmpty() && pq.peek() < day){
                pq.poll();
            }
            while(cur < len && events[cur][0] == day){
                pq.offer(events[cur][1]);
                cur++;
            }
            if(!pq.isEmpty()){
                pq.poll();
                res++;
            }
        }
        return res;
    }
}
