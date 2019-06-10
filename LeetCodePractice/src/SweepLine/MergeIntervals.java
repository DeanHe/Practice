package SweepLine;
/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
*/
import java.util.*;

public class MergeIntervals {
	public List<Interval> merge(List<Interval> intervals) {
		// The key to solve this problem is defining a Comparator first to sort
		// the arraylist of Intevals.
		// And then merge some intervals.
		if (intervals == null || intervals.size() < 2) {
			return intervals;
		}
		Collections.sort(intervals, new intervalComparator());
		List<Interval> res = new ArrayList<Interval>();
		Interval pre = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			if (pre.end >= curr.start) {
				Interval merged = new Interval(pre.start, Math.max(pre.end, curr.end));
				pre = merged;
			} else {
				res.add(pre);
				pre = curr;
			}
		}
		res.add(pre);
		return res;
	}

	class intervalComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval o1, Interval o2) {
			return o1.start - o2.start;
		}
	}
}
