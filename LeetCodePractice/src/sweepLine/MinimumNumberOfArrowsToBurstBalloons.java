package sweepLine;

import java.util.*;

/*
There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 10^4 balloons.
An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example
Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).

solution: 1. greedy
2. template
# sort intervals/pairs in increasing order of the start position.
# Scan the sorted intervals, and maintain an "active set" for overlapping intervals. At most times, we do not need to use an explicit set to store them. Instead, we just need to maintain several key parameters, e.g. the number of overlapping intervals (count), the minimum ending point among all overlapping intervals (minEnd).
# If the interval that we are currently checking overlaps with the active set, which can be characterized by cur.start > minEnd, we need to renew those key parameters or change some states.
# If the current interval does not overlap with the active set, we just drop current active set, record some parameters, and create a new active set that contains the current interval.
*/
public class MinimumNumberOfArrowsToBurstBalloons {
	/**
     * @param points: a 2D array
     * @return: the minimum number of arrows that must be shot to burst all balloons
     */
    public int findMinArrowShots(int[][] points) {
		if(points == null || points.length == 0){
			return 0;
		}
        Arrays.sort(points, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]){
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
        	
		});
        int len = points.length, count = 1;
        int preEnd = points[0][1];
        for(int i = 1; i < len; i++){
        	if(points[i][0] > preEnd){
        		count++;
        		preEnd = points[i][1];
        	} 
        }
        return count;
    }

	public int findMinArrowShots2(int[][] points) {
		if(points == null || points.length == 0){
			return 0;
		}
		Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
		int count = 1;
		int minEnd = points[0][1];
		for(int i = 1; i < points.length; i++){
			if(points[i][0] > minEnd){
				count++;
				minEnd = points[i][1];
			} else {
				minEnd = Math.min(minEnd, points[i][1]);
			}
		}
		return count;
	}
}
