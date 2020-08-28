package SweepLine;
// https://www.programcreek.com/2014/05/leetcode-meeting-rooms-ii-java/
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.

Example
Given intervals = [(0,30),(5,10),(15,20)], return 2.
*/

import SweepLine.Intervals.Interval;

import java.util.*;

class Point {
    int time;
    boolean isStart;

    public Point(int time, boolean isStart) {
        this.time = time;
        this.isStart = isStart;
    }
}

public class MeetingRoomsII {
    public int minMeetingRooms(Interval[] intervals) {
        int res = 0;
        TreeMap<Integer, Integer> axis = new TreeMap<>();
        for(Interval interval : intervals){
            axis.put(interval.start, axis.getOrDefault(interval.start, 0) + 1);
            axis.put(interval.end, axis.getOrDefault(interval.end, 0) - 1);
        }
        int preSum = 0;
        for(int tag : axis.keySet()){
            preSum += axis.get(tag);
            res = Math.max(res, preSum);
        }
        return res;
    }

    public int minMeetingRooms2(Interval[] intervals) {
        List<Point> axis = new ArrayList<>();
        for (Interval i : intervals) {
            axis.add(new Point(i.start, true));
            axis.add(new Point(i.end, false));
        }
        Collections.sort(axis, (a, b) -> {
            if (a.time == b.time) {
                if ((a.isStart && a.isStart) || (!b.isStart && !b.isStart)) {
                    return 0;
                } else if (a.isStart) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return a.time - b.time;
            }
        });
        int count = 0, ans = 0;
        for (Point p : axis) {
            if (p.isStart) {
                count++;
            } else {
                count--;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
