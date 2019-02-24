package SweepLine;

import java.util.*;

/*Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

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
*/public class NonOverlappingIntervals {
	
	/**
     * @param intervals: a collection of intervals
     * @return: the minimum number of intervals you need to remove
     */
    public int eraseOverlapIntervals(List<Interval> intervals) {
    	int len = intervals.size(), overlapCount = 0;
    	if(len == 0){
    		return overlapCount;
    	}
        Collections.sort(intervals, new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o1.start;
			}
		});
        int preEnd = intervals.get(0).end;
        for(int i = 1; i < len; i++){
        	Interval cur = intervals.get(i);
        	if(preEnd > cur.start){
        		overlapCount++;
        		if(preEnd > cur.end){
        		    preEnd = cur.end;
        		}
        	}else {
        		preEnd = cur.end;
        	}
        }
		return overlapCount;
    }
}
