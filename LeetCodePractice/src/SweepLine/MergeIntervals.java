package SweepLine;

import java.util.*;

public class MergeIntervals {
	class Interval {
		      int start;
		      int end;
		      Interval() { start = 0; end = 0; }
		      Interval(int s, int e) { start = s; end = e; }
		  }
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
