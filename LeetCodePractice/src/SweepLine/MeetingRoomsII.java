package SweepLine;
// https://www.programcreek.com/2014/05/leetcode-meeting-rooms-ii-java/
/*Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
 * find the minimum number of conference rooms required.

Example
Given intervals = [(0,30),(5,10),(15,20)], return 2.*/
import java.util.*;

class Point {
	int time;
	boolean isStart;
	public Point(int time, boolean isStart){
		this.time = time;
		this.isStart = isStart;
	}
	public static Comparator<Point> PointComparator = new Comparator<Point>() {
		
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.time == o2.time){
				if((o1.isStart && o2.isStart) || (!o1.isStart && !o2.isStart)){
					return 0;
				} else if(o1.isStart){
					return 1;
				} else {
					return -1;
				}
			} else {
				return o1.time - o2.time;
			}
		}
	};
}

public class MeetingRoomsII {
	class Interval {
		int start, end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public int minMeetingRooms(Interval[] intervals) {
		List<Point> list = new ArrayList<>(intervals.length*2);
	    for(Interval i : intervals){
	    	list.add(new Point(i.start, true));
	    	list.add(new Point(i.end, false));
	    }
	    Collections.sort(list, Point.PointComparator);
	    int count = 0, ans = 0;
	    for(Point p : list){
	    	if(p.isStart){
	    		count++;
	    	} else {
	    		count--;
	    	}
	    	ans = Math.max(ans, count);
	    }
	    return ans;
	}

}
