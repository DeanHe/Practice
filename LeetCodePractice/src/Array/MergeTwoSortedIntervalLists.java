package Array;

import java.util.*;

import SweepLine.Interval;

/*Merge two sorted (ascending) lists of interval and return it as a new sorted list. The new sorted list should be made by splicing together the intervals of the two lists and sorted in ascending order.

Example
Example1

Input: list1 = [(1,2),(3,4)] and list2 = [(2,3),(5,6)]
Output: [(1,4),(5,6)]
Explanation:
(1,2),(2,3),(3,4) --> (1,4)
(5,6) --> (5,6)
Example2

Input: list1 = [(1,2),(3,4)] and list2 = [(4,5),(6,7)]
Output: [(1,2),(3,5),(6,7)]
Explanation:
(1,2) --> (1,2)
(3,4),(4,5) --> (3,5)
(6,7) --> (6,7)
Notice
The intervals in the given list do not overlap.
The intervals in different lists may overlap.*/
public class MergeTwoSortedIntervalLists {
	/**
	 * @param list1:
	 *            one of the given list
	 * @param list2:
	 *            another list
	 * @return: the new sorted list of interval
	 */
	public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
		// write your code here
		List<Interval> res = new ArrayList<>();
		if (list1 == null || list2 == null) {
			return null;
		}
		int len1 = list1.size();
		int len2 = list2.size();
		int i = 0, j = 0;
		Interval last = null, cur = null;
		while (i < len1 && j < len2) {
			if (list1.get(i).start < list2.get(j).start) {
				cur = list1.get(i);
				i++;
			} else {
				cur = list2.get(j);
				j++;
			}
			last = merge(res, last, cur);
		}
		while (i < len1) {
			last = merge(res, last, list1.get(i));
			i++;
		}
		while (j < len2) {
			last = merge(res, last, list2.get(j));
			j++;
		}
		if (last != null) {
			res.add(last);
		}
		return res;
	}

	private Interval merge(List<Interval> res, Interval last, Interval cur) {
		if (last == null) {
			return cur;
		}
		if (last.end < cur.start) {
			res.add(last);
			return cur;
		}
		last.end = Math.max(last.end, cur.end);
		return last;
	}
}
