package SweepLine.Intervals;

import java.util.*;
/*
Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).

Example
Insert (2, 5) into [(1,2), (5,9)], we get [(1,9)].

Insert (3, 4) into [(1,2), (5,9)], we get [(1,2), (3,4), (5,9)].
*/
public class InsertInterval {
	/**
     * Insert newInterval into intervals.
     * @param intervals: Sorted interval list.
     * @param newInterval: A new interval.
     * @return: A new sorted interval list.
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<>();
        int insertPos = 0;
        for(Interval i : intervals){
            if(i.end < newInterval.start){
                result.add(i);
                insertPos++;
            } else if(newInterval.end < i.start){
                result.add(i);
            } else {
                newInterval.start = Math.min(i.start, newInterval.start);
                newInterval.end = Math.max(i.end, newInterval.end);
            }
        }
        result.add(insertPos, newInterval);
        return result;
    }
}
