package DP;
/*A car travels from a starting position to a destination which is target miles east of the starting position.

Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.

The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.

When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.

What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.

Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.

 

Example 1:

Input: target = 1, startFuel = 1, stations = []
Output: 0
Explanation: We can reach the target without refueling.
Example 2:

Input: target = 100, startFuel = 1, stations = [[10,100]]
Output: -1
Explanation: We can't reach the target (or even the first gas station).
Example 3:

Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
Output: 2
Explanation: 
We start with 10 liters of fuel.
We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
We made 2 refueling stops along the way, so we return 2.
 

Note:

1 <= target, startFuel, stations[i][1] <= 10^9
0 <= stations.length <= 500
0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target*/

import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumNumberOfRefuelingStops {
	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		// mem[t] means the furthest distance that we can get with t times of
		// refueling.
		long[] dp = new long[stations.length + 1];
		dp[0] = startFuel;
		for (int k = 0; k < stations.length; k++) {
			for (int i = k; i >= 0; i--) {
				if (dp[i] >= stations[k][0]) {
					dp[i + 1] = Math.max(dp[i + 1], dp[i] + stations[k][1]);
				}
			}
		}
		for (int i = 0; i <= dp.length; i++) {
			if (dp[i] >= target) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * i is the index of next stops to refuel. res is the times that we have
	 * refeuled. pq is a priority queue that we store all available gas.
	 * 
	 * We initial res = 0 and in every loop:
	 * 
	 * We add all reachable stop to priority queue. We pop out the largest gas
	 * from pq and refeul once. If we can't refuel, means that we can not go
	 * forward and return -1
	 */ public int minRefuelStopsWithPriorityQueue(int target, int startFuel, int[][] stations) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int times, i = 0, cur = startFuel;
		for (times = 0; cur < target; times++) {
			while (i < stations.length && cur >= stations[i][0]) {
				pq.offer(stations[i][1]);
				i++;
			}
			if (pq.isEmpty()) {
				return -1;
			}
			cur += pq.poll();
		}
		return times;
	}
}
