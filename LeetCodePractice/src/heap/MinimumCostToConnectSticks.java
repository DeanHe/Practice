package heap;

import java.util.PriorityQueue;

/*
You have some sticks with positive integer lengths.
You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.
You perform this action until there is one stick remaining.
Return the minimum cost of connecting all the given sticks into one stick in this way.

Example 1:

Input: sticks = [2,4,3]
Output: 14
Example 2:

Input: sticks = [1,8,3,5]
Output: 30
Constraints:

1 <= sticks.length <= 10^4
1 <= sticks[i] <= 10^4
 */
public class MinimumCostToConnectSticks {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        int res = 0;
        for (int n : sticks) {
            pq.offer(n);
        }
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            int cost = a + b;
            res += cost;
            pq.offer(cost);
        }
        return res;
    }
}
